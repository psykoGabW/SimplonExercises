import { Component, OnInit } from '@angular/core';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'app-user-display',
  templateUrl: './user-display.component.html',
  styleUrls: ['./user-display.component.css']
})
export class UserDisplayComponent implements OnInit {

  currentUser;

  constructor() { }

  ngOnInit() {
    // Cette méthode est appelé lorsque le constructeur est prêt.
  }

  isCurrentUserSelected(): boolean {
    return (!isNullOrUndefined(this.currentUser));
  }

}
