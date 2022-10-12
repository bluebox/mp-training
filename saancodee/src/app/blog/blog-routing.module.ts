import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlogComponent } from './blog.component';
import { AllBlogsComponent } from './components/all-blogs/all-blogs.component';
import { BlogItemComponent } from './components/blog-item/blog-item.component';
import { BlogsComponent } from './components/blogs/blogs.component';

const routes: Routes = [
  { path: '', component: BlogsComponent},
  { path: 'blog/:id', component: BlogItemComponent},
  // { path: 'blog/:id', component: BlogItemComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BlogRoutingModule { }