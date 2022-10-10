package com.bp3.model;

import com.bp3.model.contract.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleNode implements Node {
  private String id;
  private String name;
  private NodeType type;
}
