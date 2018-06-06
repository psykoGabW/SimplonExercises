import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  users = [
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
  currentUser;

  setCurrentUser(user) {
    this.currentUser = user;
  }

  selectUser(userId: number) {
    if (userId >= 0 && userId < this.users.length) {
      this.currentUser = this.users[userId];
    } else {
      this.currentUser = null;
    }
  }

  constructor() { }
}
