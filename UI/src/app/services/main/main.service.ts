import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, shareReplay } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MainService {


  public readonly endpoint = '/main';

  constructor(public http: HttpClient) {}

  public create(): Observable<any> {
    return this.http.post<any>(this.endpoint+"/run", null);
  }

}
