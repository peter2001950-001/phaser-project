import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, shareReplay } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MainService {


  public readonly endpoint = '/main';
  public messages: Subject<any> = new Subject<any>();
  public socketId?: string;

  constructor(public http: HttpClient) {}

  public create(): Observable<any> {
    return this.http.post<any>(this.endpoint+"/run", {id: this.socketId});
  }

}
