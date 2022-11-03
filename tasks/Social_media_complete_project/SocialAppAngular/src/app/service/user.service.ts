import { HttpClient, HttpErrorResponse, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PostResponse } from '../model/post-response';
import { ResetPassword } from '../model/reset-password';
import { UpdateUserEmail } from '../model/update-user-email';
import { UpdateUserInfo } from '../model/update-user-info';
import { UpdateUserPassword } from '../model/update-user-password';
import { User } from '../model/user';
import { UserResponse } from '../model/user-response';

@Injectable({
	providedIn: 'root'
})
export class UserService {
	private host = environment.apiUrl;
	private jwtService = new JwtHelperService();
	getUserById(userId: number): Observable<UserResponse | any | HttpErrorResponse> {
		console.log("user id is ", userId)
		return this.httpClient.post<UserResponse | any | HttpErrorResponse>(`${this.host}/profile/${userId}/`, {});
	}


	constructor(private httpClient: HttpClient) { }
	forgotPassword(email: string): Observable<any | HttpErrorResponse> {

		return this.httpClient.post<any>(`${this.host}/forgot_password_send_email/`, { "email": email });
	}
	verifyEmail(token: string, uid: string): Observable<HttpResponse<any> | HttpErrorResponse> {
		return this.httpClient.post<HttpResponse<any> | HttpErrorResponse>(`${this.host}/verify-email/${uid}/${token}/`, null);
	}

	getUserPosts(userId: number, page: number, size: number): Observable<PostResponse[] | any | HttpErrorResponse> {
		const reqParams = new HttpParams().set('page', page).set('size', size);
		return this.httpClient.get<PostResponse[] | HttpErrorResponse>(`${this.host}/users/posts/`, { params: reqParams });
	}

	updateUserInfo(updateUserInfo: any): any {
		return this.httpClient.post<any>(`${this.host}/update/`, updateUserInfo);
	}
	updateUserEmail(updateUserEmail: UpdateUserEmail): Observable<any | HttpErrorResponse> {
		return this.httpClient.post<any | HttpErrorResponse>(`${this.host}/change-email/`, updateUserEmail);
	}

	updateUserPassword(updateUserPassword: UpdateUserPassword): Observable<any | HttpErrorResponse> {
		return this.httpClient.post<any | HttpErrorResponse>(`${this.host}/change-password/`, updateUserPassword);
	}
	resetPassword(token: string, uid: string, resetPassword: ResetPassword): Observable<any | HttpErrorResponse> {
		return this.httpClient.post<any | HttpErrorResponse>(`${this.host}/reset-password/${uid}/${token}/`, resetPassword);
	}

	getUserSearchResult(key: string, page: number, size: number): Observable<UserResponse[] | any | HttpErrorResponse> {
		const reqParams = new HttpParams().set('key', key).set('page', page).set('size', size);
		return this.httpClient.get<UserResponse[] | HttpErrorResponse>(`${this.host}/users/search/`, { params: reqParams });
	}
	followUser(userId: number): Observable<any | HttpErrorResponse> {
		const body = {
			"follower": localStorage.getItem('id'),
			"followed": userId
		}
		console.log(JSON.stringify(body));
		return this.httpClient.post<any | HttpErrorResponse>(`${this.host}/follow-user/`, body);
	}
	unfollowUser(userId: number): Observable<any | HttpErrorResponse> {
		const body = {
			"follower": localStorage.getItem('id'),
			"followed": userId
		}
		console.log(JSON.stringify(body));
		return this.httpClient.post<any | HttpErrorResponse>(`${this.host}/unfollow-user/`, body);
	}


	updateProfilePhoto(profilePhoto: File): Observable<User | HttpErrorResponse> {
		const formData = new FormData();
		formData.append('profile_photo', profilePhoto);
		return this.httpClient.post<User | HttpErrorResponse>(`${this.host}/update/profile-photo/`, formData);
	}
	updateCoverPhoto(coverPhoto: File): Observable<User | any | HttpErrorResponse> {
		const formData = new FormData();
		formData.append('cover_photo', coverPhoto);
		return this.httpClient.post<User | HttpErrorResponse>(`${this.host}/update/cover-photo/`, formData);
	}
	getUserFollowingList(userId: number, page: number, size: number): Observable<UserResponse[] | any | HttpErrorResponse> {
		const reqParams = new HttpParams().set('page', page).set('size', size);
		return this.httpClient.get<UserResponse[] | HttpErrorResponse>(`${this.host}/user/following/${userId}/`, { params: reqParams });
	}

	getUserFollowerList(userId: number, page: number, size: number): Observable<UserResponse[] | any | HttpErrorResponse> {
		const reqParams = new HttpParams().set('page', page).set('size', size);
		return this.httpClient.get<UserResponse[] | HttpErrorResponse>(`${this.host}/user/follower/${userId}/`, { params: reqParams });
	}
}
