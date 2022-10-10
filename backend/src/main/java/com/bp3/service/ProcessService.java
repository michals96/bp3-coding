package com.bp3.service;

import com.bp3.model.BpmnProcess;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;

public class ProcessService {
  private final ProcessParser processParser;
  private final ObjectMapper objectMapper;

  public ProcessService() {
    processParser = new ProcessParser();
    objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  public void reduce(final String pathToFile, final String saveFilePath) throws IOException {
    final var bpmnProcess = loadProcessFromFile(pathToFile);
    final var reducedProcess = processParser.reduceProcess(bpmnProcess);
    saveProcessToFile(reducedProcess, saveFilePath);
  }

  private BpmnProcess loadProcessFromFile(final String pathToFile) throws IOException {
    return objectMapper.readValue(new File(pathToFile), BpmnProcess.class);
  }

  private void saveProcessToFile(final BpmnProcess reducedProcess, final String saveFilePath) throws IOException {
    objectMapper.writeValue(new File(saveFilePath), reducedProcess);
  }
}
