import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PhaseListComponent } from './phase-list.component';
import { OrderListModule } from 'primeng/orderlist';
import { ButtonModule } from 'primeng/button';
import { DialogService } from 'primeng/dynamicdialog';


@NgModule({
  declarations: [
    PhaseListComponent
  ],
  providers:[DialogService],
  imports: [
    CommonModule,
    OrderListModule,
    ButtonModule
  ]
})
export class PhaseListModule { }
