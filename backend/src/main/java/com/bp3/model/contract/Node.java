package com.bp3.model.contract;

import com.bp3.model.NodeType;

/**
 * Public contract for a class that plays the role of a node within a BPM process diagram.
 */
public interface Node {
  /**
   * Returns the unique identifier for the Node.
   */
  String getId();

  /**
   * Sets unique identifier for the {@link Node}.
   */
  void setId(String id);

  /**
   * Returns the name for the Node.
   */
  String getName();

  /**
   * Sets {@link Node}'s name.
   */
  void setName(String name);

  /**
   * Returns the type of the Node.
   */
  NodeType getType();

  /**
   * Sets type for the {@link Node}.
   */
  void setType(NodeType type);
}
