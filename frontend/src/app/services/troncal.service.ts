import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs';
import { Estacion } from '../models/estacion';
import { TOKEN, APLICACION_ID, USER_ID, URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient } from '@angular/common/http';


@Injectable({
    providedIn: 'root',
})
export class TroncalService {

    constructor(private http: HttpClient) { }


    public getAllTroncales():any {
        const headers = new Headers({
            'Content-Type': 'application/json; charset=UTF-8',
            'access_token': sessionStorage.getItem(TOKEN),
            'client_id': sessionStorage.getItem(APLICACION_ID),
            'user_id': sessionStorage.getItem(USER_ID)
        });
        const options: any = ({ headers: headers });
        return this.http.get(URL_BASE_API_REST + 'troncales', options);
    }


}