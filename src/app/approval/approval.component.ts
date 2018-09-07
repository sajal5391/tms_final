import { Component, OnInit, Inject, ChangeDetectorRef, ViewChild, ElementRef, Renderer } from '@angular/core';
import { APPROVAL } from '../shared/approval';
import { MatSnackBar } from '@angular/material';
import { ApprovalService } from './approval.service';
import { LoaderService } from '../loader/loader.service';
import { ActivatedRoute } from '@angular/router';
import { SharedService } from '../shared/shared.service';
import { STATE } from '../shared/config';
//import { Colors } from '../shared/colors';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DatePipe } from '@angular/common';

@Component({
	selector: 'app-approval',
	templateUrl: './approval.component.html',
	styleUrls: ['./approval.component.css']
})




export class ApprovalComponent implements OnInit {

	window: any = window;
	approvals: any;
	selectedTab: number;
	windowheight: number;
	STATES: any;
	report_data = [];
	final_projects = [];
	final_tasks = [];
	final_report_data = [];
	final_task_data = [];
	height: any = 400;
	widthPerMin: any;


	@ViewChild('widthToMeasure', { read: ElementRef }) elementView: ElementRef;
	employee = JSON.parse(localStorage.getItem('employeeInfo'));

	empEmail = this.employee.empinfo.emp_email;
	private el: ElementRef;
	constructor(public notificationBar: MatSnackBar,
		private approvalService: ApprovalService,
		private loaderService: LoaderService,
		private route: ActivatedRoute,
		private sharedService: SharedService,
		public dialog: MatDialog,
		public cdRef: ChangeDetectorRef,
		el: ElementRef,
		//public colors : Colors,
		public renderer: Renderer
	) {
		this.el = el.nativeElement;
		this.renderer = renderer;
	}

	ngAfterViewInit() {
		// setTimeout(_ => this.window.showSidenav = false);
		// this.cdRef.detectChanges();
		console.log('task list div width', this.elementView.nativeElement.offsetWidth);
		setTimeout(_ => this.onResize());
	}

	onResize(): void {
		console.log('resize is called');
		let totalWidth = (window.innerWidth * 60) / 100;
		this.widthPerMin = totalWidth / 660;
		console.log('width per min ', this.widthPerMin);
	}

	openNotificationbar(message: string, action: string) {
		this.notificationBar.open(message, action, {
			duration: 5000,
		});
	}

	activeTab(array): any {
		return array.map(function (item) { return item.isActive; }).indexOf(true);
	}

	postApproverAction(data: any, message): void {
		this.loaderService.show()
		this.approvalService.postApproverAction(data)
			.subscribe(
				(response) => {
					console.log('approver action res is ', response);
					this.approvals = this.effortSumarry(response['data']);
					this.selectedTab = this.activeTab(this.approvals.approval);
					this.openNotificationbar(message, 'Close');
					this.loaderService.hide();
				}, (err) => {
					console.log('approver action err is ', err);
					this.loaderService.hide();
				}, () => {
					this.loaderService.hide();
				}
			);
	}

	notify(emp: any) {
		console.log('notify employee are ', emp);
		let recipients = '';
		emp.forEach(function (item) {
			if (item.filled_state == '0' || item.filled_state == '1') {
				recipients += item.emp_email + ';';
			}
		})
		location.href = ("mailto:" + recipients + "?subject=Fill your logefforts!&body=Dears,%0D%0A%0D%0AKinldy fill the Timesheet.%0D%0A%0D%0Ahttp://10.221.31.34:8080/TMS2/ %0D%0AThank you!%0D%0A" + this.employee.empinfo.emp_name);
	}

