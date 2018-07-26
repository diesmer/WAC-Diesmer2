
 function initPage(){
	 
	 var APIlocatie =  "https://ipapi.co/json/";
	 
	 fetch(APIlocatie)
	 .then(respone => respone.json())
	 .then(function(myJson) {
	     document.querySelector("#city").append(myJson.city);
	     document.querySelector("#country_name").append(myJson.country_name);
	     document.querySelector("#postal").append(myJson.postal);
	     document.querySelector("#ip").append(myJson.ip);
	     document.querySelector("#region").append(myJson.region);
	     document.querySelector("#country").append(myJson.country);
	     document.querySelector("#latitude").append(myJson.latitude);
	     document.querySelector("#longitude").append(myJson.longitude);
	     
	     showWeather(myJson.latitude, myJson.longitude, myJson.city)
 });
	 loadCountries();
 }
 
 function showWeather(latitude, longitude, city){
	 
	 var weatherAPI = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&APPID=a078d1afc34a25f93ed30b498dcc5205";
	 document.querySelector("#locatieStad").innerHTML = "Het weer in " + city;
	 
	 document.querySelector("#temp").innerHTML = "Temperatuur: ";
	 document.querySelector("#luchtvocht").innerHTML = "Luchtvochtigheid: ";
	 document.querySelector("#windsnel").innerHTML = "Windsnelheid: ";
	 document.querySelector("#windricht").innerHTML = "Windrichting: ";
	 document.querySelector("#opgang").innerHTML = "Zonsopgang: ";
	 document.querySelector("#ondergang").innerHTML = "Zonsondergang: ";
	 
	 
	 fetch(weatherAPI)
	 .then(respone => respone.json())
	 .then(function(myJson) {
		 
		 var temp = myJson.main.temp - 271;
         
	     document.querySelector("#temp").append(temp.toFixed(1));
	     document.querySelector("#luchtvocht").append(myJson.main.humidity);
	     document.querySelector("#windsnel").append(myJson.wind.speed);
	     document.querySelector("#windricht").append(windrichting(myJson.wind.deg));
	     document.querySelector("#opgang").append(gettime(myJson.sys.sunrise));
	     document.querySelector("#ondergang").append(gettime(myJson.sys.sunset));
	     
	 });
	 
	 function gettime(unixtime) {
         var date = new Date(unixtime*1000);
         var hours = date.getHours();
         var minutes = "0" + date.getMinutes();
         var seconds = "0" + date.getSeconds();

         var formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
         return formattedTime;
   };
	 
	 function windrichting(deg){
         if (deg>11.25 && deg<33.75){
           return "Noord Noord Oost";
         }else if (deg>33.75 && deg<56.25){
           return "Oost Noord Oost";
         }else if (deg>56.25 && deg<78.75){
           return "Oost";
         }else if (deg>78.75 && deg<101.25){
           return "Oost Zuid Oost";
         }else if (deg>101.25 && deg<123.75){
           return "Oost Zuid Oost";
         }else if (deg>123.75 && deg<146.25){
           return "Zuid Oost";
         }else if (deg>146.25 && deg<168.75){
           return "Zuid Zuid Oost";
         }else if (deg>168.75 && deg<191.25){
           return "Zuid";
         }else if (deg>191.25 && deg<213.75){
           return "Zuid Zuid West";
         }else if (deg>213.75 && deg<236.25){
           return "Zuid West";
         }else if (deg>236.25 && deg<258.75){
           return "West Zuid West";
         }else if (deg>258.75 && deg<281.25){
           return "West";
         }else if (deg>281.25 && deg<303.75){
           return "West Noord West";
         }else if (deg>303.75 && deg<326.25){
           return "Noord West";
         }else if (deg>326.25 && deg<348.75){
           return "Noord Noord West";
         }else{
           return "Noord"; 
         }
       }
	 
 }
 
 function loadCountries() {
	  fetch("restservices/countries")
	  .then(response => response.json())
	  .then(function(myJson) {
		 var tabel = document.getElementById("mijnTabel");
		 
		 for (const object of myJson) {
			 var rij = tabel.insertRow(1);
			 rij.addEventListener("click", function() {
				showWeather(object.latitude, object.longitude, object.capital); 
			 });
			 
			 var cell1 = rij.insertCell(0);
			 var cell2 = rij.insertCell(1);
			 var cell3 = rij.insertCell(2);
			 var cell4 = rij.insertCell(3);
			 var cell5 = rij.insertCell(4);
			 cell1.innerHTML = object.name;
			 cell2.innerHTML = object.capital;
			 cell3.innerHTML = object.region;
			 cell4.innerHTML = object.surface;
			 cell5.innerHTML = object.population;
		 }
	  });
}