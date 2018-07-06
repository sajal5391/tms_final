import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_URL } from '../shared/config';

@Injectable()
export class ApprovalService {

    constructor(private http: HttpClient) { }

    employee = JSON.parse(localStorage.getItem('employeeInfo'));    
    //emp_email_ID = this.employee.empinfo.emp_email;

    //ApprovaltUrl = SERVER_URL + 'api/effort/' + this.empID;
    loadApprovalEffortUrl = SERVER_URL + 'api/approval/' + "chetan.lavti";


    loadApprovalEffort(): Observable<any> {
        console.log('logEffort api url is', this.loadApprovalEffortUrl);
        return this.http.get<any>(this.loadApprovalEffortUrl);
    }  

    changeStatus(arr: any): Observable<any> {
        var logEffortUrl = SERVER_URL + 'api/approval/update';
        console.log('logEffort api url is', logEffortUrl);
        return this.http.post<any>(logEffortUrl, arr);
    }   
}