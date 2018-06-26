carreraApp.controller("CarreraController", function($scope, $compile) {
	$scope.title = "";

	$scope.init = function() {

	};

	$scope.txtDuracion = {
		text : $("#duracion").val(),
		length : 2,
		enabled : false,
		visible : true
	};

	$scope.txtNombre = {
		text : $("#nombre").val(),
		length : 50,
		enabled : false,
		visible : true
	};
	
	$scope.txtPopularidad = {
		text : $("#popularidad").val(),
		length : 2,
		enabled : false,
		visible : true
	};
	
	$scope.txtRemuneracion = {
		text : $("#remuneracion").val(),
		length : 6,
		enabled : false,
		visible : true
	};
	
	$scope.txtBuscar = {
		text : "",
		length : 25,
		enabled : false,
		visible : true
	};
	
    $scope.txtNomCarrera = {
		text : "",
		length : 50,
		enabled : false,
		visible : true
	};
    
    $scope.txtCostoAnual = {
		text : "",
		length : 6,
		enabled : false,
		visible : true
	};
    
    $scope.txtIngresante = {
		text : "",
		length : 3,
		enabled : false,
		visible : true
	};

    //JS - init
    
    $('#listaCarreras').hide();

    var idCarrera = "";
    var nomCarrera = "";

    var models = new Array();
    var sedes = new Array();

    function Sede(id, sede) {
        this.id = parseInt(id);
        this.sede = sede;
    }

    function Model(idCarrera, nomCarrera, idSede, nomSede, acreditado, costo, ingresantes) {
        this.idCarrera = parseInt(idCarrera);
        this.nomCarrera = nomCarrera;
        this.idSede = parseInt(idSede);
        this.nomSede = nomSede;
		this.acreditado = acreditado == "1" ? true : false;
		this.costo = parseFloat(costo);
        this.ingresantes = parseInt(ingresantes);
    }

    function boolToString(value) {
        if (value)
            return "Si";
        else
            return "No";
    }

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

    $scope.seleccionar = function(tr) {
        if ($(tr).hasClass('true')) {
            $(tr).removeClass('true');
            $(tr).toggleClass('text-success');
            $(tr).find('.seleccionar').find('i').removeClass('fa-check-square-o').addClass('fa-square-o');
            var id = $(tr).find('.idSede').text();
            var nom = $(tr).find('.nomSede').text();
            var sede = new Sede(id, nom);
            sedes.forEach(function (value, index) {
                if (sede.id === value.id) {
                    sedes.splice(index, 1);
                }
            });
        } else {
            $(tr).addClass('true');
            $(tr).toggleClass('text-success');
            $(tr).find('.seleccionar').find('i').removeClass('fa-square-o').addClass('fa-check-square-o');
            var id = $(tr).find('.idSede').text();
            var nom = $(tr).find('.nomSede').text();
            var sede = new Sede(id, nom);
            sedes.push(sede);
        }
    }

    $scope.populateAsignar =function() {
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
                '<input type="text" placeholder="Costo S/." class="costo form-control input-sm"' +
                'data-ng-model="txtCostoAnual.text' +idCarrera + value.id + index + '" maxlength={{txtCostoAnual.length}} numbers-only>' +
                '</td>' +
                '<td class="input2 text-center">' +
                '<input type="text" placeholder="%" class="ingresantes form-control input-sm" ' +
                'data-ng-model="txtIngresante.text' + value.id + idCarrera + index + '" maxlength={{txtIngresante.length}} numbers-only>' +
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

    $scope.populateAsignados = function() {
        $('#tbDetalle').html("");
        models.forEach(function (value, index) {
            $('#tbDetalle').append(
                '<tr>' +
                '<td class="text-center font-bold">' + (1 + index) + '</td>' +
                '<td class="d-none">' + index + '</td>' +
                '<td class="text-left font-bold">' + G.Trim(value.nomCarrera) + '</td>' +
                '<td class="text-left font-bold">' + G.Trim(value.nomSede) + '</td>' +
                '<td class="text-center font-bold">' + boolToString(value.acreditado) + '</td>' +
                '<td class="text-center font-bold">' + G.Trim(value.costo) + '</td>' +
                '<td class="text-center font-bold">' + G.Trim(value.ingresantes) + '</td>' +
                '<td class="text-center font-bold">' +
                '<button class="btnBorrarModel btn btn-outline-danger">' +
                '<i class="fa fa-trash-o"></i>' +
                '</button>' +
                '</td>' +
                '</tr>'
            );
        });
    }

    $scope.eliminarFila = function(fila) {
        $(fila).parent().parent().remove();
    }

     $scope.validar = function(idCarrera, nomCarrera, idSede, nomSede, costo, ingresantes) {
        if (idCarrera == "" || nomCarrera == "") {
            return false;
        } else if (idSede == "" || nomSede == "") {
            return false;
        } else if (costo == "") {
            return false;
        } else if (ingresantes == "") {
            return false;
        } else {
            return true;
        }
    }

    // Promesa
    function getAjaxResponse(idCarrera, idSede) {
        var options = {
            type: "POST",
            url: '/carreras/validar',
            data: {idCarrera: idCarrera, idSede: idSede},
            timeout: 600000
        }
        return $.ajax(options);
    }

    // Buscar Carreras
    $('#carrera').keyup(function () {
        var term = $(this).val().trim().split(" ").join("_");
        if (term == "") {
            $('#listaCarreras').hide();
        }
        if (term.length > 5) {
            $('#spinner').toggleClass('d-none');
            $('#listaCarreras').load("/carreras/autocompletar/" + term, function () {
                $('#spinner').toggleClass('d-none');
            });
            $('#listaCarreras').show();
        }
    });

    $('#listaCarreras').on('click', '.carrera', function () {
        idCarrera = $(this).find('.idCarrera').val();
        nomCarrera = $(this).find('.desc').text();
        $scope.deshabilitarBoton($('#btnSedes', false));
        $('#carrera').val("");
        $('#nomCarrera').val(nomCarrera);
        $('#idCarrera').val(idCarrera);
        $('#listaCarreras').hide();
    });

    // Seleccionar todas las sedes
    $('#btnAll').click(function () {
        $('#tbSedes tbody tr').each(function () {
        	$scope.seleccionar($(this));
        })
    });

    $('.fila').click(function () {
    	$scope.seleccionar($(this));
    })

    $scope.btnCancelar = function(){
        $scope.resetSede();
    };

    $scope.btnAceptar = function(){
    	 $scope.populateAsignar();
    };

    $('#tbAsignacion').on('click', '.btnEnviarFila', function () {
        var idCarrera = G.Trim($(this).parent().parent().find('.idCarrera').text());
        var nomCarrera = G.Trim($(this).parent().parent().find('.nomCarrera').text());
        var idSede = G.Trim($(this).parent().parent().find('.idSede').text());
        var nomSede = G.Trim($(this).parent().parent().find('.nomSede').text());
        var acreditado = G.Trim($(this).parent().parent().find('.select').find('select').val());
        var costo = G.Trim($(this).parent().parent().find('.input1').find('.costo').val());
        var ingresantes = G.Trim($(this).parent().parent().find('.input2').find('.ingresantes').val());
        if ($scope.validar(idCarrera, nomCarrera, idSede, nomSede, acreditado, costo, ingresantes)) {
            var model = new Model(idCarrera, nomCarrera, idSede, nomSede, acreditado, costo, ingresantes);
            var fila = $(this);
            var promise = getAjaxResponse(model.idCarrera, model.idSede);
            promise.done(function (result) {
                if (result == true) {
                    if (models.length >= 1) {
                        models.forEach(function (value, index, array) {
                            if (model.idSede == value.idSede && model.idCarrera == value.idCarrera) {
                                alertify.alert('¡Ya ingreso este registro!');
                                throw array;
                            } else {
                                models.push(model);
                                $scope.eliminarFila(fila);
                                $scope.populateAsignados();
                            }
                        });
                    } else {
                        models.push(model);
                        $(fila).parent().parent().remove();
                        $scope.populateAsignados();
                    }
                } else {
                    alertify.alert('Este registro ya se encuentra en la base de datos');
                }
            })

        } else {
            alertify.alert("Ingrese todos los campos");
        }
    });

    $('#tbAsignacion').on('click', '.btnBorrarFila', function () {
        var fila = $(this);
        alertify.confirm('¿Seguro de eliminar este registro?',
            function () {
                $scope.eliminarFila(fila);
            },
            function () {
            	 // TODO
            });
    });

    $('#tbDetalle').on('click','.btnBorrarModel',function () {
        console.log('Antes de eliminar: '+models);
        var fila = $(this);
        var index = $(this).parent().parent().find('.index').text();
        alertify.confirm('Esta seguro de borrar este registro?',
            function () {
                models.splice(index,1);
                $scope.eliminarFila(fila);
                console.log('Luego de eliminar: '+models);
            },function () {
            	 // TODO
            });
    });

    $('#btnRegistrar').click(function () {
        if(models.length == 0){
            alertify.alert('No ha ingresado ningun registro');
        }else{
            alertify.confirm('¿Esta seguro registrar todos los registros ingresados?',
                function () {
                    $.ajax({
                        type: "POST",
                        url: '/carreras/detalle/guardar',
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify(models),
                        success: function (data) {
                            console.log(data.responseText);
                        },
                        error: function (error) {
                            var url = error.responseText;
                            console.log(error.responseText);
                            $(location).attr("href",url);
                        }
                    });
                },function () {
                    // TODO
                });
        }
    });

    
    //JS - fin

    
});

angular.element(document).ready(function() {
	$(".wrapper-body").width(750);
});