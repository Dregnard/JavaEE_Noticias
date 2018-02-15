function insertarNoticia() {
    console.log("Pressed");
    if (existeSlug() === false) {
        console.log("No existe slug");
        guardarNoticia();
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
    $.ajax({
        url: "InsertNoticia",
        method: "POST",
        data: {titulo: titulo, noticia: noticia},
        success: function (json) {
            console.log(json.msg);
        },
        error: function (e) {
            console.log("Error fatal de la muerte");
        }
    });
}