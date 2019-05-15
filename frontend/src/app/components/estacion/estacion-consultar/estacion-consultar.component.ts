import { Component, OnInit, ViewChild } from '@angular/core';
import { TipoEstacionService } from 'src/app/services/tipoEstacion.service';
import { TipoEstacion } from 'src/app/models/tipoEstacion';
import { ToastrService } from 'ngx-toastr';
import { EstacionService } from 'src/app/services/estacion.service';
import { Estacion } from 'src/app/models/estacion';
import { SelectionModel, DataSource } from '@angular/cdk/collections';
import { Router, ActivatedRoute } from '@angular/router';
import { SelectItem } from 'primeng/api';

@Component({
  selector: 'app-estacion-consultar',
  templateUrl: './estacion-consultar.component.html',
  styleUrls: ['./estacion-consultar.component.css'],
  providers: [TipoEstacionService, EstacionService]
})
export class EstacionConsultarComponent implements OnInit {

  loading: boolean = false;
  listaTipoEstacionSelectItem: any[] = [];
  listaTipoEstaciones: Array<TipoEstacion> = [];
  tipoEstacionSel: string;
  listaEstaciones: Array<Estacion> = [];
  estacionSelect = new Estacion();
  estacionDelete : number;
  public rowsOnPage = 10;
    
    constructor(
      private toastrService: ToastrService,
      private tipoEstacionService: TipoEstacionService,
      private estacionService: EstacionService,
      private router: Router,
      private activatedRoute: ActivatedRoute
      ) { }

  ngOnInit() {
    this.tipoEstacionSel = null;
    this.obtenerListasTipoEstaciones();    
  }

  private obtenerListasTipoEstaciones() {
    this.loading = true;
    this.tipoEstacionService.getAllTipoEstacion().subscribe(
        data => {
            var getKeysArray = Object.keys(data);
            var getValueArrayTipoEsta = Object.values(data)[0];
            
            if(getValueArrayTipoEsta.length > 0){
                for (let i = 0; i < getValueArrayTipoEsta.length; i++) {
                    this.listaTipoEstaciones.push(getValueArrayTipoEsta[i]);         
                }

                this.listaTipoEstacionSelectItem = [];
                this.listaTipoEstacionSelectItem.push({ label: 'Seleccione', value: 0 });
                for (var i = 0; i < this.listaTipoEstaciones.length; i++) {
                  this.listaTipoEstacionSelectItem.push({ label: this.listaTipoEstaciones[i].nombre, value: this.listaTipoEstaciones[i].idTipoEsta });
                }

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


private obtenerEstacionByTipoEstacion(){
  this.loading = true;
  let idTipoEstacion = parseInt(this.tipoEstacionSel+"");
  this.estacionService.getEstacionByTipoEstacion(idTipoEstacion).subscribe(
    data => {
      var getKeysArray = Object.keys(data);
      var getValueArrayTipoEsta = Object.values(data)[0];
      
      this.listaEstaciones = [];
      if(getValueArrayTipoEsta.length > 0){
          for (let i = 0; i < getValueArrayTipoEsta.length; i++) {
            this.listaEstaciones.push(getValueArrayTipoEsta[i]);                       
          }
          this.loading = false;
      } else {
          this.loading = false;
          this.toastrService.error("Error en las listas de las estaciones", "Error");                
          }       
      },error => {
          this.loading = false;
          this.toastrService.error("Error en el servicio", "Error");
  }
);
}

private editar(idEstacion: number){
  this.router.navigate(['/estacion/administrar/'+idEstacion]);
}

private eliminar(idEstacion: number){
  this.estacionDelete = idEstacion;
  document.getElementById("buttonContinuar").click();
}

private aceptar(){
  this.loading = true;
  this.estacionService.deleteEstacion(this.estacionDelete).subscribe(
    data => {
        if(data){
          this.limpiarDatos();
          this.loading = false;
          this.toastrService.success("Eliminación exitosa", "Éxito");
        }                            
      },error => {
          this.loading = false;
          this.toastrService.error("Error en el servicio", "Error");
  }
);
}

private limpiarDatos(){
  this.listaEstaciones = [];
  this.estacionDelete = null;
  this.tipoEstacionSel = this.listaTipoEstacionSelectItem[0].value;
}


}
