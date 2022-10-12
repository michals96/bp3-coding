import {Component, OnInit} from '@angular/core';
import {FileClientService} from "../../services/file-client.service";

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit {
  name: string;
  file: File = null;

  constructor(private fileClientService : FileClientService) {
    this.name = 'Upload process file to parse';
  }

  onFilechange(event: any) {
    console.log(event.target.files[0])
    this.file = event.target.files[0]
  }

  upload() {
    if (this.file) {
      this.fileClientService.uploadFile(this.file).subscribe(resp => {
        alert("Uploaded")
      })
    } else {
      alert("Please select a file first")
    }
  }

  ngOnInit() {
  }
}
