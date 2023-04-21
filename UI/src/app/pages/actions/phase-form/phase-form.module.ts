import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PhaseFormComponent } from './phase-form.component';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    PhaseFormComponent
  ],
  imports: [
    CommonModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports:
  [PhaseFormComponent]
})
export class PhaseFormModule { }
