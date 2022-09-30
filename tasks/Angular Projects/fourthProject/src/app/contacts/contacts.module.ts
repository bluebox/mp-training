import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddContactComponent } from './add-contact/add-contact.component';
import { DeleteContactComponent } from './delete-contact/delete-contact.component';
import { EditContactComponent } from './edit-contact/edit-contact.component';
import { ListContactComponent } from './list-contact/list-contact.component';
import { ViewContactComponent } from './view-contact/view-contact.component';



@NgModule({
  declarations: [
    AddContactComponent,
    DeleteContactComponent,
    EditContactComponent,
    ListContactComponent,
    ViewContactComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ContactsModule { }
