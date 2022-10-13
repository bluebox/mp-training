import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlogComponent } from './blog.component';
import { AllBlogsComponent } from './components/all-blogs/all-blogs.component';
import { BlogCategorySearchComponent } from './components/blog-category-search/blog-category-search.component';
import { BlogItemComponent } from './components/blog-item/blog-item.component';
import { BlogSearchComponent } from './components/blog-search/blog-search.component';
import { BlogsComponent } from './components/blogs/blogs.component';

const routes: Routes = [
  { path: '', component: BlogsComponent},
  { path: 'blog/:id', component: BlogItemComponent},
  { path: 'search', component: BlogSearchComponent},
  { path: 'category', component: BlogCategorySearchComponent }
  // { path: 'blog/:id', component: BlogItemComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BlogRoutingModule { }