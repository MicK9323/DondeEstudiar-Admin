var utiljsApp = angular.module('utilJsApp', []);

utiljsApp.service('sharedDataService', function($window) { 
    var KEY = 'AppSharedJs.SelectedValue';
 
    var addItem = function(key, value) {
        KEY = 'AppSharedJs.SelectedValue' + '.' + key;
        $window.sessionStorage.setItem(KEY, value);
    };
 
    var getItem = function(key, remove) {
        KEY = 'AppSharedJs.SelectedValue' + '.' + key;
        valor = $window.sessionStorage.getItem(KEY);
        
        if(typeof valor != 'undefined' && remove){
            removeItem(key);
        }
        
        return valor;
    };
    
    var removeItem = function(key) {
        KEY = 'AppSharedJs.SelectedValue' + '.' + key;
        $window.sessionStorage.removeItem(KEY);
    };
 
    var addObject = function(key, object) {
        KEY = 'AppSharedJs.SelectedObject' + '.' + key;
        $window.sessionStorage.setItem(KEY, JSON.stringify(object));
    };
 
    var getObject = function(key, remove)  {
        KEY = 'AppSharedJs.SelectedObject' + '.' + key;
        valor = JSON.parse($window.sessionStorage.getItem(KEY));
 
        if(typeof valor != 'undefined' && remove){
            removeObject(key);
        }
        
        return valor; 
    };
    
    var removeObject = function(key) {
        KEY = 'AppSharedJs.SelectedObject' + '.' + key;
        $window.sessionStorage.removeItem(KEY);
    };
    
    return ({
        addItem : addItem,
        getItem : getItem,
        addObject : addObject,
        getObject : getObject,
        removeItem : removeItem,
        removeObject: removeObject
    });
 
});

utiljsApp.directive('ngBlur', ['$parse', function($parse) {
    return function(scope, element, attr) {
        var fn = $parse(attr['ngBlur']);
        element.on('blur', function(event) {
        	if(scope.$root.$$phase) {
    		  fn(scope, {$event:event});
    		} else {
    		  scope.$apply(function() {
    		    fn(scope, {$event:event});
    		  });
    		}
        });
    };
}]);

utiljsApp.directive('jqdatepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
         link: function (scope, element, attrs, controller, ngModelCtrl) {
            element.datepicker({
                dateFormat: 'dd/mm/yy',
                showOn: "button",
                buttonImage: "/legalpartner-web/resources/img/calendar.gif",   
                buttonText: "Seleccione fecha",
                disabled: attrs.ngDisabled ? attrs.ngDisabled : false,
                changeMonth: true,
                changeYear: true,
                appendText: "  (dd/mm/aaaa)",
                onSelect: function (date) {
                    scope.$apply(function(){
                    	//ngModelCtrl.$setViewValue(date);
                    	controller.$setViewValue(date);
                    });
                }
            });
          
            element.bind('keyup', function() {
				 controller.$setViewValue(element.val());
				 element.val(formateafecha(element.val()));
			});

            element.bind('focus', function() {
                controller.$setViewValue(element.val());
                controller.$render();
            });

            element.bind('blur', function() {
                controller.$setViewValue(controller.$viewValue);
                controller.$render();

                if (controller.$viewValue && controller.$viewValue.length > 0) {
                    var value = controller.$viewValue;
                    controller.$setValidity('', value && value.indexOf('_') > -1 ? false : true);
                }
                
                //if(!G.Form.ValidFecha(element.val(),"Fecha ingresada está incorrecta")) {
                if(!G.Form.ValidFecha(element.val())) {
                	element.val('');                
                	controller.$setViewValue('');
                }
                                
            });

            controller.$formatters.push(function(value) {
                controller.$setViewValue(value);
                controller.$render();

                if (controller.$viewValue && controller.$viewValue.length > 0) {
                    var value = controller.$viewValue;
                    controller.$setValidity('', value && value.indexOf('_') > -1 ? false : true);
                }
                else
                    controller.$setValidity('', false);

                return value;
            });
        }
    };
});

utiljsApp.directive('fireClick', function(){
    return {
        restrict: 'A',
        require: '^ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {        
            	scope.$watch(attrs.ngModel, attrs.ngClick, function (value, value1) {
            });
        }
    };
});
 
