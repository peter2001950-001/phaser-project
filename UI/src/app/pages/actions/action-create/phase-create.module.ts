import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PhaseCreateComponent } from './action-create.component';
import { PhaseFormModule } from '../phase-form/phase-form.module';



@NgModule({
  declarations: [
    PhaseCreateComponent
  ],
  imports: [
    CommonModule,
    PhaseFormModule
  ]
})
export class PhaseCreateModule { }
