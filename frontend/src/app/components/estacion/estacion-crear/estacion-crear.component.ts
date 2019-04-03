import { Component, OnInit } from '@angular/core';
import { SelectItem, Message } from 'primeng/primeng';
import { Estacion } from 'src/app/models/estacion';
import { RESPONSE_OK } from 'src/app/utils/constantes';
import { EstacionService } from 'src/app/services/estacion.service';
import { Router } from '@angular/router';

export interface Food {
  label: string;
  value: number;
}

@Component({
  selector: 'app-estacion-crear',
  templateUrl: './estacion-crear.component.html',
  styleUrls: ['./estacion-crear.component.css'],
  providers: [EstacionService]
})
export class EstacionCrearComponent implements OnInit {

  listaTipoEstacion: SelectItem[] = [];
  foodsfdf: string = null;//Estacion
  foods: Food[] = [];
  labelPosition = 'NO';
  nombreTitulo = "";
  isCrear: boolean = false;
  estacion: Estacion;
  private msgs: Message[] = [];

  constructor(private estacionService: EstacionService, private router: Router) { }

  ngOnInit() {
    //realizar consulta
    this.foods.push({ label: "Seleccionar", value: 0 });
    this.foods.push({ label: "Cosa1", value: 1 });
    this.foods.push({ label: "Cosa2", value: 2 });
    this.isCrear = false;
    this.nombreTitulo = "Crear Estación";
    this.estacion = new Estacion;
    //this.nombreTitulo = "Editar Estación";
  }

  private guardarEstacion() {
    if (this.validarDatos()) {
      this.estacionService.createEstacion(this.estacion).subscribe(
        data => {
          if (data.status === RESPONSE_OK) {
            this.showMessage("Creación exitosa", "success", "Éxito");
            setTimeout(() => { }, 5000);
            this.redireccionar();
          } else {
            this.showMessage(data.text(), "error", "Error");
          }

        },
        error => {
          this.showMessage(error.text(), "error", "Error");
        }
      );
    }
  }

  private validarDatos() {
    let resultado = true;
    if (!this.estacion.nombre) {
      resultado = false;
    }
    if (!this.estacion.localidad) {

    }
    if (!this.estacion.orden) {

    }
    if (!this.estacion.troncal && !this.estacion.troncal.idTroncal) {

    }
    return resultado;
  }

  private showMessage(mensaje: string, severidad: string, sumario: string) {
    this.msgs = [];
    this.msgs.push({ severity: severidad, summary: sumario, detail: mensaje });
    window.document.getElementById('mensaje').scrollIntoView();
  }

  private redireccionar() {
    this.router.navigate(['/']);
  }

}
