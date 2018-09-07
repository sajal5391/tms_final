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
export class EditProfileService {

    constructor(private http: HttpClient) {
        //console.log('api url is', SERVER_URL);
    }

    employee = JSON.parse(localStorage.getItem('employeeInfo'));

    //extract the last 4 digits of emp_id
    empID = this.employee.empinfo.emp_id.substr(this.employee.empinfo.emp_id.length - 4);

    //addProjectUrl = SERVER_URL + 'api/project/add';
    //detailsUrl = SERVER_URL + 'api/login/all';
   
    empUrl = SERVER_URL + 'api/employee/allempinfo';
    empSkillUrl = SERVER_URL + 'api/skills/allskillset';
    groupUrl = SERVER_URL + 'api/employee/groups';
    domainUrl = SERVER_URL + 'api/employee/groups/domains';

    /** POST: user data to the server for authentication */

    getEmpDetails(): Observable<any> {
       var  newDetailsUrl = SERVER_URL + 'api/employee/edit/' + this.empID;
        console.log('login api url is', newDetailsUrl);
        return this.http.get<any>(newDetailsUrl);
    }

    editEmpDetails(): Observable<any> {
        console.log('login api url is', this.empUrl);
        return this.http.get<any>(this.empUrl);
    }
    getEmpSkills(): Observable<any> {
        console.log('login api url is', this.empSkillUrl);
        return this.http.get<any>(this.empSkillUrl);
    }
    getgroupDetails(): Observable<any> {
        console.log('login api url is', this.groupUrl);
        return this.http.get<any>(this.groupUrl);
    }
    getDomainDetails(): Observable<any> {
        console.log('login api url is', this.domainUrl);
        return this.http.get<any>(this.domainUrl);
    }

    editDetailsUrl = SERVER_URL + 'api/employee/edit';

	updateDetails(data: any): Observable<any> {
		console.log('approval api url is', this.editDetailsUrl);
		return this.http.post<any>(this.editDetailsUrl, data);
	}
   
}