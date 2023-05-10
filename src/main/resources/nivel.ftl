<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Doctor FIS</title>
    <link href="https://github.com/AuroralH2020/kg-builder/raw/main/src/main/resources/spark/template/freemarker/css/favicon.ico" rel="shortcut icon" type="image/png">
    <!--leaflet -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css"integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI=" crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js" integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM=" crossorigin=""></script>
    <style type="text/css">
      #map { height: 500px; }
    </style>

   
  </head>
  <body>
    <div class="container-fluid">
      <#include "./navbar.html"/>
      
      <div class="jumbotron" style="margin-top:20px">
		<div class="mb-3">
		 	<label for="entero" class="form-label">Selecciona un nivel de feedback:</label>
	  		<select class="form-select form-select-sm" aria-label=".form-select-sm example">
	  		  <option selected>Nivel feedback</option>
			  <option value="0">Bajo</option>
			  <option value="1">Medio</option>
			  <option value="2">Bueno</option>
			  <option value="3">Muy bueno</option>
			</select>
		</div>
  		<button type="submit" style="margin-top:20px" class="btn btn-primary" id="enviar">
  			Enviar</button>
	  </div>
  	</div>
  </body>
  <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type="text/javascript">
    $("#linkhome").css('text-decoration', 'underline')
    </script>
    <script>
            $(document).ready(function() {
                $("#enviar").click(function() {
		            var entero = $("#entero").val();
		
		            var formData = new FormData();
		            formData.append("entero", entero);
		            
                    $.ajax({
                        url: "/guardar-nivel",
                        type: "POST",
                        data: formData,
                        cache: false,
                        contentType: false,
                        processData: false,
                        success: function(response) {
                            console.log("Nivel guardado con éxito");
                        },
                        error: function(jqXHR, textStatus, errorMessage) {
                            console.error("Error al enviar el integer:", errorMessage);
                        }
                    });
                });
            });
        </script>
</html>