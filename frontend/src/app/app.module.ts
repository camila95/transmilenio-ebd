import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EstacionComponent } from './components/estacion/estacion.component';
import { FooterComponent } from './components/footer/footer.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { EncabezadoComponent } from './components/encabezado/encabezado.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { EstacionCrearComponent } from './components/estacion/estacion-crear/estacion-crear.component';
import { EstacionConsultarComponent } from './components/estacion/estacion-consultar/estacion-consultar.component';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule, ToastContainerModule  } from 'ngx-toastr';
import { TroncalComponent } from './components/troncal/troncal.component';
import { TroncalCrearComponent } from './components/troncal/troncal-crear/troncal-crear.component';
import { TroncalConsultarComponent } from './components/troncal/troncal-consultar/troncal-consultar.component';
import { GenerarDatosComponent } from './components/cargar-archivo/generar-datos/generar-datos.component';
import { ServicioWebComponent } from './components/cargar-archivo/servicio-web/servicio-web.component';
import { ReportesComponent } from './components/reportes/reportes.component';
import { CargarArchivoComponent } from './components/cargar-archivo/cargar-archivo.component';
import { HttpModule } from '@angular/http';
import { NgxLoadingModule } from 'ngx-loading';
import { DataTablesModule } from 'angular-datatables';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { FilterPipeModule } from 'ngx-filter-pipe';

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
    GenerarDatosComponent,
    ServicioWebComponent,
    ReportesComponent,
    CargarArchivoComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DataTablesModule,
    DropdownModule,
    TableModule,
    DataTablesModule,
    FilterPipeModule,
    NgxLoadingModule.forRoot({}),
    ToastrModule.forRoot({
      timeOut: 7000,
      preventDuplicates: true
    }),
    ToastContainerModule
    
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule { }
