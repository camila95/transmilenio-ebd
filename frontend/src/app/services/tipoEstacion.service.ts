import { Injectable } from '@angular/core';
import { Response, RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs';
import { TOKEN, APLICACION_ID, USER_ID, URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root',
})
export class TipoEstacionService {

    constructor(private http: HttpClient) { }

    getAllTipoEstacion() {
        return this.http.get<any>(URL_BASE_API_REST + 'tipo-estaciones/');
    }
}