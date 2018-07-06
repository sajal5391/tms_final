import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Logeffort } from '../shared/logeffort';
import { LOGEFFORTS } from '../shared/mock-logeffort';
import { LogeffortTwo } from '../shared/logeffort-two';
import { LOGEFFORTSTWO } from '../shared/mock-two-logeffort';
import { MatSnackBar } from '@angular/material';
import { STATE } from '../shared/config';
import { LoaderService } from '../loader/loader.service';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-reports',
    templateUrl: './reports.component.html',
    styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

    days = [
		{ value: 'day1', viewValue: 'Wed Aug 01' },
		{ value: 'day2', viewValue: 'Thu Aug 02' },
		{ value: 'day3', viewValue: 'Fri Aug 03' },
		{ value: 'day4', viewValue: 'Sat Aug 04' },
		{ value: 'day5', viewValue: 'Sun Aug 05' },
		{ value: 'day6', viewValue: 'Mon Aug 06' },
		{ value: 'day7', viewValue: 'Tue Aug 07' }
	];
    
    public doughnutChartLabels_project: string[] = ['CV1', 'CV3', 'CV5'];
	public doughnutChartLabels_tasks: string[] = ['Project Management', 'Integration', 'Development'];
	public doughnutChartLabels_skills: string[] = ['Angular', 'Java', 'Android'];
	public doughnutChartData_project: number[] = [350, 450, 100];
	public doughnutChartData_tasks: number[] = [200, 60, 200];
	public doughnutChartData_skills: number[] = [350, 500, 150];
	public doughnutChartType: string = 'doughnut';


    public lineChartData:Array<any> = [
        {data: [65, 59, 80, 81, 56, 55, 40], label: 'Working Hours'},
       
      ];
      public lineChartLabels:Array<any> = ['Week1', 'Week2', 'Week3', 'Week4', 'Week5', 'Week6', 'Week7'];
      public lineChartOptions:any = {
        responsive: true
      };
      public lineChartColors:Array<any> = [
        { // grey
          backgroundColor: 'rgba(148,159,177,0.2)',
          borderColor: 'rgba(148,159,177,1)',
          pointBackgroundColor: 'rgba(148,159,177,1)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgba(148,159,177,0.8)'
        },
        { // dark grey
          backgroundColor: 'rgba(77,83,96,0.2)',
          borderColor: 'rgba(77,83,96,1)',
          pointBackgroundColor: 'rgba(77,83,96,1)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgba(77,83,96,1)'
        },
        { // grey
          backgroundColor: 'rgba(148,159,177,0.2)',
          borderColor: 'rgba(148,159,177,1)',
          pointBackgroundColor: 'rgba(148,159,177,1)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgba(148,159,177,0.8)'
        }
      ];
      public lineChartLegend:boolean = true;
      public lineChartType:string = 'line';
     
      public randomize():void {
        let _lineChartData:Array<any> = new Array(this.lineChartData.length);
        for (let i = 0; i < this.lineChartData.length; i++) {
          _lineChartData[i] = {data: new Array(this.lineChartData[i].data.length), label: this.lineChartData[i].label};
          for (let j = 0; j < this.lineChartData[i].data.length; j++) {
            _lineChartData[i].data[j] = Math.floor((Math.random() * 100) + 1);
          }
        }
        this.lineChartData = _lineChartData;
      }
     
      // events
      public chartClicked(e:any):void {
        console.log(e);
      }
     
      public chartHovered(e:any):void {
        console.log(e);
      }


    constructor() { }
    ngOnInit() {

		

	}
    

}
