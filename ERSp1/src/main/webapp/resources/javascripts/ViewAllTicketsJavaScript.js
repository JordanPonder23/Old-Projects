/**
 * For the 'seeAvailable Resolvers' button on the AdminConsole 
 */


window.onload = function(){
	
let xhttp = new XMLHttpRequest();
xhttp.addEventListener("load", function(){
	//alert(this.responseText);
	let unUsernamedUsers = JSON.parse(this.responseText);

	console.log(unUsernamedUsers);
});

xhttp.onreadystatechange = function(){
    

    if(xhttp.readyState==4 && xhttp.status==200){
             
    }      
}
xhttp.open("POST",'http://localhost:9007/ERSp1/resources/viewAllTickets.ms');
xhttp.send();      

}
