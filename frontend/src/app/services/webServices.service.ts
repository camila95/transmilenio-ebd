import { Injectable } from '@angular/core';
import { URL_BASE_API_REST_MY_SQL, URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { Operador } from '../models/operador';

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

  obtenerDatosOperadorMySQL(){
    return this.http.get<Response>(URL_BASE_API_REST_MY_SQL + '/masivo/operador/');
  }

  obtenerDatosTipoEstacionMySQL(){
    return this.http.get<Response>(URL_BASE_API_REST_MY_SQL + '/masivo/tipo-estacion/');
  }

  insertarDatosOperadorOracle(mapOperador: any){
    return this.http.post<Response>(URL_BASE_API_REST + '/operador/insertar-lista/', mapOperador);
  }

}