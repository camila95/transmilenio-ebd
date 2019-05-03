import { Component, OnInit } from '@angular/core';
import { Estacion } from 'src/app/models/estacion';
import { Router, ActivatedRoute } from '@angular/router';
import { EstacionService } from 'src/app/services/estacion.service';
import { TipoEstacionService } from 'src/app/services/tipoEstacion.service';
import { TipoEstacion } from 'src/app/models/tipoEstacion';
import { Troncal } from 'src/app/models/troncal';
import { TroncalService } from 'src/app/services/troncal.service';
import { ToastrService } from 'ngx-toastr';
import {SelectItem} from 'primeng/api';

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
    public loading = false;
    listaTipoEstacionSelectItem: SelectItem[] = [];
    listaTipoEstaciones: Array<TipoEstacion> = [];
    estacionSelected: Estacion;
    listaTroncalSelectItem: any[] = [];
    listaTroncales: Array<Troncal> = [];
    listaTroncalSel: any[] = [];
    listaTipoEstaSel: any[] = [];
    tipoEstacionSel: string;
    troncalSel: string;
    listaEstacionInicialSelectItem: SelectItem[] = [];
    listaEstacionFinalSelectItem: SelectItem[] = [];
    estacionInicial :number;
    estacionFinal :number;
    idEstacionEntrada : number;


    constructor(
        private toastrService: ToastrService,
        private router: Router,
        private estacionService: EstacionService,
        private tipoEstacionService: TipoEstacionService,
        private troncalService: TroncalService,
        private activatedRoute: ActivatedRoute
    ) { }

    ngOnInit() {
        this.loading = true;
        this.tipoEstacionSel = null;
        this.estacionSelected = new Estacion();
        this.cargarListasSiNo();
        this.obtenerEntradas();
        this.asignarNombres();
        this.obtenerListasTipoEstaciones();
        this.obtenerListaTroncal();
        
        if(!this.isCrear){
            this.obtenerDatosEstacion();
        }
    }

    private asignarNombres(){
        if(this.isCrear){
            this.nombreTitulo = "Crear Estación";
        }else{
            this.nombreTitulo = "Editar Estación";
        }
    }

    private obtenerEntradas(){
        this.isCrear = false;
        let sub = this.activatedRoute.params.subscribe(params => {
             let numero = +params['id'];
            if(numero == 0){
                this.isCrear = true;
            }else{
                this.idEstacionEntrada = numero;
            }
          });
    }

    private cargarListasSiNo(){
        this.listaEstacionInicialSelectItem = [];
        this.listaEstacionInicialSelectItem.push({ label: 'Seleccione', value: null });
        this.listaEstacionInicialSelectItem.push({ label:'SI', value: 1 });
        this.listaEstacionInicialSelectItem.push({ label:'NO', value: 0 });
        this.listaEstacionInicialSelectItem.push();

        this.listaEstacionFinalSelectItem = [];
        this.listaEstacionFinalSelectItem.push({ label: 'Seleccione', value: null });
        this.listaEstacionFinalSelectItem.push({ label:'SI', value: 1 });
        this.listaEstacionFinalSelectItem.push({ label:'NO', value: 0 });
    }


    private obtenerListasTipoEstaciones() {
        this.tipoEstacionService.getAllTipoEstacion().subscribe(
            data => {
                this.loading = true;
                var getKeysArray = Object.keys(data);
                var getValueArrayTipoEsta = Object.values(data)[0];
                
                if(getValueArrayTipoEsta.length > 0){
                    for (let i = 0; i < getValueArrayTipoEsta.length; i++) {
                        this.listaTipoEstaciones.push(getValueArrayTipoEsta[i]);                       
                    }
                    this.cargarListaTipoEsta(this.listaTipoEstacionSelectItem);
                    this.tipoEstacionSel = this.listaTipoEstacionSelectItem[0].value;
                    this.estacionSelected.idTipoEstacion = this.listaTipoEstacionSelectItem[0].value;
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
        this.loading = true;
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
                    this.estacionSelected.idTroncal = this.listaTroncalSelectItem[0].value;
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
    this.estacionSelected.estaIncial = this.estacionInicial;
    this.estacionSelected.estaFinal = this.estacionFinal;

    }

    private obtenerDatosEstacion(){
        this.loading = true;
        this.estacionService.getEstacionById(this.idEstacionEntrada).subscribe(
            data => {
              var getKeysArray = Object.keys(data);
              var getValueArrayTipoEsta = Object.values(data)[0];
              
              if(getValueArrayTipoEsta.length > 0){
                  for (let i = 0; i < getValueArrayTipoEsta.length; i++) {
                    this.estacionSelected = getValueArrayTipoEsta[0];                   
                  }
                  this.loading = false;
              } else {
                  this.loading = false;
                  this.toastrService.error("Error consultando la estación", "Error");                
                  }       
              },error => {
                  this.loading = false;
                  this.toastrService.error("Error en el servicio", "Error");
          }
        );
        }

    private guardarEstacion() {
        this.loading = true;
        if (this.validarDatos()) {
            this.adicionarVariables();
            if(this.isCrear){
                this.servicioCrear();
            }else{
                this.servicioEditar();
            }
        } else {
            this.loading = false;
            this.toastrService.error("Error de Datos", "Error");
        }
    }

    private servicioCrear(){
        this.loading = true;
        this.estacionService.createEstacion(this.estacionSelected).subscribe(
            data => {
                if(data != null){
                    this.limpiarVariables();
                    this.loading = false;
                    this.toastrService.success("Creación exitosa", "Éxito");
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
    }

    private servicioEditar(){
        this.loading = true;
        this.estacionService.updateEstacion(this.estacionSelected).subscribe(
            data => {
                if(data != null){
                    this.limpiarVariablesEdicion();
                    this.loading = false;
                    this.toastrService.success("Creación exitosa", "Éxito");
                    this.redireccionarConsultar();
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
        if(!this.estacionSelected.idTipoEstacion){
            resultado = false;
        }
        if(!this.estacionSelected.idTroncal){
            resultado = false;
        }
        if (this.estacionInicial == null) {
            resultado = false;
        }
        if(this.estacionFinal == null){
            resultado = false;
        }
        return resultado;
    }

    private redireccionar() {
        this.router.navigate(['/']);
    }

    private redireccionarConsultar(){
        this.router.navigate(['/estacion/consultar']);
    }

    private limpiarVariables(){
        this.estacionSelected = new Estacion();
        this.estacionInicial  = null;
        this.estacionFinal = null;
    }

    private limpiarVariablesEdicion(){
        this.estacionSelected = new Estacion();
        this.estacionInicial  = null;
        this.estacionFinal = null;
        this.idEstacionEntrada = null;
        this.isCrear = false;
    }

}