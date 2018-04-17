function HelloWorld() {
  alert("Hello World");
}

function Addition() {
  let number1 = prompt("Input first number", 0);
  let number2 = prompt("Input second number", 0);

  if (isNaN(number1) || isNaN(number2)) {
    alert("Please enter only numbers !");
  } else {
    const resultContainer = document.querySelector("#additionResult");
    resultContainer.innerHTML = "Result : " + number1 + "+" + number2 + " = " + (Number(number1) + Number(number2));
  }
}


function displayArray(container, arrayToDisplay) {
  const table = document.createElement("table");
  const row = document.createElement("tr");
  for (let i = 0; i < arrayToDisplay.length; i++) {
    const cell = document.createElement("td");
    cell.appendChild(document.createTextNode(arrayToDisplay[i]));
    row.appendChild(cell);
  }
  table.appendChild(row);
  container.appendChild(table);
}

function ArrayOfNumberExercice(arrayLength) {

  if (arrayLength < 0) {
    return;
  }

  const arrayOfNumber = Array(arrayLength);

  for (let i = 0; i < arrayLength; i++) {
    let correctInput = false;
    while (!correctInput) {
      let number = prompt("Input number n°" + (i + 1));
      if (isNaN(number)) {
        alert("Please enter only numbers !");
      } else {
        correctInput = true;
        arrayOfNumber[i] = number;
      }
    }
  }

  const resultContainer = document.querySelector("#arrayResult");
  resultContainer.innerText = ""; // Cleaning of resultContainer
  resultContainer.appendChild(document.createTextNode("Unsorted array"));
  displayArray(resultContainer, arrayOfNumber);

  arrayOfNumber.sort();

  resultContainer.appendChild(document.createTextNode("Sorted array"));
  displayArray(resultContainer, arrayOfNumber);

}
