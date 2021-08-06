/**
 * 
 */
// -------------------------ticket submit

window.onload = function() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			let user = this.responseText;
			greetAndParse(user);
		}
	}
	xhttp.open("POST", 'http://localhost:9007/ERSp1/resources/grabSesh.ms');
	xhttp.send();
}

function showForm() {
	document.getElementById("hiddenTikForm").style.display = 'block';
	document.getElementById("createNewTicket").setAttribute("onclick",
			"hideForm()");
	document.getElementById("createNewTicket").innerHTML = "Hide";
}

function hideForm() {
	document.getElementById("hiddenTikForm").style.display = 'none';
	document.getElementById("createNewTicket").setAttribute("onclick",
			"showForm()");
	document.getElementById("createNewTicket").innerHTML = "Create New Ticket";
}

function greetAndParse(e) {
	let quotesOut = e.substring(1, e.length - 1);
	document.getElementById("greeter").innerHTML = quotesOut;
	// hide user name in form for sending.. ya I know.. but eh...
	document.getElementById("name").value = quotesOut;
}
// ---------------------------tickets show

function myTickets() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			let tickets = JSON.parse(this.responseText);
			ShowMyTickets(tickets);
		}
	}
	xhttp.open("POST", 'http://localhost:9007/ERSp1/resources/myTickestSee.ms');
	xhttp.send();

}
function Top(){
	
}
function cycleForward(p) {
	// remove old style

	let substr = p.substring(6, p.length);

	let newInt = parseInt(substr);

	newInt = newInt + 1;// load for next
	let check = "scroll" + newInt;

	if (document.getElementById(p) === null) {
		let substr = p.substring(6, p.length);

		let newInt = parseInt(substr);
		newInt = 0;
		document.getElementById("NextTicket").setAttribute("onclick",
				"cycleForward('scroll0')");

	} else if (document.getElementById(p) !== null) {

		let highlight = document.getElementById(p);
		highlight.style.backgroundImage = "linear-gradient(to right, rgba(0, 0, 0, .3) , silver, white)";
		highlight.style.border = "outset";
		highlight.style.borderWidth = "2px";
		highlight.style.borderColor = "blue";
		highlight.style.borderRadius = "25px";
		highlight.style.width = "210px";
		highlight.style.padding = "6px";
		highlight.scrollIntoView();
		// mark by the ID
		highlight.setAttribute('class', 'Marker');
		let scrollindex = "scroll" + newInt;
		document.getElementById("NextTicket").setAttribute("onclick",
				"cycleForward('" + scrollindex + "')");
	}
	let oldInt = newInt-2;
	let old = document.getElementById("scroll"+oldInt);
	old.removeAttribute("style");
	
}

function ShowMyTickets(T) {
	let toAppendTo = document.getElementById("ticketList");
	let counter = 0;
	var cycleArray = new Array();
	for ( let mine in T) {
		if (T[mine].submitterName + "(" + T[mine].submitterID + ")" === document
				.getElementById("greeter").innerHTML) {
			let header = document.createElement("li");
			header.setAttribute('id', 'scroll' + counter);
			cycleArray.push('scroll' + counter);

			counter++;
			let submitted = document.createElement("li");
			let amount = document.createElement("li");
			let section2 = document.createElement("li");
			section2.innerHTML = "==--------- Info ----------~"
			amount.innerHTML = "Expense Amount: $" + T[mine].amount + ".0";
			header.innerHTML = "=-=-=-=Ticket # " + T[mine].ticketID + " =-=-"
			toAppendTo.appendChild(header);
			toAppendTo.appendChild(section2);
			let adjustedTime = T[mine].dateSubmitted;
			let fixed = adjustedTime.substring(0, 16);
			submitted.innerHTML = "Submitted at: " + fixed;
			toAppendTo.appendChild(submitted);
			toAppendTo.appendChild(amount);
			let section3 = document.createElement("li");
			section3.innerHTML = " =-~ Details ~-=-=";
			toAppendTo.appendChild(section3);
			let statusType = document.createElement("li");

			let typ = "string";
			if (T[mine].type == 1) {// enum tables would have been smarter...
									// but its a gotta move night!!
				typ = "Psychological";
			} else if (T[mine].type == 2) {
				typ = "Choking-Related (Failure related)";
			} else if (T[mine].type == 3) {
				typ = "Food/Shelter";
			} else if (T[mine].type == 4) {
				typ = "Equipment";
			} else if (T[mine].type == 5) {
				typ = "Misc and/or Missiles";
			} else if (T[mine].type == 6) {
				typ = "Deathstar-malfunction"
			}
			statusType.innerHTML = "Status: " + T[mine].status + "<br>"
					+ "Type: " + typ;
			toAppendTo.appendChild(statusType);
			let descBut = document.createElement("button");
			descBut.setAttribute('onclick', 'showDescriptin("'
					+ T[mine].expenseDescription + '")');
			descBut.style.border = "outset";
			descBut.style.borderWidth = "2px";
			descBut.style.borderColor = "blue";
			descBut.innerHTML = "Description"
			descBut.style.borderRadius = "30px";
			descBut.style.height = "34px";
			descBut.style.width = "130px";
			descBut.style.marginLeft = "10px";
			statusType.appendChild(descBut);
			statusType.appendChild(document.createElement("br"));
			statusType.appendChild(document.createElement("br"));
		}
	}

	document.getElementById("ticketSpot").style.display = "block";

	document.getElementById("NextTicket").setAttribute("onclick",
			"cycleForward('" + cycleArray[0] + "')");
}
function showDescriptin(desc) {

	let descriptionArea = document.getElementById("descBox");
	descBox.style.padding = "15px";
	descriptionArea.style.backgroundImage = "linear-gradient(to right, rgba(100, 15, 40, .1), silver, rgba(0, 0, 0, .1))";
	descBox.innerHTML = desc;
}
