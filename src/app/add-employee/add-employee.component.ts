import { Component, OnInit, Inject } from '@angular/core';
import { AddProfileService } from './add-profile.service';
import { LoginComponent } from '../login/login.component';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogConfig, MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  constructor(private router: Router, public addProfileService: AddProfileService,
    public loginComponent: LoginComponent, public dialog: MatDialog, public notificationBar: MatSnackBar) { }
  openNotificationbar(message: string, action: string) {
    this.notificationBar.open(message, action, {
      duration: 5000,
    });
  }
  // onNoClick(): void {
  //   this.dialogRef.close();
  // }
empID : any;
final_groups=[];
domains=[];
final_domains=[];

   empDetails = {
    empinfo: {
      emp_id: "123", emp_name: '', emp_email: '', emp_designation: '', emp_domain: '', emp_group: '',
      approver_one: '', approver_two: '', emp_role: '0'
    },
    skill: {
      emp_id: "123", language_one: '', language_one_level: '', language_two: '', language_two_level: '', os: '', os_level: '',
      primary_skill_one: '', primary_skill_two: '', primary_skill_three: '', secondary_skill_one: '', secondary_skill_two: '',
      secondary_skill_three: ''
    }
  }



  onSubmit() {
    
    this.addProfileService.addEmpDetails(this.empDetails).subscribe((response) => {
      console.log("addition", response);
    });
    console.log(this.empDetails,this.empID);

  }

  ngOnInit() {
    this.addProfileService.getgroupDetails().subscribe((response) => {
      //   console.log("groups", response);
     //    console.log("values" , Object.keys(response.data));
      this.final_groups = Object.keys(response.data);
       });
       this.addProfileService.getDomainDetails().subscribe((response) => {
        this.domains = Object.values(response.data);
        console.log("domains",this.domains);
        for(let i=0;i<this.domains.length;i++){
          this.domains[i].forEach((item) => {
               this.final_domains.push(item.split(':')[0]);
          });
        }
       });
       
  }
}

