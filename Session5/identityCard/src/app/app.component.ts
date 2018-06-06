import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  static ID_INPUT_NAME = 'INPUT_USER_NAME';
  static ID_INPUT_JOBTITLE = 'INPUT_USER_JOBTITLE';
  static ID_INPUT_IMG_URL = 'INPUT_USER_IMGURL';
  static ID_INPUT_AGE = 'INPUT_USER_AGE';

  title = 'Identity card application';

  user = {
    name : 'John DOE',
    jobTitle:  'Spy',
    age: '20',
    imgUrl:  'https://i1.wp.com/res.cloudinary.com/pccindow/image/upload/v1508511153/Angular_4_reoza9.png?fit=400%2C400&ssl=1'
  };

  textColorUrgent = false;
  userIsConnected = false;

setUserData(evt) {
  console.log('setUserData', evt.target);
  switch (evt.target.id) {
    case AppComponent.ID_INPUT_NAME:
      this.user.name = evt.target.value;
      break;
    case AppComponent.ID_INPUT_JOBTITLE:
      this.user.jobTitle = evt.target.value;
      break;
    case AppComponent.ID_INPUT_IMG_URL:
      this.user.imgUrl = evt.target.value;
      break;
    /*case AppComponent.ID_INPUT_AGE:
      this.user.age = evt.target.value;
      break;*/
  }
}

get ID_INPUT_NAME() {
  return AppComponent.ID_INPUT_NAME;
}

get ID_INPUT_JOBTITLE() {
  return AppComponent.ID_INPUT_JOBTITLE;
}

get ID_INPUT_IMGURL() {
  return AppComponent.ID_INPUT_IMG_URL;
}

get ID_INPUT_AGE() {
  return AppComponent.ID_INPUT_AGE;
}

}
