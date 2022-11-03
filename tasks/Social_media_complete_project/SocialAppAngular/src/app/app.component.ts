import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'SocialAppAngular';
  brightness: boolean = false
  brightness_mode(brightness: boolean) {
    this.brightness = brightness

  }
}
