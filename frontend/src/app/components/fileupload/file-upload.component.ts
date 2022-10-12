import {Component, EventEmitter, Output} from '@angular/core';
import Types, {FileClientService} from "../../services/file-client.service";
import BpmnProcess = Types.BpmnProcess;

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent {
  @Output() childToParent = new EventEmitter<BpmnProcess>();
  name: string;
  file: File = null;
  fileName: string;

  constructor(private fileClientService: FileClientService) {
    this.name = 'Choose a file';
    this.fileName = '';
  }

  onFileChange(event: any) {
    this.file = event.target.files[0];
    this.fileName = event.target.files[0].name;
  }

  uploadFile() {
    if (this.file) {
      this.fileClientService.uploadFile(this.file).subscribe(data => {
          this.sendToParent(data);
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
    this.childToParent.emit(process);
  }
}