utiljsApp.directive('fireChange', function($timeout){
    return {
        restrict: 'A',
        require: '^ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {        
            	scope.$watch(attrs.ngModel, attrs.ngChange, function (value, value1) {
            });
        }
    };
});
 
utiljsApp.directive('numbersOnly', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attr, ngModelCtrl) {
            function fromUser(text) {
                if (text) {
                	var input = text.toString();
                    var transformedInput = input.replace(/[^0-9]/g, '');
 
                    if (transformedInput !== input) {
                        ngModelCtrl.$setViewValue(transformedInput);
                        ngModelCtrl.$render();
                    }
                    return transformedInput;
                }
                return undefined;
            }            
            ngModelCtrl.$parsers.push(fromUser);
        }
    };
});

utiljsApp.directive('alfaNumbersOnly', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attr, ngModelCtrl) {
            function fromUser(text) {
                if (text) {
                    var transformedInput = text.replace(/[^a-zA-Z0-9]/g, '');
 
                    if (transformedInput !== text) {
                        ngModelCtrl.$setViewValue(transformedInput);
                        ngModelCtrl.$render();
                    }
                    return transformedInput;
                }
                return undefined;
            }            
            ngModelCtrl.$parsers.push(fromUser);
        }
    };
});

utiljsApp.directive('lettersOnly', function () {
    return {
      require: 'ngModel',
      link: function(scope, element, attr, ngModelCtrl) {
	        function fromUser(text) {
	          text = text == undefined ? "" : text;
	          var transformedInput = text.replace(/[^A-Za-zÁÉÍÓÚñáéíóúÑ\s?]/g, '');
	          transformedInput = transformedInput.replace(/\s{2,}/g, ' ');
	          var capitalized = transformedInput.toUpperCase();// CONVIRTIENDO A
																// MAYUSCULA
	          if (capitalized !== text) {
	              ngModelCtrl.$setViewValue(capitalized);
	              ngModelCtrl.$render();
	            }          
	          return capitalized;// convirtiendo a mayuscula
	        }
	        ngModelCtrl.$parsers.push(fromUser);
	   }
    };
});

