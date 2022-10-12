package com.bp3.api;

import com.bp3.model.BpmnProcess;
import com.bp3.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/parse")
@RequiredArgsConstructor
public class BpmProcessController {
  private final ProcessService processService;

  @PostMapping
  public ResponseEntity<BpmnProcess> parse(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok(processService.reduce(file));
  }
}
