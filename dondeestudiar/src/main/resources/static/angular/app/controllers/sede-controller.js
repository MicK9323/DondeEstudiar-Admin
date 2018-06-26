sedeApp.controller("SedeController", function($scope) {
	$scope.title = "";

	$scope.txtNomSede = {
		text : $("#nomSede").val(),
		length : 50,
		enabled : false,
		visible : true
	};
	
	$scope.txtDirSede = {
		text :$("#direccion").val(),
		length : 70,
		enabled : false,
		visible : true
	};
	
	$scope.txtTefSede = {
		text :$("#telf").val(),
		length : 10,
		enabled : false,
		visible : true
	};
	
	$scope.Ubicacion = {
		text : "",
		length : 50,
		enabled : false,
		visible : true
	};

	
	$scope.init = function() {
		
		
	};

});

angular.element(document).ready(function() {
	$(".wrapper-body").width(750);
});