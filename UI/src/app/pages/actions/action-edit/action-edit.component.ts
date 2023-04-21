import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { catchError, throwError } from 'rxjs';
import { ActionsService } from 'src/app/services/actions/actions.service';
import { errorHandling } from 'src/app/services/error-handling';
import { PhasesService } from 'src/app/services/phases/phases.service';

@Component({
  selector: 'app-action-edit',
  templateUrl: './action-edit.component.html',
  styleUrls: ['./action-edit.component.scss']
})
export class ActionEditComponent {
  constructor(public config: DynamicDialogConfig , private ref: DynamicDialogRef,  private svc: ActionsService, private messageService: MessageService){}
  public request: any;
  public phaseId: any;

  ngOnInit(): void {
      console.log(this.config.data)
      this.request = {...this.config.data.request};
      this.phaseId = this.config.data.phaseId;
  }

  onSubmit(event: any){
    this.svc.update(this.request.id, this.phaseId, event).pipe(
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
}
