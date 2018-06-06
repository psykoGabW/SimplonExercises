import { Component, OnInit } from '@angular/core';
import { SelectedMenuService } from '../selected-menu.service';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  private currentMenu: string;

  constructor(private menuService: SelectedMenuService) {
    this.currentMenu = this.menuService.getActiveMenu();
  }

  ngOnInit() {
  }

  private getActiveMenu(): string {
    console.log('body.component-getActiveMenu()');
    return this.menuService.getActiveMenu();
  }



}
