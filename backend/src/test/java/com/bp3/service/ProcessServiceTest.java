package com.bp3.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.bp3.exception.CorruptedFileException;
import com.bp3.model.BpmnProcess;
import com.fasterxml.jackson.databind.ObjectMapper;
import factory.TestBpmnProcessFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class ProcessServiceTest {
  @InjectMocks
  private ProcessService processService;
  @Mock
  private ProcessParserService processParserService;
  @Mock
  private ObjectMapper objectMapper;

  @Test
  void shouldReduceBpmnProcessOnCorrectFile() throws IOException {
    // given
    final var processWithServiceTasks = TestBpmnProcessFactory.produceWithServiceTasks();
    final var processWithOutServiceTasks = TestBpmnProcessFactory.produceWithOutServiceTasks();
    MultipartFile file =
        new MockMultipartFile("file", new FileInputStream(new File("src/test/resources/test-process.json")));

    // when
    when(objectMapper.readValue(file.getBytes(), BpmnProcess.class)).thenReturn(processWithServiceTasks);
    when(processParserService.reduceProcess(processWithServiceTasks)).thenReturn(processWithOutServiceTasks);
    final var reducedProcess = processService.reduce(file);

    // then
    assertThat(reducedProcess).isNotNull();
    assertThat(reducedProcess).isEqualTo(processWithOutServiceTasks);
  }

  @Test
  void shouldThrowExceptionOnNullFile() throws IOException {
    // given
    MultipartFile file =
        new MockMultipartFile("file", new FileInputStream(new File("src/test/resources/test-process.json")));

    // then
    assertThatThrownBy(() -> processService.reduce(null))
        .isInstanceOf(CorruptedFileException.class);
  }
}
