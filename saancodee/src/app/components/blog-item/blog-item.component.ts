import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-blog-item',
  templateUrl: './blog-item.component.html',
  styleUrls: ['./blog-item.component.css']
})
export class BlogItemComponent implements OnInit {
  blog: any;
  id: any;

  constructor(public service: RegisterService, private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.id = this.route.snapshot.params['id']
    this.service.getBlogData(this.id).subscribe((data:any) => {
      this.blog = data;
      console.log(data);
    })
  }

  ngOnInit(): void {
  }

}
