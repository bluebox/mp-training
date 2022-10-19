import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdmindashboardcomponentComponent } from './components/admindashboardcomponent/admindashboardcomponent.component';
import { DisplayStudentComponent } from './components/display-student/display-student.component';

const routes: Routes = [
  {
    path:"", component:AdmindashboardcomponentComponent,children:[
      { path:"display-student", component:DisplayStudentComponent}
      

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
