document.addEventListener('DOMContentLoaded', function(event) {
    //getUsersList();
    getCustomerAvgAge();
    getCustomerGender();
    getRevenue();
    getMapScooterRacks();
});

function getUsersList() {
    var url = new URL('http://localhost:8080/wascoot-1.0/rest/customer/');
    genericGETRequest(url, getCustomerList);
}

function getCustomerAvgAge() {
    var url = new URL('http://localhost:8080/wascoot-1.0/rest/customerAvgAge/');
    genericGETRequest(url, showCustomerAvgAge);
}

function getCustomerGender() {
    var url = new URL('http://localhost:8080/wascoot-1.0/rest/customerGender/');
    genericGETRequest(url, showCustomerGender);
}

function getRevenue() {
    const url = new URL('http://localhost:8080/wascoot-1.0/rest/revenue/');
    genericGETRequest(url, show_Revenue);
}

function getMapScooterRacks(){
     var url = new URL('http://localhost:8080/wascoot-1.0/rest/scooterRackPos/');
    genericGETRequest(url, showScooterRacks);
}


function getCustomerList(req){
      if (req.status == 200) {
            var jsonData = JSON.parse(req.responseText);
            var customers = jsonData['resource-list'];

            var hpcontent = "";
            for(let i=0; i<customers.length; i++){
                  var customer = customers[i]['customer'];
                  hpcontent += "<h1>"+sanitize(customer['cf'])+"</h1>";
                  hpcontent += "<p>name: "+sanitize(customer['name'])+"</p>";
                  hpcontent += "<p>surname: "+sanitize(customer['surname'])+"</p>";
                  hpcontent += "<p>email: "+sanitize(customer['email'])+"</p>";
                  hpcontent += "<p>sex: "+sanitize(customer['sex'])+"</p>";
                  hpcontent += "<p>birthdate: "+sanitize(customer['birthdate'])+"</p>";
                  hpcontent += "<p>postalCode: "+sanitize(customer['postalCode'])+"</p>";
                  hpcontent += "<p>paymentType: "+sanitize(customer['paymentType'])+"</p>";
            }
            document.getElementById("customer-content").innerHTML = hpcontent;
      }
      else {
            console.log(JSON.parse(httpRequest.responseText));
            alert("Problem processing the request");
      }
}

function showCustomerAvgAge(req){
      if (req.status == 200) {
            var jsonData = JSON.parse(req.responseText);
            var data = jsonData['resource-list'];
            /*
            var hpcontent = "";
            for(let i=0; i<data.length; i++){
                  var entry = data[i];
                  hpcontent += "<h1> Postal code : "+sanitize(entry['postalCode'])+"</h1>";
                  hpcontent += "<p>Average age : "+sanitize(entry['averageAge'])+"</p>";
            }
            document.getElementById("customer-content").innerHTML = hpcontent;
            */
            var postalCodes = [];
            var averageAges = [];

            for(let i=0; i<data.length; i++){
                var entry = data[i];
                postalCodes.push(sanitize(entry['postalCode']));
                averageAges.push(sanitize(entry['averageAge']));
            }
            renderAgeChart(postalCodes, averageAges);
      }
      else {
            console.log(JSON.parse(httpRequest.responseText));
            alert("Problem processing the request");
      }
}

function showCustomerGender(req){
      if (req.status == 200) {
            var jsonData = JSON.parse(req.responseText);
            var data = jsonData['resource-list'];
            /*
            var hpcontent = "";
            for(let i=0; i<data.length; i++){
                  var entry = data[i];
                  hpcontent += "<p> Male count : "+sanitize(entry['maleCount'])+"</p>";
                  hpcontent += "<p> Female count : "+sanitize(entry['femaleCount'])+"</p>";
            }
            document.getElementById("customer-content-2").innerHTML = hpcontent;
            */
            var maleCount = 0;
            var femaleCount = 0;

            for(let i=0; i<data.length; i++){
                var entry = data[i];
                maleCount = sanitize(entry['maleCount']);
                femaleCount = sanitize(entry['femaleCount']);
            }

            renderGenderChart(maleCount, femaleCount);
      }
      else {
            console.log(JSON.parse(httpRequest.responseText));
            alert("Problem processing the request");
      }
}

function show_Revenue(req){
    if (req.status == 200) {
        var jsonData = JSON.parse(req.responseText);
        var data = jsonData['resource-list'];
        var date = [];
        var price = [];

        for(let i=0; i<data.length; i++){
            var entry = data[i];
            date.push(sanitize(entry['date']));
            price.push(sanitize(entry['price']));
        }
        renderRevenueChart(date, price);
    }
    else {
        console.log(JSON.parse(httpRequest.responseText));
        alert("Problem processing the request");
    }
}

function renderAgeChart(postalCodes, averageAges) {
    var ctx = document.getElementById('age-chart').getContext('2d');
    var chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: postalCodes,
            datasets: [{
                label: 'Average age',
                data: averageAges,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function renderGenderChart(maleCount, femaleCount) {
    var ctx = document.getElementById('gender-chart').getContext('2d');
    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Male', 'Female'],
            datasets: [{
                label: 'Gender Distribution',
                data: [maleCount, femaleCount],
                backgroundColor: [
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 99, 132, 0.5)'
                ],
                borderColor: [
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 99, 132, 1)'
                ],
                borderWidth: 1
            }]
        }
    });
}


function renderRevenueChart(date, price) {
    var ctx = document.getElementById('revenue-chart').getContext('2d');
    var chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: date,
            datasets: [{
                label: 'Revenue',
                data: price,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function showScooterRacks(req){
    if (req.status == 200) {
        var jsonData = JSON.parse(req.responseText);
        var data = jsonData['resource-list'];

        // Eventlistener for buttons
        var buttonPadova = document.getElementById("button-padova");
        var buttonRome = document.getElementById("button-rome");

        // Actions for button events
        buttonPadova.addEventListener("click", function () {
            map.setView([45.401, 11.862], 13);
        });
        buttonRome.addEventListener("click", function () {
            map.setView([41.8987443, 12.472392], 13);
        });

        var map = L.map('map').setView([45.401, 11.862], 13);
        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(map);

        for (let i = 0; i < data.length; i++) {
            var scooterRack = data[i]['scooterrack'];
            var latitude = parseFloat(scooterRack['latitude']);
            var longitude = parseFloat(scooterRack['longitude']);
            var totalParkingSpots = scooterRack['totalParkingSpots'];
            var availableParkingSpots = scooterRack['availableParkingSpots'];
            var postalCode = scooterRack['postalCode'];
            var address = scooterRack['address'];
            var id = scooterRack['id'];

            var circle = L.circle([latitude, longitude], {
                color: 'red',
                fillColor: '#f03',
                fillOpacity: 0.5,
                radius: 500
            }).addTo(map);

            var popupContent = 'Scooterrack id: ' + id + '<br>' + 'Total Parking Spots: ' + totalParkingSpots + '<br>' +
            'Available Parking Spots: ' + availableParkingSpots + '<br>' +
            'Postal Code: ' + postalCode + '<br>' +
            'Address: ' + address;

            circle.bindPopup(popupContent);
        }

        } else {
            console.log(JSON.parse(httpRequest.responseText));
            alert("Problem processing the request");
        }

}
