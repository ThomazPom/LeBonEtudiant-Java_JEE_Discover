
var pointhashmap = {};
var map;

function initMap() {
    map = new google.maps.Map(map[0], {
        center: {lat: 48.8534100, lng: 2.3488000},
        zoom: 6
    });

    $.ajax({
        type: "POST",
        url: "AjaxServlet?action=lst_etab",
        success: function (data, textStatus, jqXHR) {
            $('<div>').append(jqXHR.responseText).find(".infoEtab").each(function () {
                var idetab = $(this).children(".idEtab").html();
                pointhashmap[idetab] = createPointOnMap(map[0], $(this).children(".latEtab").html(), $(this).children(".lonEtab").html(), idetab);

            })
        }
    });
}
var refreshAjax = function () {
    $.ajax({
        type: "POST",
        data: $("#mainwrap").serialize(),
        url: "AjaxServlet",
        success: function (data, textStatus, jqXHR) {
            $('#mainxjspreceiver').html(data);
            var newpointhashmap = {}

            var jqInfoEtabs = $("<div>" + jqXHR.responseText + "</div>").find(".infoetab");


            jqInfoEtabs.each(function () {
                var idetab = $(this).children(".idEtab").html();
                newpointhashmap[idetab] = "point!"
            });

            for (var k in pointhashmap) {
                if (newpointhashmap[k] && !pointhashmap[k].map)
                {
                    pointhashmap[k].setMap(map)
                }
                else if (!newpointhashmap[k])
                {

                    pointhashmap[k].setMap(null);
                }
            }



        },
    });
}
var timer;

var majmainresults = function (e) {

        clearTimeout(timer);
        timer = setTimeout(refreshAjax, 150);
    

}

function createPointOnMap(map, latitude, longitude, UAI)
{
    //console.log(latitude + " " + longitude + " " + UAI);
    var xsltReceiver = document.createElement("div");
    var infowindow = new google.maps.InfoWindow({
        content: xsltReceiver
    });
    var marker = new google.maps.Marker({
        position: {lat: parseFloat(latitude), lng: parseFloat(longitude)},
        map: map
    });
    marker.addListener('click', function () {
        infowindow.open(map, marker);
    });
    return marker;
}

$(document).ready(function () {

    map = $("#map");
    if ($("input.datepicker")[0])
    {
        $('input.datepicker').datepicker($.datepicker.regional[ "fr" ]);
    }
    $("#radioGroupSelecTypAnn").buttonset();
//Code à exécuter apres le chargement de la page

    $(".dropdown-menu").mouseenter(function () {
        $(this).parent().children(".dropdown-toggle").attr("data-toggle", "");
    }
    ).mouseleave(function () {
        $(this).parent().children(".dropdown-toggle").attr("data-toggle", "dropdown");
    });

    $("#slider-demande").slider({
        value: 3000,
        max: 20000,
        orientation: "horizontal",
        range: "min",
        animate: true,
        slide: sliDemande
    });

    $("#amount-annonce-demande").val($("#slider-demande").slider("value") + " €");
    $("#hidden-amount-annonce-demande").val($("#slider-demande").slider("value"));

    $("#slider-vente").slider({
        value: 3000,
        max: 20000,
        orientation: "horizontal",
        range: "min",
        animate: true,
        slide: slidEVente
    });
    $("#amount-annonce-vente").val($("#slider-vente").slider("value") + " €");
    $("#hidden-amount-annonce-vente").val($("#slider-vente").slider("value"));

    var slidERange = function (event, ui) {
        $("#amount").val("Entre " + ui.values[ 0 ] + "€ et " + ui.values[ 1 ] + "€");
        $("input[name='prixmin-search']").val(ui.values[ 0 ]);
        $("input[name='prixmax-search']").val(ui.values[ 1 ]);
        majmainresults();
    }
    rangevalues = [0, 20000];
    sliderRange = $("#slider-range").slider({
        range: true,
        min: 0,
        max: 30000,
        values: rangevalues,
        slide: slidERange
    });

    $("input[name='prixmin-search']").val(rangevalues[0]);
    $("input[name='prixmax-search']").val(rangevalues[ 1 ]);
    $("#amount").val("Entre " + $("#slider-range").slider("values", 0) +
            "€ et " + $("#slider-range").slider("values", 1) + "€");
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

    $("#formDemande").on("submit", function (ev) {
        ev.preventDefault();
        //Code d'envoi ici
        $.ajax({
            type: "POST",
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function (reponse) {
                $("#idDemandeAnnonce").remove();
                $("#formDemande").hide();
                $("#mcdemande").prepend(reponse);
            }
        });
    });


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
            $('#etabSelectSearch, #registerRegionSelect, #etabSelectVente,#etabSelectDemande').html(reponse)
            $('#etabSelectSearch, #registerRegionSelect, #etabSelectVente,#etabSelectDemande').multiselect(
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
            $('#categSelect, #categSelect-vente, #categSelect-demande').html(reponse)
            $('#categSelect, #categSelect-vente ,#categSelect-demande').multiselect(
                    {
                        enableCaseInsensitiveFiltering: true,
                        maxHeight: 600,
                        includeSelectAllOption: true
                    });
        }});
    $.ajax({
        type: "POST",
        url: "AjaxServlet",
        data: {"action": "opt_ville"},
        success: function (reponse) {
            $('#villeSelectSearch').html(reponse)
            $('#villeSelectSearch').multiselect(
                    {
                        enableCaseInsensitiveFiltering: true,
                        maxHeight: 600,
                        includeSelectAllOption: true
                    });
        }});
    $.ajax({
        type: "POST",
        url: "AjaxServlet",
        data: {"action": "opt_dept"},
        success: function (reponse) {
            $('#deptSelectSearch').html(reponse)
            $('#deptSelectSearch').multiselect(
                    {
                        enableCaseInsensitiveFiltering: true,
                        maxHeight: 600,
                        includeSelectAllOption: true
                    });
        }});
    $.ajax({
        type: "POST",
        url: "AjaxServlet",
        data: {"action": "opt_region"},
        success: function (reponse) {
            $('#regionSelectSearch').html(reponse)
            $('#regionSelectSearch').multiselect(
                    {
                        enableCaseInsensitiveFiltering: true,
                        maxHeight: 600,
                        includeSelectAllOption: true
                    });
        }});
    if (document.getElementById('map')) {
        initMap()
    }
    ;
    $("#maincontainer").on('change', 'input', majmainresults).on('keyup', "input[type='text']", majmainresults);

});



