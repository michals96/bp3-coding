package com.bp3.model;

import com.bp3.model.contract.Node;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The different types of {@link Node}s within a BPM process diagram.
 */
public enum NodeType {
  GATEWAY("Gateway"),
  END("End"),
  HUMAN_TASK("HumanTask"),
  SERVICE_TASK("ServiceTask"),
  START("Start");

  private static final Map<String, NodeType> FORMAT_MAP = Stream
      .of(NodeType.values())
      .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

  private final String formatted;

  NodeType(String formatted) {
    this.formatted = formatted;
  }

  @JsonCreator
  public static NodeType fromString(String nodeType) {
    return Optional
        .ofNullable(FORMAT_MAP.get(nodeType))
        .orElseThrow(() -> new IllegalArgumentException(nodeType));
  }
}
