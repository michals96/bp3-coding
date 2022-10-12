/*
package com.bp3.service;

import static factory.TestBpmnProcessFactory.assertNoServiceTasks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import factory.TestBpmnProcessFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ProcessParserTest {
  private static ProcessParser processParser;

  @BeforeAll
  static void init() {
    processParser = new ProcessParser();
  }

  @Test
  void shouldReduceProcessWithServiceTasks() {
    // given
    final var bpmnProcess = TestBpmnProcessFactory.produceWithServiceTasks();

    // when
    final var reducedProcess = processParser.reduceProcess(bpmnProcess);

    // then
    assertNoServiceTasks(reducedProcess);
    assertNotEquals(bpmnProcess.getEdges().size(), reducedProcess.getEdges().size());
    assertNotEquals(bpmnProcess.getNodes().size(), reducedProcess.getNodes().size());
    assertTrue(bpmnProcess.getEdges().size() > reducedProcess.getEdges().size());
    assertTrue(bpmnProcess.getNodes().size() > reducedProcess.getNodes().size());
  }

  @Test
  void shouldNotReduceProcessWithServiceTasks() {
    // given
    final var bpmnProcess = TestBpmnProcessFactory.produceWithOutServiceTasks();

    // when
    final var reducedProcess = processParser.reduceProcess(bpmnProcess);

    // then
    assertNoServiceTasks(reducedProcess);
    assertEquals(bpmnProcess.getEdges().size(), reducedProcess.getEdges().size());
    assertEquals(bpmnProcess.getNodes().size(), reducedProcess.getNodes().size());
  }
}
*/
