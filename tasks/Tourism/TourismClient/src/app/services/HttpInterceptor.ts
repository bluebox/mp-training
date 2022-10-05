import { AuthService } from "./auth.service";
import { Injectable, Inject, Optional } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
// import { AuthService } from ‘./auth.service’;
@Injectable()
export class UniversalAppInterceptor implements HttpInterceptor {

  constructor( private authService: AuthService) { }

  intercept(req: HttpRequest<{}>, next: HttpHandler) {
    const token = this.authService.getAccessToken();
    req = req.clone({
      url:  req.url,
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next.handle(req);
  }
}
