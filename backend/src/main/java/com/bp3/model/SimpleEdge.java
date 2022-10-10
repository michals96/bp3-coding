package com.bp3.model;

public class SimpleEdge  {
  private String from;
  private String to;

  public SimpleEdge(final String from, final String to) {
    this.from = from;
    this.to = to;
  }

  public SimpleEdge() {
  }

  public String getFrom() {
    return this.from;
  }

  public String getTo() {
    return this.to;
  }

  public void setFrom(final String nodeId) {
    this.from = nodeId;
  }

  public void setTo(final String nodeId) {
    this.to = nodeId;
  }
}
