package com.bp3.service;

import static java.util.stream.Collectors.toList;

import com.bp3.model.BpmnProcess;
import com.bp3.model.NodeType;
import com.bp3.model.SimpleEdge;
import com.bp3.model.SimpleNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
class ProcessParserService {

  public BpmnProcess reduceProcess(BpmnProcess process) {
    final var gatewayConnections = getGatewayConnections(process);
    final var reducedNodes = reduceServiceTasks(process);
    final var edges = ifReducedNodes(process, reducedNodes) ? createNewEdges(reducedNodes, gatewayConnections) : process.getEdges();
    return new BpmnProcess(reducedNodes, edges);
  }

  private Map<String, List<String>> getGatewayConnections(final BpmnProcess process) {
    Map<String, List<String>> gatewayConnections = new HashMap<>();

    final var gateways = process.getNodes().stream()
        .filter(n -> n.getType() == NodeType.GATEWAY)
        .collect(toList());

    final var edges = process.getEdges();

    for (SimpleNode gateway : gateways) {
      List<String> gatewayOutputs = new ArrayList<>();
      for (SimpleEdge edge : edges) {
        if (gateway.getId().equals(edge.getFrom())) {
          final var b = (Integer.parseInt(gateway.getId()) > Integer.parseInt(edge.getTo())) ? gatewayOutputs.add(String.valueOf((Integer.parseInt(edge.getTo())*(-1)))) :
              gatewayOutputs.add(edge.getTo());
        }

        if (gateway.getId().equals(edge.getTo())) {
          gatewayOutputs.add(edge.getFrom());
        }
      }

      if (!gatewayOutputs.isEmpty()) {
        gatewayConnections.put(gateway.getId(), gatewayOutputs);
      }
    }

    return gatewayConnections;
  }

  private List<SimpleNode> reduceServiceTasks(final BpmnProcess process) {
    return process.getNodes().stream()
        .filter(n -> n.getType() != NodeType.SERVICE_TASK)
        .collect(toList());
  }

  private boolean ifReducedNodes(final BpmnProcess process, final List<SimpleNode> reducedNodes) {
    return process.getNodes().size() != reducedNodes.size();
  }

  private List<SimpleEdge> createNewEdges(final List<SimpleNode> reducedNodes, Map<String, List<String>> gatewayConnections) {
    List<SimpleEdge> edges = new ArrayList<>();
    final var reducedNodesIds = reducedNodes.stream()
        .map(n -> n.getId())
        .collect(toList());

    for (int i = 0; i < reducedNodes.size(); ++i) {
      if (i != reducedNodes.size() - 1) {
        final var node = reducedNodes.get(i + 1);
        if (node.getType().equals(NodeType.GATEWAY)) {
          final var gatewayEdges = gatewayConnections.get(node.getId());
          int gatewayOutputs = 0;
          for (String edge : gatewayEdges) {
            if (reducedNodesIds.contains(edge) && (Integer.valueOf(node.getId()) > Integer.valueOf(edge))) {
              edges.add(new SimpleEdge(edge, node.getId()));
            } else if ((reducedNodesIds.contains(edge) && (Integer.valueOf(node.getId()) < Integer.valueOf(edge)))) {
              edges.add(new SimpleEdge(node.getId(), edge));
              gatewayOutputs++;
            } else if ((Integer.valueOf(edge) < 0 && reducedNodesIds.contains(String.valueOf(Math.abs(Integer.valueOf(edge)))))) {
              edges.add(new SimpleEdge(node.getId(), String.valueOf(Math.abs(Integer.valueOf(edge)))));
            }
          }
          i = i + gatewayOutputs;
        } else {
          edges.add(new SimpleEdge(reducedNodes.get(i).getId(), node.getId()));
        }
      }
    }

    return edges;
  }
}
