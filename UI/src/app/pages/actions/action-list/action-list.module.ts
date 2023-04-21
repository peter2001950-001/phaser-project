import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActionListComponent } from './action-list.component';
import { OrderListModule } from 'primeng/orderlist';
import { ButtonModule } from 'primeng/button';
import { DialogService } from 'primeng/dynamicdialog';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';


@NgModule({
  declarations: [
    ActionListComponent
  ],
  providers:[DialogService],
  imports: [
    CommonModule,
    OrderListModule,
    ButtonModule,
    DropdownModule,
    FormsModule,
    TableModule

  ]
})
export class ActionListModule { }
