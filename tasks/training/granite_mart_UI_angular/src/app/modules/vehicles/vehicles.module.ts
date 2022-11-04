import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehiclesListComponent } from './vehicles-list/vehicles-list.component';
import { VehiclesViewComponent } from './vehicles-view/vehicles-view.component';
import { VehiclesRegisterComponent } from './vehicles-register/vehicles-register.component';
import { VehiclesUpdateComponent } from './vehicles-update/vehicles-update.component';



@NgModule({
  declarations: [
    VehiclesListComponent,
    VehiclesViewComponent,
    VehiclesRegisterComponent,
    VehiclesUpdateComponent
  ],
  imports: [
    CommonModule
  ]
})
export class VehiclesModule { }
