import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon'

import { BlogRoutingModule } from './blog-routing.module';
import { BlogsComponent } from './components/blogs/blogs.component';
import { BlogCategoryComponent } from './components/blog-category/blog-category.component';
import { BlogItemComponent } from './components/blog-item/blog-item.component';
import { BlogComponent } from './blog.component';
import { AllBlogsComponent } from './components/all-blogs/all-blogs.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BlogSearchComponent } from './components/blog-search/blog-search.component';
import { BlogCategorySearchComponent } from './components/blog-category-search/blog-category-search.component';


@NgModule({
  declarations: [
    BlogComponent,
    BlogsComponent,
    BlogCategoryComponent,
    BlogItemComponent,
    AllBlogsComponent,
    BlogSearchComponent,
    BlogCategorySearchComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BlogRoutingModule,
    MatButtonModule,
    MatIconModule
  ]
})
export class BlogModule { }
