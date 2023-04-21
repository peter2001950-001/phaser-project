import { DialogService } from 'primeng/dynamicdialog';
import { Component, OnInit } from '@angular/core';
import { ActionCreateComponent } from '../action-create/action-create.component';
import { ActionEditComponent } from '../action-edit/action-edit.component';
import { ActionsService } from 'src/app/services/actions/actions.service';
import { PhasesService } from 'src/app/services/phases/phases.service';

@Component({
  selector: 'app-phase-list',
  templateUrl: './action-list.component.html',
  styleUrls: ['./action-list.component.scss']
})
export class ActionListComponent implements OnInit {

    constructor(private svc: ActionsService, private phaseService: PhasesService,  public dynamicDialog: DialogService){

    }

  public phases : Array<any> = [];
  public selectedPhase?: any;
  public actions: Array<any> = [];
  ngOnInit(): void {
      this.fetchPhases();
  }

  phaseChange(){
    this.fetchData();
  }
  fetchData(){
    if(this.selectedPhase)
    this.svc.fetchLatest(this.selectedPhase.id).subscribe(res=>{
      this.actions = res;
    })
  }

  fetchPhases(){
      this.phaseService.fetchLatest().subscribe(res=>{
        this.phases = res;
      })
  }

    deleteEvent(item: any){
      if(item){
        this.svc.delete(item.id, this.selectedPhase.id).subscribe(res=>{
          this.fetchData();
        });
      }

    }
    edit(item: any){
        let ref;
        if(item && this.selectedPhase){
          ref = this.dynamicDialog.open(ActionEditComponent, {
            header: 'Edit a action',
            width: '500px',
            data: {request: item, phaseId: this.selectedPhase.id } ,
          });
          ref?.onClose.subscribe(res=>{
            this.fetchData();
          })
    }}

    create(){
      if(!this.selectedPhase) return;
      let ref;
        ref = this.dynamicDialog.open(ActionCreateComponent, {
          header: 'Create new action',
          width: '500px',
          data: {phaseId: this.selectedPhase.id}
      });
      ref?.onClose.subscribe(res=>{
        this.fetchData();
      })
    }

}
