import { Component, OnInit, ViewChild } from '@angular/core';
import { TipoEstacionService } from 'src/app/services/tipoEstacion.service';
import { TipoEstacion } from 'src/app/models/tipoEstacion';
import { ToastrService } from 'ngx-toastr';
import { EstacionService } from 'src/app/services/estacion.service';
import { Estacion } from 'src/app/models/estacion';
import { SelectionModel, DataSource } from '@angular/cdk/collections';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { Router } from '@angular/router';

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

  public displayedColumns: string[] = ['Id','nombre', 'Dirección', 'Localidad', 'Opciones'];
    public dataSource = new MatTableDataSource<Estacion>();
    //public dataSource : Estaciones[];
    public selection = new SelectionModel<Estacion>(true, []);
    filtroAdv:boolean = false;
    estacionSelect = new Estacion();
    estacionDelete = new Estacion();
    
    @ViewChild(MatPaginator) paginator: MatPaginator;
    
    constructor(
      private toastrService: ToastrService,
      private tipoEstacionService: TipoEstacionService,
      private estacionService: EstacionService,
      private router: Router
      ) { 
        //const users = Array.from({length: 100}, (_, k) => createNewEstacion(k + 1));
       this.dataSource = new MatTableDataSource<Estacion>();
       
  }

  ngOnInit() {
    this.tipoEstacionSel = null;
    this.dataSource.paginator = this.paginator;
    this.obtenerListasTipoEstaciones();
    this.table();
    
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

private obtenerEstacionByTipoEstacion(){
  let idTipoEstacion = parseInt(this.tipoEstacionSel+"");
  this.estacionService.getEstacionByTipoEstacion(idTipoEstacion).subscribe(
    data => {
      this.loading = false;
      var getKeysArray = Object.keys(data);
      var getValueArrayTipoEsta = Object.values(data)[0];
      
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

private table(){
  this.dataSource = new MatTableDataSource<Estacion>(this.listaEstaciones);
                    if (this.listaEstaciones.length > 0 && !this.filtroAdv) {
                        this.dataSource.paginator = this.paginator;
                        this.estacionSelect = this.listaEstaciones[0];
                    }
}

private editar(idEstacion: number){
  //this.router.navigate(['/estacion/administrar/'+idEstacion]);
  this.router.navigate(['/estacion/administrar/']);
  //falta
}

private eliminar(estacion: Estacion){
  this.estacionDelete = estacion;
  this.aceptar();
}

private aceptar(){
  console.log("ACEPTO ELIMINAR "+this.estacionDelete)
  this.estacionService.deleteEstacion(this.estacionDelete.idEstacion).subscribe(
    data => {
        if(data){
          this.loading = true;
          console.log("***elimino**");
          this.obtenerListasTipoEstaciones();
          this.loading = false;
        }                     
       
       
      },error => {
          this.loading = false;
          this.toastrService.error("Error en el servicio", "Error");
  }
);
}


}
