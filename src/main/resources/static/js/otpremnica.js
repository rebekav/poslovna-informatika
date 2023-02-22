$(document).ready(function(){

    racunOtpremnice();

    $("#addStavkaOtpremnice").click(function(e){

        $("#addModal").modal("show");
        $("#confirmSave").on("click",function(e) {
            var kolicina = $("#kolicina").val();
            var roba = $("#roba").val();
            var robaNaziv;
            var jedinicaM;
            var cena;
            var ukupanIznos;
            if (!roba || !kolicina || kolicina<=0 )
                return;
            $.ajax({url: '/roba/'+roba,
                dataType: "json",
                contentType: "application/json",
                async: false,
                success: function(data){
                    robaNaziv=data.nazivRobe;
                    jedinicaM=data.jedinicaMere;
                }});


            var id = window.location.href.slice(window.location.href.indexOf('detalji/') + 8);
            var stavkaOtpremnice = {
                jedinicaMere: jedinicaM,
                kolicina: kolicina,
                opis: robaNaziv,
                roba:roba,
                otpremnica: id,
                ukupanIznos:jedinicnaCena*kolicina,
                cena: jedinicnaCena
            }
            $.ajax({url: '/stavkaOtpremnice',
                dataType: 'json',
                contentType:'application/json',
                type:'POST',
                data: JSON.stringify(stavkaOtpremnice),
                complete: function(data){
                    racunOtpremnice();
                    window.location.reload();

                }
            });
            $("#addModal").modal("hide");
        });
    })

    $("#roba").change(function(e){
        e.preventDefault();
        var id = $("#roba").val();
        $.ajax({url: '/roba/cena/'+id,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: function(data){
                console.log(data);
                jedinicnaCena = data.cena;
                $("#jedinicnaCena").text(data.cena);
                izracunajUkupanIznos();
            }});
    });
    $("#kolicina").on("input",function(e){
        e.preventDefault();
        izracunajUkupanIznos();
    });

    function izracunajUkupanIznos(){
        var kolicina = $("#kolicina").val();
        $("#ukupanIznos").text(jedinicnaCena*kolicina);
    };

    function racunOtpremnice() {
        var rezultat = 0;
        rezultat = parseFloat(rezultat);
        $(".ukupno").each(function(){
            var rez = parseFloat($(this).html());
            rezultat+=rez;
        });
        $("#rezultat").html(rezultat);
    }
});