import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TheatreDataService } from 'src/app/services/theatre-data.service';

@Component({
  selector: 'app-theatre-form',
  templateUrl: './theatre-form.component.html',
  styleUrls: ['./theatre-form.component.css']
})
export class TheatreFormComponent implements OnInit {
  public theatre:FormGroup=new FormGroup({
    Theatre_id:new FormControl("",[Validators.required]),
    Theatre_owner:new FormControl("",[Validators.required]),
    Theatre_name:new FormControl("",[Validators.required]),
    Theatre_location:new FormControl("",[Validators.required]),
    Theatre_screens:new FormControl("",[Validators.required])
  })

  constructor(private theatres:TheatreDataService) { }

  ngOnInit(): void {
  }
  OnSubmit(data:any){
    if(this.theatre.valid){
      this.theatres.postTheatre(this.theatre.value).subscribe(data=>console.log(data))
    }
    else{
      alert("fill the form properly")
    }
  }

}
