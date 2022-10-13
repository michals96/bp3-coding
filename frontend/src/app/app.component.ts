import {Component} from '@angular/core';
import {BpmnProcess} from "./types/bpmn.types";
import {FileClientService} from "./services/file-client.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title: string = 'BP-3 Coding challenge';
  process: BpmnProcess;

  constructor(private fileClientService: FileClientService, private snackBar: MatSnackBar) {
  }

  clearFile() {
    this.process = null;
  }

  setProcess(file: File) {
    this.fileClientService.uploadFile(file).subscribe(data => {
        this.process = data;
        this.snackBar.open('Message archived', 'Undo', {
          duration: 3000
        });
        alert("File uploaded")
      },
      error => {
        alert(error.error.message);
      })
  }
}
