/**
 * the view button will do this --
 */
function myWork(){

	let http = new XMLHttpRequest();

	http.onreadystatechange = function() {

		if (http.readyState == 4 && http.status == 200) {
				
			var e = JSON.parse(this.responseText); 
			
			displayMyTickets(e);
		}
	}
	http.open("POST",'http://localhost:9007/ERSp1/resources/myWork.ms');
	http.send();
}
function displayDesc(desc){
	let closeButton = document.createElement("button");
	closeButton.innerHTML = "Close Window";
	closeButton.style.border ="outset";
	closeButton.style.borderWidth= "2px";
	closeButton.style.borderRadius = "10px";
	closeButton.style.width = "200px";
	closeButton.style.marginLeft ="100px";
	closeButton.style.height = "40px";
	closeButton.setAttribute("onclick", "hideDescription()");
	document.getElementById("descriptionSpot").style.display = "block";
	
	document.getElementById("descriptionSpot").innerHTML = "~Sob Story~ : <br>"+desc;
	document.getElementById("descriptionSpot").appendChild(closeButton);	
}
function hideDescription(){
	document.getElementById("descriptionSpot").style.display = "none";
}

function displayMyTickets(c){
	document.getElementById("toHide").style.display ="none";
	let mytickets = document.getElementById("myTickets");
	mytickets.style.padding = "10px";
	mytickets.style.border ="inset";
	mytickets.style.borderWidth ="2px";
	let swi = false;
	for(let dance in c){
	let adjust=	c[dance].dateSubmitted;
	let adjusted = adjust.substring(0, 16);
	let newLine = document.createElement("div");
	let newLine2 = document.createElement("div");
	let newLine3 = document.createElement("div");
	let newLine4 = document.createElement("div");
	let describe = document.createElement("button");
	describe.setAttribute("onclick", "displayDesc('"+c[dance].expenseDescription+"')");
	describe.style.border ="outset";
	describe.style.borderWidth ="3px";
	describe.style.width = "100px";
	describe.style.height = "30px";
	describe.style.background ="gold";
	describe.innerHTML ="Description!";
	describe.style.borderRadius ="4px";
	describe.style.borderBottomLeftRadius ="30px";
	describe.style.borderBottomRightRadius ="30px";
	newLine4.appendChild(describe);
	newLine3.innerHTML = "Type of Expense: " + c[dance].type; 
	newLine2.style.background ="silver";
	newLine2.innerHTML = "Submitted by "+c[dance].submitterName;
	newLine.style.padding = "3.5px";
	if(swi ==false){
		newLine.style.background ="black";
		newLine.style.color ="gold";
		swi =true;
	}else if(swi == true){
		newLine.style.background ="red";
		newLine.style.color ="white";
		swi =false;
	}
	
	let newHeader = document.createElement("div");
	newHeader.innerHTML = "Ticket No. "+c[dance].ticketID;
	newLine.innerHTML = "Amount: "+ c[dance].amount+" Submitted on: "+adjusted; 
	mytickets.appendChild(document.createElement("br"));
	mytickets.appendChild(newHeader);
	
	mytickets.appendChild(newLine);
	mytickets.appendChild(newLine2);
	mytickets.appendChild(newLine3);
	mytickets.appendChild(newLine4);
	}
}

window.onload = function() {

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			var e = JSON.parse(this.responseText); // <-- data returned from
			// servlet
			tableSetup(e); // <--I need to call another function with the data
			// |
		}
	}
	xhttp.open("POST",
			'http://localhost:9007/ERSp1/resources/viewAllTickets.ms');
	xhttp.send();
}

function populateResolvers() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			var e = JSON.parse(this.responseText);
			showResolvers(e);
		}
	}
	xhttp.open("POST",
			'http://localhost:9007/ERSp1/resources/returnResolvers.ms');
	xhttp.send();
}

