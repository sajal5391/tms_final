import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LogeffortComponent } from '../logeffort/logeffort.component';

import { DashRoutingModule } from './dash-routing.module'
import { ApprovalComponent } from '../approval/approval.component';
import { ProjectListComponent } from '../projectlist/projectlist.component';

@NgModule({
    imports: [
        CommonModule,
    ],
    declarations: [
        LogeffortComponent,
        ApprovalComponent,
        ProjectListComponent
    ],
    providers: []
})
export class DashModule { }