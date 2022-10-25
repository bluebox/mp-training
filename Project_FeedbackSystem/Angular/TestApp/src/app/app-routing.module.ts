import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ShowfacultyComponent } from './showfaculty/showfaculty.component';
import { ShowstudentComponent } from './showstudent/showstudent.component';

const routes: Routes = [
  {path: 'allstudent', component: ShowstudentComponent},
  {path: 'allfaculty', component: ShowfacultyComponent},
  {path: '', component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [ShowstudentComponent, ShowfacultyComponent]
