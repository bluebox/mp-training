import { Component } from '@angular/core';
import { Home } from './Home';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'TestApp';
  childData: Home[] | undefined;
  parentMethod(text:Home[]){
    this.childData = text;
    console.log(text);
  }
}
