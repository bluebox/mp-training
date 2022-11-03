import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoanTypesComponent } from './loan-types/loan-types.component';
import { SearchComponent } from './search/search.component';

@Injectable({
  providedIn: 'root'
})
export class UnsavedGuard implements CanDeactivate<SearchComponent> {
  canDeactivate(component: SearchComponent){
    if(component.isDirty){
      return window.confirm("you have some unsave changes , Are you Sure to go back")
    }
    return true;
  }
  
}
