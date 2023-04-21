import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActionCreateComponent} from './action-create.component';
import { ActionFormModule } from '../action-form/action-form.module';



@NgModule({
  declarations: [
    ActionCreateComponent
  ],
  imports: [
    CommonModule,
    ActionFormModule
  ]
})
export class ActionCreateModule { }
