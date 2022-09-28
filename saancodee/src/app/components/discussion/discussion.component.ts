import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-discussion',
  templateUrl: './discussion.component.html',
  styleUrls: ['./discussion.component.css']
})
export class DiscussionComponent implements OnInit {

  params!:any;
  data!:any;

  constructor(private router: Router, private route: ActivatedRoute, public service:RegisterService) { 
    this.params = this.route.snapshot.params;
    this.data = {id:this.params['id'], discussionId: this.params['discussionId']}
    this.service.getDiscussion(this.data).subscribe((data) => {
      console.log(data)
    })
   }

  ngOnInit(): void {
  }

}
