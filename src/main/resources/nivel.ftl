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
        <h1 class="display-4">Welcome Smart Lab</h1>
        <p class="lead">This page has been build using the <a href="https://github.com/AuroralH2020/kg-builder">Knowledge Graph Builder</a> in the context of the European AURORAL project. The goal of the page is to display the different sensors deployed in the laboratory of the research group <a href="https://oeg.fi.upm.es/">Ontology Engineering Group </a> at the Universidad Politécnica de Madrid.</p>
        <h5>If you want a demo or a tutorial contact us.</h5>
        <hr class="my-4">
        <div style="text-align: center;">
        <img src="https://github.com/oeg-upm/websiteFooterLogos/blob/master/images/oeg.jpg?raw=true" width="200px"/>
        <img src="https://www.auroral.eu/img/logos/AURORAL-Horizontal_o.png" width="400px" />
        </div>
        <div class="mb-3">
  		<label for="formFile" class="form-label">Default file input example</label>
  		<input class="form-control" type="file" id="formFile">
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
</html>
