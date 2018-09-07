import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_URL } from '../shared/config';

export interface LoginUser {
    username: string,
    password: string
}

const httpOptions = {
    headers: new HttpHeaders({
        'content-type': 'text/plain'
    })
};
@Injectable()
export class AdminEditEmployeeService {

    constructor(private http: HttpClient) {
        //console.log('api url is', SERVER_URL);
    }

   // employee = JSON.parse(localStorage.getItem('employeeInfo'));

    //extract the last 4 digits of emp_id
   // empID = this.employee.empinfo.emp_id.substr(this.employee.empinfo.emp_id.length - 4);

    //addProjectUrl = SERVER_URL + 'api/project/add';
    detailsUrl = SERVER_URL + 'api/login/all';
    empUrl = SERVER_URL + 'api/employee/allempinfo';
    empSkillUrl = SERVER_URL + 'api/skills/allskillset';
    groupUrl = SERVER_URL + 'api/employee/groups';
    domainUrl = SERVER_URL + 'api/employee/groups/domains';

    /** POST: user data to the server for authentication */
    getgroupDetails(): Observable<any> {
        console.log('login api url is', this.groupUrl);
        return this.http.get<any>(this.groupUrl);
    }
    getDomainDetails(): Observable<any> {
        console.log('login api url is', this.domainUrl);
        return this.http.get<any>(this.domainUrl);
    }
}