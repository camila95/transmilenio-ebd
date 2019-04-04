import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs';
import { Estacion } from '../models/estacion';
import { TOKEN, APLICACION_ID, USER_ID, URL_BASE_API_REST } from '../utils/constantes';

@Injectable({
  providedIn: 'root'
})
export class EstacionService {

  constructor(private http: Http) { }


  public getEstacionByTipoEstacion(idTipoEstacion: string): Observable<Response> {
    const headers = new Headers({
      'Content-Type': 'application/json; charset=UTF-8',
      'access_token': sessionStorage.getItem(TOKEN),
      'client_id': sessionStorage.getItem(APLICACION_ID),
      'user_id': sessionStorage.getItem(USER_ID)
    });
    const options = new RequestOptions({ headers: headers });
    return this.http.get(URL_BASE_API_REST + 'estacion/buscarEstacion/' + idTipoEstacion, options);
  }


  public createEstacion(estacion: Estacion): Observable<Response> {
    const headers = new Headers({
      'Content-Type': 'application/json; charset=UTF-8',
      'access_token': sessionStorage.getItem(TOKEN),
      'client_id': sessionStorage.getItem(APLICACION_ID),
      'user_id': sessionStorage.getItem(USER_ID)
    });
    const options = new RequestOptions({ headers: headers });
    return this.http.post(URL_BASE_API_REST + 'estacion/crearEstacion/', estacion, options);
  }



  public updateEstacion(estacion: Estacion): Observable<Response> {
    const headers = new Headers({
      'Content-Type': 'application/json; charset=UTF-8',
      'access_token': sessionStorage.getItem(TOKEN),
      'client_id': sessionStorage.getItem(APLICACION_ID),
      'user_id': sessionStorage.getItem(USER_ID)
    });
    const options = new RequestOptions({ headers: headers });
    return this.http.post(URL_BASE_API_REST + 'estacion/crearEstacion/', estacion, options);
  }

  public deleteEstacion(estacion: Estacion): Observable<Response> {
    const headers = new Headers({
      'Content-Type': 'application/json; charset=UTF-8',
      'access_token': sessionStorage.getItem(TOKEN),
      'client_id': sessionStorage.getItem(APLICACION_ID),
      'user_id': sessionStorage.getItem(USER_ID)
    });
    const options = new RequestOptions({ headers: headers });
    return this.http.get(URL_BASE_API_REST + 'estacion/crearEstacion/' + estacion.idEstacion, options);
  }

}
