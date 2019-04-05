import { Component, OnInit } from '@angular/core';
import { ReporteService } from 'src/app/services/reporte.service';
import { RESPONSE_OK } from 'src/app/utils/constantes';

@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css'],
  providers: [ReporteService]
})
export class ReportesComponent implements OnInit {

  constructor(private reporteService: ReporteService) { }

  ngOnInit() {
    
  }

  private obtenerReporte(){
    if(this.validarDatos()){
      this.reporteService.getReporte(1,4).subscribe(
        data => {
            if (data.status === RESPONSE_OK) {
              
            }
          },
          error => {
              //this.toastrService.error(error.text, "Error");
          }
      );
     }
  }

  private validarDatos(){
    let resultado = true;
    if(resultado){

    }
    return resultado;
  }

}
