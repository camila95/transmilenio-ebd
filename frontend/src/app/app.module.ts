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
import { EstacionCrearComponent } from './components/estacion/estacion-crear/estacion-crear.component';
import { EstacionConsultarComponent } from './components/estacion/estacion-consultar/estacion-consultar.component';
import { MaterialModule } from 'src/material.module';

@NgModule({
  declarations: [
    AppComponent,
    EstacionComponent,
    FooterComponent,
    InicioComponent,
    EncabezadoComponent,
    EstacionCrearComponent,
    EstacionConsultarComponent
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
