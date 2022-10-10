package com.bp3.file;

import com.bp3.model.BpmnProcess;
import com.bp3.model.NodeType;
import com.bp3.model.SimpleEdge;
import com.bp3.model.SimpleNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {
  public FileService() {
  }

  public void execute(final String pathToFile, final String saveFilePath) throws IOException {
    final var bpmnProcess = loadBpmnProcessFromFile(pathToFile);
    final var reducedProcess = reduceProcess(bpmnProcess);
    saveBpmnProcessToFile(reducedProcess, saveFilePath);
  }

  private void saveBpmnProcessToFile(final BpmnProcess reducedProcess, final String saveFilePath) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.writeValue(new File(saveFilePath), reducedProcess);
  }

  private BpmnProcess reduceProcess(BpmnProcess process) {
    final var reducedNodes = reduceNodes(process);
    // If there were no nodes reduced that means that there were no service tasks present, hence do not create new edges
    final var edges = process.getNodes().size() == reducedNodes.size() ? process.getEdges() : createEdges(reducedNodes);
    return new BpmnProcess(reducedNodes, edges);
  }

  private List<SimpleEdge> createEdges(final List<SimpleNode> reducedNodes) {
    List<SimpleEdge> edges = new ArrayList<>();

    for (int i = 0; i < reducedNodes.size(); ++i) {
      if (i != reducedNodes.size() - 1) {
        edges.add(new SimpleEdge(reducedNodes.get(i).getId(), reducedNodes.get(i + 1).getId()));
      }
    }

    return edges;
  }

  private List<SimpleNode> reduceNodes(final BpmnProcess process) {
    return process.getNodes().stream().filter(n -> n.getType() != NodeType.SERVICE_TASK).collect(Collectors.toList());
  }


  private BpmnProcess loadBpmnProcessFromFile(final String pathToFile) throws IOException {
    final var file = new File(pathToFile);
    final var objectMapper = new ObjectMapper();
    return objectMapper.readValue(file, BpmnProcess.class);
  }
}
