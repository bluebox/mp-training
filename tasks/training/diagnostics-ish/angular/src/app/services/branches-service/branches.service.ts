import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = 'http://127.0.0.1:8000/';

@Injectable({
  providedIn: 'root',
})
export class BranchesService {
  constructor(private http: HttpClient) { }

  // Branches
  addBranch(data: any) {
    return this.http.post<any>(`${baseUrl}appointments/branches/`, data);
  }

  getBranches(): Observable<Object> {
    return this.http.get<any>(`${baseUrl}appointments/branches/`);
  }

  // branch
  getBranch(id: any): Observable<Object> {
    return this.http.get<any>(`${baseUrl}appointments/branch/${id}`);
  }


  updateBranch(id: any,data: any) {
    return this.http.put<any>(`${baseUrl}appointments/branch/${id}/`, data);
  }

  deleteBranch(id: any) {
    return this.http.delete<any>(`${baseUrl}appointments/branch/${id}`);
  }

  getSearchedBranches(text:string){
    let queryParams = { "text": text };
    return this.http.get<any>(`${baseUrl}appointments/search-branches/`, { params: queryParams })
  }
}
