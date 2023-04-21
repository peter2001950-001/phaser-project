import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from '../service/layout.service';

@Component({
    selector: 'app-layout-menu',
    templateUrl: './layout-menu.component.html'
})
export class LayoutMenuComponent implements OnInit {

    model: any[] = [];

    constructor(public layoutService: LayoutService) { }

    ngOnInit() {
        this.model = [
            {
                label: 'Home',
                items: [
                    { label: 'Phases', icon: 'pi pi-fw pi-home', routerLink: ['/phases'] },
                    { label: 'Actions', icon: 'pi pi-fw pi-home', routerLink: ['/actions'] },
                    { label: 'Run', icon: 'pi pi-fw pi-home', routerLink: ['/run'] }
                ]
            }
        ];
    }
}