function UserList() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			var e = JSON.parse(this.responseText);
			pullUpUsers(e);
		}
	}
	xhttp
			.open("POST",
					'http://localhost:9007/ERSp1/resources/pullEmailList.ms');
	xhttp.send();

	document.getElementById("Reveal").setAttribute('onclick', 'hideWorkload()');
	document.getElementById("Reveal").innerHTML = "Hide Workload";
}
// -10-v
function Restore(p){

	let xhttp = new XMLHttpRequest(); 

	xhttp.open('GET','http://localhost:9007/ERSp1/resources/'+e+'Restore.ms');
	xhttp.send();// http://localhost:9007
}

function DeleteUserEntry(e) {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

		}
	}
	xhttp.open("GET",'http://localhost:9007/ERSp1/resources/'+e+'DeleteUserEntry.ms');
	xhttp.send();
	document.getElementById("prompt"+e).style.display = "none";	
	let toAppend = document.getElementById("entry"+e);
	let prompt = document.createElement("div");
	let restore = document.createElement("button");
	prompt.innerHTML = "Deleted.. but you can restore if you would like!";
	prompt.style.background = "rgba(0,0,0,.15)";
	prompt.style.borderBottomLeftRadius = "250px";
	prompt.style.borderBottomRightRadius = "600px";
	prompt.style.borderTopRightRadius = "30px";
	prompt.style.borderTopLeftRadius = "8px";
	restore.innerHTML = "!-RESTORE-!";
	restore.style.border = "outset";
	restore.style.borderTop = "inset";
	restore.style.borderWidth = "8px";
	restore.style.borderRadius = "20px";
	restore.style.background = "yellow";
	restore.style.color = "red";
	restore.setAttribute('onclick', 'Restore('+e+')'); // -10-^
	prompt.appendChild(restore);
	toAppend.appendChild(prompt);
}

function showDesc() {
	// description
	document.getElementById("description").style.display = 'block';
	document.getElementById("desc1").setAttribute('onclick', 'hideDesc()');
}
function hideDesc() {
	document.getElementById("description").style.display = 'none';
	document.getElementById("desc1").setAttribute('onclick', 'showDesc()');
}
function deny(e) {

	let row = document.getElementById("entry" + e);
	let prompt = document.createElement('div');
	prompt.setAttribute('id', 'prompt' + e);
	prompt.innerHTML = "Are you sure you would like to delete this.. ";
	prompt.style.background = "rgba(0,0,0,.15)";
	prompt.style.borderBottomLeftRadius = "250px";
	prompt.style.borderBottomRightRadius = "600px";
	prompt.style.borderTopRightRadius = "30px";
	prompt.style.borderTopLeftRadius = "8px";
	let brk = document.createElement("br")
	prompt.appendChild(brk);
	let yes = document.createElement("button");
	yes.innerHTML = "Yes";
	yes.style.border = "outset";
	yes.style.borderTop = "inset";
	yes.style.borderWidth = "2px";
	yes.style.borderBottomLeftRadius = "20px";
	yes.style.background = "green";
	yes.style.color = "gold";
	yes.setAttribute("onclick", "DeleteUserEntry(" + e + ")");
	let no = document.createElement("button");
	no.setAttribute("onclick", "closePrompt(" + e + ")");
	no.innerHTML = "No";
	no.style.border = "outset";
	no.style.borderTop = "inset";
	no.style.borderWidth = "2px";
	no.style.background = "red";
	no.style.color = "white";
	no.style.borderBottomRightRadius = "20px";
	prompt.appendChild(yes);
	prompt.appendChild(no);
	row.appendChild(prompt);
}

function closePrompt(e) {
	let deleteRow = document.getElementById('entry' + e);

	while (deleteRow.firstChild) {
		deleteRow.removeChild(deleteRow.firstChild);
	}
	deleteRow.remove();
}

