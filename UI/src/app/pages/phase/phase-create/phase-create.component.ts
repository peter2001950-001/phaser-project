import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { catchError, throwError } from 'rxjs';
import { errorHandling } from 'src/app/services/error-handling';
import { PhasesService } from 'src/app/services/phases/phases.service';

@Component({
  selector: 'app-phase-create',
  templateUrl: './phase-create.component.html',
  styleUrls: ['./phase-create.component.scss']
})
export class PhaseCreateComponent {
  constructor(private svc: PhasesService, private messageService: MessageService, private ref: DynamicDialogRef) {

  }
  public request: any;
  ngOnInit(): void {
   this.request = {
      name :""
   }
  }

  onSubmit(event: any){
    this.svc.create(event).pipe(
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

  onCancel(event: any){

  }
}