utiljsApp.directive('normalText', function () {
    return {
      require: 'ngModel',
      link: function(scope, element, attr, ngModelCtrl) {
	        function fromUser(text) {
	          var transformedInput = text.replace(/[^A-Za-zÁÉÍÓÚñáéíóúÑ?[0-9\s\.\-\#\,\]?]/g, '');
	          transformedInput = transformedInput.replace(/\s{2,}/g, ' ');
//	          var capitalized = transformedInput.toUpperCase();// CONVIRTIENDO A
																// MAYUSCULA
	          if (transformedInput !== text) {
	              ngModelCtrl.$setViewValue(transformedInput);
	              ngModelCtrl.$render();
	            }          
	          return transformedInput;// convirtiendo a mayuscula
	        }
	        ngModelCtrl.$parsers.push(fromUser);
	   }
    };
});

utiljsApp.directive('normalTextUser', function () {
    return {
      require: 'ngModel',
      link: function(scope, element, attr, ngModelCtrl) {
	        function fromUser(text) {
	          var transformedInput = text.replace(/[^A-Za-zÁÉÍÓÚñáéíóúÑ?[0-9\.\]?]/g, '');
	          transformedInput = transformedInput.replace(/\s{2,}/g, ' ');
	          var capitalized = transformedInput.toUpperCase();// CONVIRTIENDO A
																// MAYUSCULA
	          if (transformedInput !== text) {
	              ngModelCtrl.$setViewValue(transformedInput);
	              ngModelCtrl.$render();
	            }          
	          return transformedInput;// convirtiendo a mayuscula
	        }
	        ngModelCtrl.$parsers.push(fromUser);
	   }
    };
});

utiljsApp.directive('normalTextName', function () {
    return {
      require: 'ngModel',
      link: function(scope, element, attr, ngModelCtrl) {
	        function fromUser(text) {
	          var transformedInput = text.replace(/[^A-Za-zÁÉÍÓÚñáéíóúÑ?[\s\]?]/g, '');
	          transformedInput = transformedInput.replace(/\s{2,}/g, ' ');
//	          var capitalized = transformedInput.toUpperCase();// CONVIRTIENDO A
																// MAYUSCULA
	          if (transformedInput !== text) {
	              ngModelCtrl.$setViewValue(transformedInput);
	              ngModelCtrl.$render();
	            }          
	          return transformedInput;// convirtiendo a mayuscula
	        }
	        ngModelCtrl.$parsers.push(fromUser);
	   }
    };
});


utiljsApp.directive('normalTextLimited', function () {
    return {
      require: 'ngModel',
      link: function(scope, element, attr, ngModelCtrl) {
	        function fromUser(text) {
	          var transformedInput = text.replace(/[^A-Za-zÁÉÍÓÚñáéíóúÑ?[0-9\.\-\]?]/g, '');
	          transformedInput = transformedInput.replace(/\s{2,}/g, ' ');
//	          var capitalized = transformedInput.toUpperCase();// CONVIRTIENDO A
																// MAYUSCULA
	          if (transformedInput !== text) {
	              ngModelCtrl.$setViewValue(transformedInput);
	              ngModelCtrl.$render();
	            }          
	          return transformedInput;// convirtiendo a mayuscula
	        }
	        ngModelCtrl.$parsers.push(fromUser);
	   }
    };
});


utiljsApp.directive('ngEnter', function() {
	return function(scope, element, attrs) {
		element.bind('keydown keypress', function(event) {
			if (event.which === 13) {
				scope.$apply(function() {
					scope.$eval(attrs.ngEnter, {
						$event : event
					});
				});
				event.preventDefault();
			}
		});
	};
});

utiljsApp.directive('formatRut', function () {
	var maxCharactersInput	= 15;
	var maxCharactersRut	= 13;
	var maxDigitosRut		= 11;
	var minCharactersRut 	= 3;
	var styleInput			= "SPACE_TXT_LBL";
	
    return {
        require: 'ngModel',
        link: function (scope, element, attr, ngModelCtrl) {
            function fromUser(text) {
                if (text) {
                    var transformedInput = text.replace(/[^0123456789Kk]/g, '');
                    transformedInput = transformedInput.toUpperCase();
                    if(G.Len(transformedInput) > maxDigitosRut) {
                    	transformedInput = G.Mid(transformedInput, 1, maxDigitosRut);
                    }
                    transformedInput = RUT.FmtRut(RUT.Desformatear(transformedInput, true));
                    if (transformedInput !== text) {
                        ngModelCtrl.$setViewValue(transformedInput);
                        ngModelCtrl.$render();
                    }
//                    if(G.Len(text) == maxCharactersInput){
//                    	RUT.mensajesRutDirectiva(element[0].id, transformedInput, minCharactersRut, maxCharactersRut, styleInput);
//                    }
                    return transformedInput;
                }
                return undefined;
            }            
            ngModelCtrl.$parsers.push(fromUser);
        }
    };
}); 


utiljsApp.directive('validarRut', function () {
	var maxCharactersInput	= 15;
	var maxCharactersRut	= 13;
	var maxDigitosRut		= 11;
	var minCharactersRut 	= 3;
	var styleInput			= "ERROR_RUT";
	
    return {
        require: 'ngModel',
        link: function (scope, element, attr, ngModelCtrl) {
        	element.bind('blur', function() {
        		var rut = ngModelCtrl.$viewValue;
        		if(G.Trim(rut) != ""){
        			rut = G.ReplaceAll(rut, ".", "");
        			rut = G.ReplaceAll(rut, "-", "");
        			var nrut = rut.substring(0, G.Len(rut) - 1);
        				nrut = parseInt((nrut == "" ? 0 : nrut), 10);
	        		var dv	 = rut.substring(G.Len(rut) - 1);
	        		rut = RUT.FmtRut(parseInt(nrut) + dv);
	        		ngModelCtrl.$setViewValue(rut);
	        		ngModelCtrl.$render();
        			RUT.mensajesRutDirectiva(element[0].id, rut, minCharactersRut, maxCharactersRut, styleInput);
        			return true;
        		} else {
        			$("#" + element[0].id).removeClass(styleInput);
        		}
            });
//        	element.bind('keydown keypress', function(event) {
//    			if (event.which === 13) {
//   					RUT.mensajesRutDirectiva(element[0].id, ngModelCtrl.$viewValue, minCharactersRut, maxCharactersRut, styleInput);
//    				event.preventDefault();
//    			}
//    		});
        }
    };
}); 

utiljsApp.directive('upperText', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attr, ngModelCtrl) {
            function fromUser(text) {
                if (text) {
                    var transformedInput = text.toUpperCase();
 
                    if (transformedInput !== text) {
                        ngModelCtrl.$setViewValue(transformedInput);
                        ngModelCtrl.$render();
                    }
                    return transformedInput;
                }
                return undefined;
            }            
            ngModelCtrl.$parsers.push(fromUser);
        }
    };
});

