import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { WebService } from 'src/app/services/webServices.service';
import { Operador } from 'src/app/models/operador';
import { TipoEstacion } from 'src/app/models/tipoEstacion';

@Component({
  selector: 'app-servicio-web',
  templateUrl: './servicio-web.component.html',
  styleUrls: ['./servicio-web.component.css'],
  providers: [WebService]
})
export class ServicioWebComponent implements OnInit {

  loading: boolean = false;
  listaTablaSelectItem: SelectItem[] = [];
  listaOperador: Array<Operador> = [];
  listaTipoEstacion: Array<TipoEstacion> = [];
  tablaSel : number;
  lista: any = [];
  numero: number;
  private infoInsertar: any = {
    listaOperadores: null
  }
  
  constructor(
    private webServices: WebService,
    private toastrService: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.obtenerTablas();
  }

  obtenerTablas(){
    this.loading = true;
    this.listaTablaSelectItem = [];
    this.listaTablaSelectItem.push({ label: 'Seleccione', value: 0 });                 
    this.listaTablaSelectItem.push({ label: 'Horario', value: 1 });
    this.listaTablaSelectItem.push({ label: 'Operador', value: 2 });
    this.listaTablaSelectItem.push({ label: 'Ruta', value: 3 });
    this.listaTablaSelectItem.push({ label: 'Tipo Estación', value: 4 });
    this.listaTablaSelectItem.push({ label: 'Tipo Paradero', value: 5 });
    this.tablaSel = this.listaTablaSelectItem[0].value;
    this.loading = false;
  }

  obtenerRegistros(){
    this.loading = true;
    if(this.validarTabla()){
      if(this.tablaSel == 2){
        this.webServices.obtenerDatosOperadorMySQL().subscribe(
          data => {
            if(data){
              this.lista = JSON.parse(data.toString());
              if(this.lista != null){
                this.numero = this.lista.length;
                for (let i = 0; i < this.lista.length; i++) {
                  let dto = this.lista[i];
                  let operador = new Operador();
                  operador.idOperador = dto.idOperador;
                  operador.nombre = dto.nombre;
                  operador.telefono = dto.telefono;
                  operador.direccion = dto.direccion;
                  operador.representante = dto.representante;
                  operador.paginaWeb = dto.pagina_web;
                  this.listaOperador.push(operador);
                }
              }
              this.loading = false;
            }
          },error => {
            this.loading = false;
            this.toastrService.error("Error en el servicio de la tabla Operador", "Error");
          }); 
        }

        if(this.tablaSel == 4){
           this.webServices.obtenerDatosTipoEstacionMySQL().subscribe(
          data => {
            if(data){
              this.lista = JSON.parse(data.toString());
              if(this.lista != null){
                this.numero = this.lista.length;
                for (let i = 0; i < this.lista.length; i++) {
                  let dto = this.lista[i];
                  let tipoEstacion = new TipoEstacion();
                  tipoEstacion.idTipoEsta = dto.idTipoEsta;
                  tipoEstacion.nombre = dto.nombre;
                  this.listaTipoEstacion.push(tipoEstacion);
                }
              }
              this.loading = false;
            }
          },error => {
            this.loading = false;
            this.toastrService.error("Error en el servicio de la tabla Tipo Estación", "Error");
          }); 
        }
    }else{
      this.loading = false;
      this.toastrService.error("Seleccione una tabla", "Error");
    }

  }

  insertarRegistros(){
    this.loading = true;
    if(this.validarTabla()){
      if(this.tablaSel == 2){
        this.infoInsertar.listaOperadores = this.listaOperador;
        this.webServices.insertarDatosOperadorOracle(this.infoInsertar).subscribe(
          data => {
            if(data != null){
                this.tablaSel = 0;
                this.numero = 0;
                this.loading = false;
                this.toastrService.success("Creación exitosa", "Éxito");
            }
          },error => {
            this.loading = false;
            this.toastrService.error("Error en insertar en la tabla Operador", "Error");
          }); 
      }

        if(this.tablaSel == 4){

        }
      } else{
      this.loading = false;
      this.toastrService.error("No se ha seleccionado ninguna tabla", "Error");   
    }

  }

  validarTabla(){
    let resultado = false;
    if(this.tablaSel > 0){
      resultado = true;
    }
    return resultado;
  }
}
