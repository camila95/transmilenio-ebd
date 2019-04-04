import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs';
import { Estacion } from '../models/estacion';
import { TOKEN, APLICACION_ID, USER_ID, URL_BASE_API_REST } from '../utils/constantes';

@Injectable({
    providedIn: 'root',
})
export class TipoEstacionService {

    constructor(private http: Http) { }


    public getAllTipoEstacion(): Observable<Response> {
        const headers = new Headers({
            'Content-Type': 'application/json; charset=UTF-8',
            'access_token': sessionStorage.getItem(TOKEN),
            'client_id': sessionStorage.getItem(APLICACION_ID),
            'user_id': sessionStorage.getItem(USER_ID)
        });
        const options = new RequestOptions({ headers: headers });
        return this.http.get(URL_BASE_API_REST + 'tipoEstacion/getAll', options);
    }


}