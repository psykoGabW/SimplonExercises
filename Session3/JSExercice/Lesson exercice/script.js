console.log("Hello world");

// Object declaration
var player = {
  name : "Player one",
  highscore : 999,

  // present function
  present: function(){
    return this.name + " - Higscore  : " + this.highscore;
  }
};

console.log(player);

player.__proto__.login = "player1";

console.log(player);
console.log(player.present());

const player2 = Object.create(player);
player2.name = "Player two";
player2.NaMe = "PlAyEr 2"; // Case sensitive language... A new attribute is created
player2.highscore = 0;
console.log(player2);
player2.__proto__.favoriteGame = "Pacman";
console.log(player2.present());
console.log(player);



/*
console.log(1   ==  1);  // true
console.log("1" ==  1);  // true
console.log(1   === 1 ); // true
console.log("1" === 1);  // false
*/

// Je crée une fonction que je stocke dans une variable
const sayHello = function () {
  console.log("Hello there!");
}

// J'appelle ma fonction en utilisant le nom de ma variable
// Suivi de parenthèses
sayHello();

// La même chose qu'avant mais en fléché
const sayBonjour = () => console.log("Bonjour toi!");

sayBonjour();

// Inception... Fonction, incluant des fonctions...
function sayHelloBis(string) {
  const hello = string;
  return function(name) {
    return hello + " " + name;
  }
}

// J'exécute directement les deux fonctions
console.log(sayHelloBis("Bonjour")("Jules"));

// Récupération du paragraphe p avec class lien
const pLien = document.querySelector("p.lien");

const newLink = document.createElement("a"); // Création du lien
newLink.href = "http://simplon.co"; // Remplissage de l'url du lien
newLink.textContent = "Simplon"; // Remplissage du titre du lien
newLink.style.color = "rgb(206, 15, 15)"; // Lien en rouge
newLink.style.textDecoration = "none"; // Texte non souligné

pLien.appendChild(newLink); // Ajout de la balise a au paragraphe
