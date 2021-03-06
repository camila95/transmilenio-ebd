import { Component, OnInit } from '@angular/core';
import { ReporteService } from 'src/app/services/reporte.service';
import { RESPONSE_OK } from 'src/app/utils/constantes';
import { SelectItem } from 'primeng/api';
import { Estacion } from 'src/app/models/estacion';
import { ToastrService } from 'ngx-toastr';
import { EstacionService } from 'src/app/services/estacion.service';
import { Router, ActivatedRoute } from '@angular/router';
import { saveAs } from 'file-saver';
import { ConsultaRutaTroncalDTO } from 'src/app/models/dto/consultaRutaTroncalDTO';
import { ExcelService } from 'src/app/services/excel.service';

@Component({
    selector: 'app-reporte-ruta-troncal',
    templateUrl: './reporte-ruta-troncal.component.html',
    styleUrls: ['./reporte-ruta-troncal.component.css'],
    providers: [EstacionService, ReporteService, ExcelService]
  })
  export class ReporteRutaTroncalComponent implements OnInit {

    loading: boolean = false;
    listaEstaciones: Array<Estacion> = [];
    listaEstacionSelectItem: SelectItem[] = [];
    estacionSel : number;
    listaSentidoSelectItem: SelectItem[] = [];
    sentidoSel: number;
    listaReporteAlimen : Array<ConsultaRutaTroncalDTO> = [];

    constructor(
      private  reporteService: ReporteService,
      private toastrService: ToastrService,
      private estacionService: EstacionService,
      private router: Router,
      private activatedRoute: ActivatedRoute,
      private excelService:ExcelService) {
    }


    ngOnInit(): void {
      this.loading = true;
      this.obtenerListasEstaciones();
      this.obtenerListasSentido();
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

  private obtenerListasSentido(){
    this.loading = true;  
    this.listaSentidoSelectItem = [];
    this.listaSentidoSelectItem.push({ label: 'Seleccione', value: 0 });
    this.listaSentidoSelectItem.push({ label: 'Norte', value: 1 });
    this.listaSentidoSelectItem.push({ label: 'Sur', value: 2 });
    this.listaSentidoSelectItem.push({ label: 'Este', value: 3 });
    this.listaSentidoSelectItem.push({ label: 'Oeste', value: 4 });
    this.listaSentidoSelectItem.push({ label: 'Sureste', value: 5 });
    this.listaSentidoSelectItem.push({ label: 'Suroeste', value: 6 });
    this.listaSentidoSelectItem.push({ label: 'Noreste', value: 7 });
    this.listaSentidoSelectItem.push({ label: 'Noroeste', value: 8 });

    this.sentidoSel = this.listaSentidoSelectItem[0].value;
    this.loading = false;    
  }

    private redireccionar() {
      this.router.navigate(['/']);
    }

    private obtenerReporte(){
      if(this.validarDatos()){
        this.reporteService.getReporteRutaTroncal(this.estacionSel,this.sentidoSel).subscribe(
          data => {
            var getKeysArray = Object.keys(data);
            var getValueArrayReporteRutaAlimen = Object.values(data)[0];
      
            this.listaReporteAlimen = []; 
            if(getValueArrayReporteRutaAlimen.length > 0){
              for (let i = 0; i < getValueArrayReporteRutaAlimen.length; i++) {
                this.listaReporteAlimen.push(getValueArrayReporteRutaAlimen[i]);         
              }

              this.excelService.exportAsExcelFile(this.listaReporteAlimen, 'rutas-Troncales');

              this.loading = false;
            } else {
              this.loading = false;
              this.toastrService.error("No se encontraron registros", "Error");                
              }  
          },error => {
            this.loading = false;
            this.toastrService.error("Error en el servicio", "Error");
          });
       }
    }

  
    private validarDatos(){
      let resultado = false;
      if(this.estacionSel > 0 && this.sentidoSel > 0){
        resultado = true;
      }
      return resultado;
    }

  }