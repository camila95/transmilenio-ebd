import { TipoEstacion } from './tipoEstacion';
import { Troncal } from './troncal';


export class Estacion {
    public idEstacion: number;
    public nombre: string;
    public direccion: number;
    public localidad: string;
    public latitud: string;
    public longitud: string;
    public orden: number;
    public estaFinal: number;
    public estaIncial: number;
    public idTroncal: number;
    public idTipoEstacion: number;

    constructor() { }

}