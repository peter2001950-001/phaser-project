import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PhaseRoutingModule } from './phase-routing.module';
import { PhaseListModule } from './phase-list/phase-list.module';
import { PhaseCreateModule } from './phase-create/phase-create.module';
import { PhaseEditModule } from './phase-edit/phase-edit.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    PhaseRoutingModule,
    PhaseListModule,
    PhaseCreateModule,
    PhaseEditModule
  ]
})
export class PhaseModule { }
