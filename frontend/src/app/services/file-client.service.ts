import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import BpmnProcess = Types.BpmnProcess;

@Injectable({
  providedIn: 'root'
})
export class FileClientService {

  constructor(private httpClient: HttpClient) {}

  public uploadFile(file: File): Observable<BpmnProcess> {
    let data: FormData = new FormData();
    data.append('file', file);
    return this.httpClient
      .post<BpmnProcess>('http://localhost:8080/parse', data);
  }
}

declare module Types {
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
}

export default Types;


