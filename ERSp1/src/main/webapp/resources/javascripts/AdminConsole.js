///**
// * ADMIN CONSOLE JS FILE
// */
//window.onload = function(){
//    
//	document.getElementById("EmptyForm").addEventListener("click", function() {
//		document.getElementById("OverseerForm").style.display = "none";
//		document.getElementById("EmptyForm").style.display = "none";
//		document.getElementById("OverseerCreate").style.display = "block";
//		document.getElementById("OverseerFormRl").reset();
//	});
//	// EmptyForm2
//	document.getElementById("EmptyForm2").addEventListener("click", function() {
//
//		document.getElementById("EmployeeForm1").style.display = "none";
//		document.getElementById("EmptyForm2").style.display = "none";
//		document.getElementById("EmpCreate").style.display = "block";
//		document.getElementById("EmployeeFormID").reset();
//
//	});
//
//	// EmpCreate
//	document.getElementById("EmpCreate").addEventListener("click", function() {
//		document.getElementById("EmployeeForm1").style.display = "block";
//		document.getElementById("EmptyForm2").style.display = "block";
//		document.getElementById("EmpCreate").style.display = "none";
//
//	});
//	document
//			.getElementById("buttonAdjustcolor")
//			.addEventListener(
//					"click",
//					function() {
//						document.getElementById("buttonAdjustcolor1").style.display = "none";
//					});
//	document
//			.getElementById("OverseerCreate")
//			.addEventListener(
//					"click",
//					function() {
//						document.getElementById("OverseerCreate").style.display = "none";
//
//					});
//	document
//			.getElementById("OverseerCreate")
//			.addEventListener(
//					"click",
//					function() {
//						document.getElementById("OverseerForm").style.display = "block";
//						document.getElementById("EmptyForm").style.display = "block";
//
//					});
//	document.getElementById("buttonAdjustcolor2").addEventListener("click",
//			function() {
//				console.log("button can do two things");
//			});
//	//-------------------------------------------------
//	document.getElementById("Reveal").addEventListener("click", function(){
//
//window.onload = function(){
//        let xhttp = new XMLHttpRequest();
//  xhttp.addEventListner("load", function(){
//	  alert(this.responseText);
//	  
//  });
//        
//            xhttp.onreadystatechange = function(){
//             
//
//                if(xhttp.readyState==4 && xhttp.status==200){
//                      
//                }      
//            }
//            xhttp.open("POST",'/ERSp1/resources/pullEmailList.ms');
//            xhttp.send();      
//}

//}
//window.onload = function(){
////Reveal
//document.getElementById("Reveal").addEventListener("click", function(){
//
//	 	let xhttp = new XMLHttpRequest();
//	 	xhttp.addEventListener("load", function(){
//	 			alert(this.responseText);
//	 			let unUsernamedUsers = JSON.parse(this.responseText);
//// alert(unUsernamedUsers);
//			
//let div = document.createElement("div");
//div.style.backgroundImage ="linear-gradient(to right, rgba(0, 0, 0, .5), white, #800000, rgba(0, 0, 0, .78))";
//div.style.border ="inset";
//div.style.borderWidth ="5px";
//div.style.borderColor="silver";
//div.style.paddingLeft ="30px";
//div.setAttribute("id", "EmailListCase");
//document.getElementById("LstUsers").appendChild(div);
//let line1 = document.createElement("li");
//		 	
//document.getElementById("EmailListCase").appendChild(line1);
//line1.innerhtml ="First Name -- Last Name == Given Email";
//		 	
//let index = 0;
//for(let i =0; unUsernamedUsers.length; i++){
//unUsernamedUsers[i].firstName;
//unUsernamedUsers[i].lastName;
//unUsernamedUsers[i].email;
//			 	
//let Validate = document.createElement("input");
//Validate.type = "button";
//Validate.style.width = "200px";
//Validate.style.height = "34px";
//Validate.value = "Validate Employee";
//// Validate.onclick('click', sendEmail);
//let line = document.createElement("li");
//// line.appendChild(Validate);
//line.innerHTML = unUsernamedUsers[i].firstName + " "
//+unUsernamedUsers[i].lastName+" ";
//			 	 
//document.getElementById("addHere").appendChild(line);
//			 	
//let elemForm = document.createElement('form');
//let input = document.createElement('input');
//input.type = "text";
//input.setAttribute("id", i.toString()); //each input will have a unique id -8^
//
//input.setAttribute("Placeholder", unUsernamedUsers[i].email);
//input.value = unUsernamedUsers[i].email;
//elemForm.appendChild(input);
//var span = document.createElement('span');
//span.innerHTML = '<button id="but" onclick="callJavascriptFunction()" />';
//elemForm.appendChild(Validate);
//line.appendChild(elemForm);
//			 	
//		}
//	});
//
//
//		xhttp.onreadystatechange = function(){
//
//				if(xhttp.readyState==4 && xhttp.status==200){
//
//					}
//			}
//			xhttp.open("POST",'/ERSp1/resources/pullEmailList.ms');
//
//			xhttp.send();
//	   
//});
//
//}
//window.onload = function() {
//	document.getElementById("EmptyForm").addEventListener("click", function() {
//		document.getElementById("OverseerForm").style.display = "none";
//		document.getElementById("EmptyForm").style.display = "none";
//		document.getElementById("OverseerCreate").style.display = "block";
//		document.getElementById("OverseerFormRl").reset();
//	});
//	// EmptyForm2
//	document.getElementById("EmptyForm2").addEventListener("click", function() {
//
//		document.getElementById("EmployeeForm1").style.display = "none";
//		document.getElementById("EmptyForm2").style.display = "none";
//		document.getElementById("EmpCreate").style.display = "block";
//		document.getElementById("EmployeeFormID").reset();
//
//	});
//
//	// EmpCreate
//	document.getElementById("EmpCreate").addEventListener("click", function() {
//		document.getElementById("EmployeeForm1").style.display = "block";
//		document.getElementById("EmptyForm2").style.display = "block";
//		document.getElementById("EmpCreate").style.display = "none";
//
//	});
//	document
//			.getElementById("buttonAdjustcolor")
//			.addEventListener(
//					"click",
//					function() {
//						document.getElementById("buttonAdjustcolor1").style.display = "none";
//					});
//	document
//			.getElementById("OverseerCreate")
//			.addEventListener(
//					"click",
//					function() {
//						document.getElementById("OverseerCreate").style.display = "none";
//
//					});
//	document
//			.getElementById("OverseerCreate")
//			.addEventListener(
//					"click",
//					function() {
//						document.getElementById("OverseerForm").style.display = "block";
//						document.getElementById("EmptyForm").style.display = "block";
//
//					});
//	document.getElementById("buttonAdjustcolor2").addEventListener("click",
//			function() {
//				console.log("button can do two things");
//			});
//}
//(i.toString(), unUsernamedUsers[i].firstName, unUsernamedUsers[i].lastName,
//unUsernamedUsers[i].email)
//_emailIndex, email

//function sendEmail() { // id passed from each unique input (so admin can change
//	// the email if they'd like) -v-8
//	let url = "http://localhost:9007/ERSp1/resources/AdminConsole.html";
//	let link = "mailto: "
//			+ email
//			+ "?cc="
//			+ email
//			+ ""
//			+ "&subject="
//			+ escape("Greetings From, Darth Admin")
//			+ "&body="
//			+ url
//			+ " "
//			+ escape(" Greetings Jordan, We, at _____, would like to learn more about the value you can bring as a front-end developer to our company!");
//	window.location.href = link;  }
