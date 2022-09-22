import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'main-root',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {
  response: any;
  id: string = "";
  myForm: FormGroup = new FormGroup ({
    id: new FormControl('')
  })

  constructor(private http: HttpClient, private route: Router) {

  }

  getData(form: FormGroup) {
    console.log(form.value);
    this.route.navigate(['search', form.value.id]);
  }

  title = 'weather';
}