	approverAction(state: string, date: string, emp: any, all: boolean): void {
		var self = this;
		console.log('apppver action is ', state);
		console.log('apppver date is ', date);
		console.log('apppver employee is ', emp.emp_id);

		if (state == this.STATES.APPROVED) {
			if (all) {
				//approve all
				const dialogRef = this.dialog.open(DialogApproveAll, {
					width: '250px',
				});

				dialogRef.afterClosed().subscribe(result => {
					console.log('The dialog was closed', result);
					if (result) {
						var empArr = [];
						emp.forEach(function (item) {
							if (item.filled_state == self.STATES.SUBMITTED) {
								empArr.push(item.emp_id);
							}
						});
						var actionData = {
							iris_date: date,
							filled_state: state,
							emp_ids: empArr,
							comments: 'Approved',
							approverEmail: this.empEmail
						};
						console.log('action data is ', actionData);
						var message = 'All employee Approval is complete';
						this.postApproverAction(actionData, message);
					} else {
						console.log('approve all aborted');
					}
				});
			} else {
				var empArr = [];
				empArr.push(emp.emp_id);
				var actionData = {
					iris_date: date,
					filled_state: state,
					emp_ids: empArr,
					emp_email: emp.emp_email,
					comments: 'Approved',
					approverEmail: this.empEmail//'chetan.lavti'
				};
				console.log('action data is ', actionData);
				var message = 'Approval is complete';
				this.postApproverAction(actionData, message);
			}
		} else {
			var empArr = [];
			empArr.push(emp.emp_id);
			var actionData = {
				iris_date: date,
				filled_state: state,
				emp_ids: empArr,
				emp_email: emp.emp_email,
				comments: 'Reject',
				approverEmail: this.empEmail//'chetan.lavti'
			};
			const dialogRef = this.dialog.open(DialogReject, {
				width: '250px',
				data: { comment: actionData }
			});

			dialogRef.afterClosed().subscribe(result => {
				console.log('The dialog was closed', result);
				console.log('action data is ', actionData);
				// actionData.comments = result;
				if (result && actionData.comments) {
					console.log('action data is ', actionData);
					var message = 'Rejection is complete';
					this.postApproverAction(actionData, message);
				} else {
					console.log('reject aborted');
				}
			});
		}
	}

