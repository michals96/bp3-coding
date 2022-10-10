package com.bp3.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BpmnProcess {
  List<SimpleNode> nodes;
  List<SimpleEdge> edges;
}
