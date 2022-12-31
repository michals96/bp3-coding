package com.bp3.model;

import com.bp3.model.contract.Node;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonSerialize(using = SimpleNodeSerializer.class)
@EqualsAndHashCode
public class SimpleNode implements Node {
  private String id;
  private String name;
  private NodeType type;
}
