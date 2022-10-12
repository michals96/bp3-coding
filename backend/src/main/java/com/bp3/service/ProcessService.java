package com.bp3.service;

import com.bp3.exception.CorruptedFileException;
import com.bp3.model.BpmnProcess;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProcessService {
  private final ProcessParserService processParserService;
  private final ObjectMapper objectMapper;

  public BpmnProcess reduce(final MultipartFile file) {
    final var bpmnProcess = loadProcessFromFile(file);
    return processParserService.reduceProcess(bpmnProcess);
  }

  private BpmnProcess loadProcessFromFile(final MultipartFile file) {
    try {
      return objectMapper.readValue(file.getBytes(), BpmnProcess.class);
    } catch (JsonMappingException e) {
      throw new CorruptedFileException("The file is corrupted");
    } catch (Exception e) {
      throw new CorruptedFileException("Bad request");
    }
  }
}
