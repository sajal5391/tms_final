import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from "@angular/flex-layout";

import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule, 
         MatButtonModule,
         MatChipsModule, 
         MatCardModule, 
         MatMenuModule, 
         MatToolbarModule, 
         MatIconModule, 
         MatSidenavModule, 
         MatSnackBarModule,
         MatFormFieldModule, 
         MatListModule, 
         MatExpansionModule,
         MatTabsModule,
         MatSelectModule,
         MatRadioModule,
         MatProgressBarModule,
         MatCheckboxModule,
         MatDialogModule } from '@angular/material';
import { AppRoutingModule } from './/app-routing.module';
import { LoginComponent } from './login/login.component';
import { DashComponent } from './dash/dash.component';
import { CalendarComponent } from './calendar/calendar.component';
import { EmployeeProfileComponent } from './employee-profile/employee-profile.component';
import { LogeffortComponent } from './logeffort/logeffort.component';
import { ApprovalComponent } from './approval/approval.component';
import { ProjectListComponent } from './projectlist/projectlist.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashComponent,
    CalendarComponent,
    EmployeeProfileComponent,
    LogeffortComponent,
    ApprovalComponent,
    ProjectListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule, ReactiveFormsModule,
    FlexLayoutModule,
    BrowserAnimationsModule,
    MatButtonModule, 
    MatCheckboxModule, 
    MatSnackBarModule,
    MatCardModule, 
    MatMenuModule, 
    MatToolbarModule,
    MatProgressBarModule,
    MatIconModule,
    MatFormFieldModule, 
    MatSidenavModule,
    MatInputModule,
    MatListModule,
    MatChipsModule,
    MatExpansionModule,
    MatTabsModule,
    MatSelectModule,
    MatDialogModule,
    MatRadioModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    EmployeeProfileComponent
  ]
})
export class AppModule { }