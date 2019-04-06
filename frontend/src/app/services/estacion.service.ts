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


  getEstacionByTipoEstacion(idTipoEstacion: number) {
    return this.http.get<Response>(URL_BASE_API_REST + 'estaciones/' + idTipoEstacion);
  }


  createEstacion(estacion: Estacion) {
    return this.http.post<Response>(URL_BASE_API_REST + 'estaciones/', estacion);
  }


  updateEstacion(estacion: Estacion) {
    return this.http.put<Response>(URL_BASE_API_REST + 'estaciones/', estacion);
  }


  deleteEstacion(estacion: Estacion) {
    return this.http.delete<Response>(URL_BASE_API_REST + 'estaciones/' + estacion);
  }
}