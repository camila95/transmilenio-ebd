import { Component, OnInit } from '@angular/core';
import { Estacion } from 'src/app/models/estacion';
import { RESPONSE_OK } from 'src/app/utils/constantes';
import { Router } from '@angular/router';
import { EstacionService } from 'src/app/services/estacion.service';
import { TipoEstacionService } from 'src/app/services/tipoEstacion.service';
import { TipoEstacion } from 'src/app/models/tipoEstacion';
import { Troncal } from 'src/app/models/troncal';
import { TroncalService } from 'src/app/services/troncal.service';

@Component({
  selector: 'app-estacion-crear',
  templateUrl: './estacion-crear.component.html',
  styleUrls: ['./estacion-crear.component.css'],
  providers: [TipoEstacionService, EstacionService, TroncalService]
})
export class EstacionCrearComponent implements OnInit {

    listaTipoEstacion: any[] = [];
    foodsfdf: string = null;//Estacion
    labelPosition = 'NO';
    nombreTitulo = "";
    isCrear: boolean = false;
    estacionSelected: Estacion = new Estacion();
    listaTipoEstaciones: Array<TipoEstacion> = [];
    listaTipoEstaSel: any[] = [];
    listaTroncal: Array<Troncal> = [];
    listaTroncalSel: any[] = [];

    constructor(
        private router: Router,
        private estacionService: EstacionService,
        private tipoEstacionService: TipoEstacionService,
        private troncalService: TroncalService
    ) { }

    ngOnInit() {
        this.obtenerListasTipoEstaciones();
        this.obtenerListaTroncal();
        this.isCrear = false;
        this.nombreTitulo = "Crear Estación";
        //this.nombreTitulo = "Editar Estación";
    }


    private obtenerListasTipoEstaciones() {
        this.tipoEstacionService.getAllTipoEstacion().subscribe(
            data => {
                if (data.status === RESPONSE_OK) {
                    this.listaTipoEstaciones = data.json();
                    this.cargarListaTipoEsta(this.listaTipoEstaSel);
                } else {
                    //this.toastrService.error(data.text, "Error");
                }
            },
            error => {
                //this.toastrService.error(error.text, "Error");
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
                if (data.status === RESPONSE_OK) {
                    this.listaTroncal = data.json();
                    this.cargarListaTroncal(this.listaTroncalSel);
                } else {
                    //this.toastrService.error(data.text, "Error");
                }
            },
            error => {
                //this.toastrService.error(error.text, "Error");
            }
        );
    }
  }

    private cargarListaTroncal(listaSelectItem: any[]) {
        listaSelectItem.push({ label: 'Seleccione', value: 0 });
        for (var i = 0; i < this.listaTroncal.length; i++) {
            listaSelectItem.push({ label: this.listaTroncal[i].nombreZona, value: this.listaTroncal[i].idTroncal });
        }
    }

    private guardarEstacion() {
        if (this.validarDatos()) {
            this.estacionService.createEstacion(this.estacionSelected).subscribe(
                data => {
                    if (data.status === RESPONSE_OK) {
                       // this.toastrService.success("Creación exitosa", "Éxito");
                        this.redireccionar();
                    } else {
                       // this.toastrService.success(data.text, "Error");
                    }
                },
                error => {
                   // this.toastrService.success(error.text, "Error");
                }
            );
        } else {
           // this.toastrService.warning("Camilitaaaaaaaaaaa", "Error");
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
        if (!this.estacionSelected.orden) {
            resultado = false;
        }
        if (!this.estacionSelected.troncal || !this.estacionSelected.troncal.idTroncal) {
            resultado = false;
        }
        return resultado;
    }

    private redireccionar() {
        this.router.navigate(['/']);
    }

}
