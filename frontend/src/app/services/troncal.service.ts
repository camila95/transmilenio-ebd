import { Injectable } from '@angular/core';
import { URL_BASE_API_REST } from '../utils/constantes';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root',
})
export class TroncalService {

    constructor(private http: HttpClient) { }

    getAllTroncales() {
        return this.http.get<any>(URL_BASE_API_REST + 'troncales');
    }
}