import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryComponent } from './components/category/category.component';
import { DiscussionComponent } from './components/discussion/discussion.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PostQuestionComponent } from './components/post-question/post-question.component';
import { ProblemComponent } from './components/problem/problem.component';
import { ProblemdiscussionComponent } from './components/problemdiscussion/problemdiscussion.component';
import { ProblemsComponent } from './components/problems/problems.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from './shared/auth.guard';

const routes: Routes = [
  {path:"", component:HomeComponent},
  {path:"login", component:LoginComponent},
  {path:"register", component:RegisterComponent},
  {path:"problems", component:ProblemsComponent},
  {path:"problems/category/:id", component:CategoryComponent},
  {path:"problems/:id", component:ProblemComponent},
  // {path:"problems/discussions", component:ProblemdiscussionComponent},
  {path:"problems/discussions/:id", component:ProblemdiscussionComponent},
  {path:"problems/discussions/:id/:discussionId", component:DiscussionComponent},
  {path:"post-question", component:PostQuestionComponent},
  {path:"profile", component:ProfileComponent, canActivate: [AuthGuard]},
  {path:"edit-profile", component:EditProfileComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
