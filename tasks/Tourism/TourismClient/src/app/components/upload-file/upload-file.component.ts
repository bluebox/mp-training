import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  file : any;


  onchange(e:any){
    console.log(e);
    this.file = e.target.files[0]
  }




  uploadfile(file: any){
    const formData = new FormData();
    console.log("file", file, file.name);
    // Store form name as "file" with file data
    formData.append("file", this.file);
    console.log(formData);
    // Make http post request over api
    // with formData as req
    this.http.post('/api/bookings/uploadImage/', formData).subscribe(
      data => console.log(data),
      err => {
          if(err.status == 404 || 500){
            alert(err.message)
          }
          else{
            alert(err.error.detail)
          }
        }
      )
  }

  upload(e:any){
    e.preventDefault()
    console.log(e);
    console.log(this.file);
    this.uploadfile(this.file)
  }

}
