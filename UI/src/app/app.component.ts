import { Component } from '@angular/core';
import {webSocket, WebSocketSubject} from 'rxjs/webSocket';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'contactApp';

  private socket: WebSocketSubject<any>;

  constructor() {
    this.socket = webSocket('ws://localhost:8080/ws');
  }

  public connect(): WebSocketSubject<any> {
    return this.socket;
  }

  public subscribe(topic: string) {
    this.socket.next({ command: 'subscribe', destination: '/topic/' + topic });
  }

}
