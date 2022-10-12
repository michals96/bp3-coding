import {Component, Input} from '@angular/core';
import Types from "../../services/file-client.service";
import BpmnProcess = Types.BpmnProcess;

@Component({
  selector: 'app-json-render',
  templateUrl: './json-render.component.html',
  styleUrls: ['./json-render.component.scss'],
})
export class JsonRenderComponent {

  @Input('childToMaster') process: BpmnProcess;

  constructor() {
  }
}
