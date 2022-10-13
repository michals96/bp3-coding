import {Component, EventEmitter, Output} from '@angular/core';
import {FileClientService} from "../../services/file-client.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {BpmnProcess} from "../../types/bpmn.types";

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent {
  @Output() sendProcess = new EventEmitter<BpmnProcess>();
  file: File = null;
  name: string;

  constructor(private fileClientService: FileClientService, private snackBar: MatSnackBar) {
    this.name = '';
  }

  onFileChange(event: any) {
    this.file = event.target.files[0];
    this.name = event.target.files[0].name;
  }

  uploadFile() {
    if (this.file) {
      this.fileClientService.uploadFile(this.file).subscribe(data => {
          this.sendToParent(data);
          this.snackBar.open('Message archived', 'Undo', {
            duration: 3000
          });
          alert("File uploaded")
        },
        error => {
          alert(error.error.message);
        })
    } else {
      alert("Please select a file first")
    }
  }

  sendToParent(process: BpmnProcess) {
    this.sendProcess.emit(process);
  }

  clearProcess() {
    this.file = null;
    this.name = "";
    this.sendProcess.emit();
  }
}
