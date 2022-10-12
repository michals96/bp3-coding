package com.bp3.exception;

public class CorruptedFileException extends RuntimeException {
  public CorruptedFileException(String message) {
    super(message);
  }
}
