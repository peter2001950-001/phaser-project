import { ActionModule } from './../pages/actions/action.module';
import { ContactListModule } from './../pages/contacts/contact-list/contact-list.module';
import { LayoutModule } from './layout.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [{
      path: '',
      redirectTo: '/contacts',
      pathMatch: 'full'
    },
    {
      path: 'contacts',
      loadChildren: () => import('../pages/contacts/contacts.module').then((m) => m.ContactsModule)
    },
    {
      path: 'phases',
      loadChildren: () => import('../pages/phase/phase.module').then((m) => m.PhaseModule)
    },
    {
      path: 'actions',
      loadChildren: () => import('../pages/actions/action.module').then((m) => m.ActionModule)
    },
    {
      path: 'run',
      loadChildren: () => import('../pages/main/main.module').then((m) => m.MainModule)
    }]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }

export class LayoutComponents {
  public static components = [
    LayoutComponent
  ];
}

