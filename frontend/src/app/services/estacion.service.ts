import { Injectable } from '@angular/core';
import { Estacion } from '../models/estacion';
import { URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class EstacionService {

  constructor(private http: HttpClient) { }


  getEstacionByTipoEstacion(idTipoEstacion: number) {
    return this.http.get<any>(URL_BASE_API_REST + 'estaciones/' + idTipoEstacion);
  }


  createEstacion(estacion: Estacion) {
    return this.http.post<any>(URL_BASE_API_REST + 'estaciones/', estacion);
  }


  updateEstacion(estacion: Estacion) {
    return this.http.put<any>(URL_BASE_API_REST + 'estaciones/', estacion);
  }


  deleteEstacion(estacion: Estacion) {
    return this.http.delete<any>(URL_BASE_API_REST + 'estaciones/' + estacion);
  }
}