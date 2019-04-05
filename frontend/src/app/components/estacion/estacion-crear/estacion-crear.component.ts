import { Component, OnInit, Input } from '@angular/core';
import { SelectItem, Message } from 'primeng/primeng';
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

  listaTipoEstacion: SelectItem[] = [];
  foodsfdf: string = null;//Estacion
  labelPosition = 'NO';
  nombreTitulo = "";
  isCrear: boolean = false;
  estacionSelected: Estacion = new Estacion();
  private msgs: Message[] = [];
  listaTipoEstaciones: Array<TipoEstacion> = [];
  listaTipoEstaSel: SelectItem[] = [];
  listaTroncal: Array<Troncal> = [];
  listaTroncalSel: SelectItem[] = [];

  constructor(
    private router: Router,
    private estacionService: EstacionService,
    private tipoEstacionService: TipoEstacionService,
    private troncalService: TroncalService) { }

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
          this.showMessage(data.text(), "danger", "Error");
        }
      },
      error => {
        this.showMessage(error.text(), "danger", "Error");
      }
    );
  }

  private cargarListaTipoEsta(listaSelectItem: SelectItem[]) {
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
          this.showMessage(data.text(), "danger", "Error");
        }
      },
      error => {
        this.showMessage(error.text(), "danger", "Error");
      }
    );
  }

  private cargarListaTroncal(listaSelectItem: SelectItem[]) {
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
            this.showMessage("Creación exitosa", "success", "Éxito");
            setTimeout(() => { }, 5000);
            this.redireccionar();
          } else {
            this.showMessage(data.text(), "danger", "Error");
          }

        },
        error => {
          this.showMessage(error.text(), "danger", "Error");
        }
      );
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

  private showMessage(mensaje: string, type: string, sumario: string) {
    this.msgs = [];
    this.msgs.push({ severity: type, summary: sumario, detail: mensaje });
    window.document.getElementById('mensaje').scrollIntoView();
  }

  private redireccionar() {
    this.router.navigate(['/']);
  }
}
