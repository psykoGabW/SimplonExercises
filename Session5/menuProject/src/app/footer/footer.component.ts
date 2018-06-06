import { Component, OnInit } from '@angular/core';
import { SelectedMenuService } from '../selected-menu.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  currentMenu: string;

  constructor(private menuService: SelectedMenuService) {
  }

  ngOnInit() {
    this.menuService.menuSelection.subscribe(
      (menu: string) => { this.currentMenu = menu; },
      (error) => { console.log(error); }
    );
  }

}
