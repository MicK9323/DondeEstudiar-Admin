usuarioApp.controller("UsuarioController", function($scope) {
	$scope.title = "";

	$scope.txtDni = {
		text : $("#dni").val(),
		length : 8,
		enabled : false,
		visible : true
	};
	
	$scope.txtNombre = {
		text :$("#nombre").val(),
		length : 35,
		enabled : false,
		visible : true
	};
	
	$scope.txtApellido = {
		text :$("#apellido").val(),
		length : 35,
		enabled : false,
		visible : true
	};
	
	$scope.txtIdUsuario = {
		text :$("#usuario").val(),
		length : 35,
		enabled : false,
		visible : true
	};
	
	$scope.txtClave1 = {
		text :$("#clave").val(),
		length : 20,
		enabled : false,
		visible : true	
	};
	
	$scope.txtClave2 = {
		text : "",
		length : 20,
		enabled : false,
		visible : true	
	};

	
	$scope.init = function() {
		
		
	};

});

angular.element(document).ready(function() {
	$(".wrapper-body").width(750);
});