function ShowOverseerForm() {
	document.getElementById("EmptyForm").style.display = "block";
	document.getElementById("OverseerForm").style.display = "block";
	document.getElementById("OverseerCreate").style.display = "none";
}
function hideOverseerForm() {
	document.getElementById("OverseerCreate").style.display = "block";
	document.getElementById("EmptyForm").style.display = "none";
	document.getElementById("OverseerForm").style.display = "none";
}

function showEmployeeForm() {
	document.getElementById("EmptyForm2").style.display = "block";
	document.getElementById("EmployeeForm1").style.display = "block";
	document.getElementById("EmpCreate").style.display = "none";
}
function hideEmployeeForm() {
	document.getElementById("EmptyForm2").style.display = "none";
	document.getElementById("EmployeeForm1").style.display = "none";
	document.getElementById("EmpCreate").style.display = "block";
}

function hideWorkload() {
	document.getElementById("Reveal").setAttribute('onclick', 'UserList()');
	let firstColumn = document.getElementById("addHere");
	let secondColumn = document.getElementById("addHere2");
	while (firstColumn.firstChild) {
		while (secondColumn.firstChild) {
			secondColumn.removeChild(secondColumn.firstChild);
		}
		firstColumn.removeChild(firstColumn.firstChild);
	}
}

function pullUpUsers(list) {
	let columnIndex = 5;
	for ( let k in list) {
		if (columnIndex < 20) {
			let listSet = document.getElementById("addHere");
			let newrow = document.createElement("li")
			newrow.setAttribute('id', 'entry' + list[k].stormTrooperID);
			listSet.appendChild(newrow);
			newrow.innerHTML = list[k].firstName + " " + list[k].lastName
					+ " (" + list[k].stormTrooperID + ")";
			newrow.appendChild(document.createElement("br"));
			// --emailFunctionality
			let email = document.createElement("button");
			email.style.background = "green";
			email.style.color = "gold";
			email.style.fontStyle = "bold";
			email.style.borderRadius = "15px";
			email.style.width = "80px";
			email.style.height = "35px";
			email.innerHTML = "Confirm";
			email.setAttribute('onclick', 'emailConfirmation("' + list[k].email
					+ '", "' + list[k].stormTrooperID + '")');
			// ----
			let deny = document.createElement("button");
			deny.style.background = "red";
			deny.style.color = "blue";
			
			deny.style.fontStyle = "bold";
			deny.style.width = "40px";
			deny.style.height = "30px";
			deny.innerHTML = "(X)";
			deny.style.border = "outset";

			deny.style.borderRadius = "50px";
			deny
					.setAttribute('onclick', 'deny(' + list[k].stormTrooperID
							+ ')');
			// --
			newrow.appendChild(deny);
			newrow.appendChild(email);
			// --

		} else if (columnIndex >= 20 & columnIndex < 40) {
			listSet = document.getElementById("addHere2");
			let newrow = document.createElement("li");
			newrow.setAttribute('id', 'entry' + list[k].stormTrooperID);
			newrow.innerHTML = list[k].firstName + " " + list[k].lastName
					+ " (" + list[k].stormTrooperID + ")";
			listSet.appendChild(newrow);
			newrow.appendChild(document.createElement("br"));
			// --emailFunctionality
			let email2 = document.createElement("button");
			email2.style.background = "green";
			email2.style.color = "gold";
			email2.style.fontStyle = "bold";
			email2.style.borderRadius = "15px";
			email2.style.width = "80px";
			email2.style.height = "35px";
			email2.innerHTML = "Confirm";
			email2.setAttribute('onclick', 'emailConfirmation("'
					+ list[k].email + '")');
			// ---
			let deny = document.createElement("button");
			deny.style.background = "red";
			deny.style.color = "blue";
			deny.style.fontStyle = "bold";
			deny.style.width = "40px";
			deny.style.height = "30px";
			deny.innerHTML = "(X)";
			deny.style.border = "outset";

			deny.style.borderRadius = "50px";
			deny
					.setAttribute('onclick', 'deny(' + list[k].stormTrooperID
							+ ')');
			// --
			newrow.appendChild(email2);
			newrow.appendChild(deny);
			// --
		} else {
			break;
			document.getElementById("Notice").innerHTML = "Finish the Above workload and more results will populate later.. Lord Vader doesn't like it when his Administrators get behind on their work.. slows down the whole empire!";
		}
		columnIndex++;
	}
}

