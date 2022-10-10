package com.bp3.model;

public class SimpleEdge implements Edge {
  private String from;
  private String to;

  public SimpleEdge(final String from, final String to) {
    this.from = from;
    this.to = to;
  }

  public SimpleEdge() {
  }

  @Override
  public String getFrom() {
    return this.from;
  }

  @Override
  public void setFrom(final String nodeId) {
    this.from = nodeId;
  }

  @Override
  public String getTo() {
    return this.to;
  }

  @Override
  public void setTo(final String nodeId) {
    this.to = nodeId;
  }
}
