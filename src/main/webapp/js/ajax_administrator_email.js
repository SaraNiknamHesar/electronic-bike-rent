/*
 Copyright 2018-2023 University of Padua, Italy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
limitations under the License.

    Author: Nicola Ferro (ferro@dei.unipd.it)
Version: 1.0
Since: 1.0
*/

// Add an event listener to the button,
// to invoke the function making the AJAX call
document.getElementById("ajaxButton")
	.addEventListener("click", searchAdministratorByEmail);
console.log("Event listener added to ajaxButton.")

/**
 * Searches for employee above the given salary.
 *
 * @returns {boolean} true if the HTTP request was successful; false otherwise.
 */
function searchAdministratorByEmail() {

	// get the value of the salary from the form field
	const email = document.getElementById("emailID").value;

	console.log("Email: %s.", email);

	const url = "http://localhost:8080/wascoot-1.0/rest/administrator/email/" + email;

	console.log("Request URL: %s.", url)

	// the XMLHttpRequest object
	const xhr = new XMLHttpRequest();

	if (!xhr) {
		console.log("Cannot create an XMLHttpRequest instance.")

		alert("Giving up :( Cannot create an XMLHttpRequest instance");
		return false;
	}

	// set up the call back for handling the request
	xhr.onreadystatechange = function () {
		processResponse(this);
	};

	// perform the request
	console.log("Performing the HTTP GET request.");

	xhr.open("GET", url, true);
	xhr.send();

	console.log("HTTP GET request sent.");
}



/**
 * Processes the HTTP response and writes the results back to the HTML page.
 *
 * @param xhr the XMLHttpRequest object performing the request.
 */
function processResponse(xhr) {

	// not finished yet
	if (xhr.readyState !== XMLHttpRequest.DONE) {
		console.log("Request state: %d. [0 = UNSENT; 1 = OPENED; 2 = HEADERS_RECEIVED; 3 = LOADING]",
			xhr.readyState);
		return;
	}

	const div = document.getElementById("results");

	// remove all the children of the result div, appended by a previous call, if any
	div.replaceChildren();

	if (xhr.status !== 200) {
		console.log("Request unsuccessful: HTTP status = %d.", xhr.status);
		console.log(xhr.response);

		div.appendChild(document.createTextNode("Unable to perform the AJAX request."));

		return;
	}

	// generate the table, appending node-by-node
	const table = document.createElement("table");
	div.appendChild(table)

	// placeholders for generic DOM nodes
	let e, ee, eee;


	// table header
	e = document.createElement("thead");
	table.appendChild(e); // append the table header to the table

	// the row in the table header
	ee = document.createElement("tr");
	e.appendChild(ee); // append the row to the table header

	// a generic element of the table header row
	eee = document.createElement("th");
	eee.appendChild(document.createTextNode("Id"));
	ee.appendChild(eee); // append the cell to the row

	eee = document.createElement("th");
	eee.appendChild(document.createTextNode("Email"));
	ee.appendChild(eee); // append the cell to the row

	eee = document.createElement("th");
	eee.appendChild(document.createTextNode("name"));
	ee.appendChild(eee); // append the cell to the row

	// table body
	e = document.createElement("tbody");
	table.appendChild(e); // append the table body to the table

	// parse the response as JSON and extract the resource-list array
	const resourceList = JSON.parse(xhr.responseText)["resource-list"];

	for (let i = 0; i < resourceList.length; i++) {

		// extract the i-th administrator and create a table row for it
		let administrator = resourceList[i].administrator;

		ee = document.createElement("tr");
		e.appendChild(ee); // append the row to the table body

		// create a cell for the badge of the administrator
		eee = document.createElement("td");
		eee.appendChild(document.createTextNode(administrator["id"]));
		ee.appendChild(eee); // append the cell to the row

		// create a cell for the surname of the administrator
		eee = document.createElement("td");
		eee.appendChild(document.createTextNode(administrator["email"]));
		ee.appendChild(eee); // append the cell to the row

		// create a cell for the age of the administrator
		eee = document.createElement("td");
		eee.appendChild(document.createTextNode(administrator["name"]));
		ee.appendChild(eee); // append the cell to the row


		console.log("HTTP GET request successfully performed and processed.");
	}

}
