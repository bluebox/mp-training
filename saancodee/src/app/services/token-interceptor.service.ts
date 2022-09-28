import { HttpInterceptor } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { RegisterService } from './register.service'; 

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  
  constructor(private injector: Injector) { }
  intercept(req: { clone: (arg0: { setHeaders: { Authorization: string; }; }) => any; }, next: { handle: (arg0: any) => any; }) {
    let registerservice = this.injector.get(RegisterService)
    let tokenizedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${registerservice.getToken()}`
      }
    })
    return next.handle(tokenizedReq)
  }
}
