import { Component, OnInit } from '@angular/core';
import { OrganizationService } from './organization.service';

@Component({
  selector: 'app-organization',
  templateUrl: './organization.component.html',
  styleUrls: ['./organization.component.css']
})
export class OrganizationComponent implements OnInit {

  constructor(public organizationService : OrganizationService) { }
groups=[];
domains=[];
final_groups=[];
final_group_count=[];
group_details=[];
mps_count=0;
total_count=0
ngOnInit() {

    this.organizationService.getgroupDetails().subscribe((response) => {
   //   console.log("groups", response);
  //    console.log("values" , Object.keys(response.data));
   this.final_groups = Object.keys(response.data);
   //this.final_group_count = Object.values(response.data);

     
   for(let i=0 ; i<this.final_group_count.length; i++){
     this.mps_count = this.mps_count + this.final_group_count[i];
   }
   console.log("total count" , this.mps_count);
var count=0;
   Object.values(response.data).forEach((item)=>{
    count = 0;
    for (var key in item) {
      count = count + 1;
    }
    this.final_group_count.push(count);
   });

   for(let i=0; i<this.final_groups.length;i++){
     this.group_details.push({
       'name':this.final_groups[i],
       'count':this.final_group_count[i]
     })
   }
  console.log("final" , this.group_details);

this.group_details.forEach((item)=>{
  this.total_count = this.total_count + item.count;
})

  });



    this.organizationService.getDomainDetails().subscribe((response) => {
     this.domains = Object.values(response.data);
     console.log("domains" , this.domains);
    });
    
  }


}
