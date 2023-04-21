import { DialogService } from 'primeng/dynamicdialog';
import { PhasesService } from './../../../services/phases/phases.service';
import { Component, OnInit } from '@angular/core';
import { PhaseCreateComponent } from '../action-create/action-create.component';
import { PhaseEditComponent } from '../phase-edit/phase-edit.component';

@Component({
  selector: 'app-phase-list',
  templateUrl: './phase-list.component.html',
  styleUrls: ['./phase-list.component.scss']
})
export class PhaseListComponent implements OnInit {

    constructor(private svc: PhasesService, public dynamicDialog: DialogService){

    }
  ngOnInit(): void {
      this.fetchData();
  }
  fetchData(){
    this.svc.fetchLatest().subscribe(res=>{
      this.phases = res;
      this.selectedPhase = null;
    })
  }
    public phases: Array<any>  = [];
    public selectedPhase?: any;

    reorder(e: any){

      console.log(e);
      console.log(this.phases)
      var ordering = [];
      for (const key in this.phases) {
        ordering.push({id: this.phases[key].id, order: key});
      }

      this.svc.ordering({orderItems: ordering}).subscribe(res=>{});

    }

    select(e:any){
      this.selectedPhase = e.value[0];
    }

    deleteEvent(item: any){
      if(item){
        this.svc.delete(item.id).subscribe(res=>{
          this.fetchData();
        });
      }

    }
    edit(item: any){
        let ref;
        if(item){
          ref = this.dynamicDialog.open(PhaseEditComponent, {
            header: 'Edit a phase',
            width: '500px',
            data: item,
          });
          ref?.onClose.subscribe(res=>{
            this.fetchData();
          })
    }}

    create(){
      let ref;
        ref = this.dynamicDialog.open(PhaseCreateComponent, {
          header: 'Create new phase',
          width: '500px'
      });
      ref?.onClose.subscribe(res=>{
        this.fetchData();
      })
    }

}
