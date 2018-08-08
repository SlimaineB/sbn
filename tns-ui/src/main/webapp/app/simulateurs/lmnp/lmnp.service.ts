import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';
import { LmpnIn } from '../../shared';

@Injectable()
export class LmnpService {
    constructor(private http: HttpClient) { }

    simulateLmnp(lmnpIn: LmpnIn): Observable<HttpResponse<any>> {
        return this.http.put(SERVER_API_URL + 'simulateur/lmnp', lmnpIn, {observe: 'response'});
    }

}