function emailConfirmation(email, t) {
	let link = "mailto:"
			+ email
			+ "?cc="
			+ "&subject="
			+ escape("Welcome to EmpireReimbursement-System")
			+ "&body="
			+ escape("This is an alert from your ERS Administration. We may have just "
					+ "added your information into our system! We have provided you now with a "
					+ "link ( http://localhost:9007/ERSp1/resources/FinishRegister.html )[<insert a space after the link if your email client does not recognize it as a hyperlink, initially]"
					+ " to finish setting up your account, ENTER YOUR EMPLOYEE ID: ("
					+ t + ")! All hail lord vader and stuff! -- Administration")
	window.location.href = link;
}
function showResolvers(resolverJSON) {

	document.getElementById("resolversShow").style.backgroundColor = "olive";
	console.log('huh?');
	let emailIndex = 1;
	for ( let r in resolverJSON) {
		let newLine = document.createElement("div");
		let emailButton = document.createElement("button");
		newLine.innerHTML = "Resolver: " + resolverJSON[r].lastname + ", "
				+ resolverJSON[r].firstname + " Username: ("
				+ resolverJSON[r].username + ")";
		newLine.style.fontStyle = "italic";
		newLine.setAttribute('id', '' + emailIndex + '');
		emailButton.setAttribute('onclick', 'EmailOverseer("' + emailIndex
				+ '") ');
		emailButton.innerHTML = "Email";
		emailButton.style.marginLeft = "20px";
		emailButton.style.borderRadius = "10px";
		emailButton.style.background = "red";
		// hide the email itself within the form ( we don't need to show
		// this-it's already getting big )-
		let hiddSpan = document.createElement("span");
		hiddSpan.innerHTML = resolverJSON[r].email;
		hiddSpan.style.visibility = "hidden";
		let spanID = emailIndex + "em";
		hiddSpan.setAttribute('id', '' + spanID + '');
		newLine.appendChild(hiddSpan);
		// -0-0-0-
		document.getElementById("resolversShow").appendChild(newLine);
		newLine.appendChild(emailButton);
		document.getElementById("resolversShow").style.border = "thick outset black";
		document.getElementById("resolversShow").style.width = "800px";
		document.getElementById("resolversShow").style.padding = "20px";
		document.getElementById("resolversButton").style.background = "silver";
		document.getElementById("resolversButton").style.border = "inset";
		document.getElementById("resolversButton").innerHTML = "Close Resolver List"
		document.getElementById("resolversButton").setAttribute('onclick',
				'closeRL()');
		emailIndex++;
	}
}
function EmailOverseer(e) {
	let email = document.getElementById("3em").innerHTML;

	let link = "mailto:"
			+ email
			+ "?cc="
			+ "&subject="
			+ escape("Work Alert from Darth Admin")
			+ "&body="
			+ escape("This is an alert from your ERS Administration. We may have updated your workload! Login to your management console or vader will hear of your failure.")
	window.location.href = link;

}

function closeRL() {
	let shower = document.getElementById("resolversShow");

	while (shower.firstChild) {

		shower.removeChild(shower.firstChild);
	}
	let newButton = document.createElement("button");
	document.getElementById("resolversShow").style.width = "100px";
	newButton.innerHTML = "Show";
	newButton.style.background = "red";
	newButton.style.borderRadius = "3px";
	newButton.setAttribute('onclick', 'populateResolvers()');
	newButton.setAttribute('id', 'resolversButton');
	shower.appendChild(newButton);
	document.getElementById("resolversButton").style.border = "outset";
	document.getElementById("resolversButton").style.background = "red";
}

function tableSetup(unUsernamedUsers) {
	for ( let i in unUsernamedUsers) {
		var id = unUsernamedUsers[i].ticketID;
		console.log(id);
		var status = unUsernamedUsers[i].status;
		console.log(name);
		let timedetails = unUsernamedUsers[i].dateSubmitted;
		let amountDetails = unUsernamedUsers[i].amount;
		let tikID = unUsernamedUsers[i].ticketID;
		let employeeName = unUsernamedUsers[i].submitterName;
		let empID = unUsernamedUsers[i].submitterID;
		let tikStatus = unUsernamedUsers[i].status;
		let assignedR = unUsernamedUsers[i].resolverID;
		let newRow = document.createElement('tr');
		let newDataID = document.createElement('td');
		newDataID.style.paddingLeft = "30px";
		let newDataST = document.createElement('td');
		newDataST.style.paddingLeft = "15px";
		let detailsButton = document.createElement('button');
		detailsButton.setAttribute("class", "btn btn-secondary btn-sm");
		detailsButton.style.border = "thin outset black";
		detailsButton.style.width = "120px";
		detailsButton.style.height = "30px";
		// --dynamic onclick
		detailsButton.innerHTML = 'Ticket-Details';
		detailsButton.setAttribute("onclick", "dynDetail('" + assignedR + "','"
				+ tikStatus + "','" + amountDetails + "', '" + timedetails
				+ "','" + tikID + "','" + employeeName + "','" + empID + "')");
		detailsButton.style.marginLeft = "30px";
		newRow.appendChild(newDataID);
		newRow.appendChild(newDataST);
		newRow.appendChild(detailsButton);
		newDataID.innerHTML = id;
		newDataST.innerHTML = status;
		let hiddenTbody = document.getElementById("AllTicketsView");
		hiddenTbody.appendChild(newRow);
	}
}

function dynDetail(assignedR, tikStatus, amount, time, id, name, empID) {
	document.getElementById("ticketDetails").style.display = "block";
	let adjustTime = time.substring(0, 16);
	let OwnrAmnt = document.getElementById("ticketOwnerAndAmount");
	let expInput = document.createElement('input');
	expInput.type = "text";
	expInput.name = 'expenseChange';
	expInput.placeholder = amount;

	OwnrAmnt.innerHTML = "Expense Amount: $" + amount + ".0 - Submitted by, "
			+ name + " (" + empID + ")";
	let record = document.getElementById("ticketSubmitted");
	record.innerHTML = "Submitted: " + adjustTime;
	let tikID = document.getElementById("ticketTitle");
	tikID.style.fontSize = "20px";
	tikID.innerHTML = "~======- Ticket #" + id + " -=~";
	let RS = document.getElementById("ResolverAndStatus");
	RS.innerHTML = "Current Status: " + tikStatus + " | Assigned to: "
			+ assignedR;

}

function closeTicket() {
	document.getElementById("ticketDetails").style.display = 'none';
}

function updateTicket() {

	document.getElementById("changeForm").style.display = "block";
	document.getElementById("update").onclick = hideUp;
	document.getElementById("update").innerHTML = "Hide";
	// ------
	let hiddenID = document.getElementById("TikID");

	hiddenID.style.display = "none";
	hiddenID.value = document.getElementById("ticketTitle").innerHTML;
}
function hideUp() {
	document.getElementById("changeForm").style.display = "none";
	document.getElementById("update").onclick = updateTicket;
	document.getElementById("update").innerHTML = "Update";
}

function revealTable() {
	document.getElementById("ticketsDisplay").style.display = "block";
	document.getElementById("buttonAdjustcolor1").onclick = HideTable;
}

function HideTable() {
	document.getElementById("ticketsDisplay").style.display = "none";
	document.getElementById("buttonAdjustcolor1").onclick = revealTable;
}
