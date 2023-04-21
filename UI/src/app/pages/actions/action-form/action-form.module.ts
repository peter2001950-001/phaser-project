import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActionFormComponent } from './action-form.component';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    ActionFormComponent
  ],
  imports: [
    CommonModule,
    InputTextModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports:
  [ActionFormComponent]
})
export class ActionFormModule { }