/*jpiro*/
/*<div ng-jqgrid="gridPrueba" //identificador de grilla
*	pager="true" //flag incluir paginacion (pie de grilla)
*	config="configGridPrueba" //en controller $scope.configGridPrueba {...}
*	data="dataGridPrueba" //en controller $scope.dataGridPrueba [...] (para caso data:local) 
*	api="ejecutarGridPrueba"  // para invocar funciones del grid, ejem: $scope.ejecutarGridPrueba.metodo('getRowData',1) ></div>
*/
utiljsApp.directive("ngJqgrid",function(){
	return {
		restrict: 'A',
        replace: true,
        scope: {
            config: '=',
            data:   '=?',
            api:    '=?',
        },
        link: function (scope, element, attrs) {
        	var table, div;

            scope.$watch('config', function (value) {
            	element.children().empty();
                
                table = angular.element('<table id="' + attrs.ngJqgrid + '"></table>');
                element.append(table);
                
                if (eval(attrs.pager)) {
                	value.pager = '#' + attrs.ngJqgrid + 'Pager';
                	var pager = angular.element(value.pager);
                	if (pager.length == 0) {
                        div = angular.element('<div id="' + attrs.ngJqgrid + 'Pager"></div>');
                        element.append(div);
                    }
                }
                //arma grilla segun datos en "config"
                table.jqGrid(value);
                
                // permite insert(), clear(), refresh() la grid desde
                // fuera de la directiva (ejem. desde un controller). Usar:
                // view:  <ng-jqgrid ... api="gridapi">
                scope.api = {
                    // ctrl:  $scope.gridapi.metodo('method', 'arg1', ...);
                	metodo : function(){
                		var args = Array.prototype.slice.call(arguments,0);
                        return table.jqGrid.apply(table, args);
                	},
                	//ctrl:  $scope.gridapi.insert();
                    insert: function(rows) {
                        if (rows) {
                            for (var i = 0; i < rows.length; i++) {
                                scope.data.push(rows[i]);
                            }
                            table.jqGrid('setGridParam', { data: scope.data })
                                 .trigger('reloadGrid');
                        }
                    },
                    //ctrl:  $scope.gridapi.clear();
                    clear: function() {
                    	//scope.data.length = 0;
                    	table.jqGrid('clearGridData', { data: scope.data }).trigger('reloadGrid');
                        table.jqGrid('GridDestroy');
                    },
                	//ctrl:  $scope.gridapi.refresh();
                    refresh: function() {
                        table
                            .jqGrid('clearGridData')
                            .jqGrid('setGridParam', { data: scope.data })
                            .trigger('reloadGrid');
                    },
                    reloadPage: function(pagex) {
                        table.trigger('reloadGrid',[{page:pagex}]);
                    },
                };
            });
            //data local
            scope.$watchCollection('data', function (value) {
            	table.jqGrid('clearGridData');
            	table.jqGrid('setGridParam', { data : value })
                     .trigger('reloadGrid')
                ;
            });
        }
	}
});
utiljsApp.directive('dateMask', function() {
	return {
	    restrict: 'A',
	    require: 'ngModel',
		link: function (scope, elem, attrs, controller) {
			$(elem).mask('99/99/9999');

			elem.bind('keyup', function() {
				 controller.$setViewValue(elem.val());
                // do not call $render() since cursor will make this move to end
			});

            elem.bind('focus', function() {
                controller.$setViewValue(elem.val());
                controller.$render();
            });

            elem.bind('blur', function() {
                controller.$setViewValue(controller.$viewValue);
                controller.$render();

                if (controller.$viewValue && controller.$viewValue.length > 0) {
                    var value = controller.$viewValue;
                    controller.$setValidity('', value && value.indexOf('_') > -1 ? false : true);
                }
            });

            controller.$formatters.push(function(value) {
                controller.$setViewValue(value);
                controller.$render();

                if (controller.$viewValue && controller.$viewValue.length > 0) {
                    var value = controller.$viewValue;
                    controller.$setValidity('', value && value.indexOf('_') > -1 ? false : true);
                }
                else
                    controller.$setValidity('', false);

                return value;
            });

		}
	};
});

