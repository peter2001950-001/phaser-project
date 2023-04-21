import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { catchError, throwError } from 'rxjs';
import { errorHandling } from 'src/app/services/error-handling';
import { PhasesService } from 'src/app/services/phases/phases.service';

@Component({
  selector: 'app-phase-edit',
  templateUrl: './phase-edit.component.html',
  styleUrls: ['./phase-edit.component.scss']
})
export class PhaseEditComponent {
  constructor(public config: DynamicDialogConfig , private ref: DynamicDialogRef,  private svc: PhasesService, private messageService: MessageService){}
  public request: any;

  ngOnInit(): void {
      console.log(this.config.data)
      this.request = {...this.config.data};
  }

  onSubmit(event: any){
    this.svc.update(this.request.id, event).pipe(
      catchError((err: any) => {
      errorHandling(err, this.messageService);
      console.log(err);
      return throwError(err);
  })).subscribe(res=>{
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Successfully saved phase'
    });
    this.ref.close(res);
  },err=>{});
  }
}
