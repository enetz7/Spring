$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/departamento/all"
    }).then(function(data) {
      console.log(data);
    });
});