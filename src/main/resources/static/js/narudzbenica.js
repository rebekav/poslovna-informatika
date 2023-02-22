$(document).ready(function(){

    $("#addStavkaNarudzbenica").click(function(e){

        $("#addModal").modal("show");
        $("#confirmSave").on("click",function(e) {
            var kolicina = $("#kolicina").val();
            var roba = $("#roba").val();
            var robaNaziv;
            var jedinicaM;
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
            var stavkaNarudzbenice = {
                jedinicaMere: jedinicaM,
                kolicina: kolicina,
                opis: robaNaziv,
                narudzbenica: id,
                roba: roba
            }
            $.ajax({url: '/stavkaNarudzbenice',
                dataType: 'json',
                contentType:'application/json',
                type:'POST',
                data: JSON.stringify(stavkaNarudzbenice),
                complete: function(data){
                    window.location.reload();
                }
            });
            $("#addModal").modal("hide");
        });
    })

});