import { Component, OnInit, Inject } from '@angular/core';
import { ApproveCmsService } from './approve-cms.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-approve-cms',
  templateUrl: './approve-cms.component.html',
  styleUrls: ['./approve-cms.component.css']
})
export class ApproveCmsComponent implements OnInit {

  constructor(public approveCmsService: ApproveCmsService, public notificationBar: MatSnackBar, public dialog: MatDialog, ) { }

  openNotificationbar(message: string, action: string) {
    this.notificationBar.open(message, action, {
      duration: 5000,
    });
  }

  empList: any;
  color: any;
  comments: any;
  disable_approve="true";
  disable_reject="true";
  group_color; domain_color; pri_skill1_color; pri_skill2_color; pri_skill3_color; sec_skill1_color; sec_skill2_color;
  sec_skill3_color; lang1_color; lang1_level_color; lang2_color; lang2_level_color; os_color; os_level_color;
  approver1_color; approver2_color;

  before = {
    status: '',
    empinfo: {
      emp_id: '', emp_name: '', emp_email: '', emp_designation: '', emp_domain: '', emp_group: '',
      approver_one: '', approver_two: '', emp_role: '0'
    },
    skill: {
      emp_id: '', language_one: '', language_one_level: '', language_two: '', language_two_level: '', os: '', os_level: '',
      primary_skill_one: '', primary_skill_two: '', primary_skill_three: '', secondary_skill_one: '', secondary_skill_two: '',
      secondary_skill_three: ''
    }
  }

  after = {
    status: '',
    empinfo: {
      emp_id: '', emp_name: '', emp_email: '', emp_designation: '', emp_domain: '', emp_group: '',
      approver_one: '', approver_two: '', emp_role: '0'
    },
    skill: {
      emp_id: '', language_one: '', language_one_level: '', language_two: '', language_two_level: '', os: '', os_level: '',
      primary_skill_one: '', primary_skill_two: '', primary_skill_three: '', secondary_skill_one: '', secondary_skill_two: '',
      secondary_skill_three: ''
    }
  }


  showDetails(id) {
    this.color = 'teal';
    this.disable_approve="false";
    this.disable_reject="false";
    this.approveCmsService.getEmpDetails(id).subscribe((response) => {
      console.log(response.data);
      this.before = response.data.before;
      this.after = response.data.after;

      console.log("before", this.before, "after", this.after);
      this.difference(this.before, this.after);

    });

  }

  difference(obj1, obj2) {
    if (obj1.empinfo.emp_group != obj2.empinfo.emp_group) { this.group_color = "yellow"; }
    if (obj1.empinfo.emp_domain != obj2.empinfo.emp_domain) { this.domain_color = "yellow"; }
    if (obj1.empinfo.primary_skill_one != obj2.empinfo.primary_skill_one) { this.pri_skill1_color = "yellow"; }
    if (obj1.empinfo.primary_skill_two != obj2.empinfo.primary_skill_two) { this.pri_skill2_color = "yellow"; }
    if (obj1.empinfo.primary_skill_three != obj2.empinfo.primary_skill_three) { this.pri_skill3_color = "yellow"; }
    if (obj1.empinfo.secondary_skill_one != obj2.empinfo.secondary_skill_one) { this.sec_skill1_color = "yellow"; }
    if (obj1.empinfo.secondary_skill_two != obj2.empinfo.secondary_skill_two) { this.sec_skill2_color = "yellow"; }
    if (obj1.empinfo.secondary_skill_three != obj2.empinfo.secondary_skill_three) { this.sec_skill3_color = "yellow"; }
    if (obj1.empinfo.language_one != obj2.empinfo.language_one) { this.lang1_color = "yellow"; }
    if (obj1.empinfo.language_one_level != obj2.empinfo.language_one_level) { this.lang1_level_color = "yellow"; }
    if (obj1.empinfo.language_two != obj2.empinfo.language_two) { this.lang2_color = "yellow"; }
    if (obj1.empinfo.language_two_level != obj2.empinfo.language_two_level) { this.lang2_level_color = "yellow"; }
    if (obj1.empinfo.os != obj2.empinfo.os) { this.os_color = "yellow"; }
    if (obj1.empinfo.os_level != obj2.empinfo.os_level) { this.os_level_color = "yellow"; }
    if (obj1.empinfo.approver_one != obj2.empinfo.approver_one) { this.approver1_color = "yellow"; }
    if (obj1.empinfo.approver_two != obj2.empinfo.approver_two) { this.approver2_color = "yellow"; }

  }

  sendResponse(response) {

    if (response == 'approve') {
 
      var status_approve = { status: 1, reason: "approved", empinfo: this.after.empinfo, skill: this.after.skill };
      console.log("check", status_approve);
      this.approveCmsService.sendResponse(status_approve).subscribe((response) => {
        console.log(response);
        this.openNotificationbar('Employee Edited Details Approved', 'Close');
      });
      this.disable_approve="true";
      this.disable_reject="true";
    } else if (response == 'reject') {
      
      var status_reject = { status: 2, reason: '', empinfo: this.after.empinfo, skill: this.after.skill };

      const dialogRef = this.dialog.open(DialogReject1, {
        width: '250px',
        data: { comment: status_reject }
      });
      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          console.log("reason", status_reject);
          this.approveCmsService.sendResponse(status_reject).subscribe((response) => {
            console.log(response);
            this.openNotificationbar('Employee Edited Details Rejected', 'Close');
          });
        }
      });
      this.disable_approve="true";
    this.disable_reject="true";
    }
  }

  ngOnInit() {

    this.approveCmsService.employeeList().subscribe((response) => {

      this.empList = response.data;
      console.log("employee list ", this.empList);
    });



  }

}


@Component({
  selector: 'dialog-reject1',
  templateUrl: './dialog-reject1.html',
})
export class DialogReject1 {

  constructor(
    public dialogRef: MatDialogRef<DialogReject1>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
