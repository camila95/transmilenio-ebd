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
import { EstacionEditarComponent } from './components/estacion-editar/estacion-editar.component';
import { EstacionConsultarComponent } from './components/estacion-consultar/estacion-consultar.component';
import { EstacionEliminarComponent } from './components/estacion-eliminar/estacion-eliminar.component';
import { CargarArchivoComponent } from './components/cargar-archivo/cargar-archivo.component';
import { TroncalCrearComponent } from './components/troncal-crear/troncal-crear.component';
import { TroncalConsultarComponent } from './components/troncal-consultar/troncal-consultar.component';
import { TroncalEditarComponent } from './components/troncal-editar/troncal-editar.component';
import { TroncalEliminarComponent } from './components/troncal-eliminar/troncal-eliminar.component';

@NgModule({
  declarations: [
    AppComponent,
    EstacionComponent,
    FooterComponent,
    InicioComponent,
    EncabezadoComponent,
    EstacionEditarComponent,
    EstacionConsultarComponent,
    EstacionEliminarComponent,
    CargarArchivoComponent,
    TroncalCrearComponent,
    TroncalConsultarComponent,
    TroncalEditarComponent,
    TroncalEliminarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    DropdownModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule { }
