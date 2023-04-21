import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActionEditComponent } from './action-edit.component';
import { ActionFormModule } from '../action-form/action-form.module';



@NgModule({
  declarations: [
    ActionEditComponent
  ],
  imports: [
    CommonModule,
    ActionFormModule
  ]
})
export class ActionEditModule { }
