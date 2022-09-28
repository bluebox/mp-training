import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categoryId:any;
  problems:any;
  tags:any;

  constructor(private route:ActivatedRoute, private service: RegisterService, private router: Router) { 
    this.categoryId = this.route.snapshot.params['id'];
    this.service.getCategory(this.categoryId).subscribe((data:any) => {
      console.log(data)
      this.problems = data.problem
      this.tags = data.tag
    })
   }

  ngOnInit(): void {
  }

}
