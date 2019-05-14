import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { WebService } from 'src/app/services/webServices.service';

@Component({
  selector: 'app-servicio-web',
  templateUrl: './servicio-web.component.html',
  styleUrls: ['./servicio-web.component.css'],
  providers: [WebService]
})
export class ServicioWebComponent implements OnInit {

  loading: boolean = false;
  listaTablaSelectItem: SelectItem[] = [];
  tablaSel : number;
  
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
        this.webServices.obtenerCountOperador().subscribe(
          data => {
            if(data){

            }
          },error => {
            this.loading = false;
            this.toastrService.error("Error en el servicio de la tabla Operador", "Error");
          }); 
        }

        if(this.tablaSel == 4){
           this.webServices.obtenerCountTipoEstacion().subscribe(
          data => {
            if(data){

            }
          },error => {
            this.loading = false;
            this.toastrService.error("Error en el servicio de la tabla Tipo Estación", "Error");
          }); 
        }
    }
  }

  insertarRegistros(){
    this.loading = true;
    if(this.validarTabla()){

    }else{
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
