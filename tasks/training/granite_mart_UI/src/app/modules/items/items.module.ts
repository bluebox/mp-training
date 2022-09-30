import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemListComponent } from './item-list/item-list.component';
import { ItemViewComponent } from './item-view/item-view.component';
import { ItemRegisterComponent } from './item-register/item-register.component';
import { ItemUpdateComponent } from './item-update/item-update.component';



@NgModule({
  declarations: [
    ItemListComponent,
    ItemViewComponent,
    ItemRegisterComponent,
    ItemUpdateComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ItemsModule { }
