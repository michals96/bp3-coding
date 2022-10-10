package com.bp3;

/**
 * Public contract for a class that plays the role of a node within a BPM process diagram.
 */
public interface Node {
  /**
   * Returns the unique identifier for the Node.
   */
  String getId();

  /**
   * Returns the name for the Node.
   */
  String getName();

  /**
   * Returns the type of the Node.
   */
  NodeType getType();

  /**
   * Sets unique identifier for the {@link Node}.
   */
  void setId(String id);

  /**
   * Sets {@link Node}'s name.
   */
  void setName(String name);

  /**
   * Sets type for the {@link Node}.
   */
  void setType(NodeType type);
}
