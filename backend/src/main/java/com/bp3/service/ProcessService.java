package com.bp3.service;

import com.bp3.model.BpmnProcess;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProcessService {
  private final ProcessParser processParser;
  private final ObjectMapper objectMapper;

/*  public ProcessService() {
    processParser = new ProcessParser();
    objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }*/

  public BpmnProcess reduce(final MultipartFile file) throws IOException {
    final var bpmnProcess = loadProcessFromFile(file);
    return processParser.reduceProcess(bpmnProcess);
  }

  private BpmnProcess loadProcessFromFile(final MultipartFile file) throws IOException {
    return objectMapper.readValue(file.getBytes(), BpmnProcess.class);
  }
}
