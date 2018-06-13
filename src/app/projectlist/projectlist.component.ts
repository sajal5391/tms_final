import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-projectlist',
  templateUrl: './projectlist.component.html',
  styleUrls: ['./projectlist.component.css']
})
export class ProjectListComponent implements OnInit {
  today = Date.now();
  public saveUsername:any;
  public saveUsername_osu:any;
  public saveUsername_platform:any;
    windowHeight: any;
    
    x:any;
    size:any;
    commonsize:any;
    inputsize:any;
    smartPhone = [
      { value: '1', viewValue: 'G3 ' },
      { value: '2', viewValue: 'G4 ' },
      { value: '3', viewValue: 'G5 ' },
      { value: '4', viewValue: 'G6 ' },
      {value: '5', viewValue: 'Neo ' },
      { value: '6', viewValue: 'V30 ' },
      { value: '7', viewValue: 'Cv1 ' },
      { value: '7', viewValue: 'Cv3 ' },
      { value: '7', viewValue: 'Cv5 ' },
      { value: '7', viewValue: 'Cv1 ' },
      { value: '7', viewValue: 'Cv3 ' },
      {value: '7', viewValue: 'Cv5 ' },
    ];

    osuProjects = [
      { value: '1', viewValue: 'G3 ' },
      { value: '2', viewValue: 'G4 ' },
      { value: '3', viewValue: 'G5 ' },
      { value: '4', viewValue: 'G6 ' },
      {value: '5', viewValue: 'Neo ' },
      { value: '6', viewValue: 'V30 ' },
      { value: '7', viewValue: 'Cv1 ' },
      { value: '7', viewValue: 'Cv3 ' },
      { value: '7', viewValue: 'Cv5 ' },
      { value: '7', viewValue: 'Cv1 ' },
      { value: '7', viewValue: 'Cv3 ' },
      {value: '7', viewValue: 'Cv5 ' },
    ];

    platformProjects = [
      { value: '1', viewValue: 'ims ' },
      { value: '2', viewValue: 'smart watch' },
      { value: '3', viewValue: 'tv' },
      { value: '4', viewValue: 'vehicle' },
      {value: '5', viewValue: 'android' },
      
    ];

    myProjectList =[
      {id:'', viewValue:''}
    ];
    OSU =[
      {id:'', viewValue:''}
    ];
    Platform =[
      {id:'', viewValue:''}
    ];
  
    

public onSaveUsernameChanged (e , value){
    this.saveUsername = value;
  if(e.checked){
    this.myProjectList.push({ id: '', viewValue:value});
  }
  else{
    this.myProjectList.pop();
  }     
}

public onSaveUsernameChangedOsu (e , value){
  this.saveUsername_osu = value;
if(e.checked){
  this.OSU.push({ id: '', viewValue:value});
}
else{
  this.OSU.pop();
}     
}



public onSaveUsernameChangedPlatform (e , value){
  this.saveUsername_platform = value;
if(e.checked){
  this.Platform.push({ id: '', viewValue:value});
}
else{
  this.Platform.pop();
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
  
