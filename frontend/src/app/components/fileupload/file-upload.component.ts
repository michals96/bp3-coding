import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent {
  @Output() sendFile = new EventEmitter<File>();
  @Output() clearFile = new EventEmitter<void>();
  file: File = null;
  name: string;

  onFileChange(event: any) {
    this.file = event.target.files[0];
    this.name = event.target.files[0].name;
  }

  uploadFile() {
    if (this.file) {
      this.sendToParent(this.file);
    } else {
      alert("Please select a file first")
    }
  }

  sendToParent(file: File) {
    this.sendFile.emit(file);
  }

  clearProcess() {
    this.file = null;
    this.clearFile.emit();
    this.name = "";
  }
}
