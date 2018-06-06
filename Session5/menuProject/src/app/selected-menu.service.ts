import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SelectedMenuService {

  private menus = ['Menu 1', 'Menu 2', 'Menu 3'];
  private currentMenu = 'None';

  private selectionSubject = new Subject<string>();

  public menuSelection = this.selectionSubject.asObservable();

  getMenus(): string[] {
    return this.menus;
  }

  setCurrentMenu(menu: string) {
    this.currentMenu = menu;
    this.selectionSubject.next(this.currentMenu);
  }

  getActiveMenu(): string {
    return this.currentMenu;
  }

}
