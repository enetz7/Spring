$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/all"
    }).then(function(response) {
    	var trHTML = '';
        $.each(response, function (i, item) {
            trHTML += '<tr><td>' +"Id: "+ item.id + '</td><td>' + "Nombre: "+item.nombre +'</td>' +'<td>'+'<form method="POST" action="/verProyecto?id='+item.id+'">'+'<button type="submit">Ver detalles</button></form>'+ '</td></tr>';
        });
        $('#tablaProyectos').append(trHTML);
    });
});