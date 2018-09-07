import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectStatusComponent } from '../project-status/project-status.component';
import { JobTaskListComponent } from '../job-task-list/job-task-list.component';
import { AdminEditEmployeeComponent } from '../admin-edit-employee/admin-edit-employee.component';
import { AddEmployeeComponent } from '../add-employee/add-employee.component';
import { UpdateDataComponent } from '../update-data/update-data.component';
import { LanguagesComponent } from '../languages/languages.component';


const adminRoutes: Routes = [
    { 
        path: 'project-status', 
        component: ProjectStatusComponent
    }, { 
        path: 'job-task-list', 
        component: JobTaskListComponent
    },
    { 
        path: 'admin-edit-employee', 
        component: AdminEditEmployeeComponent
    },
    { 
        path: 'add-employee', 
        component: AddEmployeeComponent
    },
    { 
        path: 'update-data', 
        component: UpdateDataComponent
    },
    { 
        path: 'languages', 
        component: LanguagesComponent
    }
    
];

@NgModule({
    imports: [
        RouterModule.forChild(adminRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class AdminRoutingModule { }