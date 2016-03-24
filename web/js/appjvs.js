 var map;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 48.8534100, lng: 2.3488000},
          zoom: 6
        });
        
      }
$(document).ready(function (){
    //Code à exécuter apres le chargement de la page
    initMap();
     $('#regionSelect').multiselect();
     $('#categSelect').multiselect();
       $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 30000,
      values: [ 0, 20000 ],
      slide: function( event, ui ) {
        $( "#amount" ).val( "Entre " + ui.values[ 0 ] + "€ et " + ui.values[ 1 ]+"€" );
      }
    });
    
    
    $( "#amount" ).val( "Entre " + $( "#slider-range" ).slider( "values", 0 ) +
      "€ et " + $( "#slider-range" ).slider( "values", 1 ) +"€");

});