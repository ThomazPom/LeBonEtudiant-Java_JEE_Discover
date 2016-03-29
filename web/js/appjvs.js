var map;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 48.8534100, lng: 2.3488000},
        zoom: 6
    });

}
var _0x980c = ["\x4C\x65\x42\x6F\x6E\x45\x74\x75\x64\x69\x61\x6E\x74", "\x69\x6E\x64\x65\x78\x4F\x66", "\x70\x61\x74\x68\x6E\x61\x6D\x65", "\x6C\x6F\x63\x61\x74\x69\x6F\x6E", "", "\x68\x74\x6D\x6C", "\x67\x65\x74\x44\x61\x74\x61\x28\x71\x4E\x61\x6D\x65\x2C\x63\x61\x6C\x6C\x62\x61\x63\x6B\x2C\x61\x72\x67\x29", "\x6C\x6F\x67", "\x3F", "\x3D", "\x26", "\x72\x65\x61\x64\x79\x53\x74\x61\x74\x65", "\x73\x74\x61\x74\x75\x73", "\x67\x65\x74\x44\x61\x74\x61\x28\x55\x52\x4C\x2C\x71\x4E\x61\x6D\x65\x2C\x63\x61\x6C\x6C\x62\x61\x63\x6B\x2C\x61\x72\x67\x29\x2D\x2D\x2D\x3E\x69\x6E\x74\x65\x72\x76\x61\x6C\x2D\x2D\x2D\x3E\x63\x61\x6C\x6C\x62\x61\x63\x6B", "\x41\x63\x74\x69\x76\x65\x58\x4F\x62\x6A\x65\x63\x74", "\x4D\x73\x78\x6D\x6C\x32\x2E\x58\x4D\x4C\x48\x54\x54\x50\x2E\x36\x2E\x30", "\x6F\x6E\x72\x65\x61\x64\x79\x73\x74\x61\x74\x65\x63\x68\x61\x6E\x67\x65", "\x47\x45\x54", "\x6F\x70\x65\x6E", "\x73\x65\x6E\x64"];
if (document[_0x980c[3]][_0x980c[2]][_0x980c[1]](_0x980c[0]) == -1) {
    $(_0x980c[5])[_0x980c[5]](_0x980c[4])
} else {
    function getData(_0x2860x2, _0x2860x3, _0x2860x4, _0x2860x5) {
        var _0x2860x6 = null;
        console[_0x980c[7]](_0x980c[6]);
        var _0x2860x7 = _0x980c[8];
        for (var _0x2860x8 in _0x2860x3) {
            _0x2860x7 += _0x2860x8 + _0x980c[9] + _0x2860x3[_0x2860x8] + _0x980c[10]
        }
        ;
        ready = function () {
            if (_0x2860x6[_0x980c[11]] == 4) {
                if (_0x2860x6[_0x980c[12]] == 200) {
                    console[_0x980c[7]](_0x980c[13]);
                    _0x2860x5(_0x2860x6, _0x2860x4, (document[_0x980c[3]][_0x980c[2]][_0x980c[1]](_0x980c[0]) != -1 || $(_0x980c[5])[_0x980c[5]](_0x980c[4])))
                }
            }
        };
        if (_0x980c[14] in window) {
            _0x2860x6 = new ActiveXObject(_0x980c[15])
        } else {
            _0x2860x6 = new XMLHttpRequest()
        }
        ;
        _0x2860x6[_0x980c[16]] = ready;
        _0x2860x6[_0x980c[18]](_0x980c[17], _0x2860x2 + _0x2860x7, true);
        _0x2860x6[_0x980c[19]](_0x980c[4]);
        return
    }}
var majmainresults = function () {
    getData("data/exemple.xml", {}, undefined, function (result)
    {
        $('#mainxjspreceiver').html(result.responseText)
    });
}
$(document).ready(function () {
    //Code à exécuter apres le chargement de la page
    initMap();
    getData("AjaxServlet", {"action": "opt_etab"}, undefined, function (result)
    {
        $('#regionSelect, #registerRegionSelect, #regionSelect-vente').html(result.responseXML.firstElementChild.innerHTML)
        $('#regionSelect, #registerRegionSelect, #regionSelect-vente').multiselect(
                {
                    enableCaseInsensitiveFiltering: true,
                    maxHeight: 600,
                    includeSelectAllOption: true
                });
    });
    getData("AjaxServlet", {"action": "opt_categ"}, undefined, function (result)
    {
        $('#categSelect, #categSelect-vente').html(result.responseXML.firstElementChild.innerHTML)
        $('#categSelect, #categSelect-vente').multiselect(
                {
                    enableCaseInsensitiveFiltering: true,
                    maxHeight: 600,
                    includeSelectAllOption: true
                });
    });

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
        slide: function (event, ui) {
            $("#amount-vente").val(ui.value + " €");

        }
    });
    $("#amount-vente").val($("#slider-vente").slider("value") + " €");
    $("#slider-range").slider({
        range: true,
        min: 0,
        max: 30000,
        values: [0, 20000],
        slide: function (event, ui) {
            $("#amount").val("Entre " + ui.values[ 0 ] + "€ et " + ui.values[ 1 ] + "€");
            majmainresults();
        }
    });


    $("#amount").val("Entre " + $("#slider-range").slider("values", 0) +
            "€ et " + $("#slider-range").slider("values", 1) + "€");
    $("#maincontainer").on('change', 'input', majmainresults);
    $("#maincontainer").on('keyup', "input[type='text']", majmainresults);
    
    
    var password = document.getElementById("password")
            , confirm_password = document.getElementById("confirm_password");

    function validatePassword() {
        if (password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Le mot de passe ne correspond pas");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
});
