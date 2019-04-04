import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EstacionComponent } from './components/estacion/estacion.component';
import { FooterComponent } from './components/footer/footer.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { EncabezadoComponent } from './components/encabezado/encabezado.component';
import { DropdownModule } from 'primeng/primeng';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/material.module';
import { TroncalComponent } from './components/troncal/troncal.component';
import { TroncalCrearComponent } from './components/troncal/troncal-crear/troncal-crear.component';
import { TroncalConsultarComponent } from './components/troncal/troncal-consultar/troncal-consultar.component';
import { RutaComponent } from './components/ruta/ruta.component';
import { RutaCrearComponent } from './components/ruta/ruta-crear/ruta-crear.component';
import { RutaConsultarComponent } from './components/ruta/ruta-consultar/ruta-consultar.component';
import { GenerarDatosComponent } from './components/cargar-archivo/generar-datos/generar-datos.component';
import { ServicioWebComponent } from './components/cargar-archivo/servicio-web/servicio-web.component';
import { ReportesComponent } from './components/reportes/reportes.component';

import { CargarArchivoComponent } from './components/cargar-archivo/cargar-archivo.component';
import { EstacionCrearComponent } from './components/estacion/estacion-crear/estacion-crear.component';
import { EstacionConsultarComponent } from './components/estacion/estacion-consultar/estacion-consultar.component';

@NgModule({
  declarations: [
    AppComponent,
    EstacionComponent,
    FooterComponent,
    InicioComponent,
    EncabezadoComponent,
    EstacionCrearComponent,
    EstacionConsultarComponent,
    TroncalComponent,
    TroncalCrearComponent,
    TroncalConsultarComponent,
    RutaComponent,
    RutaCrearComponent,
    RutaConsultarComponent,
    GenerarDatosComponent,
    ServicioWebComponent,
    ReportesComponent,
    CargarArchivoComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    DropdownModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule { }
