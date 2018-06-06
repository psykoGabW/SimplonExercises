import { Component, OnInit } from '@angular/core';
import { isUndefined, isNullOrUndefined } from 'util';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  // private userService: UserService;
  public users;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.users = this.userService.users;
  }

  selectUserWithPreventDefault(userId: number, e: Event) {
    e.preventDefault();
    this.userService.selectUser(userId);
  }

}
