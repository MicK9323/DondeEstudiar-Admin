carrerasedeApp.controller("CarreraSedeController", function($scope,$compile) {
    $scope.title = "";


   
    
    $scope.asignar = function(){		
        $('#tbAsignacion').html("");
        sedes.forEach(function (value, index) {
            $('#tbAsignacion').append($compile(
        		'<tr>' +
                '<td class="nomCarrera text-left">' + nomCarrera + '</td>' +
                '<td class="idCarrera d-none">' + idCarrera + '</td>' +
                '<td class="nomSede text-left">' + value.sede + '</td>' +
                '<td class="idSede d-none">' + value.id + '</td>' +
                '<td class="select text-center">' +
                '<select class="acreditado form-control input-sm custom-select">' +
                '<option value="1">Si</option>' +
                '<option value="0">No</option>' +
                '</select>' +
                '</td>' +
                '<td class="input1 text-center">' +
                '<input type="text" placeholder="Costo S/." class="costo form-control input-sm">' +
                '</td>' +
                '<td class="input2 text-center">' +
                '<input type="text" placeholder="%" class="ingresantes form-control input-sm" ' +
                'data-ng-model="txtCostoAnual.text" data-maxlength={{txtCostoAnual.length}} numbers-only>' +
                '</td>' +
                '<td class="text-center">' +
                '<button class="btnEnviarFila btn btn-outline-success">' +
                '<i class="mdi mdi-arrow-down-bold-hexagon-outline"></i>' +
                '</button>' +
                '</td>' +
                '<td>' +
                '<button class="btnBorrarFila btn btn-outline-danger">' +
                '<i class="fa fa-trash-o"></i>' +
                '</button>' +
                '</td>' +
                '</tr>'		
            )($scope));
        });
        $scope.resetSede();
        $scope.resetCarrera();
        $scope.deshabilitarBoton($('#btnSedes'), true);
    
	};
	
	$scope.deshabilitarBoton = function(boton, value) {
        if (value) {
            boton.prop('disabled', true);
        } else {
            boton.prop('disabled', false);
        }
    }

     $scope.resetCarrera = function() {
        idCarrera = "";
        nomCarrera = "";
        $('#carrera').val("");
        $('#nomCarrera').val("");
        $('#idCarrera').val("");
    }

    // Seleccionar sedes

    $scope.resetSede = function() {
    	 
        sedes = new Array();
        $('#tbSedes tbody tr').each(function () {
            $(this).removeClass('true');
            $(this).removeClass('text-success');
            $(this).find('.seleccionar').find('i').removeClass('fa-check-square-o').addClass('fa-square-o');
        });
    }
	
	
    $scope.init = function() {

    };

});

angular.element(document).ready(function() {
    $(".wrapper-body").width(750);



});