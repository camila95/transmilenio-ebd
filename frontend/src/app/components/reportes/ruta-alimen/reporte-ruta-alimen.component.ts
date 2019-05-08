import { Component, OnInit } from '@angular/core';
import { ReporteService } from 'src/app/services/reporte.service';
import { RESPONSE_OK } from 'src/app/utils/constantes';
import { SelectItem } from 'primeng/api';
import { Estacion } from 'src/app/models/estacion';
import { RutaAlimen } from 'src/app/models/rutaAlimen';
import { ToastrService } from 'ngx-toastr';
import { EstacionService } from 'src/app/services/estacion.service';
import { Router, ActivatedRoute } from '@angular/router';
import { RutaAlimenService } from 'src/app/services/rutaAlimen.service';
import { ConsultaRutaAlimenDTO } from 'src/app/models/dto/consultaRutaAlimenDTO';

@Component({
    selector: 'app-reporte-ruta-alimen',
    templateUrl: './reporte-ruta-alimen.component.html',
    styleUrls: ['./reporte-ruta-alimen.component.css'],
    providers: [EstacionService, RutaAlimenService, ReporteService]
  })
  export class ReporteRutaAlimenComponent implements OnInit {

    loading: boolean = false;
    listaEstaciones: Array<Estacion> = [];
    listaEstacionSelectItem: SelectItem[] = [];
    estacionSel : number;
    isHabilitada : boolean = false;
    listaRutaAlimen: Array<RutaAlimen> = [];
    listaRutaAlimenSelectItem: SelectItem[] = [];
    rutaAlimenSel : number;
    listaReporteAlimen : Array<ConsultaRutaAlimenDTO> = [];

    constructor(
      private toastrService: ToastrService,
      private estacionService: EstacionService,
      private rutaAlimenService: RutaAlimenService,
      private  reporteService: ReporteService,
      private router: Router,
      private activatedRoute: ActivatedRoute
      ) { }

    ngOnInit(): void {
      this.loading = true;
      this.isHabilitada = false;
      this.obtenerListasEstaciones();
    }



    private obtenerListasEstaciones() {
      this.loading = true;
      this.estacionService.getAllEstaciones().subscribe(
          data => {
              var getKeysArray = Object.keys(data);
              var getValueArrayEsta = Object.values(data)[0];
              
              if(getValueArrayEsta.length > 0){
                  for (let i = 0; i < getValueArrayEsta.length; i++) {
                      this.listaEstaciones.push(getValueArrayEsta[i]);         
                  }
  
                  this.listaEstacionSelectItem = [];
                  this.listaEstacionSelectItem.push({ label: 'Seleccione', value: 0 });
                  for (var i = 0; i < this.listaEstaciones.length; i++) {
                    this.listaEstacionSelectItem.push({ label: this.listaEstaciones[i].nombre, value: this.listaEstaciones[i].idEstacion });
                  }
  
                  this.estacionSel = this.listaEstacionSelectItem[0].value;
                  this.loading = false;
              } else {
                  this.loading = false;
                  this.toastrService.error("Error en las listas de estación", "Error");                
                  }       
              },error => {
                  this.loading = false;
                  this.toastrService.error("Error en el servicio", "Error");
          }
      );
  }


  private obtenerListasRutaAlimen() {
    this.loading = true;
    if(this.estacionSel > 0){
    this.rutaAlimenService.getAllRutaAlimen(this.estacionSel).subscribe(
        data => {
            var getKeysArray = Object.keys(data);
            var getValueArrayRutaAlimen = Object.values(data)[0];
            
            this.listaRutaAlimen = []; 
            if(getValueArrayRutaAlimen.length > 0){
                for (let i = 0; i < getValueArrayRutaAlimen.length; i++) {
                    this.listaRutaAlimen.push(getValueArrayRutaAlimen[i]);         
                }

                this.listaRutaAlimenSelectItem = [];
                this.listaRutaAlimenSelectItem.push({ label: 'Seleccione', value: 0 });
                for (var i = 0; i < this.listaRutaAlimen.length; i++) {
                  this.listaRutaAlimenSelectItem.push({ label: this.listaRutaAlimen[i].nombre, value: this.listaRutaAlimen[i].idRutaAlimen });
                }

                this.rutaAlimenSel = this.listaRutaAlimenSelectItem[0].value;
                this.isHabilitada = true;
                this.loading = false;
            } else {
                this.loading = false;
                this.toastrService.error("Error en las listas de las rutas alimentadoras", "Error");                
                }       
            },error => {
                this.loading = false;
                this.toastrService.error("Error en el servicio", "Error");
        }
    );
  }else{
    this.loading = false;
  }
}

private redireccionar() {
  this.router.navigate(['/']);
}

private obtenerReporte(){
  this.loading = true;
  this.reporteService.getReporteRutaAlimen(this.estacionSel, this.rutaAlimenSel).subscribe(
    data => {
      var getKeysArray = Object.keys(data);
      var getValueArrayReporteRutaAlimen = Object.values(data)[0];

      this.listaReporteAlimen = []; 
      if(getValueArrayReporteRutaAlimen.length > 0){
        for (let i = 0; i < getValueArrayReporteRutaAlimen.length; i++) {
          this.listaReporteAlimen.push(getValueArrayReporteRutaAlimen[i]);         
        }
        this.loading = false;
      } else {
        this.loading = false;
        this.toastrService.error("Error en las listas de las rutas alimentadoras", "Error");                
        }  
    },error => {
      this.loading = false;
      this.toastrService.error("Error en el servicio", "Error");
}
);

 }

  }