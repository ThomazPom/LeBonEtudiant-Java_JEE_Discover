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
})