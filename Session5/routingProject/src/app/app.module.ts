import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BnpSuperNavComponent } from './bnp-super-nav/bnp-super-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule } from '@angular/material';

import { RouterModule, Routes } from '@angular/router';
import { BioComponent } from './bio/bio.component';
import { RealisationsComponent } from './realisations/realisations.component';
import { ContactsComponent } from './contacts/contacts.component';

import { FlexLayoutModule } from '@angular/flex-layout';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DisplayRealisationDetailsComponent } from './display-realisation-details/display-realisation-details.component';

const routes: Routes = [
  {path: 'bio', component: BioComponent},
  {path: 'realisations/:id', component: DisplayRealisationDetailsComponent},
  {path: 'realisations', component: RealisationsComponent},
  {path: 'contacts', component: ContactsComponent},
  {path: '', redirectTo: 'bio', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent}

];

@NgModule({
  declarations: [
    AppComponent,
    BnpSuperNavComponent,
    BioComponent,
    RealisationsComponent,
    ContactsComponent,
    PageNotFoundComponent,
    DisplayRealisationDetailsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FlexLayoutModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
