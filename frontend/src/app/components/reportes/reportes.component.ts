import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css']
})
export class ReportesComponent implements OnInit {
  estaciones=['estacion1','estacion2','estacion3','estacion4'];
  rutas=['ruta1','ruta2','ruta3','ruta4'];
  constructor() {
   }

  ngOnInit() {
  }

}
