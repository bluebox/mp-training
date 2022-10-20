import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdmindashboardcomponentComponent } from './components/admindashboardcomponent/admindashboardcomponent.component';
import { AdminhomepageComponent } from './components/adminhomepage/adminhomepage.component';
import { DisplayStudentComponent } from './components/display-student/display-student.component';
import { DisplayTeacherComponent } from './components/display-teacher/display-teacher.component';

const routes: Routes = [
  {
    path:"", component:AdmindashboardcomponentComponent,children:[
      { path:'', component:AdminhomepageComponent},
      { path:"display-student", component:DisplayStudentComponent},
      { path:'display-teacher', component:DisplayTeacherComponent}
      

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
