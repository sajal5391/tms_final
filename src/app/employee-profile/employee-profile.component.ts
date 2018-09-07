import { Component, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent {

  constructor(
    public dialogRef: MatDialogRef<EmployeeProfileComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  // onNoClick(): void {
  //   this.dialogRef.close();
  // }
 group:any;
 domains=[];
 selectedGroup : any;
 empDetails:any;
 empData = {
   empID:'',empName:'',empEmailID:'',empDesignation:'',empNumber:'',empGroup:'',empDomain:''
   ,empApprover1:'',empApprover2:''
 };

 select(val:any){
   console.log(this.group);
   var name = this.group;

   if(name == "MPS-1"){
     this.domains=['Model Team India' , 'Model Team AME' , 'Integration' , 'COTA/FOTA' , 'Build & Release'];
   }
 }

 showEmpDetails(obj){
  console.log("show data" , obj);
 }

 ngOnInit() {

  }
}
