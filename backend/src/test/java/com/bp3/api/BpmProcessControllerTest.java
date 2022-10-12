package com.bp3.api;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bp3.config.GlobalExceptionHandler;
import com.bp3.exception.CorruptedFileException;
import com.bp3.service.ProcessService;
import java.io.File;
import java.io.FileInputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@Import({BpmProcessController.class, GlobalExceptionHandler.class})
class BpmProcessControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private ProcessService processService;

  @Test
  void shouldReturnValidResponseOnValidRequest() throws Exception {
    // given
    final var file = new MockMultipartFile("file", new FileInputStream(new File("src/test/resources/test-process.json")));

    // then
    mockMvc.perform(multipart("/parse")
            .file("file", file.getBytes())
            .contentType(MediaType.MULTIPART_FORM_DATA))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturnErrorOnEmptyFile() throws Exception {
    // when
    doThrow(new CorruptedFileException("BAD REQUEST")).when(processService).reduce(any());

    // then
    mockMvc.perform(multipart("/parse")
            .file("file", new byte[] {})
            .contentType(MediaType.MULTIPART_FORM_DATA))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.message", is(equalTo("BAD REQUEST"))));
  }
}
