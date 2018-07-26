var n1;
var n2;
var berekenaar;
var antwoord;

function antwoord() {
	
	n2 = document.getElementById("display").innerHTML;
	
	if (berekenaar == 1) {
		antwoord = parseInt(n1) + parseInt(n2);
	} else if (berekenaar == 2) {
		antwoord = parseInt(n1) - parseInt(n2);
	} else if (berekenaar == 3) {
		antwoord = parseInt(n1) * parseInt(n2);
	} else if (berekenaar == 4) {
		antwoord = parseInt(n1) / parseInt(n2);
	}
		document.getElementById("display").innerHTML = antwoord;
		n1=0;
		n2=0;
	
}

function getal(getal) {
	display = document.getElementById("display").innerHTML;
	if (display == 0) {
		document.getElementById("display").innerHTML = getal;
	} else {
		document.getElementById("display").innerHTML = display + getal;
	}
}

function bereken(dif) {
	n1 = document.getElementById("display").innerHTML;
	document.getElementById("display").innerHTML = 0;
	berekenaar = dif;
}

function leegmaken() {
	n1 = 0;
	n2 = 0;
	antwoord = 0;
	document.getElementById("display").innerHTML = 0;
}