function reinitFormVente() {
    $("#formVente [name='titre']").val("");
    $("#formVente [name='email']").val("");
    $("#formVente [name='telephone']").val("");
    $("#formVente textarea").val("");
    $("#formVente #etabSelectVente").multiselect('deselectAll', false).multiselect('updateButtonText');
    $("#formVente #categSelect-vente").multiselect('deselectAll', false).multiselect('updateButtonText');
    $("#formVente #slider-vente").slider("option", "value", 3000);
    slidEVente(undefined, {value: 3000});
}

function reinitFormDemande() {
    $("#formDemande [name='titre']").val("");
    $("#formDemande [name='email']").val("");
    $("#formDemande [name='telephone']").val("");
    $("#formDemande textarea").val("");
    $("#formDemande #etabSelectDemande").multiselect('deselectAll', false).multiselect('updateButtonText');
    $("#formDemande #categSelect-demande").multiselect('deselectAll', false).multiselect('updateButtonText');
    $("#formDemande #slider-demande").slider("option", "value", 3000);
    sliDemande(undefined, {value: 3000});
}

$("body").on("click", ".btn.btn-primary.postOtherVente", function () {
    $(".confirmVenteOverlay").remove();
    $("#idVenteAnnonce").val("-1");
    $("#formVente").show();
    reinitFormVente();
}).on("click", ".btn.btn-warning.editVente", function () {
    $(".confirmVenteOverlay").remove();
    $("#formVente").show();
}).on("click", ".btn.btn-warning.effacerForm-Vente", function () {
    reinitFormVente();
}).on("click", ".btn.btn-primary.postOtherDemande", function () {
    $(".confirmDemandeOverlay").remove();
    $("#idDemandeAnnonce").val("-1");
    $("#formDemande").show();
    reinitFormDemande();
}).on("click", ".btn.btn-warning.editDemande", function () {
    $(".confirmDemandeOverlay").remove();
    $("#formDemande").show();
}).on("click", ".btn.btn-warning.effacerForm-Demande", function () {
    reinitFormDemande();
});
;

var slidEVente = function (event, ui) {
    $("#amount-annonce-vente").val(ui.value + " €");
    $("#hidden-amount-annonce-vente").val(ui.value);
}
var sliDemande = function (event, ui) {
    $("#amount-annonce-demande").val(ui.value + " €");
    $("#hidden-amount-annonce-demande").val(ui.value);
}
//*****pagination*******//
$("body").on('click', "form[name='listUserPagin'] .pagination li,form[name='listAdPagin']  .pagination li", function () {

    $('input[name="pageCourante"]').val($(this).attr("data"));
    $("form[name='listAdPagin'],form[name='listUserPagin']").submit();
});
$("body").on('change', "form[name='listUserPagin'] select[name='nbResultPage'],form[name='listAdPagin'] select[name='nbResultPage']", function () {
    $("form[name='listAdPagin'],form[name='listUserPagin']").submit();
});
$("body").on('submit', "form[name='listAdPagin'], form[name='listUserPagin']", function (e) {
    e.preventDefault();
    var container = $(this).parent();
    $.ajax({
        url: "AjaxServlet",
        type: 'POST',
        data: $(this).serialize(),
        success: function (responseHTML, status, xhr)
        {
            container.html(responseHTML);
        }

    })
});
