import { Injectable } from '@angular/core';
import { URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root',
})
export class TroncalService {

    constructor(private http: HttpClient) { }

    getAllTroncales() : Observable<any>{
        return this.http.get<any>(URL_BASE_API_REST + 'troncales');
    }
}
