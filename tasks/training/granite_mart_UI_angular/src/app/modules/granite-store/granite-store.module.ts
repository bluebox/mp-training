import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreListComponent } from './store-list/store-list.component';
import { StoreViewComponent } from './store-view/store-view.component';
import { StoreRegisterComponent } from './store-register/store-register.component';
import { StoreUpdateComponent } from './store-update/store-update.component';



@NgModule({
  declarations: [
    StoreListComponent,
    StoreViewComponent,
    StoreRegisterComponent,
    StoreUpdateComponent
  ],
  imports: [
    CommonModule
  ]
})
export class GraniteStoreModule { }
