import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PhaseEditComponent } from './phase-edit.component';
import { PhaseFormModule } from '../phase-form/phase-form.module';



@NgModule({
  declarations: [
    PhaseEditComponent
  ],
  imports: [
    CommonModule,
    PhaseFormModule
  ]
})
export class PhaseEditModule { }
