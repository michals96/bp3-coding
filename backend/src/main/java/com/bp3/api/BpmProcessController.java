package com.bp3.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/parse")
public class BpmProcessController {

  @PostMapping
  public void parse(@RequestParam("file")MultipartFile file) {
    System.out.println("ok");
  }
}
