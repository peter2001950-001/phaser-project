import { Component } from '@angular/core';
import { MainService } from 'src/app/services/main/main.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent {

  constructor(public svc: MainService) {
    
  }
  runProcess(){
      this.svc.create().subscribe(res=>{

      })
  }
}
