import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import localStorage from 'local-storage';
import { catchError, Observable, throwError } from 'rxjs';
import { UserServicesService } from './user-services.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService  implements HttpInterceptor{

  constructor(private inject :Injector) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authservice=this.inject.get(UserServicesService)
    let jwttoken=this.AddTokenHeader(req, authservice.getToken())
    console.log(jwttoken)
    return next.handle(jwttoken).pipe(
      catchError(err => { 
        if(err.data.status === 401) {
          // need to implement logout methods
          authservice.logout1()

          // refresh token logic

        }
        return throwError("err occured at token intercept")
      })
    );
  }
  AddTokenHeader(request: HttpRequest<any>, token:any){
  return request.clone({
    headers:request.headers.set('Authorization', 'Bearer '+token)
  });
  }

    


}
