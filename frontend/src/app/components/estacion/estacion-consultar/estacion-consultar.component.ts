import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-estacion-consultar',
  templateUrl: './estacion-consultar.component.html',
  styleUrls: ['./estacion-consultar.component.css']
})
export class EstacionConsultarComponent implements OnInit {
columnas = ['nombre', 'años','direccion'];
datos1=['omar', '10','12'];
datos2=['omar2', '20','12'];
datos3=['omar3', '30','12'];
  constructor() { }

  ngOnInit() {
  }

}
