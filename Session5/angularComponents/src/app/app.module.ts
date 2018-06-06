import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserDisplayComponent } from './user-display/user-display.component';

import { UserService } from './user.service';


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserDisplayComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [UserService], // Déclaration du service user pour utilisation
  bootstrap: [AppComponent]
})
export class AppModule { }
