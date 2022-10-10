package com.bp3.model;

import java.util.List;

public class BpmnProcess {
  List<SimpleNode> nodes;
  List<SimpleEdge> edges;

  public BpmnProcess(final List<SimpleNode> nodes, final List<SimpleEdge> edges) {
    this.nodes = nodes;
    this.edges = edges;
  }

  public BpmnProcess() {
  }

  public List<SimpleNode> getNodes() {
    return nodes;
  }

  public void setNodes(final List<SimpleNode> nodes) {
    this.nodes = nodes;
  }

  public List<SimpleEdge> getEdges() {
    return edges;
  }

  public void setEdges(final List<SimpleEdge> edges) {
    this.edges = edges;
  }
}
