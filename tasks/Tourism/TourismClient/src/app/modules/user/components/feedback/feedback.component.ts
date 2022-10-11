import { Component, Input, OnInit } from '@angular/core';
import { FeedBack } from 'src/app/Interfaces/FeedbackInterface';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

  constructor(private auth: AuthService) { }
  @Input() feedback: any;
  user:any
  feedbackDate: any


  ngOnInit(): void {
    if(this.feedback?.user_id){
      this.feedbackDate = new Date(this.feedback.updated_at).toUTCString()
    }
  }
}
