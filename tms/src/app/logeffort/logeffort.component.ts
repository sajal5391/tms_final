import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from '@angular/material';

@Component({
	selector: 'app-logeffort',
	templateUrl: './logeffort.component.html',
	styleUrls: ['./logeffort.component.css']
})
export class LogeffortComponent implements OnInit {
today = Date.now();
	windowHeight: any;
	x:any;
	size:any;
	commonsize:any;


	projects = [
		{ value: 'project-A', viewValue: 'G6' },
		{ value: 'project-B', viewValue: 'Neo' },
		{ value: 'project-C', viewValue: 'V30' },
		{ value: 'project-D', viewValue: 'CV5' },
	];
	tabArray = [
		{ value: '1', viewValue: 'Monday' },
		{ value: '2', viewValue: 'Tuesday' },
		{ value: '3', viewValue: 'Wednesday' },
		{ value: '4', viewValue: 'Thursday' },
		{ value: '5', viewValue: 'Friday' },
		{ value: '6', viewValue: 'Saturday' },
		{ value: '7', viewValue: 'Sunday' },
	];
	Common = [
		{ value: 'Common-A', viewValue: 'Meeting' },
		{ value: 'Common-B', viewValue: 'Personal Work' },
		{ value: 'Common-C', viewValue: 'Innovation' },
		{ value: 'Common-D', viewValue: 'Hr Event' },
	];

	positions = [{ skills: '', job: '', time: ''}];
	commonPositions = [{ commonJob: '', ctime:''}]; 
	allPositions = [{ project: '', addbutton:'', deletebutton:'', skillset: '', jobTask: '' , timeTaken:''}];

	constructor(public snackBar: MatSnackBar) { }
	public addPosition() {
		this.positions.push({ skills: '', job: '', time: ''});
	  }
	  public addcommonPositions() {
		this.commonPositions.push({commonJob: '', ctime:''});
	  }
	  public addAllPosition() {
			
		this.allPositions.push({ project: '', addbutton:'', deletebutton:'', skillset: '', jobTask: '' , timeTaken:''});
	  }

	  

	  public deletecommonPosition() {
		this.commonsize=this.commonPositions.length;
		if(this.commonsize > 1){
			this.commonPositions.pop();
		}
		if(this.commonsize==1){
			alert("You cannot delete the first row");
		}
	  }
	  
	  public deletePosition() {
		this.size=this.positions.length;
		if(this.size > 1){
			this.positions.pop();
		}
		if(this.size==1){
			alert("You cannot delete the first row");
		}
	  }

	  public addTime(){
         

	  }

	ngOnInit() {
		// this.windowHeight = window.screen.height - 360;
		this.x= (60*window.screen.height) / 100;
		this.windowHeight = this.x;
		
		console.log("screen height is " + window.screen.height);
		
	}

}
