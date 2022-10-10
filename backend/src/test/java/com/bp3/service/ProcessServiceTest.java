package com.bp3.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProcessServiceTest {
  private static final String FILE_PATH = "src/test/resources/test-process.json";
  private static final String EMPTY_FILE_PATH = "src/test/resources/empty-test-process.json";
  private static final String CORRUPTED_FILE_PATH = "src/test/resources/corrupted-test-process.json";
  private static final String BAD_FILE_PATH = "BAD_PATH";
  private static final String SAVE_FILE_PATH = "src/test/resources/reduced-process.json";
  private static ProcessService processService;

  @BeforeAll
  static void init() {
    processService = new ProcessService();
  }

  @AfterEach
  void clean() throws IOException {
    File file = new File(SAVE_FILE_PATH);
    Files.deleteIfExists(file.toPath());
  }

  @Test
  public void reduceCorrectJsonFileThrowsNoException() {
    assertDoesNotThrow(() -> processService.reduce(FILE_PATH, SAVE_FILE_PATH));
  }

  @Test
  public void reduceCorrectJsonFileSavesOutputFile() throws IOException {
    // when
    processService.reduce(FILE_PATH, SAVE_FILE_PATH);
    File file = new File(SAVE_FILE_PATH);

    // then
    assertTrue(file.isFile());
    assertTrue(file.exists());
  }

  @Test
  public void reduceEmptyJsonFileThrowsException() {
    assertThrows(JsonMappingException.class, () -> processService.reduce(EMPTY_FILE_PATH, SAVE_FILE_PATH));
  }

  @Test
  public void reduceNonExistingJsonFileThrowsException() {
    assertThrows(FileNotFoundException.class, () -> processService.reduce(BAD_FILE_PATH, SAVE_FILE_PATH));
  }

  @Test
  public void reduceIncorrectJsonFileThrowsException() {
    assertThrows(JsonMappingException.class, () -> processService.reduce(CORRUPTED_FILE_PATH, SAVE_FILE_PATH));
  }
}
