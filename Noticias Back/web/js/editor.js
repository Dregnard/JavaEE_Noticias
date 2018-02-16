$(document).ready(function(){
    $('#imagen').fileinput({
        allowedFileExtensions: ['jpg', 'png']
    });
});

function insertarNoticia() {
    $("#modalLoad").modal("show");
    if (existeSlug() === false) {
        guardarNoticia();
    } else {
        $("#modalLoad").modal("hide");
        $("#modalTitle").html("Error");
        $("#modalBody").html("Ya existe una noticia con el mismo slug. Cambia el título de la noticia y vuelve a intentarlo otra vez.");
        $("#modalMsg").modal("show");
    }
    return false;
}

function existeSlug() {
    var titulo = $("#titulo").val();
    var existe = false;
    $.ajax({
        url: "ExistNoticiaBySlug",
        async: false,
        data: {titulo: titulo},
        success: function (json) {
            existe = false;
        },
        error: function (e) {
            existe = true;
        }
    });
    return existe;
}

function guardarNoticia() {
    var titulo = $("#titulo").val();
    var noticia = tinyMCE.activeEditor.getContent();
    var imagen = $('input[type=file]')[0].files[0];
    
    var fd = new FormData();
    fd.append("titulo", titulo);
    fd.append("noticia", noticia);
    fd.append("imagen", imagen);

    $.ajax({
        url: "InsertNoticia",
        type: "POST",
        data: fd,
        contentType: false, //IMPORTANTE!
        processData: false, //IMPORTANTE!
        cache: false,
        success: function (json) {
            console.log(json.msg);
            $("#modalLoad").modal("hide");
            $("#modalTitle").html("Success");
            $("#modalBody").html(json.msg);
            $("#modalMsg").modal("show");
        },
        error: function (e) {
            $("#modalLoad").modal("hide");
            $("#modalTitle").html("Error");
            $("#modalBody").html(e["responseJSON"]["error"]);
            $("#modalMsg").modal("show");
        }
    });
}