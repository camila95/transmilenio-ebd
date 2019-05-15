import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GeneracionMasivaService } from 'src/app/services/generacionMasiva.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-generar-datos',
  templateUrl: './generar-datos.component.html',
  styleUrls: ['./generar-datos.component.css'],
  providers: [GeneracionMasivaService]
})
export class GenerarDatosComponent implements OnInit {
  
  public loading = false;
  datoOperador: number;
  datoRuta: number;
  datoHorario: number;
  datoTipoEstacion: number;
  datoTipoParadero: number;
  dato : number;

  constructor(
    private servicesGeneracion: GeneracionMasivaService,
    private toastrService: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loading = true;
    this.datoOperador = 0;
    this.datoRuta = 0;
    this.datoHorario = 0;
    this.datoTipoEstacion = 0;
    this.datoTipoParadero = 0;
    this.dato = 0;
    this.loading = false;
  }

  private redireccionar() {
    this.router.navigate(['/']);
}

private generarDatos(){

  if(this.datoOperador > 0){
    var generacionMasiva = {
      operador: this.datoOperador
    };
    this.servicesGeneracion.createEstacion(generacionMasiva).subscribe(
      data => {
          this.loading = true;
          if(data){
            this.loading = false;
            this.toastrService.success("Se estan generando los " + this.datoOperador + " registros", "Éxito");
            this.datoOperador = 0;
          }
        },error => {
          this.loading = false;
          this.toastrService.error("Error en el servicio", "Error");
          }
    );
  }
}

private generarOperador(dato: number){
  var generacionMasiva = {
    operador: 0,
    ruta: 0,
    horario: 0,
    tipoEstacion: 0,
    tipoParadero: 0
  };
}

}
