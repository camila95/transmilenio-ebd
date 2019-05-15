import { Injectable } from '@angular/core';
import { URL_BASE_API_REST_MY_SQL } from '../utils/constantes';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class GeneracionMasivaService {

  constructor(private http: HttpClient) { }

  createEstacion(datoGenerar: any) {
    return this.http.post<Response>(URL_BASE_API_REST_MY_SQL + 'masivo/generar-registros/', datoGenerar);
  }
}