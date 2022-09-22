import { Component } from '@angular/core';
import { Student } from './interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'FoodOrderFrontend';

  user:string="Admin"
  data=""

  outputReceived(data:string)
  {
      this.data=data
  }


}
