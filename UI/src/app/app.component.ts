import { Component } from '@angular/core';
import {webSocket, WebSocketSubject} from 'rxjs/webSocket';
import { MainService } from './services/main/main.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'contactApp';

  private socket: WebSocketSubject<any>;

  constructor(private mainService: MainService) {
    this.socket = webSocket('ws://localhost:8080/ws');
    this.socket.subscribe(
      (message) => {
        console.log('Received message:', message);
        mainService.messages.push(message);
      },
      (error) => {
        console.error('WebSocket error:', error);
      }
    );
  }

  public connect(): WebSocketSubject<any> {
    return this.socket;
  }

}
