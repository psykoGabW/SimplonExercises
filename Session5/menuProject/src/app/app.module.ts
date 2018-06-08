import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { BodyComponent } from './body/body.component';
import { FooterComponent } from './footer/footer.component';
import { SelectedMenuService} from './selected-menu.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule, MatCheckbox, MatCheckboxModule } from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    BodyComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatCheckboxModule
  ],
  providers: [SelectedMenuService],
  bootstrap: [AppComponent]
})
export class AppModule { }
