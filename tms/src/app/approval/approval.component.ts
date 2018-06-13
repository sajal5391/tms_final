import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrls: ['./approval.component.css']
})
export class ApprovalComponent implements OnInit {
  today = Date.now();
    windowHeight: any;
    x:any;
    size:any;
    commonsize:any;
    inputsize:any;
    color:any = 'primary';
    mode:any = 'determinate';
    workhrs:any="8";
    value:any = 50;
    bufferValue:any = 60;
    totalhrs:any=40;
  

    Employees = [
      { value: '1', viewValue: 'Employee1' },
      { value: '2', viewValue: 'Employee2' },
      { value: '3', viewValue: 'Employee3' },
      { value: '4', viewValue: 'Employee4' },
      { value: '5', viewValue: 'Employee5' },
      { value: '6', viewValue: 'Employee6' },
      { value: '7', viewValue: 'Employee7' },
      { value: '7', viewValue: 'Employee8' },
      { value: '7', viewValue: 'Employee9' },
      { value: '7', viewValue: 'Employee10' },
    ];
  

progressBar(){

  if(this.workhrs == 8){
      this.color = "primary";
      this.value=80;
  }
  else if(this.workhrs < 8 ){
    this.color="warn";
    this.value=50;
  }
  else if(this.workhrs > 8 ){
    this.color="accent";
    this.value=100;
  }

  if(this.totalhrs == 40){
    this.color = "primary";
    this.value=80;
}
else if(this.totalhrs < 40 ){
  this.color="warn";
  this.value=50;
}
else if(this.totalhrs > 40 ){
  this.color="accent";
  this.value=100;
}
   
}

    constructor() {
      this.inputsize=500;
     }
  
  
    ngOnInit() {
      // this.windowHeight = window.screen.height - 360;
      this.x= (60*window.screen.height) / 100;
      this.windowHeight = this.x;
      
      console.log("screen height is " + window.screen.height);
    
    }
  
  }
  
