import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActionRoutingModule } from './action-routing.module';
import { ActionCreateModule } from './action-create/phase-create.module';
import { ActionEditModule } from './action-edit/action-edit.module';
import { ActionListModule } from './action-list/action-list.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ActionRoutingModule,
    ActionCreateModule,
    ActionEditModule,
    ActionListModule
  ]
})
export class ActionModule { }
