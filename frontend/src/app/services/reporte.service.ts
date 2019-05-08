import { Injectable } from '@angular/core';
import { Estacion } from '../models/estacion';
import { URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ReporteService {

  constructor(private http: HttpClient) { }

  getReporteRutaAlimen(idEstacion: number, idRutaAlimen: number) {
    return this.http.get<Response>(URL_BASE_API_REST + 'reporte/ruta-alimentadora/' + idEstacion + "/" + idRutaAlimen);
  }

  getReporteRutaTroncal(idEstacion: number, idSentido: number) {
    return this.http.get<Response>(URL_BASE_API_REST + 'reporte/ruta-troncal/' + idEstacion + "/" + idSentido);
  }

}