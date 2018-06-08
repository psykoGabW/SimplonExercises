import { Component, OnInit } from '@angular/core';
import { MenuService } from '../menu.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private menuService: MenuService) { }

  private selectedMenu: HTMLElement;

  ngOnInit() {
    this.selectedMenu = document.getElementById('HomeMenu');
  }

  getSelectedMenu(): string {
    return this.menuService.selectedMenu;
  }

  setSelectedMenu( e: Event) {
    this.selectedMenu.style.color = '';

    this.selectedMenu = <HTMLElement> e.target;
    this.selectedMenu.style.color = 'white';

    e.preventDefault();
  }

}
