package factory;

import com.bp3.model.BpmnProcess;
import com.bp3.model.NodeType;
import com.bp3.model.SimpleEdge;
import com.bp3.model.SimpleNode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  public static boolean assertNoServiceTasks(BpmnProcess process) {
    process.getNodes().forEach(n -> assertNotSame(n.getType(), NodeType.SERVICE_TASK));
    return true;
  }
}
