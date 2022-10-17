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

    final var gatewaysInProcess = process.getNodes().stream()
        .filter(n -> n.getType() == NodeType.GATEWAY)
        .collect(toList());

    final var edgesInProcess = process.getEdges();

    collectGatewayConnections(gatewayConnections, gatewaysInProcess, edgesInProcess);

    return gatewayConnections;
  }

  private void collectGatewayConnections(final Map<String, List<String>> gatewayConnections, final List<SimpleNode> gateways,
                                         final List<SimpleEdge> edges) {
    for (final var gateway : gateways) {
      List<String> connections = new ArrayList<>();
      for (final var edge : edges) {
        if (isGatewayInputEdge(gateway, edge)) {
          collectInputConnection(gateway, connections, edge);
        }

        if (isGatewayOutputEdge(gateway, edge)) {
          collectOutputConnection(connections, edge);
        }
      }

      appendGatewayConnections(gatewayConnections, gateway, connections);
    }
  }

  private void collectOutputConnection(final List<String> connections, final SimpleEdge edge) {
    connections.add(edge.getFrom());
  }

  private void collectInputConnection(final SimpleNode gateway, final List<String> gatewayOutputs, final SimpleEdge edge) {
    if (isRecursiveBranching(gateway, edge)) {
      // Marking recursive branches as nodes with negative int as ID
      gatewayOutputs.add(String.valueOf((Integer.parseInt(edge.getTo()) * (-1))));
    } else {
      gatewayOutputs.add(edge.getTo());
    }
  }

  private void appendGatewayConnections(final Map<String, List<String>> gatewayConnections, final SimpleNode gateway,
                                        final List<String> gatewayOutputs) {
    if (!gatewayOutputs.isEmpty()) {
      gatewayConnections.put(gateway.getId(), gatewayOutputs);
    }
  }

  private boolean isGatewayOutputEdge(final SimpleNode gateway, final SimpleEdge edge) {
    return gateway.getId().equals(edge.getTo());
  }

  private boolean isRecursiveBranching(final SimpleNode gateway, final SimpleEdge edge) {
    return Integer.parseInt(gateway.getId()) > Integer.parseInt(edge.getTo());
  }

  private boolean isGatewayInputEdge(final SimpleNode gateway, final SimpleEdge edge) {
    return gateway.getId().equals(edge.getFrom());
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
        .map(SimpleNode::getId)
        .collect(toList());

    for (int i = 0; i < reducedNodes.size(); ++i) {
      if (i != reducedNodes.size() - 1) {
        final var node = reducedNodes.get(i + 1);
        if (node.getType().equals(NodeType.GATEWAY)) {
          i = createEdgeForGateway(gatewayConnections, edges, reducedNodesIds, i, node);
        } else {
          edges.add(new SimpleEdge(reducedNodes.get(i).getId(), node.getId()));
        }
      }
    }

    return edges;
  }

  private int createEdgeForGateway(final Map<String, List<String>> gatewayConnections, final List<SimpleEdge> edges,
                                   final List<String> reducedNodesIds,
                                   int i, final SimpleNode node) {
    final var gatewayEdges = gatewayConnections.get(node.getId());
    int gatewayOutputs = 0;

    for (String edge : gatewayEdges) {
      if (reducedNodesIds.contains(edge) && (Integer.parseInt(node.getId()) > Integer.parseInt(edge))) {
        edges.add(new SimpleEdge(edge, node.getId()));
      } else if ((reducedNodesIds.contains(edge) && (Integer.parseInt(node.getId()) < Integer.parseInt(edge)))) {
        edges.add(new SimpleEdge(node.getId(), edge));
        gatewayOutputs++;
      } else if ((Integer.parseInt(edge) < 0 && reducedNodesIds.contains(String.valueOf(Math.abs(Integer.parseInt(edge)))))) {
        edges.add(new SimpleEdge(node.getId(), String.valueOf(Math.abs(Integer.parseInt(edge)))));
      }
    }

    i = i + gatewayOutputs;
    return i;
  }
}