utiljsApp.directive("ngMask",function(){
    return {
        restrict: 'A',
        link: function(scope, el, attrs){
          $(el).inputmask(attrs.ngMask);
        }
    };
});

function formateafecha(fecha) { 
	var long = fecha.length; 
	var dia; 
	var mes; 
	var ano; 
	var primerslap=false; 
	var segundoslap=false; 

	if ((long>=2) && (primerslap==false)) { 
		dia=fecha.substr(0,2); 
		if ((G.IsNumeric(dia)) && (dia<=31) && (dia!="00")) { 
			fecha=fecha.substr(0,2)+"/"+fecha.substr(3,7); 
			primerslap=true; 
		} 
		else { 
			fecha=""; 
			primerslap=false;
		} 
	} 
	else { 
		dia=fecha.substr(0,1); 
		if (G.IsNumeric(dia)==false) {
			fecha="";
		} 
		if ((long<=2) && (primerslap=true)) {
			fecha=fecha.substr(0,1); primerslap=false; 
		} 
	} 
	if ((long>=5) && (segundoslap==false)) { 
		mes=fecha.substr(3,2); 
		if ((G.IsNumeric(mes)==true) &&(mes<=12) && (mes!="00")) { 
			fecha=fecha.substr(0,5)+"/"+fecha.substr(6,4); segundoslap=true; 
		} 
		else { 
			fecha=fecha.substr(0,3); 
			segundoslap=false;
		} 
	} 
	else { 
		if ((long<=5) && (segundoslap=true)) { 
			fecha=fecha.substr(0,4); segundoslap=false; 
		} 
	} 
	if (long>=7) { 
		ano=fecha.substr(6,4); 
		if (G.IsNumeric(ano)==false) { 
			fecha=fecha.substr(0,6); 
		} 
		else { 
			if (long==10){ 
				if ((ano==0) || (ano<1900) || (ano>2100)) { 
					fecha=fecha.substr(0,6); 
				} 
			} 
		} 
	} 
	if (long>=10) { 
		fecha=fecha.substr(0,10); 
		dia=fecha.substr(0,2); 
		mes=fecha.substr(3,2); 
		ano=fecha.substr(6,4); 
		// A\u00f1o no viciesto y es febrero y el dia es mayor a 28
		if ((ano%4 != 0) && (mes ==02) && (dia > 28)) { 
			fecha=fecha.substr(0,2)+"/"; 
		} 
	} 
	return (fecha); 
};

utiljsApp.directive('formatoDecimal', function () {
    return {
      require: 'ngModel',
      scope1: {
    	    preDecimal : '@preDecimal',
    	    maxDecimal : '@maxDecimal',
			thousandsSeparator :'@thousandsSeparator', 
			decimalSeparator  : '@decimalSeparator'
		},
      link: function(scope, element, attr, ngModelCtrl) {
    	  if(!attr.thousandsSeparator){
    		  attr.thousandsSeparator = ",";
    		}
    		if(!attr.decimalSeparator){
    			attr.decimalSeparator = ".";
    		}
    		
    		$.fn.alphanum.setNumericSeparators({
    			thousandsSeparator: attr.thousandsSeparator,
    			decimalSeparator: attr.decimalSeparator
    		});
    		
    		if(attr.maxDecimal != null && attr.maxDecimal != undefined) {
    			$(element).numeric({
    			    allowMinus   : false, // Allow the - sign
    			    allowThouSep : false, // Allow the thousands separator,
											// default is the comma eg 12,000
    			    maxPreDecimalPlaces: attr.preDecimal, // The max number
															// digits before the
															// decimal point
    			    maxDecimalPlaces: attr.maxDecimal // The max number of
														// decimal places
    			});
    		} else {
    			$(element).numeric({
    			    allowMinus   : false, 
    			    allowThouSep : false, 
    			    maxPreDecimalPlaces: attr.preDecimal 
    			});
    		}
      	}
    };
});



