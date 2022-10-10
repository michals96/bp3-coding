package com.bp3.model;

public class SimpleNode implements Node {
  private String id;
  private String name;
  private NodeType type;

  public SimpleNode(final String id, final String name, final NodeType type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public SimpleNode() {
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public void setId(final String id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public NodeType getType() {
    return type;
  }

  @Override
  public void setType(final NodeType type) {
    this.type = type;
  }
}
