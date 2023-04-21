import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { catchError, throwError } from 'rxjs';
import { ActionsService } from 'src/app/services/actions/actions.service';
import { errorHandling } from 'src/app/services/error-handling';
import { PhasesService } from 'src/app/services/phases/phases.service';

@Component({
  selector: 'app-action-create',
  templateUrl: './action-create.component.html',
  styleUrls: ['./action-create.component.scss']
})
export class ActionCreateComponent {
  constructor(private svc: ActionsService,public config: DynamicDialogConfig, private messageService: MessageService, private ref: DynamicDialogRef) {

  }
  public request: any;
  public phaseId: string ="";
  ngOnInit(): void {
   this.phaseId = this.config.data.phaseId;
   this.request = {
      name :"",
      duration: 0
   }
  }

  onSubmit(event: any){
    this.svc.create(this.phaseId, event).pipe(
      catchError((err: any) => {
      errorHandling(err, this.messageService);
      console.log(err);
      return throwError(err);
  })).subscribe(res=>{
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Successfully saved action'
    });
    this.ref.close(res);
  },err=>{});
  }

  onCancel(event: any){

  }
}
