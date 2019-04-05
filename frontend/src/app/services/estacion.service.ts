import { Injectable } from '@angular/core';
import { Estacion } from '../models/estacion';
import { URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class EstacionService {

  constructor(private http: HttpClient) { }


  getEstacionByTipoEstacion(idTipoEstacion: number):Observable<any> {
    return this.http.get<any>(URL_BASE_API_REST + 'estaciones/' + idTipoEstacion);
  }


  createEstacion(estacion: Estacion):Observable<any> {
    return this.http.post<any>(URL_BASE_API_REST + 'estaciones/', estacion);
  }


  updateEstacion(estacion: Estacion):Observable<any> {
    return this.http.put<any>(URL_BASE_API_REST + 'estaciones/', estacion);
  }


  deleteEstacion(estacion: Estacion): Observable<any> {
    return this.http.delete<any>(URL_BASE_API_REST + 'estaciones/' + estacion);
  }
}