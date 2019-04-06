import { Component, OnInit } from '@angular/core';
import { Estacion } from 'src/app/models/estacion';
import { RESPONSE_OK } from 'src/app/utils/constantes';
import { Router } from '@angular/router';
import { EstacionService } from 'src/app/services/estacion.service';
import { TipoEstacionService } from 'src/app/services/tipoEstacion.service';
import { TipoEstacion } from 'src/app/models/tipoEstacion';
import { Troncal } from 'src/app/models/troncal';
import { TroncalService } from 'src/app/services/troncal.service';
import { ToastrService } from 'ngx-toastr';

@Component({
    selector: 'app-estacion-crear',
    templateUrl: './estacion-crear.component.html',
    styleUrls: ['./estacion-crear.component.css'],
    providers: [EstacionService, TipoEstacionService, TroncalService]
})
export class EstacionCrearComponent implements OnInit {

    foodsfdf: string = null;//Estacion
    labelPosition = 'NO';
    nombreTitulo = "";
    isCrear: boolean = false;
    loading: boolean = false;
    listaTipoEstacionSelectItem: any[] = [];
    listaTipoEstaciones: Array<TipoEstacion> = [];
    estacionSelected: Estacion;
    listaTroncalSelectItem: any[] = [];
    listaTroncales: Array<Troncal> = [];
    listaTroncalSel: any[] = [];
    listaTipoEstaSel: any[] = [];
    tipoEstacionSel: string;
    troncalSel: string;
    estacionInicial :string;
    estacionFinal :string;

    constructor(
        private toastrService: ToastrService,
        private router: Router,
        private estacionService: EstacionService,
        private tipoEstacionService: TipoEstacionService,
        private troncalService: TroncalService
    ) { }

    ngOnInit() {
        this.estacionSelected = new Estacion();
        this.obtenerListasTipoEstaciones();
        this.obtenerListaTroncal();
        this.isCrear = false;
        this.nombreTitulo = "Crear Estación";
        //this.nombreTitulo = "Editar Estación";
        this.isCrear = false;
        this.loading = false;
        this.tipoEstacionSel = null;
    }


    private obtenerListasTipoEstaciones() {
        this.tipoEstacionService.getAllTipoEstacion().subscribe(
            data => {
                this.loading = false;
                var getKeysArray = Object.keys(data);
                var getValueArrayTipoEsta = Object.values(data)[0];
                
                if(getValueArrayTipoEsta.length > 0){
                    for (let i = 0; i < getValueArrayTipoEsta.length; i++) {
                        this.listaTipoEstaciones.push(getValueArrayTipoEsta[i]);                       
                    }
                    this.cargarListaTipoEsta(this.listaTipoEstacionSelectItem);
                    this.tipoEstacionSel = this.listaTipoEstacionSelectItem[0].value;
                    this.loading = false;
                } else {
                    this.loading = false;
                    this.toastrService.error("Error en las listas del tipo de estación", "Error");                
                    }       
                },error => {
                    this.loading = false;
                    this.toastrService.error("Error en el servicio", "Error");
            }
        );
    }

    private cargarListaTipoEsta(listaSelectItem: any[]) {
        listaSelectItem.push({ label: 'Seleccione', value: 0 });
        for (var i = 0; i < this.listaTipoEstaciones.length; i++) {
            listaSelectItem.push({ label: this.listaTipoEstaciones[i].nombre, value: this.listaTipoEstaciones[i].idTipoEsta });
        }
    }


    private obtenerListaTroncal() {
        this.troncalService.getAllTroncales().subscribe(
            data => {
                this.loading = true;
                var getKeysListaTroncal = Object.keys(data);
                var getValueArrayTroncal = Object.values(data)[0];
       
                if(getValueArrayTroncal.length > 0){
                    for (let i = 0; i < getValueArrayTroncal.length; i++) {
                       this.listaTroncales.push(getValueArrayTroncal[i]);                       
                    }
                    this.cargarListaTroncal(this.listaTroncalSelectItem);
                    this.troncalSel = this.listaTroncalSelectItem[0].value;
                    this.loading = false;
                } else {
                    this.loading = false;           
                    this.toastrService.error("Error en las listas de troncal", "Error");  
                } 
            }, error => {
                this.loading = false;
                this.toastrService.error("Error en el servicio", "Error");
            }
        );
    }

    private cargarListaTroncal(listaSelectItem: any[]) {
        listaSelectItem.push({ label: 'Seleccione', value: 0 });
        for (var i = 0; i < this.listaTroncales.length; i++) {
            listaSelectItem.push({ label: this.listaTroncales[i].nombreZona, value: this.listaTroncales[i].idTroncal });
        }
    }

    private adicionarVariables(){
        let tipoEstacion = new TipoEstacion();
        tipoEstacion.idTipoEsta = parseInt(this.tipoEstacionSel+"");
        this.estacionSelected.tipoEstacion = tipoEstacion;
        let troncal = new Troncal();
        troncal.idTroncal = parseInt(this.troncalSel+"");
        this.estacionSelected.troncal = troncal;
        if(this.estacionInicial == "SI"){
            this.estacionSelected.estaIncial = 1;
        }else{
            this.estacionSelected.estaIncial = 0;
        }

        if(this.estacionFinal == "SI"){
            this.estacionSelected.estaFinal = 1;
        }else{
            this.estacionSelected.estaFinal = 0;
        }

    }

    private guardarEstacion() {
        if (this.validarDatos()) {
            this.adicionarVariables();
            this.estacionService.createEstacion(this.estacionSelected).subscribe(
                data => {
                    this.loading = true;
                    if(data != null){
                        this.toastrService.success("Creación exitosa", "Éxito");
                        this.loading = false;
                        this.redireccionar();
                    } else {
                        this.loading = false;
                        this.toastrService.error("Error en la respuesta del servicio", "Error");
                    }
                },
                error => {
                    this.loading = false;
                    this.toastrService.error("Error en crear la estación", "Error");
                }
            );
        } else {
            this.loading = false;
            this.toastrService.error("Error de Datos", "Error");
        }
    }

    private validarDatos() {
        let resultado = true;
        if (!this.estacionSelected.nombre) {
            resultado = false;
        }
        if (!this.estacionSelected.localidad) {
            resultado = false;
        }
        if (!this.estacionSelected.direccion) {
            resultado = false;
        }
        if (!this.estacionSelected.orden) {
            resultado = false;
        }
        if (!this.estacionSelected.longitud) {
            resultado = false;
        }
        if (!this.estacionSelected.latitud) {
            resultado = false;
        }
        if(this.tipoEstacionSel == "0"){
            resultado = false;
        }
        if(this.troncalSel == "0"){
            resultado = false;
        }
        if (!this.estacionInicial) {
            resultado = false;
        }
        if(!this.estacionFinal){
            resultado = false;
        }
        return resultado;
    }

    private redireccionar() {
        this.router.navigate(['/']);
    }

}