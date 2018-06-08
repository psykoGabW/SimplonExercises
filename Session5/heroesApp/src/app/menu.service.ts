import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private _selectedMenu = '';

  private menus: Map<string, string>;

  constructor() { }

  set selectedMenu(menu: string) {
    this._selectedMenu = menu;
  }

  get selectedMenu(): string {
    return this._selectedMenu;
  }
}
