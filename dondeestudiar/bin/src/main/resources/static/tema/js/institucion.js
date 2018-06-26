$(document).ready(function() {
	$('#listaUbigueos').hide();
	var nomSede = "";
	var ubigueo = "";
	var dirSede = "";
	var telfSede = "";
	var coordenadas = "";
	
	function limpiarCampos(){
		nomSede="";
		ubigueo="";
		dirSede="";
		telfSede="";
		$('#nomSede').val("");
		$('#dirSede').val("");
		$('#telfSede').val("");
		$('#listaUbigueos').hide();
	}
	
	$('#ubiSede').keyup(function(){
        var term = $('#ubiSede').val().trim().split(" ").join("_");        
        if( term.length >= 3 ){
        	console.info('termino de busqueda: '+term);
        	$('#listaUbigueos').load("/instituciones/cargar-ubigueo/"+term)
        	$('#listaUbigueos').show()
        }        
    });
	
	$('#listaUbigueos').on('click','.ubicacion', function(){
		console.info('Click');
		ubigueo = $(this).find('.codUbigueo').val();
		$('#ubiSede').val($(this).find('.desc').text());
		$('#listaUbigueos').hide();
		console.info('Ubicacion: '+$('#ubiSede').val());
		console.info('Codigo: '+ubigueo);
	});
	
	
	$('#agregarSede').click(function(){
		console.info("click");
		nomSede = $('#nomSede').val().trim().split(" ").join("_");
		dirSede = $('#dirSede').val().trim().split(" ").join("_");
		telfSede = $('#telfSede').val().trim().split(" ").join("_");
		var array = [];
		array = $('#coordenadas').val().trim().split(",");
		coordx = array[0].replace("(","").trim();
		coordy = array[1].replace(")","").trim();
		console.info("Latitud: "+coordx);
		console.info("Longitud: "+coordy);
		if( nomSede == "" || dirSede == "" || telfSede == "" || ubigueo == "" ){
			alertify
			  .alert("Porfavor ingrese todos los campos");
		}else {
			var data = [];
			data.push(nomSede,ubigueo,dirSede,telfSede,coordx,coordy);
			alertify.confirm("Esta seguro de agregar esta sede?",
					  function(){
						console.info("data: "+data);
						$('#detSedes').load('/instituciones/cargar-sedes/'+data);
						  limpiarCampos();
					    alertify.success('Sede Agregada');
					  },
					  function(){
					    alertify.message('Cancelado por el usuario');
					  });
		}
	});
	

  // Basic
  $(".dropify").dropify();

  // Translated
  $(".dropify-fr").dropify({
    messages: {
      default: "Pulse para seleccionar una imagen",
      replace: "Pulse para reemplazar la imagen",
      remove: "Elimnar",
      error: "Error al leer el fichero"
    }
  });

  // Used events
  var drEvent = $("#input-file-events").dropify();

  drEvent.on("dropify.beforeClear", function(event, element) {
    return confirm(
      'Do you really want to delete "' + element.file.name + '" ?'
    );
  });

  drEvent.on("dropify.afterClear", function(event, element) {
    alert("File deleted");
  });

  drEvent.on("dropify.errors", function(event, element) {
    console.log("Has Errors");
  });

  var drDestroy = $("#input-file-to-destroy").dropify();
  drDestroy = drDestroy.data("dropify");
  $("#toggleDropify").on("click", function(e) {
    e.preventDefault();
    if (drDestroy.isDropified()) {
      drDestroy.destroy();
    } else {
      drDestroy.init();
    }
  });
});
