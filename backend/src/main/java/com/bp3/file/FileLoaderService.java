package com.bp3.file;

import com.bp3.model.BpmnProcess;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class FileLoaderService implements FileService {
  public FileLoaderService() {
  }

  @Override
  public void process() throws IOException {
    final var file = new File("C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\1-simple-process.json");
    final var objectMapper = new ObjectMapper();
    final var bpmnProcess = objectMapper.readValue(file, BpmnProcess.class);
  }
}
