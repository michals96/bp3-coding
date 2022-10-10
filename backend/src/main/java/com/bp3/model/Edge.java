package com.bp3.model;

/**
 * Public contract for a class that plays the role of a edge between two {@link Node}s within
 * a BPM process diagram.
 */
public interface Edge {
  /**
   * Returns the unique identifier for the source {@link Node}.
   */
  String getFrom();

  /**
   * Returns the unique identifier for the target {@link Node}.
   */
  String getTo();

  /**
   * Sets unique identifier for the target {@link Node}.
   */
  void setFrom(String nodeId);

  /**
   * Sets unique identifier for the source {@link Node}.
   */
  void setTo(String nodeId);
}
