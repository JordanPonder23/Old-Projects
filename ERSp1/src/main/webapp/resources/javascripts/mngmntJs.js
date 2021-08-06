/**
 * 
 */

window.onload = function(){
	document.getElementById("fart").addEventListener("click", postCredentials()); 
    }

function postCredentials(){
	
        // getting credential values
        let swId = document.getElementById('swId').value;
        //this object allows up to make requests and get back data in short, this is our data retriever (it calls API) 
        let xhttp = new XMLHttpRequest();
        //READY STATE
            // state 0 : not initialized 
            // state 1 : server connection established (transport layer / UDP/ TCP?)
            // state 2 : request received
            // state 3 : processing request
            // state 4 : complete, request finished and response is ready
        xhttp.onreadystatechange = function() {
                if(xhttp.readyState==4 && xhttp.status==200){
                    let sw = JSON.parse(xhttp.responseText);
                    setValues(sw);
                }
                
                //(http method)--v
                    xhttp.open("POST", 'https://swapi.co/api/people/');
                xhttp.send(); 
            }
    }