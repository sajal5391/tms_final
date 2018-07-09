import { Component, OnInit } from '@angular/core';
import { ApprovalDetails } from '../shared/approval';
import { EmpApproval } from '../shared/emp-approval';
import { DatePipe } from '@angular/common';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { FormControl, Validators } from '@angular/forms';
import { Logeffort } from '../shared/logeffort';
import { LOGEFFORTS } from '../shared/mock-logeffort';
import { LogeffortTwo } from '../shared/logeffort-two';
import { LOGEFFORTSTWO } from '../shared/mock-two-logeffort';
import { STATE } from '../shared/config';
import { ApprovalService } from './approval.service';
import { LoaderService } from '../loader/loader.service';
import { ActivatedRoute } from '@angular/router';


// import Chart from 'chart.js/src/chart.js';


@Component({
	selector: 'app-approval',
	templateUrl: './approval.component.html',
	styleUrls: ['./approval.component.css']

})
export class ApprovalComponent implements OnInit {

	employee: any;
	approvaldata : any;
	selectedTab: any;
	screen_width : any;
	effortboxwidth_perhour : any;
    employee_name = [{name:''}];
    totaltime : any;
	date :any;
	state : any;
<<<<<<< HEAD
	emp_id_arr = [];
	constructor(public dialog: MatDialog, 
		private approvalService: ApprovalService, 
		private loaderService: LoaderService,
	private route : ActivatedRoute,
	public notificationBar: MatSnackBar) { 

	 this.screen_width = (window.screen.width) + "px";
	 this.effortboxwidth_perhour = ((Math.floor((.6*(.9*((window.screen.width))))/11))-20);
  
	}

	openNotificationbar(message: string, action: string) {
        this.notificationBar.open(message, action, {
            duration: 5000,
        });
    }
=======

	constructor(public dialog: MatDialog, 
		private approvalService: ApprovalService, 
		private loaderService: LoaderService,
	private route : ActivatedRoute) { 

	 this.screen_width = (window.screen.width) + "px";
	 this.effortboxwidth_perhour = ((Math.floor((.6*(.9*((window.screen.width))))/11))-10);
  
	}
>>>>>>> da87ba741a32241e4602307c613b069e8df2e8b9

	/*openDialog(): void {
		const dialogRef = this.dialog.open(EmployeeDetailsComponent, {
		  width: '250px',
		});
	
		dialogRef.afterClosed().subscribe(result => {
		  console.log('The dialog was closed');
	
		});
	  }
*/
	public doughnutChartLabels_project:string[]= ['CV1' , 'CV3' , 'CV5'];
	public doughnutChartLabels_tasks: string[] = ['Project Management', 'Integration', 'Development'];
	public doughnutChartLabels_skills: string[] = ['Angular', 'Java', 'Android'];
	public doughnutChartData_project: number[] = [350, 450, 100];
	public doughnutChartData_tasks: number[] = [200, 60, 200];
	public doughnutChartData_skills: number[] = [350, 500, 150];
	public doughnutChartType: string = 'doughnut';


    loadApprovalEffort(): void {
        this.approvalService.loadApprovalEffort()
            .subscribe(
                (response) => {
                    console.log('approvaleffort submit response is ', response);
                    if (response['status'] == 'true') {

                        localStorage.setItem('approvalEffort', JSON.stringify(response['data']));
                        // this.openNotificationbar('Effort data submitted successfully!', 'Close');
                    } else {
                        // this.openNotificationbar(response['message'], 'Close');
                    }
                }, (err) => {
                    console.error('approvaleffort submit error ', err);
                    this.loaderService.hide();
                }, () => {
                    this.loaderService.hide(); //on complete hide the loader
                }
            );
	}
<<<<<<< HEAD
	
approveAll(obj){
	this.date = obj.iris_date;
   console.log(this.date);
    obj.empEfforts.forEach((item) => {
		this.emp_id_arr.push(item.emp_id);		
	}); 
	  console.log(this.emp_id_arr);
	var emp_arr ={
		iris_date:this.date,
		filled_state: "3",
		emp_ids:this.emp_id_arr
	}
	 this.approvalService.changeStatus(emp_arr)
	 .subscribe(
		 (response)=>{
			 console.log("response is " , response);

		 }
	 )
	 this.openNotificationbar('effort approved for all employees','close');
}

performAction(obj,number){
	this.date = obj.iris_date;
   console.log(this.date);
    // var emp_name = obj.emp_Efforts.emp_name;
	 console.log(obj.empEfforts.emp_name);
	  if(number==1){
		var emp_arr ={
			iris_date:this.date,
			filled_state: "3",
			emp_ids:[]
			
		}
		 this.approvalService.changeStatus(emp_arr)
		 .subscribe(
			 (response)=>{
				  console.log("approval response" , response);
			 }
		 )
		 this.openNotificationbar('effort approved','close');
	  }
	  if(number==2){
		var emp_arr ={
			iris_date:this.date,
			filled_state: "4",
			emp_ids:[]
			
		}
		 this.approvalService.changeStatus(emp_arr)
		 .subscribe(
			 (response)=>{
				  console.log("approval response" , response);
			 }
		 )
		 this.openNotificationbar('effort rejected','close');
	  }
	  if(number==3){
	
	  }
	
}

=======
	
approveAll(obj){
	this.date = obj.iris_date;
	var arr = obj.empEfforts;
	this.state = true;
	//this.approvalService.changeStatus(this.date ,this.state);
}
>>>>>>> da87ba741a32241e4602307c613b069e8df2e8b9
	ngOnInit() {

        this.loadApprovalEffort();
        console.log("ApprovalEffort is called ");
        console.log(JSON.parse(localStorage.getItem('employeeInfo')));
		console.log("screen width is " + this.screen_width , "perhourwidth is " + this.effortboxwidth_perhour);
		this.route.data
            .subscribe((res: any) => {
                console.log('resolved Approval list is ', res);
				this.approvaldata = JSON.parse(JSON.stringify(res.approval.data));//this.effortSumarry(res.data); ;
				//this.totaltime = this.totaltime(this.approvaldata.iris_date , this.approvaldata.empEfforts.emp_name , this.approvaldata.empEfforts.effort);
				console.log("employee_details inside are " + this.approvaldata);
            })
	}

}
