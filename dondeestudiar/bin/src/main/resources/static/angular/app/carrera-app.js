var carreraApp = angular.module('CarreraApp', ['utilJsApp']);

carreraApp.config([ "$httpProvider", function($httpProvider) {
	$httpProvider.defaults.cache = false;

	if (!$httpProvider.defaults.headers.get) {
		$httpProvider.defaults.headers.get = {};
	}
	// Desactivar IE Ajax Request Caching
	$httpProvider.defaults.headers.get["If-Modified-Since"] = "0";

} ]);