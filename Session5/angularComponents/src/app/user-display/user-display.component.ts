import { Component, OnInit, Input } from '@angular/core';
import { isNullOrUndefined } from 'util';
import { IUser } from '../IUser';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-display',
  templateUrl: './user-display.component.html',
  styleUrls: ['./user-display.component.css']
})
export class UserDisplayComponent implements OnInit {
  userToDisplay: IUser;

  constructor(private userService: UserService) {
    this.userService.userUpdateAnnounce.subscribe(
      (user: IUser) => this.updateUser(user),
      () => {console.log('User update error'); }
    );
  }

  ngOnInit() {
    // Cette méthode est appelé lorsque le composant est prêt.
    this.userToDisplay = this.userService.currentUser;
  }

  isCurrentUserSelected(): boolean {
    return (!isNullOrUndefined(this.userToDisplay));
  }

  updateUser(user: IUser) {
    this.userToDisplay = user;
  }

}
