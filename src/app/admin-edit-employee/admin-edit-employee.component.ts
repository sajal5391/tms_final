import { Component, OnInit } from '@angular/core';
import { AdminEditEmployeeService } from './admin-edit-employee.service';

@Component({
  selector: 'app-admin-edit-employee',
  templateUrl: './admin-edit-employee.component.html',
  styleUrls: ['./admin-edit-employee.component.css']
})
export class AdminEditEmployeeComponent implements OnInit {

  constructor(public adminEditService : AdminEditEmployeeService) { }
  final_groups = [];
  emp_details:any;
  ngOnInit() {

this.adminEditService.getgroupDetails().subscribe((response)=>{
  this.final_groups = Object.keys(response.data);
  console.log("groups" , this.final_groups);
  this.emp_details = Object.values(response.data);
  console.log(this.emp_details[0][0]);

});

  }

}
