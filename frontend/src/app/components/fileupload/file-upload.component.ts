import {Component, EventEmitter, Output} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";

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

  constructor(private snackBar: MatSnackBar) {
  }

  onFileChange(event: any) {
    this.file = event.target.files[0];
    this.name = event.target.files[0].name;
  }

  uploadFile() {
    if (this.file) {
      this.sendToParent(this.file);
    } else {
      this.snackBar.open("Please select a file first", 'Ok', {
        duration: 3000,
        horizontalPosition: "center",
        verticalPosition: "top"
      });
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
