import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: String = 'promo bnp-pf n°1';
  welcomeMsg: String = 'Welcome to the Thunderdome !!! ';
  user = {
    id: 0,
    name: 'François François',
    image: 'http://s1.dmcdn.net/AX3HF/x240-2Fg.jpg',
    jobTitle: 'Chanteur populaire'
  };

  myColor = 'red';

  testClickButton() {
    console.log('testClickButton-user:', this.user);
    this.myColor = 'blue';
  }

testMouseOverButton(mouseoverEvt: MouseEvent) {
  console.log('testMouseOverButton:', mouseoverEvt.target);
}

testInput(textParam) {
  const elementTextInput = (<HTMLInputElement> document.getElementById('idChampInput'));
  console.log('Recup directe depuis un champs : ', elementTextInput.value);
  console.log('Recup depuis param: ', textParam);
  this.user.name = textParam;
}

}

