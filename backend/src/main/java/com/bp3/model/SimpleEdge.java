package com.bp3.model;

import com.bp3.model.contract.Edge;
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
@JsonSerialize(using = SimpleEdgeSerializer.class)
@EqualsAndHashCode
public class SimpleEdge implements Edge {
  private String from;
  private String to;
}
