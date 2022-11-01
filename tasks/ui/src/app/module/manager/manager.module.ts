import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManagerRoutingModule } from './manager-routing.module';
// import { NavbarComponent } from 'src/app/navbar/navbar.component';
import { ManagerhomeComponent } from '../components/managerhome/managerhome.component';
import { NavComponent } from '../components/nav/nav.component';
import { HomeComponent } from '../components/home/home.component';
import { ManagertaskComponent } from '../components/managertask/managertask.component';
import { FormsModule } from '@angular/forms';

// /home/mphs/Desktop/angulartask/facilityui/src/app/oncreate.directive.ts
@NgModule({
  declarations: [
    ManagerhomeComponent,
    NavComponent,
    HomeComponent,
    ManagertaskComponent
    

    
  ],
  imports: [
    CommonModule,
    ManagerRoutingModule,
    FormsModule
    // appOncreate
  ]
})
export class ManagerModule { }
