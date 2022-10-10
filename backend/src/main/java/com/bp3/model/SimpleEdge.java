package com.bp3.model;

import com.bp3.model.contract.Edge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleEdge implements Edge {
  private String from;
  private String to;
}
