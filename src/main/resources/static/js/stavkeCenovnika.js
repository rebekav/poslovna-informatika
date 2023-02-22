$("#addStavkaCenovnika").click(function(e){

    $("#addModal").modal("show");
    $("#confirmSave").on("click",function(e) {
        var cena = $("#cena").val();
        var roba = $("#roba").val();



        var cenovnikid = $('.cenovnikId').val();
        $.ajax({url: '/stavkeCenovnika',
            dataType: 'json',
            contentType:'application/json',
            type:'GET',
            data: {
                cenovnikId: cenovnikid,
                cena: cena,
                robaId: roba

            },
            complete: function(data){
                window.location.reload();
            }
        });
        $("#addModal").modal("hide");
    });
})