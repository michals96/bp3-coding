package com.bp3.service;

import factory.TestBpmnProcessFactory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;

class ProcessParserTest {
  private ProcessParser processParser;
  private TestBpmnProcessFactory processFactory;

  @BeforeAll
  void init() {
    processParser = new ProcessParser();
    processFactory = new TestBpmnProcessFactory();
  }

  @Test
  void shouldReduceProcessWithServiceTasks() {
    // given
    final var bpmnProcess = processFactory.produceWithServiceTasks();

    // when
    final var reducedProcess = processParser.reduceProcess(bpmnProcess);

    // then
    assertNotSame(bpmnProcess, reducedProcess);
  }

  @Test
  void shouldNotReduceProcessWithServiceTasks() {
  }
}
