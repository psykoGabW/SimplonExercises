
function submitCustomerForm(event) {

  let customerDetails = customerInformations("customer_form");
  addCustomerRow(customerDetails, "customers_list");

  // Those 2 lines must been commented for Gatling test
  // cleanForm("customer_form");
  // event.preventDefault();

}

function addCustomerRow(customerDetails, tableName) {
  const customerTable = document.getElementById(tableName);
  const workingTBody = customerTable.getElementsByTagName("tbody")[0];
  const nextId = workingTBody.getElementsByTagName("tr").length + 1;
  customerDetails["id"] = nextId;

  // const customerRow = document.createElement("tr");
  const customerRow = workingTBody.insertRow();

  const dataOrdered = ["id", "knickname", "firstname", "lastname", "birthdate", "credits", "email", "select"];

  for (let i = 0; i < 8; i++) {
    //const cell = document.createElement("td");
    const cell = customerRow.insertCell(i);

    if (dataOrdered[i] === "select") {
      // Specific case to add checkbox
      cell.setAttribute("class", "td_checkbox");
      const checkbox = document.createElement("input");
      checkbox.type = "checkbox";
      cell.appendChild(checkbox);
    } else {
      //cell.appendChild(document.createTextNode(customerDetails[dataOrdered[i]]));
      cell.textContent = customerDetails[dataOrdered[i]];
    }
    //customerRow.appendChild(cell); // Unnecessary when using insertCell
  }
  //workingTBody.appendChild(customerRow); // Not necessary when using insertRow
}

/*
Get all input field from given form and put them in object through its attributes.
*/
function customerInformations(formName) {

  const workingForm = document.getElementById(formName);
  const inputs = workingForm.getElementsByTagName("input");

  let result = {};
  for (let i of inputs) {
    result[i.name] = i.value;
  }

  return result;
}


/*
Clean all input field from given form.
WARNING : This function must not be called before a "real" submit !
*/
function cleanForm(formName){
  const workingForm = document.getElementById(formName);
  const inputs = workingForm.getElementsByTagName("input");

  let result = {};
  for (let i of inputs) {
    i.value = "";
  }
}

function init() {
  'use strict';
  document.getElementById("customer_form").addEventListener("submit", submitCustomerForm, false);
}
document.addEventListener("DOMContentLoaded", init, false);
