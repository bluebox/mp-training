import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';
import { UserLogin } from '../model/user-login';
import { UserSignup } from '../model/user-signup';

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	logoutSubject = new Subject<boolean>();
	loginSubject = new Subject<any>();
	private host = environment.apiUrl;
	private authToken!: string | null;
	private authRefreshToken!: string | null;
	private authUser!: any;
	private principal!: any;
	private jwtService = new JwtHelperService();

	constructor(private http: HttpClient, private route: Router) { }
	login(data: UserLogin): Observable<HttpResponse<User> | any | HttpErrorResponse> {
		return this.http.post(`${this.host}/login/`, data);
	}
	signup(userSignup: UserSignup): Observable<HttpResponse<User> | any | HttpErrorResponse> {
		return this.http.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/register/`, userSignup);
	}

	logout(): void {
		this.authToken = null;
		this.authRefreshToken = null;
		this.authUser = null;
		this.principal = 0;
		localStorage.removeItem('authUser');
		localStorage.removeItem('authToken');
		localStorage.clear();
		this.logoutSubject.next(true);
	}
	getuserdata(): Observable<HttpResponse<User> | any> {


		return this.http.get<HttpResponse<User> | any | HttpErrorResponse>(`${this.host}/profile/`);

	}
	getUserFollowersData(user_id: number): Observable<any> {
		return this.http.get<HttpResponse<User[]> | HttpErrorResponse>(`${this.host}/${user_id}/followers_data/`);
	}

	storeTokenInCache(authToken: string): void {
		if (authToken != null && authToken != '') {
			this.authToken = authToken;
			localStorage.setItem('authToken', authToken);
		}
	}
	storeRefreshTokenInCache(authRefreshToken: string): void {
		if (authRefreshToken != null && authRefreshToken != '') {
			this.authRefreshToken = authRefreshToken;
			localStorage.setItem('authRefreshToken', authRefreshToken);
		}
	}
	storeAuthUserInCache(authUser: any): void {
		if (authUser != null) {
			this.authUser = authUser;
			localStorage.setItem('authUser', JSON.stringify(authUser));
		}
		this.loginSubject.next(authUser);
	}


	getAuthTokenFromCache(): string | null {
		return localStorage.getItem('authToken');
	}
	getAuthUserFromCache(): any {
		return (localStorage.getItem('authUser'));
	}
	loadAuthTokenFromCache(): void {
		this.authToken = localStorage.getItem('authToken');
	}
	getAuthUserId(): number {
		return parseInt(localStorage.getItem('id') || '');
	}

	isUserLoggedIn(): boolean {
		this.loadAuthTokenFromCache();

		if (this.authToken != null && this.authToken != '') {
			console.log(this.jwtService.decodeToken(this.authToken))
			if (this.jwtService.decodeToken(this.authToken).user_email != null || '') {
				if (!this.jwtService.isTokenExpired(this.authToken)) {
					this.principal = this.jwtService.decodeToken(this.authToken).user_email;
					return true;
				}
			}
		}

		this.logout();
		return false;
	}

}
