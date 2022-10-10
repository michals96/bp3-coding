package com.bp3.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileLoaderService implements FileService {
  public FileLoaderService(){}

  @Override
  public void process() throws IOException {
    File file = new File(
        "C:\\Users\\micha\\Desktop\\mr\\bp3-coding\\data\\1-simple-process.json");

    BufferedReader br
        = new BufferedReader(new FileReader(file));

    String st;
    while ((st = br.readLine()) != null) {
      System.out.println(st);
    }
  }
}
