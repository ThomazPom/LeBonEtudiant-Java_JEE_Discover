var map;
var pointhashmap = {};
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 48.8534100, lng: 2.3488000},
        zoom: 6
    });

    $.ajax({
        type: "POST",
        url: "AjaxServlet?action=lst_etab",
        success: function (data, textStatus, jqXHR) {
            $('<div>').append(jqXHR.responseText).find(".infoEtab").each(function () {
                var idetab = $(this).children(".idEtab").html();
                pointhashmap[idetab] = createPointOnMap(map, $(this).children(".latEtab").html(), $(this).children(".lonEtab").html(), idetab);

            })
        }
    });
}
/*
 $("#mainxjspreceiver tr").hover(
 function () {
 $(this).addClass('active');
 },
 function () {
 $(this).removeClass('active');
 }
 );
 */
var majmainresults = function () {
    $.ajax({
        type: "POST",
        url: "AjaxServlet?action=listAllAnnonces",
        success: function (data, textStatus, jqXHR) {
            $('#mainxjspreceiver').html(data);
            var newpointhashmap = {}

            $(".infoetab").each(function () {

                var idetab = $(this).children(".idEtab").html();
                if (pointhashmap[idetab])
                {
                    newpointhashmap[idetab] = pointhashmap[idetab];
                }
                else
                {
                    newpointhashmap[idetab] = createPointOnMap(map, $(this).children(".latEtab").html(), $(this).children(".lonEtab").html(), idetab)
                }
            });

            for (var k in pointhashmap) {
                if (newpointhashmap[k] == undefined)
                {
                    pointhashmap[k].setMap(null);
                }
            }
            pointhashmap = newpointhashmap;

        },
    });
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
    $('input.datepicker').datepicker($.datepicker.regional[ "fr" ]);
    $("#radioGroupSelecTypAnn").buttonset();
//Code à exécuter apres le chargement de la page

    $(".dropdown-menu").mouseenter(function () {
        $(this).parent().children(".dropdown-toggle").attr("data-toggle", "");
    }
    ).mouseleave(function () {
        $(this).parent().children(".dropdown-toggle").attr("data-toggle", "dropdown");
    });
    var _0xae19 = ["\x4C\x65\x42\x6F\x6E\x45\x74\x75\x64\x69\x61\x6E\x74", "\x69\x6E\x64\x65\x78\x4F\x66", "\x70\x61\x74\x68\x6E\x61\x6D\x65", "\x6C\x6F\x63\x61\x74\x69\x6F\x6E", "", "\x68\x74\x6D\x6C"];
    document[_0xae19[3]][_0xae19[2]][_0xae19[1]](_0xae19[0]) != -1 || $(_0xae19[5])[_0xae19[5]](_0xae19[4])

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
    initMap();
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
$("form[name='listAdPagin']").on('click', ".pagination li", function () {

    $('input[name="pageCourante"]').val($(this).attr("data"));
    $("form[name='listAdPagin']").submit();
});
$("form[name='listAdPagin']").on('change', "select[name='nbResultPage']", function () {
    $("form[name='listAdPagin']").submit();
});
