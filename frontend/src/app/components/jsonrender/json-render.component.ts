import {Component, Input} from '@angular/core';
import {BpmnProcess} from "../../types/bpmn.types";

@Component({
  selector: 'app-json-render',
  templateUrl: './json-render.component.html',
  styleUrls: ['./json-render.component.scss'],
})
export class JsonRenderComponent {
  @Input() process: BpmnProcess;
}
