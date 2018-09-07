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
export class ApproveCmsService {
s
    constructor(private http: HttpClient) {
        //console.log('api url is', SERVER_URL)s;
    }

    employee = JSON.parse(localStorage.getItem('employeeInfo'));
    //extract the last 4 digits of emp_id
    empEmail = this.employee.empinfo.emp_email;

    //addProjectUrl = SERVER_URL + 'api/project/add';
    tempEmail = "TMS11@lge.com";
    

    /** POST: user data to the server for authentication */
    employeeListUrl = SERVER_URL + 'api/employee/compare/';

    getEmpDetails(id:any): Observable<any> {
      var empDetailsUrl = SERVER_URL + 'api/employee/compare/' + id;
        return this.http.get<any>(empDetailsUrl);
    }


	employeeList(): Observable<any> {
		console.log('approval api url is', this.employeeListUrl);
		return this.http.post<any>(this.employeeListUrl, "{'emp_email':'"+this.tempEmail+"'}" );
    }
    
    editDetailsUrl = SERVER_URL + 'api/employee/edit';

	sendResponse(response: any): Observable<any> {
		console.log('approval api url is', this.editDetailsUrl);
		return this.http.post<any>(this.editDetailsUrl, response);
	}
   
   
}