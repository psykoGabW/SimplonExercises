import { Injectable } from '@angular/core';
import { IUser } from './IUser';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  users: IUser[] = [
    {
      'id': 0,
      'name': 'Aisha Eichmann',
      'image': 'https://s3.amazonaws.com/uifaces/faces/twitter/malykhinv/128.jpg',
      'jobTitle': 'Customer Division Assistant'
    },
    {
      'id': 1,
      'name': 'Calista Skiles',
      'image': 'https://s3.amazonaws.com/uifaces/faces/twitter/nessoila/128.jpg',
      'jobTitle': 'Dynamic Solutions Orchestrator'
    },
    {
      'id': 2,
      'name': 'Kristofer Hermiston',
      'image': 'https://s3.amazonaws.com/uifaces/faces/twitter/ernestsemerda/128.jpg',
      'jobTitle': 'Dynamic Markets Planner'
    }
  ];
  currentUser: IUser;

  // Observable string sources
  private userUpdate = new Subject<IUser>();

  // Observable string streams
  public userUpdateAnnounce = this.userUpdate.asObservable();

  setCurrentUser(user) {
    console.log('Coucou');
    this.currentUser = user;
    this.userUpdate.next(this.currentUser);
  }

  selectUser(userId: number) {
    console.log('UserService.selectUser: userId = ' + userId);
    if (userId >= 0 && userId < this.users.length) {
      this.setCurrentUser(this.users[userId]);
    } else {
      this.currentUser = null;
    }
  }

  constructor() { }
}
