package com.bp3.service;

import com.bp3.model.BpmnProcess;
import com.bp3.model.NodeType;
import com.bp3.model.SimpleEdge;
import com.bp3.model.SimpleNode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
class ProcessParser {

  public BpmnProcess reduceProcess(BpmnProcess process) {
    final var reducedNodes = reduceServiceTasks(process);
    final var edges = ifReducedNodes(process, reducedNodes) ? createNewEdges(reducedNodes) : process.getEdges();
    return new BpmnProcess(reducedNodes, edges);
  }

  private List<SimpleNode> reduceServiceTasks(final BpmnProcess process) {
    return process.getNodes().stream()
        .filter(n -> n.getType() != NodeType.SERVICE_TASK)
        .collect(Collectors.toList());
  }

  private boolean ifReducedNodes(final BpmnProcess process, final List<SimpleNode> reducedNodes) {
    return process.getNodes().size() != reducedNodes.size();
  }

  private List<SimpleEdge> createNewEdges(final List<SimpleNode> reducedNodes) {
    List<SimpleEdge> edges = new ArrayList<>();

    for (int i = 0; i < reducedNodes.size(); ++i) {
      if (i != reducedNodes.size() - 1) {
        edges.add(new SimpleEdge(reducedNodes.get(i).getId(), reducedNodes.get(i + 1).getId()));
      }
    }

    return edges;
  }
}
