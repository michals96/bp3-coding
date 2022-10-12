import {Component} from '@angular/core';
import Types from "./services/file-client.service";
import BpmnProcess = Types.BpmnProcess;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title: string = 'BP-3 Coding challenge';
  process: BpmnProcess;

  childToParent(process: BpmnProcess) {
    this.process = process;
  }
}
