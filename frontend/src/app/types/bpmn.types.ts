export interface Node {
  id: number;
  name: string;
  type: string;
}

export interface Edge {
  from: number;
  to: number;
}

export interface BpmnProcess {
  nodes: Node[];
  edges: Edge[];
}
