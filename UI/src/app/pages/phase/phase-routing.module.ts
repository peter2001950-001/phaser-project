import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PhaseListComponent } from './phase-list/phase-list.component';

const routes: Routes = [
  {
    path: '',
    component: PhaseListComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PhaseRoutingModule { }
