import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddLoansComponent } from './add-loans/add-loans.component';
import { AdminAccessGuard } from './admin-access.guard';
import { AdminDeleteComponent } from './admin-delete/admin-delete.component';
import { AdminEditComponent } from './admin-edit/admin-edit.component';
import { AdminManageComponent } from './admin-manage/admin-manage.component';
import { AdminGuard } from './admin.guard';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './auth.guard';
import { ClientComponent } from './client/client.component';
import { EditContactComponent } from './contacts/edit-contact/edit-contact.component';
import { ListContactComponent } from './contacts/list-contact/list-contact.component';
import { ViewContactComponent } from './contacts/view-contact/view-contact.component';
import { CustomerCheckGuard } from './customer-check.guard';
import { AddCustomerComponent } from './customers/add-customer/add-customer.component';
import { CustomersComponent } from './customers/customers.component';
import { LoanTypesComponent } from './loan-types/loan-types.component';
import { LoansComponent } from './loans/loans.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ProductComponent } from './product/product.component';
import { Product1Component } from './product1/product1.component';
import { Product2Component } from './product2/product2.component';
import { ResolverGuard } from './resolver.guard';
import { SearchComponent } from './search/search.component';
import { SuperAdminGuard } from './super-admin.guard';
import { UnsavedGuard } from './unsaved.guard';

const routes: Routes = [
  {path:'customer',
  children: [
    { path: '', component: CustomersComponent },
    { path: 'add', component: AddCustomerComponent },


  ]
},
  { path: 'loans', component: LoansComponent },
  { path: 'loans/add-loan', component: AddLoansComponent, outlet: 'route1', children: [] },
  { path: 'loan-types', component: LoanTypesComponent , canDeactivate:[UnsavedGuard]},
  {
    path: 'contacts',
    children: [
      { path: 'view-contact', component: ViewContactComponent },
      { path: 'edit-contact', component: EditContactComponent },


    ]
  },
  { path: 'new-loans', redirectTo: 'loans' },//redirect to loans 
  { path: '', redirectTo: 'customers', pathMatch: 'full' },//redirect to loans 
  { path: 'product1/1', component: Product1Component },
  { path: 'product2/2', component: Product2Component },
  { path: 'product/:id', component: ProductComponent },
  { path: 'product/:id/photos/:photoId', component: ProductComponent },
  { path: 'clients', component: ClientComponent, canActivate: [AuthGuard, AdminGuard] },
  { path: 'search', component: SearchComponent, canDeactivate:[UnsavedGuard] },
  {
    path: 'admin', canActivate: [SuperAdminGuard],
    children: [
      { path: '', component: AdminComponent, },
      {
        path: '', 
        canActivateChild: [AdminAccessGuard],
        children: [
          { path: 'manage', component: AdminManageComponent },
          { path: 'delete', component: AdminDeleteComponent },
          { path: 'edit', component: AdminEditComponent },
        ]
      }

    ]
  },
  { path: 'payments', loadChildren: () => import('./payments/payments.module').then(m => m.PaymentsModule) },
  { path: 'customers',
  canLoad:[CustomerCheckGuard],
   loadChildren: () => import('./customers/customers.module').then(m => m.CustomersModule) },
  { path: '**', component: PageNotFoundComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
