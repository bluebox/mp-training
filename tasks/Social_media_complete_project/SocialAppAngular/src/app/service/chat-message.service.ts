import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ChatMessage } from '../model/chat-message';

@Injectable({
  providedIn: 'root'
})
export class ChatMessageService {
  private host = environment.apiUrl;
  constructor(private http: HttpClient) { }
  getChatData(target_user_id: number): Observable<any> {
    const reqParams = new HttpParams().set('target_user', target_user_id)
    return this.http.get<ChatMessage[] | HttpErrorResponse>(`${this.host}/chat_data/current_user/`, { params: reqParams });
  }
}
