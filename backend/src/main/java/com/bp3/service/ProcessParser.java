package com.bp3.service;

import com.bp3.model.BpmnProcess;
import com.bp3.model.NodeType;
import com.bp3.model.SimpleEdge;
import com.bp3.model.SimpleNode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessParser {

  public ProcessParser() {
  }

  public BpmnProcess reduceProcess(BpmnProcess process) {
    final var reducedNodes = reduceNodes(process);
    // If there were no nodes reduced that means that there were no service tasks present, hence do not create new edges
    final var edges = process.getNodes().size() == reducedNodes.size() ? process.getEdges() : createEdges(reducedNodes);
    return new BpmnProcess(reducedNodes, edges);
  }

  private List<SimpleEdge> createEdges(final List<SimpleNode> reducedNodes) {
    List<SimpleEdge> edges = new ArrayList<>();

    for (int i = 0; i < reducedNodes.size(); ++i) {
      if (i != reducedNodes.size() - 1) {
        edges.add(new SimpleEdge(reducedNodes.get(i).getId(), reducedNodes.get(i + 1).getId()));
      }
    }

    return edges;
  }

  private List<SimpleNode> reduceNodes(final BpmnProcess process) {
    return process.getNodes().stream().filter(n -> n.getType() != NodeType.SERVICE_TASK).collect(Collectors.toList());
  }
}