	effortSumarry(obj): any {
		var self = this;
		obj.approval.forEach(function (item) {
			var month = +item.iris_date.split('-')[1] - 1;
			item.displayDate = new Date(item.iris_date.split('-')[2], month, item.iris_date.split('-')[0]).toDateString().slice(0, 10);
			console.log(item.displayDate);
		});

		console.log('approval with summary is ', obj);
		return obj;
	}

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
			var t = h + '.' + m

		} else {
			var t = h + '.' + m

		}
		return t;
	}

	emptyData() {
		this.doughnutChartData_project.length = 0;
		this.doughnutChartData_task.length = 0;
		this.doughnutChartLabels_project.length = 0;
		this.doughnutChartLabels_task.length = 0;
	  }

	  //function to show reports on tab click 
	onTabClick(event) {
		var date = event.tab.textLabel;
		this.approvals.approval.forEach((item) => {
			console.log("shitty dates are ", item.displayDate);
			if (item.displayDate == date) {
				item.empEfforts.forEach((data) => {
					data.effort.forEach((info) => {
						this.report_data.push({
							'project_name': info.project_name,
							'task_name': info.task_name,
							'total_hours': +info.hours,
							'total_mins': +info.mins

						});
						console.log("hrs and mins", +info.hours, +info.mins);
					});
				});
			}
		});

		//  this.generateReports(this.approvals,date);
		this.report_data.forEach((item) => {
			this.final_projects.push(item.project_name);
		});
		this.report_data.forEach((item) => {
			this.final_tasks.push(item.task_name);
		});
		var myNewProjectList = Array.from(new Set(this.final_projects));
		var myNewTaskList = Array.from(new Set(this.final_tasks));

		var hours = 0;
		var mins = 0;
		for (let j = 0; j < myNewProjectList.length; j++) {
			for (let i = 0; i < this.report_data.length; i++) {
				if (myNewProjectList[j] == this.report_data[i].project_name) {
					hours = hours + this.report_data[i].total_hours;
					mins = mins + this.report_data[i].total_mins;
				}
			}
			var time = this.minsToHours(+hours, +mins);
			if (time != 0) {
				this.final_report_data.push({
					'final_project_name': myNewProjectList[j],
					'final_project_hours': time,
				});
			}
			time = 0;
			hours = 0;
			mins = 0;
		}

		for (let j = 0; j < myNewTaskList.length; j++) {
			for (let i = 0; i < this.report_data.length; i++) {
				if (myNewTaskList[j] == this.report_data[i].task_name) {
					hours = hours + this.report_data[i].total_hours;
					mins = mins + this.report_data[i].total_mins;
				}

			}
			var time = this.minsToHours(hours, mins);
			if (time != 0) {
				this.final_task_data.push({
					'final_task_name': myNewTaskList[j],
					'final_task_hours': parseFloat(time),
				});
			}
			time = 0;
		}


		for (let i = 0; i < this.final_report_data.length; i++) {
			this.report_data.forEach((item) => {
				if (item.project_name == this.final_report_data[i].final_project_name) {
					this.report_data.splice(i, 1);
				}
			});
		}
		for (let i = 0; i < this.final_task_data.length; i++) {
			this.report_data.forEach((item) => {
				if (item.task_name == this.final_task_data[i].final_task_name) {
					this.report_data.splice(i, 1);
				}
			});
		}


	this.emptyData();

		if (this.report_data.length == 0) {
			this.doughnutChartLabels_project.push('No Project data');
			this.doughnutChartData_project.push(100);
			this.doughnutChartLabels_task.push('No Task data');
			this.doughnutChartData_task.push(100);
			//this.lineChartColors.length = 0;
			this.lineChartColors.forEach((item) => {
				item.backgroundColor.length = 0;
			});
			this.lineChartColors.forEach((item) => {
				item.backgroundColor.push('grey');
			});
		}
		else {
			this.final_report_data.forEach((item) => {
				this.doughnutChartLabels_project.push(item.final_project_name);
				this.doughnutChartData_project.push(item.final_project_hours);

			});
			this.final_task_data.forEach((item) => {
				this.doughnutChartLabels_task.push(item.final_task_name);
				this.doughnutChartData_task.push(item.final_task_hours);
			});
			this.lineChartColors.forEach((item) => {
				item.backgroundColor.length = 0;
			});
			this.lineChartColors.forEach((item) => {
				for (let i = 0; i < 50; i++) {
					item.backgroundColor.push(this.getRandomColor());
				}
			});
		}

		this.report_data.length = 0;
		this.final_report_data.length = 0;
		this.final_task_data.length = 0;
	}

	  //function to show reports after every refresh

	generateReports(obj) {
		// console.log("full data is", obj);
		obj.approval.forEach((item) => {
			if (item.isActive == true) {
				item.empEfforts.forEach((data) => {
					data.effort.forEach((info) => {

						this.report_data.push({
							'project_name': info.project_name,
							'task_name': info.task_name,
							'total_hours': +info.hours,
							'total_mins': +info.mins
						});
					});
				});
			}
		});

		console.log("final check", this.report_data);

		this.report_data.forEach((item) => {
			this.final_projects.push(item.project_name);
		});
		this.report_data.forEach((item) => {
			this.final_tasks.push(item.task_name);
		});
		var myNewProjectList = Array.from(new Set(this.final_projects));
		var myNewTaskList = Array.from(new Set(this.final_tasks));


		var hours = 0;
		var mins = 0;
		for (let j = 0; j < myNewProjectList.length; j++) {
			for (let i = 0; i < this.report_data.length; i++) {
				if (myNewProjectList[j] == this.report_data[i].project_name) {
					hours = hours + this.report_data[i].total_hours;
					mins = mins + this.report_data[i].total_mins;
				}
			}
			var time = this.minsToHours(+hours, +mins);
			if (time != 0) {
				this.final_report_data.push({
					'final_project_name': myNewProjectList[j],
					'final_project_hours': time,
				});
			}
			time = 0;
			hours = 0;
			mins = 0;
		}

		for (let j = 0; j < myNewTaskList.length; j++) {
			for (let i = 0; i < this.report_data.length; i++) {
				if (myNewTaskList[j] == this.report_data[i].task_name) {
					time = time + this.report_data[i].total_hours;
				}
				console.log("time is ", time);
			}
			if (time != 0) {
				this.final_task_data.push({
					'final_task_name': myNewTaskList[j],
					'final_task_hours': time,
				});
			}
			time = 0;
		}

		if (this.report_data.length == 0) {
			this.doughnutChartLabels_project.push('No Project data');
			this.doughnutChartData_project.push(100);
			this.doughnutChartLabels_task.push('No Task data');
			this.doughnutChartData_task.push(100);
			this.lineChartColors.forEach((item) => {
				item.backgroundColor.length = 0;
			});
			this.lineChartColors.forEach((item) => {
				item.backgroundColor.push('grey');
			});
			this.doughnutChartLabels_project.length = 1;
			this.doughnutChartData_project.length = 1;
			this.doughnutChartLabels_task.length = 1;
			this.doughnutChartData_task.length = 1;
		}
		else {
			this.final_report_data.forEach((item) => {
				this.doughnutChartLabels_project.push(item.final_project_name);
				this.doughnutChartData_project.push(parseFloat(item.final_project_hours));
			});
			this.final_task_data.forEach((item) => {
				this.doughnutChartLabels_task.push(item.final_task_name);
				this.doughnutChartData_task.push(parseFloat(item.final_task_hours));
			});
			this.lineChartColors.forEach((item) => {
				item.backgroundColor.length = 0;
			});
			this.lineChartColors.forEach((item) => {
				for (let i = 0; i < 50; i++) {
					item.backgroundColor.push(this.getRandomColor());
				}
			});
		}
		this.report_data.length = 0;
		this.final_report_data.length = 0;
		this.final_task_data.length = 0;
	}
	public lineChartColors: Array<any> = [
		{ // first color
			backgroundColor: [],
		}
	];


	//generating
	getRandomColor() {
		var letters = '0123456789ABCDEF'.split('');
		var color = '#';
		for (var i = 0; i < 6; i++) {
			color += letters[Math.floor(Math.random() * 16)];
		}
		return color;
	}


	ngOnInit() {
		// this.window.showSidenav = true;
		this.windowheight = (73 * window.screen.height) / 100;

		// this.approvals = this.effortSumarry(APPROVAL.data);
		// this.selectedTab = this.activeTab(this.approvals);
		this.STATES = STATE;
		this.route.data
			.subscribe((res: any) => {
				console.log('resolved approvals are ', res);
				this.approvals = this.effortSumarry(res.approvals.data);
				this.generateReports(this.approvals);
				this.selectedTab = this.activeTab(this.approvals.approval);
			});
	}

	postPrevNextWeek(weekNumber, year) {
		this.loaderService.show();
		this.approvalService.weekEffort(weekNumber, year)
			.subscribe(
				(response) => {
					console.log('week approval response is ', response);
					if (response['status'] == 'true') {
						this.approvals = this.effortSumarry(response['data']);
						this.generateReports(this.approvals);
						this.selectedTab = this.activeTab(this.approvals.approval);
						// this.logefforts.time_sheet = JSON.parse(JSON.stringify(this.logefforts.time_sheet));
						// this.selectedTab = JSON.parse(JSON.stringify(this.activeTab(this.logefforts.time_sheet)));
						// this.selected.setValue(this.selectedTab);
						console.log('weekEffort selectedTab is', this.selectedTab);

					} else {
						this.openNotificationbar(response['message'], 'Close');
					}
					this.loaderService.hide();
				}, (err) => {
					console.error('logeffort submit error ', err);
					this.loaderService.hide();
				}, () => {
					this.loaderService.hide(); //on complete hide the loader
				}
			);
	}

	prevNextWeek(str: string): void {
		console.log("prevNextWeek fucntion call", str);
		if (str === 'prev') {
			let weekNumber = this.approvals.week_number - 1;
			let year = this.approvals.approval[0].iris_date.split("-")[2];
			this.postPrevNextWeek(weekNumber, year);
		} else if (str === 'next') {
			let weekNumber = this.approvals.week_number + 1;
			let year = this.approvals.approval[0].iris_date.split("-")[2];
			this.postPrevNextWeek(weekNumber, year);
		}
	}

	// Doughnut

	public doughnutChartType: string = 'doughnut';
	public doughnutChartLabels_project: string[] = [];
	public doughnutChartData_project: any = [];
	public doughnutChartLabels_task: string[] = [];
	public doughnutChartData_task: any = [];

	public doughnutChartOptions_projects: any = {
		legend: {
			display: true,
			position: 'right',
			labels: {
				fontColor: 'black',
				fontSize: 12,
				position: 'bottom',
				usePointStyle: true,
				boxWidth: 100
			}
		},
		title: {
			display: true,
			position: 'top',
			text: 'Project Wise Analysis'
		},
		elements: {
			radius: 20
		}
	};

	public doughnutChartOptions_tasks: any = {
		legend: {
			display: true,
			position: 'right',
			labels: {
				fontColor: 'black',
				fontSize: 12,
				position: 'bottom',
				usePointStyle: true,
				boxWidth: 100
			}
		},
		title: {
			display: true,
			position: 'top',
			text: 'Tasks Wise Analysis'
		},
		elements: {
			radius: 20
		}
	};


	//public x = 800;
	// events
	public chartClicked(e: any): void {
		console.log(e);
	}

	public chartHovered(e: any): void {
		console.log(e);
	}

}

@Component({
	selector: 'dialog-approve-all',
	templateUrl: './dialog-approve-all.html',
})
export class DialogApproveAll {

	constructor(
		public dialogRef: MatDialogRef<DialogApproveAll>,
		@Inject(MAT_DIALOG_DATA) public data: any) { }

	onNoClick(): void {
		this.dialogRef.close();
	}
}

@Component({
	selector: 'dialog-reject',
	templateUrl: './dialog-reject.html',
})
export class DialogReject {

	constructor(
		public dialogRef: MatDialogRef<DialogReject>,
		@Inject(MAT_DIALOG_DATA) public data: any) { }

	onNoClick(): void {
		this.dialogRef.close();
	}

}
