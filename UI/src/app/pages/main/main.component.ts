import { Component, OnInit } from '@angular/core';
import { ActionsService } from 'src/app/services/actions/actions.service';
import { MainService } from 'src/app/services/main/main.service';
import { PhasesService } from 'src/app/services/phases/phases.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(public svc: MainService, public phaseSvc: PhasesService, public actionSvc: ActionsService) {

  }

  phases: any[] = [];
  actions: any[] = [];
  runningProcess: boolean = false;

  ngOnInit(): void {
    this.phaseSvc.fetchLatest().subscribe(res=>{
      this.phases = res;
      for (const key in this.phases) {
        this.actionSvc.fetchLatest(this.phases[key].id).subscribe(res=>{
          for (const i in res) {
              this.actions.push(res[i]);
          }
        })
      }
    })

    this.svc.messages.subscribe(message=>{
      this.handleMessage(message);
    })
    console.log(this.actions)
  }
  runProcess(){
      if(this.runningProcess) return;
      this.svc.create().subscribe(res=>{

      })
  }

  handleMessage(message: any){
    switch(message.type){
      case "PhaseStart":
        var phase = this.phases.find(x=>x.id == message.message);
        phase.started = true;
        phase.finished = false;
      break;
      case "PhaseEnded":
        var phase = this.phases.find(x=>x.id == message.message);
        phase.started = true;
        phase.finished = true;
      break;
      case "ActionStart":
        var action = this.actions.find(x=>x.id == message.message);
        action.started = true;
        action.finished = false;
      break;
      case "ActionFinish":
          var action = this.actions.find(x=>x.id == message.message);
          action.started = true;
          action.finished = true;
          action.duration = message.duration;
      break;
      case "ProcessStarted":
         this.runningProcess = true;
      break;
      case "ProcessEnded":
         this.runningProcess = false;
      break;
    }
  }


}
