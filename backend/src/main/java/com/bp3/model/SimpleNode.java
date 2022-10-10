package com.bp3.model;

public class SimpleNode {
  private String id;
  private String name;
  private String type;

  public SimpleNode(final String id, final String name, final String type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public SimpleNode() {
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.type;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setType(final String type) {
    this.type = type;
  }
}
