import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, shareReplay } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PhasesService {


  public readonly endpoint = '/phase';

  constructor(public http: HttpClient) {}

  public create(request: any): Observable<any> {
    return this.http.post<any>(this.endpoint, request);
  }

  public ordering(request: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}/ordering`, request);
  }

  public update(id: string, request: any): Observable<any> {
    return this.http.put<any>(`${this.endpoint}/${id}`, {...request});
  }

  public delete(id: string): Observable<any> {
    return this.http.delete<any>(`${this.endpoint}/${id}`);
  }

  public fetchLatest(params?: HttpParams): Observable<any> {
    return this.http.get<any>(this.endpoint, { params }).pipe(shareReplay(1));
  }
}
