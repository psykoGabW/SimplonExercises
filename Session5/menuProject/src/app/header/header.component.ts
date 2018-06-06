import { Component, OnInit } from '@angular/core';
import { SelectedMenuService } from '../selected-menu.service';
import { debuglog, isNullOrUndefined } from 'util';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private static readonly ACTIVE_STYLE = 'activeMenu';
  private static readonly INACTIVE_STYLE = 'inactiveMenu';

  myMenus: string[];

  private selectedItem: HTMLElement;

  constructor(private menuService: SelectedMenuService) {
    this.myMenus = menuService.getMenus();
    this.selectedItem = null;
  }

  ngOnInit() {
  }


  setMenu(menu: string, e: Event) {
    const newSelectedItem = <HTMLElement>e.target;
    if (!isNullOrUndefined(newSelectedItem)) {
      if (this.selectedItem !== null) {
        this.selectedItem.className = HeaderComponent.INACTIVE_STYLE;
      }
      this.selectedItem = newSelectedItem;
      this.selectedItem.className = HeaderComponent.ACTIVE_STYLE;
    }
    e.preventDefault();
    this.menuService.setCurrentMenu(menu);
  }


}
