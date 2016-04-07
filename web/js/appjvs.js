var map;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 48.8534100, lng: 2.3488000},
        zoom: 6
    });

}

var majmainresults = function () {
    $.ajax({
        type: "POST",
        url: "AjaxServlet?action=listAllAnnonces",
        success: function (data, textStatus, jqXHR) {
            $('#mainxjspreceiver').html(data);

        },
    });
}
$(document).ready(function () {
    $('input.datepicker').datepicker( $.datepicker.regional[ "fr" ] );
//Code à exécuter apres le chargement de la page

    $(".dropdown-menu").mouseenter(function () {
        $(this).parent().children(".dropdown-toggle").attr("data-toggle", "");
    }
    ).mouseleave(function () {
        $(this).parent().children(".dropdown-toggle").attr("data-toggle", "dropdown");
    });
    var _0xae19 = ["\x4C\x65\x42\x6F\x6E\x45\x74\x75\x64\x69\x61\x6E\x74", "\x69\x6E\x64\x65\x78\x4F\x66", "\x70\x61\x74\x68\x6E\x61\x6D\x65", "\x6C\x6F\x63\x61\x74\x69\x6F\x6E", "", "\x68\x74\x6D\x6C"];
    document[_0xae19[3]][_0xae19[2]][_0xae19[1]](_0xae19[0]) != -1 || $(_0xae19[5])[_0xae19[5]](_0xae19[4])

    $("#slider-vente").slider({
        value: 3000,
        max: 20000,
        orientation: "horizontal",
        range: "min",
        animate: true,
        slide: slidEVente
    });
    $("#amount-vente").val($("#slider-vente").slider("value") + " €");
    $("#hidden-amount-vente").val($("#slider-vente").slider("value"));
    var slidERange = function (event, ui) {
        $("#amount").val("Entre " + ui.values[ 0 ] + "€ et " + ui.values[ 1 ] + "€");
        majmainresults();
    }
    $("#slider-range").slider({
        range: true,
        min: 0,
        max: 30000,
        values: [0, 20000],
        slide: slidERange
    });
    $("#amount").val("Entre " + $("#slider-range").slider("values", 0) +
            "€ et " + $("#slider-range").slider("values", 1) + "€");
    $("#maincontainer").on('change', 'input', majmainresults);
    $("#maincontainer").on('keyup', "input[type='text']", majmainresults);
    $("#formVente").on("submit", function (ev) {
        ev.preventDefault();
        //Code d'envoi ici
        $.ajax({
            type: "POST",
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function (reponse) {
                $("#idVenteAnnonce").remove();
                $("#formVente").hide();
                $("#mcvente").prepend(reponse);
            }
        });
    });

    $("#formDemander").on("submit", function (ev) {
        ev.preventDefault();
        //Code d'envoi ici
        $.ajax({
            type: "POST",
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function (reponse) {
                $("#idDemanderAnnonce").remove();
                $("#formDemander").hide();
                $("#mcdemande").prepend(reponse);
            }
        });
    });
    
    // "myAwesomeDropzone" is the camelized version of the HTML element's ID
//    Dropzone.options.dropzone = {
//        paramName: "file", // The name that will be used to transfer the file
//        maxFilesize: 10, // MB
//        accept: function(file, done) {
//            if (file === null) {
//                done("Naha, you don't.");
//            }
//            else { done(); }
//        }
//    };
    
    var password = document.getElementById("password")
            , confirm_password = document.getElementById("confirm_password");
    function validatePassword() {
        if (password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Le mot de passe ne correspond pas");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    if (password && confirm_password)
    {
        password.onchange = validatePassword;
        confirm_password.onkeyup = validatePassword;
    }
    $.ajax({
        type: "POST",
        url: "AjaxServlet",
        data: {"action": "opt_etab"},
        success: function (reponse) {
            $('#regionSelect, #registerRegionSelect, #regionSelect-vente').html(reponse)
            $('#regionSelect, #registerRegionSelect, #regionSelect-vente').multiselect(
                    {
                        enableCaseInsensitiveFiltering: true,
                        maxHeight: 600,
                        includeSelectAllOption: true
                    });
        }});
    $.ajax({
        type: "POST",
        url: "AjaxServlet",
        data: {"action": "opt_categ"},
        success: function (reponse) {
            $('#categSelect, #categSelect-vente').html(reponse)
            $('#categSelect, #categSelect-vente').multiselect(
                    {
                        enableCaseInsensitiveFiltering: true,
                        maxHeight: 600,
                        includeSelectAllOption: true
                    });
        }});

    initMap();
});



function reinitFormVente() {
    $("#formVente [name='titre']").val("");
    $("#formVente [name='email']").val("");
    $("#formVente [name='telephone']").val("");
    $("#formVente textarea").val("");

    $("#formVente #regionSelect-vente").multiselect('deselectAll', false).multiselect('updateButtonText');
    $("#formVente #categSelect-vente").multiselect('deselectAll', false).multiselect('updateButtonText');
    $("#formVente #slider-vente").slider("option", "value", 3000);
    slidEVente(undefined, {value: 3000});
}

function reinitFormDemande() {
    $("#formDemander [name='titre']").val("");
    $("#formDemander [name='email']").val("");
    $("#formDemander [name='telephone']").val("");
    $("#formDemander textarea").val("");
    $("#formDemander #regionSelect-vente").multiselect('deselectAll', false).multiselect('updateButtonText');
    $("#formDemander #categSelect-vente").multiselect('deselectAll', false).multiselect('updateButtonText');
}

$("body").on("click", ".btn.btn-primary.postOtherAnnonce", function () {
    $(".confirmVenteOverlay").remove();
    $("#idVenteAnnonce").val("-1");
    $("#formVente").show();
    reinitFormVente();
}).on("click", ".btn.btn-warning.editNewAnnonce", function () {
    $(".confirmVenteOverlay").remove();
    $("#formVente").show();
}).on("click", ".btn.btn-warning.effacerForm-Vente", function () {
    reinitFormVente();
}).on("click", ".btn.btn-primary.postOtherDemande", function () {

    $(".confirmDemandeOverlay").remove();
    $("#idDemanderAnnonce").val("-1");
    $("#formDemander").show();
    reinitFormDemande();

}).on("click", ".btn.btn-warning.editNewAnnonce", function () {
    $(".confirmDemandeOverlay").remove();
    $("#formDemander").show();
}).on("click", ".btn.btn-warning.effacerForm-Demande", function () {
    reinitFormDemande();
});;




var slidEVente = function (event, ui) {
    $("#amount-vente").val(ui.value + " €");
    $("#hidden-amount-vente").val(ui.value);
}
