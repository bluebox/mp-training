import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpClient
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private http:HttpClient) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let token=sessionStorage.getItem('token')
    let requestInterceptor = request.clone({
      setHeaders:{
        Authorization:`Bearer ${token}`}
    })
    return next.handle(requestInterceptor);
  }
}
