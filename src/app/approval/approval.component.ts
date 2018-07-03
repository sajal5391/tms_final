import { Component, OnInit } from '@angular/core';
import { ApprovalDetails } from '../shared/approval';
import { DatePipe } from '@angular/common';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
// import Chart from 'chart.js/src/chart.js';


@Component({
	selector: 'app-approval',
	templateUrl: './approval.component.html',
	styleUrls: ['./approval.component.css']

})
export class ApprovalComponent implements OnInit {

	employee: any;
	time: any;
	hrs: any;
	mins: any;
	selectedTab: any;
	donut: any;

	height = 20;
	width = 20;

	/*openDialog(): void {
		const dialogRef = this.dialog.open(EmployeeDetailsComponent, {
		  width: '250px',
		 
		});
	
		dialogRef.afterClosed().subscribe(result => {
		  console.log('The dialog was closed');
	
		});
	  }
*/
	public doughnutChartLabels_project: string[] = ['CV1', 'CV3', 'CV5'];
	public doughnutChartLabels_tasks: string[] = ['Project Management', 'Integration', 'Development'];
	public doughnutChartLabels_skills: string[] = ['Angular', 'Java', 'Android'];
	public doughnutChartData_project: number[] = [350, 450, 100];
	public doughnutChartData_tasks: number[] = [200, 60, 200];
	public doughnutChartData_skills: number[] = [350, 500, 150];
	public doughnutChartType: string = 'doughnut';


	days = [
		{ value: 'day1', viewValue: 'Wed Aug 01' },
		{ value: 'day2', viewValue: 'Thu Aug 02' },
		{ value: 'day3', viewValue: 'Fri Aug 03' },
		{ value: 'day4', viewValue: 'Sat Aug 04' },
		{ value: 'day5', viewValue: 'Sun Aug 05' },
		{ value: 'day6', viewValue: 'Mon Aug 06' },
		{ value: 'day7', viewValue: 'Tue Aug 07' }
	];

	minsToHours(hours, mins): any {
		var total = (hours * 60 + mins) / 60;
		console.log("total is", total);
		var h = total.toString().split('.')[0];
		if (total.toString().split('.')[1]) {
			var m = "0." + total.toString().split('.')[1];
		} else {
			var m = "0";
		}
		console.log("h is", h);
		console.log("m is", m);
		m = (+m * 60).toFixed(0);
		if (m.toString().length > 1) {
			var t = h + ':' + m
		} else {
			var t = h + ':0' + m
		}
		return t;
	}

	progressBar = [

		{ width: 80, project: "CV1 - 2hrs" },
		{ width: 90, project: "CV3 - 2hrs" },
		{ width: 90, project: "CV5 - 2hrs" },
		{ width: 70, project: "CV7 - 1hrs" },
		{ width: 90, project: "Joan - 2hrs" },
		{ width: 80, project: "Common - 1hrs" },
		{ width: 60, project: "DIVA-1hrs" }


	];

	activeTab(array): any {
		return array.map(function (item) { return item.isActive; }).indexOf(true);
	}

	constructor(public dialog: MatDialog) {

	}

	summerizeUserEffort(arr): any {
        let self = this;
        var array = JSON.parse(JSON.stringify(arr)); //arr.map(x => Object.assign({}, x)); //deep copy of array (without reference) with this this.userLogEffort will not be effected
        array.forEach(function (val) {
            val.task = [{ skill_set: val.skill_set, task_name: val.task_name, hours: val.hours, mins: val.mins }];
        });

        var output = [];

        array.forEach(function (value) {
            console.log('output is', output);
            var existing = output.filter(function (v, i) {
                return v.project_name == value.project_name;
            });
            console.log('existing is', existing);
            if (existing.length) {
                var existingIndex = output.indexOf(existing[0]);
                output[existingIndex].task = output[existingIndex].task.concat(value.task);
            } else {
                output.push(value);
            }
        });

        console.log('final output is', output);
     
        output.forEach(function (item) {
            if (item.task.length > 1) {
                var totalHours = item.task.reduce(function (v, n) {
                    return v + +n.hours;
                }, 0);
                var totalMins = item.task.reduce(function (v, n) {
                    return v + +n.mins;
                }, 0);

                item.time = self.minsToHours(totalHours, totalMins);
            } else {
                item.time = self.minsToHours(+item.task[0].hours, +item.task[0].mins);
            }

        })
        return output;

	}
	

	effortSumarry(obj): any {
        var self = this;
        obj.time_sheet.forEach(function (item) {
            // if (item.effort.length) {
            //     item.summaryEffort = self.summerizeUserEffort(item.effort);
            // }
            item.displayDate = new Date(item.iris_date.split('-')[2], item.iris_date.split('-')[1], item.iris_date.split('-')[0]).toDateString().slice(0,10);
            item.summaryEffort = self.summerizeUserEffort(item.effort);
        });
        return obj;
    }
	ngOnInit() {

		this.employee = ApprovalDetails;
		this.time = this.minsToHours(this.employee.efforts, this.employee.efforts);
		//this.selectedTab = this.activeTab(this.employee);
		this.selectedTab = 0;

	}

}
