import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, shareReplay } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActionsService {


  public readonly endpoint = '/phase';

  constructor(public http: HttpClient) {}

  public create(phaseId: string, request: any): Observable<any> {
    return this.http.post<any>(`${this.endpoint}/${phaseId}/actions`, request);
  }

  public update(id: string,phaseId: string, request: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}/${phaseId}/actions/${id}`, {...request});
  }

  public delete(id: string, phaseId: string): Observable<any> {
    return this.http.delete<any>(`${this.endpoint}/${phaseId}/actions/${id}`);
  }

  public fetchLatest(phaseId: string, params?: HttpParams): Observable<any> {
    return this.http.get<any>(`${this.endpoint}/${phaseId}/actions`).pipe(shareReplay(1));
  }
}
