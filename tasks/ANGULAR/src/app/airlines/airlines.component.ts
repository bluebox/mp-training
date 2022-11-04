import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SharedService } from 'src/app/shared.service';


@Component({
  selector: 'app-airlines',
  templateUrl: './airlines.component.html',
  styleUrls: ['./airlines.component.css']
})
export class AirlinesComponent implements OnInit {
  // photofilename:string=""
  // photofilepath:string=""
  imageUrl: string = "/assets/images/cart.jpeg";
  // fileToUpload: File = null
  readonly PhotoUrl = "http://127.0.0.1:8000/media/"
  name:string=''
  image:any;
  nom:any;
  isactive:boolean=false;
  Airlineslist:any=[];
  @ViewChild("fileInput") fileInput: any;
  controleForm:any;

  pointControleFilter: FormGroup | undefined;
  constructor(private service:SharedService) { }

  file: any;
  Name: any;

  updateFile(e:any){
    this.file = e.target.files[0]
  }
  updateName(e:any){
    this.Name = e.target.value
  }




  ngOnInit(): void {

this.refreshAirlinesList()
// this.controleForm = new FormGroup({
//   nom= new FormControl('', [Validators.required])
// });

}
refreshAirlinesList(){
  this.service.getAirlineslist().subscribe(data=>{
    this.Airlineslist=data;
  console.log(data);
  });
}
onsubmit()
{
  this.isactive=true
}
// namechange(event:any)
// {
//   this.name=event.target.value;
//   console.log(this.name);

// }
// imagechange(event:any)
// {
//   this.image=event.target.files[0]

// }
upload()
{
  const formData = new FormData();
  formData.append('name', this.Name);
  formData.append('file', this.file);
  this.service.uploadphoto(formData).subscribe(data=>
    {
      console.log(data);

    })
  console.log(formData)
}
}

// onsubmit()
// {
//   const formdata =new FormData();
//   formdata.append('airlines_name',this.name);
//   formdata.append('airlnes_img',this.image);
//   console.log(formdata);


//   this.service.uploadphoto(formdata).subscribe(data=>
//   {

//      alert(data.toString())
//   }
//   )
// handleInputFile(file: FileList){
//   this.fileToUpload = file.item(0);
//   var reader = new FileReader();
//   reader.onload = (event:any) => {
//     this.imageUrl = event.target.result;
//   }
//   reader.readAsDataURL(this.fileToUpload);
// }

// OnSubmit(Caption,Image){
//   this.service.postFile(Caption.value, this.fileToUpload).subscribe(
//     (data:any) => {
//       console.log("Done");
//       Caption.value = "";
//       Image.value = "";
//     }
//   );
// }



