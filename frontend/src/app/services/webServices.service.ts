import { Injectable } from '@angular/core';
import { URL_BASE_API_REST_MY_SQL } from '../utils/constantes';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class WebService {

  constructor(private http: HttpClient) { }

  obtenerCountOperador(){
    return this.http.get<Response>(URL_BASE_API_REST_MY_SQL + 'masivo/operador/count');
  }
  obtenerCountTipoEstacion(){
    return this.http.get<Response>(URL_BASE_API_REST_MY_SQL + 'masivo/tipo-estacion/count');
  }
}