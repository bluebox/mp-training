import { Component } from '@angular/core';
import { DataServiceService } from './data-service.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'AngularPractice';
  datafromchild:any='';
  data:any='';
  constructor(private ds:DataServiceService){
    
    this.data=this.ds.getData();
  }
  fromChild(data:any){
      this.datafromchild=data
  }
}
