import { Component, OnInit, Inject } from '@angular/core';
import { EditProfileService } from './edit-profile.service';
import { LoginComponent } from '../login/login.component';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogConfig, MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  constructor(private router: Router, public editProfileService: EditProfileService,
    public loginComponent: LoginComponent, public dialog: MatDialog, public notificationBar: MatSnackBar) { }
  openNotificationbar(message: string, action: string) {
    this.notificationBar.open(message, action, {
      duration: 5000,
    });
  }
  // onNoClick(): void {
  //   this.dialogRef.close();
  // }
  key: any;
  empDetails: any;
  final_domains = [];
  image="http://portal.lgsoftindia.com/NewPortal/Images/PICS/4339.jpg";
  //test="http://portal.lgsoftindia.com/NewPortal/Images/PICS/4339.jpg";

  disable = "true";
  empID = JSON.parse(localStorage.getItem('employeeInfo')).empinfo.emp_id;
  groups = [];
  domains = [];


  editProfile() {
    this.disable = "false";

  }
  onCancel() {
    this.disable = "true";
  }


  editEmpProfile() {
    // var empData = { empinfo: this.empDetails, skill: this.empSkillSet };
    console.log("final data ", this.empDetails);
    this.editProfileService.updateDetails(this.empDetails).subscribe((response) => {
      console.log('editor action res is ', response);
      //   localStorage.setItem('employeeInfo', JSON.stringify(response));
      this.openNotificationbar('Edited Details sent for approval ', 'Close');
      this.router.navigate(['/dash/logeffort'], { replaceUrl: true });
    }, (err) => {
      console.log('approver action err is ', err);

    }

    );
  }


  onSubmit() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    let dialogRef = this.dialog.open(DialogEditProfile, {
      width: '250px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.editEmpProfile();
        //  this.router.navigate(['/dash/logeffort'], { replaceUrl: true });
        console.log("edition successfull");
      } else {
        console.log("edition failed");
      }
      console.log('The dialog was closed');
    });
  }

  ngOnInit() {
    var count = 0;
    this.editProfileService.getEmpDetails().subscribe((response) => {
      console.log("actual response", response.data[0]);
      this.empDetails = response.data[0];
      console.log("actual response", this.empDetails);
    });


    this.editProfileService.getgroupDetails().subscribe((response) => {
      // console.log("groups", Object.keys(response.data));
      this.groups = Object.keys(response.data);
 
      Object.values(response.data).forEach((item) => {
        count = 0;
        for (var key in item) {
          count = count + 1;
        }
        console.log(count);
      });
    });

    this.editProfileService.getDomainDetails().subscribe((response) => {
      // console.log("domains" , Object.values(response.data));
      this.domains = Object.values(response.data);
      console.log("domains", this.domains);
      for (let i = 0; i < this.domains.length; i++) {
        this.domains[i].forEach((item) => {
          this.final_domains.push(item.split(':')[0]);
        });
      }
    });

  }
}


@Component({
  selector: 'dialog-edit-profile',
  templateUrl: './dialog-edit-profile.html',
})
export class DialogEditProfile {

  constructor(
    public dialogRef: MatDialogRef<DialogEditProfile>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}