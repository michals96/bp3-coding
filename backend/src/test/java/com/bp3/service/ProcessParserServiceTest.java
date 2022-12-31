package com.bp3.service;

import static factory.TestBpmnProcessFactory.assertNoServiceTasks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import factory.TestBpmnProcessFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ProcessParserServiceTest {
  private static ProcessParserService processParserService;

  @BeforeAll
  static void init() {
    processParserService = new ProcessParserService();
  }

  @Test
  void processParserShouldReturnParsedProcess1() throws JsonProcessingException {
    // given
    final var bpmnProcess = TestBpmnProcessFactory.produceProcess1();
    final var bpmnProcessReduced = TestBpmnProcessFactory.produceReducedProcess1();

    // when
    final var reducedProcess = processParserService.reduceProcess(bpmnProcess);

    // then
    assertThat(reducedProcess).usingRecursiveComparison().isEqualTo(bpmnProcessReduced);
    assertNoServiceTasks(reducedProcess);
  }

  @Test
  void processParserShouldReturnParsedProcess2() throws JsonProcessingException {
    // given
    final var bpmnProcess = TestBpmnProcessFactory.produceProcess2();
    final var bpmnProcessReduced = TestBpmnProcessFactory.produceReducedProcess2();

    // when
    final var reducedProcess = processParserService.reduceProcess(bpmnProcess);

    // then
    assertThat(reducedProcess).usingRecursiveComparison().isEqualTo(bpmnProcessReduced);
    assertNoServiceTasks(reducedProcess);
  }

  @Test
  void processParserShouldReturnParsedProcess3() throws JsonProcessingException {
    // given
    final var bpmnProcess = TestBpmnProcessFactory.produceProcess3();
    final var bpmnProcessReduced = TestBpmnProcessFactory.produceReducedProcess3();

    // when
    final var reducedProcess = processParserService.reduceProcess(bpmnProcess);

    // then
    assertThat(reducedProcess).usingRecursiveComparison().isEqualTo(bpmnProcessReduced);
    assertNoServiceTasks(reducedProcess);
  }

  @Test
  void processParserShouldReturnParsedProcess4() throws JsonProcessingException {
    // given
    final var bpmnProcess = TestBpmnProcessFactory.produceProcess4();
    final var bpmnProcessReduced = TestBpmnProcessFactory.produceReducedProcess4();

    // when
    final var reducedProcess = processParserService.reduceProcess(bpmnProcess);

    // then
    assertThat(reducedProcess).usingRecursiveComparison().isEqualTo(bpmnProcessReduced);
    assertNoServiceTasks(reducedProcess);
  }

  @Test
  void shouldReduceProcessWithServiceTasks() {
    // given
    final var bpmnProcess = TestBpmnProcessFactory.produceWithServiceTasks();

    // when
    final var reducedProcess = processParserService.reduceProcess(bpmnProcess);

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
    final var reducedProcess = processParserService.reduceProcess(bpmnProcess);

    // then
    assertNoServiceTasks(reducedProcess);
    assertEquals(bpmnProcess.getEdges().size(), reducedProcess.getEdges().size());
    assertEquals(bpmnProcess.getNodes().size(), reducedProcess.getNodes().size());
  }
}
