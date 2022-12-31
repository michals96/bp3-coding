package factory;

import static org.junit.jupiter.api.Assertions.assertNotSame;

import com.bp3.model.BpmnProcess;
import com.bp3.model.NodeType;
import com.bp3.model.SimpleEdge;
import com.bp3.model.SimpleNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class TestBpmnProcessFactory {
  private TestBpmnProcessFactory() {
  }

  public static BpmnProcess produceWithServiceTasks() {
    final var nodes = List.of(
        new SimpleNode("0", "Start", NodeType.START),
        new SimpleNode("1", "ServiceTask", NodeType.SERVICE_TASK),
        new SimpleNode("2", "HumanTask", NodeType.HUMAN_TASK),
        new SimpleNode("3", "ServiceTask", NodeType.SERVICE_TASK),
        new SimpleNode("4", "HumanTask", NodeType.HUMAN_TASK),
        new SimpleNode("5", "End", NodeType.START));

    final var edges = List.of(
        new SimpleEdge("0", "1"),
        new SimpleEdge("1", "2"),
        new SimpleEdge("2", "3"),
        new SimpleEdge("3", "4"),
        new SimpleEdge("4", "5")
    );

    return new BpmnProcess(nodes, edges);
  }

  public static BpmnProcess produceWithOutServiceTasks() {
    final var nodes = List.of(
        new SimpleNode("0", "Start", NodeType.START),
        new SimpleNode("1", "HumanTask", NodeType.HUMAN_TASK),
        new SimpleNode("2", "HumanTask", NodeType.HUMAN_TASK),
        new SimpleNode("3", "End", NodeType.START));

    final var edges = List.of(
        new SimpleEdge("0", "1"),
        new SimpleEdge("1", "2"),
        new SimpleEdge("2", "3")
    );

    return new BpmnProcess(nodes, edges);
  }

  public static BpmnProcess produceProcess1() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String processJson = "{\n" +
        "  \"nodes\": [\n" +
        "    {\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"Start\",\n" +
        "      \"type\": \"Start\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 1,\n" +
        "      \"name\": \"A\",\n" +
        "      \"type\": \"ServiceTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"B\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 3,\n" +
        "      \"name\": \"C\",\n" +
        "      \"type\": \"ServiceTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 4,\n" +
        "      \"name\": \"D\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 5,\n" +
        "      \"name\": \"End\",\n" +
        "      \"type\": \"End\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"edges\": [\n" +
        "    {\n" +
        "      \"from\": 0,\n" +
        "      \"to\": 1\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 1,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 2,\n" +
        "      \"to\": 3\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 4\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 4,\n" +
        "      \"to\": 5\n" +
        "    }\n" +
        "  ]\n" +
        "}\n";

    return objectMapper.readValue(processJson, BpmnProcess.class);
  }

  public static BpmnProcess produceReducedProcess1() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String processJson = "{\n" +
        "  \"nodes\": [\n" +
        "    {\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"Start\",\n" +
        "      \"type\": \"Start\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"B\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 4,\n" +
        "      \"name\": \"D\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 5,\n" +
        "      \"name\": \"End\",\n" +
        "      \"type\": \"End\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"edges\": [\n" +
        "    {\n" +
        "      \"from\": 0,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 2,\n" +
        "      \"to\": 4\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 4,\n" +
        "      \"to\": 5\n" +
        "    }\n" +
        "  ]\n" +
        "}";

    return objectMapper.readValue(processJson, BpmnProcess.class);
  }

  public static BpmnProcess produceProcess2() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String processJson = "{\n" +
        "  \"nodes\": [\n" +
        "    {\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"Start\",\n" +
        "      \"type\": \"Start\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 1,\n" +
        "      \"name\": \"A\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"B\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 3,\n" +
        "      \"name\": \"C\",\n" +
        "      \"type\": \"ServiceTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 4,\n" +
        "      \"name\": \"D\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 5,\n" +
        "      \"name\": \"End\",\n" +
        "      \"type\": \"End\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"edges\": [\n" +
        "    {\n" +
        "      \"from\": 0,\n" +
        "      \"to\": 1\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 1,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 2,\n" +
        "      \"to\": 3\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 4\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 4,\n" +
        "      \"to\": 5\n" +
        "    }\n" +
        "  ]\n" +
        "}\n";

    return objectMapper.readValue(processJson, BpmnProcess.class);
  }

  public static BpmnProcess produceReducedProcess2() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String processJson = "{\n" +
        "  \"nodes\": [\n" +
        "    {\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"Start\",\n" +
        "      \"type\": \"Start\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 1,\n" +
        "      \"name\": \"A\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"B\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 4,\n" +
        "      \"name\": \"D\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 5,\n" +
        "      \"name\": \"End\",\n" +
        "      \"type\": \"End\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"edges\": [\n" +
        "    {\n" +
        "      \"from\": 0,\n" +
        "      \"to\": 1\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 1,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 2,\n" +
        "      \"to\": 4\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 4,\n" +
        "      \"to\": 5\n" +
        "    }\n" +
        "  ]\n" +
        "}";

    return objectMapper.readValue(processJson, BpmnProcess.class);
  }

  public static BpmnProcess produceProcess3() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String processJson = "{\n" +
        "  \"nodes\": [\n" +
        "    {\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"Start\",\n" +
        "      \"type\": \"Start\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 1,\n" +
        "      \"name\": \"A\",\n" +
        "      \"type\": \"ServiceTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"B\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 3,\n" +
        "      \"name\": \"G1\",\n" +
        "      \"type\": \"Gateway\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 4,\n" +
        "      \"name\": \"C\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 5,\n" +
        "      \"name\": \"D\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 6,\n" +
        "      \"name\": \"G2\",\n" +
        "      \"type\": \"Gateway\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 7,\n" +
        "      \"name\": \"#\",\n" +
        "      \"type\": \"ServiceTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 8,\n" +
        "      \"name\": \"End\",\n" +
        "      \"type\": \"End\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"edges\": [\n" +
        "    {\n" +
        "      \"from\": 0,\n" +
        "      \"to\": 1\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 1,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 2,\n" +
        "      \"to\": 3\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 4\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 5\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 4,\n" +
        "      \"to\": 6\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 5,\n" +
        "      \"to\": 6\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 6,\n" +
        "      \"to\": 7\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 7,\n" +
        "      \"to\": 8\n" +
        "    }\n" +
        "  ]\n" +
        "}\n";

    return objectMapper.readValue(processJson, BpmnProcess.class);
  }

  public static BpmnProcess produceReducedProcess3() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String processJson = "{\n" +
        "  \"nodes\": [\n" +
        "    {\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"Start\",\n" +
        "      \"type\": \"Start\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"B\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 3,\n" +
        "      \"name\": \"G1\",\n" +
        "      \"type\": \"Gateway\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 4,\n" +
        "      \"name\": \"C\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 5,\n" +
        "      \"name\": \"D\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 6,\n" +
        "      \"name\": \"G2\",\n" +
        "      \"type\": \"Gateway\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 8,\n" +
        "      \"name\": \"End\",\n" +
        "      \"type\": \"End\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"edges\": [\n" +
        "    {\n" +
        "      \"from\": 0,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 2,\n" +
        "      \"to\": 3\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 4\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 5\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 4,\n" +
        "      \"to\": 6\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 5,\n" +
        "      \"to\": 6\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 6,\n" +
        "      \"to\": 8\n" +
        "    }\n" +
        "  ]\n" +
        "}";

    return objectMapper.readValue(processJson, BpmnProcess.class);
  }

  public static BpmnProcess produceProcess4() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String processJson = "{\n" +
        "  \"nodes\": [\n" +
        "    {\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"Start\",\n" +
        "      \"type\": \"Start\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 1,\n" +
        "      \"name\": \"A\",\n" +
        "      \"type\": \"ServiceTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"B\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 3,\n" +
        "      \"name\": \"G1\",\n" +
        "      \"type\": \"Gateway\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 4,\n" +
        "      \"name\": \"C\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 5,\n" +
        "      \"name\": \"D\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 6,\n" +
        "      \"name\": \"G2\",\n" +
        "      \"type\": \"Gateway\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 7,\n" +
        "      \"name\": \"#\",\n" +
        "      \"type\": \"ServiceTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 8,\n" +
        "      \"name\": \"End\",\n" +
        "      \"type\": \"End\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"edges\": [\n" +
        "    {\n" +
        "      \"from\": 0,\n" +
        "      \"to\": 1\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 1,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 2,\n" +
        "      \"to\": 3\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 4\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 5\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 4,\n" +
        "      \"to\": 6\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 5,\n" +
        "      \"to\": 6\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 6,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 6,\n" +
        "      \"to\": 7\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 7,\n" +
        "      \"to\": 8\n" +
        "    }\n" +
        "  ]\n" +
        "}\n";

    return objectMapper.readValue(processJson, BpmnProcess.class);
  }

  public static BpmnProcess produceReducedProcess4() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    String processJson = "{\n" +
        "  \"nodes\": [\n" +
        "    {\n" +
        "      \"id\": 0,\n" +
        "      \"name\": \"Start\",\n" +
        "      \"type\": \"Start\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 2,\n" +
        "      \"name\": \"B\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 3,\n" +
        "      \"name\": \"G1\",\n" +
        "      \"type\": \"Gateway\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 4,\n" +
        "      \"name\": \"C\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 5,\n" +
        "      \"name\": \"D\",\n" +
        "      \"type\": \"HumanTask\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 6,\n" +
        "      \"name\": \"G2\",\n" +
        "      \"type\": \"Gateway\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": 8,\n" +
        "      \"name\": \"End\",\n" +
        "      \"type\": \"End\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"edges\": [\n" +
        "    {\n" +
        "      \"from\": 0,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 2,\n" +
        "      \"to\": 3\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 4\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 3,\n" +
        "      \"to\": 5\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 4,\n" +
        "      \"to\": 6\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 5,\n" +
        "      \"to\": 6\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 6,\n" +
        "      \"to\": 2\n" +
        "    },\n" +
        "    {\n" +
        "      \"from\": 6,\n" +
        "      \"to\": 8\n" +
        "    }\n" +
        "  ]\n" +
        "}";

    return objectMapper.readValue(processJson, BpmnProcess.class);
  }

  public static boolean assertNoServiceTasks(BpmnProcess process) {
    process.getNodes().forEach(n -> assertNotSame(n.getType(), NodeType.SERVICE_TASK));
    return true;
  }
}
