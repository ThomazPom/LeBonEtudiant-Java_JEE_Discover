 var map;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 48.8534100, lng: 2.3488000},
          zoom: 6
        });
        
      }
      function getData(URL,args,callBackArg,callback)
{
	var req=null;
	console.log("getData(qName,callback,arg)");
	var argString="?";
	for (var k in args){
		argString+=k+"="+args[k]+"&";
	}
	ready = function() {
		if (req.readyState == 4) {
			if(req.status == 200) {
				console.log("getData(URL,qName,callback,arg)--->interval--->callback");
				callback(req,callBackArg);
			}
		}
	}
	if ("ActiveXObject" in window)
	{            
		req = new ActiveXObject("Msxml2.XMLHTTP.6.0");
	}
	else 
	{
		req = new XMLHttpRequest();	
	}
	req.onreadystatechange = ready;
	req.open("GET", URL+argString, true);
	req.send("");
	return;

}
var majmainresults = function(){
    getData("data/exemple.xml",{},undefined,function(result)
    {
        lol=result;
        $('#mainxjspreceiver').html(result.responseText)
    });
}
$(document).ready(function (){
    //Code à exécuter apres le chargement de la page
    initMap();
     $('#regionSelect').multiselect();
     $('#categSelect').multiselect();
       $( "#slider-vente" ).slider({
      range: false,
      min: 0,
      max: 30000,
      value: 50 ,
      slide: function( event, ui ) {
        $( "#amount-vente" ).val(ui.values[ 0 ] + "€" );
      }
    });
       $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 30000,
      values: [ 0, 20000 ],
      slide: function( event, ui ) {
        $( "#amount" ).val( "Entre " + ui.values[ 0 ] + "€ et " + ui.values[ 1 ]+"€" );
            majmainresults();
      }
    });
    
    
    $( "#amount" ).val( "Entre " + $( "#slider-range" ).slider( "values", 0 ) +
      "€ et " + $( "#slider-range" ).slider( "values", 1 ) +"€");
$("#maincontainer").on('change', 'input', majmainresults);
$("#maincontainer").on('keyup', "input[type='text']", majmainresults);

});
