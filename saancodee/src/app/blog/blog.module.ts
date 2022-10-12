import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BlogRoutingModule } from './blog-routing.module';
import { BlogsComponent } from './components/blogs/blogs.component';
import { BlogCategoryComponent } from './components/blog-category/blog-category.component';
import { BlogItemComponent } from './components/blog-item/blog-item.component';
import { BlogComponent } from './blog.component';
import { AllBlogsComponent } from './components/all-blogs/all-blogs.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    BlogComponent,
    BlogsComponent,
    BlogCategoryComponent,
    BlogItemComponent,
    AllBlogsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BlogRoutingModule
  ]
})
export class BlogModule { }
