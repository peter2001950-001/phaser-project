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
  private socketConnectionId?: string;

  constructor(private mainService: MainService) {
    this.socket = webSocket('ws://localhost:8080/ws');
    this.socket.subscribe(
      (message) => {
        if(message.type == "ConnectionEstablished"){
          this.mainService.socketId = message.message;
          console.log("Connection established: " + message.message);
        }else{
          console.log('Received message:', message);
          mainService.messages.next(message);
        }

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
