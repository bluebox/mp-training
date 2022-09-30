import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { catchError, Observable, retry } from 'rxjs';

@Injectable()
export class CommonInterceptorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
   
 const  API_KEY='1234'
 const  ROLE_KEY='1GJJ234'
  return next.handle(request.clone({setHeaders:{API_KEY, ROLE_KEY}}));
  // return next.handle(request.clone({setHeaders:{API_KEY, ROLE_KEY}})).pipe(retry(2),catchError(error=>{});
  // ));
  }
}
