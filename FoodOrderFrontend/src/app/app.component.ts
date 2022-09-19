import { Component } from '@angular/core';
import { Student } from './interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'FoodOrderFrontend';

  students: Student[] = [

    {id: 1, name: "Hardik"},

    {id: 2, name: "Paresh"},

    {id: 3, name: "Rakesh"},

  ]
}
