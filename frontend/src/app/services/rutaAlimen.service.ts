import { Injectable } from '@angular/core';
import { URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class RutaAlimenService {

  constructor(private http: HttpClient) { }

  getAllRutaAlimen(idEstacion: number){
    return this.http.get<Response>(URL_BASE_API_REST + 'ruta-alimen/'+idEstacion);
  }

}