import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { ApprovalService } from './approval.service';


@Injectable()
export class ApprovalResolve implements Resolve<any> {
    constructor(private approvalService: ApprovalService) { }

    resolve(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<any> | Promise<any> | any {

        return this.approvalService.loadApprovalEffort();
    }
}

