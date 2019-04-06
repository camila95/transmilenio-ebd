import { Injectable } from '@angular/core';
import { URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { Http, Response, RequestOptions, Headers, URLSearchParams } from '@angular/http';

@Injectable({
    providedIn: 'root',
})
export class TroncalService {

    constructor(private http: HttpClient) { }

    getAllTroncales() {
        return this.http.get<Response>(URL_BASE_API_REST + 'troncales/');
    }
}
