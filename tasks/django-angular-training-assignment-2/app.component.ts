import { OnDestroy, Component, Input } from '@angular/core';
import { ChildComponent } from './child/child.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  // this variable is passed to child component
  name:String = "sairam";

  // this is child level variable
  // this is initially undefined because no event triggered to access child level variables
  childName:String | undefined

  // this is toggled whenever destrop button is clicked to destroy the child component
  destroyed:boolean= true;

  // once click event is triggered child level variables can be accessed
  clicked(x:any) {
    this.childName = x
  }

  // this function toggles
  destroy(){
    this.destroyed = !this.destroyed;
  }
}
