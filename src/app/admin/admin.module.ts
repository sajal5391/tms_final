import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from "@angular/flex-layout";
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AdminRoutingModule } from './admin-routing.module'

import {
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatCardModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatListModule,
    MatExpansionModule,
    MatTabsModule,
    MatSelectModule,
    MatRadioModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatProgressBarModule,
    MatGridListModule,
    MatTooltipModule,
    MatSnackBarModule,
    MatDatepickerModule,
    MatNativeDateModule
} from '@angular/material';
import { ChartsModule } from 'ng2-charts';
import { SharedService } from '../shared/shared.service';
import { ProjectStatusComponent } from '../project-status/project-status.component';
import { JobTaskListComponent } from '../job-task-list/job-task-list.component';
import { AdminService } from './admin.service';
import { FilterPipe } from '../project-status/filter.pipe';
import { ProjectStatusService } from '../project-status/project-status.service';
import { FilterTaskPipe } from '../job-task-list/filter.pipe';
import { JobTaskListService } from '../job-task-list/job-task-list.service';
import { AdminEditEmployeeComponent } from '../admin-edit-employee/admin-edit-employee.component';
import { AdminEditEmployeeService } from '../admin-edit-employee/admin-edit-employee.service';
import { AddEmployeeComponent } from '../add-employee/add-employee.component';
import { AddProfileService } from '../add-employee/add-profile.service';
import { UpdateDataComponent } from '../update-data/update-data.component';
import { LanguagesComponent } from '../languages/languages.component';
import { LanguagesService } from '../languages/languages.service';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule, 
        ReactiveFormsModule,
        FlexLayoutModule,
        BrowserAnimationsModule,
        HttpClientModule,
        CommonModule,
        MatButtonModule,
        MatCheckboxModule,
        MatCardModule,
        MatMenuModule,
        MatToolbarModule,
        MatIconModule,
        MatFormFieldModule,
        MatSidenavModule,
        MatInputModule,
        MatListModule,
        MatExpansionModule,
        MatTabsModule,
        MatSelectModule,
        MatDialogModule,
        MatRadioModule,
        MatProgressSpinnerModule,
        MatProgressBarModule,
        MatGridListModule,
        MatSnackBarModule,
        MatTooltipModule,
        ChartsModule,
        MatDatepickerModule,
        MatNativeDateModule
    ],
    declarations: [
        ProjectStatusComponent,
        JobTaskListComponent,
        AdminEditEmployeeComponent,
        AddEmployeeComponent,
        UpdateDataComponent,
        LanguagesComponent,
        FilterPipe,
	FilterTaskPipe
    ],
    providers: [
        SharedService,
        MatDatepickerModule,
        AdminService,
        ProjectStatusService,
        AddProfileService,
     JobTaskListService,
     LanguagesService,
     AdminEditEmployeeService
    ]
})
export class AdminModule { }