var G = G || {};

G = {
	
	 cadenaSaltoLinea: function(cadena){
		var cadenaAux = "";
        var newLine = "\r\n";
    	var cadenaList = cadena.split(",");
		
		if(cadenaList.length > 1){
			
			for(var i = 0 ; i < cadenaList.length ; i++){
				
				if(cadenaAux == "") {cadenaAux = cadenaList[i];}
				else {
					cadenaAux += newLine
					cadenaAux += cadenaList[i];
				}
			}
		}else{
			cadenaAux = cadena;
		}
		return cadenaAux;
	},
	ValidarNumero: function(e) {
	    tecla = (document.all) ? e.keyCode : e.which;
	    if (tecla==8){
	        return true;
	    }
	    patron =/[0-9]/;
	    tecla_final = String.fromCharCode(tecla);
	    return patron.test(tecla_final);
	},	
	ListIndex: function(idCombo) {
		return document.getElementById(idCombo).selectedIndex;
	},
	Length: function(cadena) {
		return ("" + cadena).length;
	},
	Left: function(cadena, longitud) {
		if (longitud > cadena.length) {
			return cadena;
		}
		else {
			return cadena.substring(0, longitud);
		}
	},
	Right: function(cadena, longitud) {
		if (longitud > cadena.length) {
			return cadena;
		}
		else {
			return cadena.substring(cadena.length - longitud);
		}
	},
	Mid: function(cadena, inicio, longitud) {
		if (inicio > cadena.length) {
			return "";
		}
		else {
			inicio = inicio - 1;
			if (longitud === undefined) {
				return cadena.substring(inicio);
			}
			else {
				return cadena.substring(inicio, inicio + longitud);
			}
		}
	},
	UCase: function(cadena) {
		return (""+cadena).toUpperCase();
	},
	ReplaceAll : function(cadena, CaracterBusqueda, CaracterReemplazo) {
		  var outString = cadena;
		  if(cadena != null && cadena != undefined){
			  if(cadena != ""){
				  while (true) {
					    var idx = outString.indexOf(CaracterBusqueda);
					    if (idx == -1) {
					      break;
					    }
					      outString = outString.substring(0, idx) + CaracterReemplazo +
					      outString.substring(idx + CaracterBusqueda.length);
					  }  
			  }				  
		  }
		  return outString;
	},	
	LCase: function(cadena) {
		return cadena.toLowerCase();
	},
	InStr: function(inicio, cadena, cadenaBuscada) {
		var posicion;
		if ( inicio === undefined )
			posicion = cadena.indexOf(cadenaBuscada);
		else
			posicion = cadena.indexOf(cadenaBuscada, inicio - 1);

		if ( posicion == -1 ) {
			return 0;
		}
		else {
			return posicion + 1;
		}
	},
	Chr: function(numero) {
		return String.fromCharCode(numero);
	},
	Len: function(cadena) {
		return cadena.length;
	},
	String: function(cantidad, cadenaRepetir) {
		var result = "";

		for (var i = 0; i < cantidad; i++) {
			result = result + cadenaRepetir;
		}

		return result;
	},
	Format: function(valor, formato){
		var cadena;
		cadena = valor;
		if(!isNaN(valor)){
			if(valor < 0)
				cadena = Math.abs(valor);

			cadena = Math.round(cadena).toString();
		}
		var lenValor = cadena.length;
		var lenFormato = formato.length;
		for(var i = (lenFormato - lenValor) - 1; i >= 0; i--){
			cadena = formato[i] + cadena;
		}
		return cadena;
	},
	IsNumeric: function(cadena) {
		if(G.IsEmpty(G.Trim(cadena))) { cadena = "."; }
		if (isNaN(cadena)) {
			return false;
		}
		else {
			return true;
		}
	},
	Trim: function(cadena) {
		return $.trim(cadena+"");
	},
	IsEmpty : function(cadena) {
		if(cadena == null || cadena=="" || cadena == undefined){
			return true;
		}
		return false;
	},
	Val: function(cadena) {
		if(cadena == undefined){
			return 0;
		}else{
			var result = parseInt(cadena.replace(/\s/g, ""));
			if( isNaN(result) )
				return 0;
			else
				return result;	
		}
	},
	MsgBox: function(mensaje) {
		if(mensaje != undefined && mensaje != null && mensaje != ""){
			alert(mensaje);
		}
	},
	SetFocus: function(idElement) {
		var element = document.getElementById(idElement);
		element.focus();
	},
	Show: function(url) {
		window.location.href = url;
	},
	vbModal: function(idElement) {
		$("#" + idElement).show('slow');
	},
	Unload: function(idElement) {
		$("#" + idElement).hide();
	},
	Visible: function(idElement, boolean) {
		if( boolean )
			$("#" + idElement).show();
		else
			$("#" + idElement).hide();
	},
	Enabled: function(idElement, boolean) {
		if( boolean )
			$("#" + idElement).prop("disabled", false);
		else
			$("#" + idElement).prop("disabled", true);
	},
	ReadOnly : function(idElement, boolean) {
		document.getElementById(idElement).readOnly = boolean;
	},
	isDate: function(date) {
		return (new Date(date) !== "Invalid Date" && !isNaN(new Date(date)) ) ? true : false;
	},
	PadLeft: function(str, pad) 	{
		//PadLeft("123" , "00000000") >> 00000123
		var retorno = "";
		if(str != null && pad!=null)
		{
			str = str.toString();
			retorno = pad.substring(0, pad.length - str.length) + str;
		}
		return retorno;
	},
	CDbl: function(texto) {		
		return parseFloat(texto);
	},
	Str: function(texto) {		
		if(isNaN(texto)){
			return texto; //verificar el comportamiento con visual
		} else {
			return " " + parseInt(texto)
		}
	},
	PadRight: function(str, pad) {
		//PadRight("123" , "00000000") >> 12300000
		var retorno = "";
		if(str != null && pad!=null)
		{
			str = str.toString();
			retorno = str + pad.substring(0, pad.length - str.length);
		}
		return retorno;
	},
	BackColor: function(element, constant){
		var color = "";
		if(constant == "K_AMARIL") {
			color = "#FFFFE0";
		} else if(constant == "K_BLANCO") {
			color = "#FFFFFF";
		}
		$("#"+element).css("background-color", color);
		$("#"+element).css("border", "1px solid #999");
	},	
	Form: {
		ValidCombo: function(element, msj) {
			if (element.val() == -1 || $.trim(element.val()) == "")
			{
				alert(msj);
				return false;
			}
			else {
				return true;
			}
		},
		ValidCadena: function(element, msj) {
			if ($.trim(element.val()) == "" || element.val() == null) {
				alert(msj);
				return false;
			}
			else {
				return true;
			}
		},
		ValidFecha: function(campo, msj) {
			// / Formato dd/mm/yyyy
			//var fecha = $.trim(element.val());
			var fecha = $.trim(campo);
			var filter = /^(0?[1-9]|[12][0-9]|3[01])[\/](0?[1-9]|1[012])[/\\/](19|20)\d{2}$/;
			if (filter.test(fecha)) {
				return true;
			}
			else {
				if(msj != null)
					alert(msj);
				return false;
			}
		},
		DateDiff : function(interval, fecha1, fecha2) {
			var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
			var sep1 = fecha1.indexOf('/') != -1 ? '/' : '-';
			var sep2 = fecha2.indexOf('/') != -1 ? '/' : '-';
			var aFecha1 = fecha1.split(sep1);
			var aFecha2 = fecha2.split(sep2);
			
			var firstDate1 = new Date(aFecha1[2], aFecha1[1], aFecha1[0]);
			var firstDate2 = new Date(aFecha2[2], aFecha2[1], aFecha2[0]);
			var diffDays = Math.round(Math.abs((firstDate2.getTime() - firstDate1.getTime()) / (oneDay)));
			if(interval == "d") {				
				return diffDays;
			} else if(interval == "m") { 
				var month = 0;
				var continuar = true;
				do {
					var day = new Date(aFecha1[2] || new Date().getFullYear(), aFecha1[1], 0).getDate();
					if(diffDays >= day) {
						month++;
						diffDays = diffDays - day;
						fecha1 = G.Form.DateAdd("d", day, fecha1);
						sep1 = fecha1.indexOf('/') != -1 ? '/' : '-';						
						aFecha1 = fecha1.split(sep1);
						continuar = true;	
						if(diffDays == day) {
							continuar = false;
				        }
					} else {
						month++;
						continuar = false;
					}
				} while(continuar);
				return month;
			} else if(interval == "yyyy") { 
				var year = 0;
				var continuar = true;
				do {
					var day = 365;
					var anio = aFecha1[2];				
					if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))) {
						day = 366;
					}  
					if(diffDays >= day) {
						year++;
						diffDays = diffDays - day;
						fecha1 = G.Form.DateAdd("d", day, fecha1);
						sep1 = fecha1.indexOf('/') != -1 ? '/' : '-';						
						aFecha1 = fecha1.split(sep1);
						continuar = true;
						if(diffDays == day) {
							continuar = false;
				        }
					} else {
						continuar = false;
					}
				} while(continuar);
			}
		},
		DateDiffNegativo : function(interval, fecha1, fecha2) {
			var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
			var sep1 = fecha1.indexOf('/') != -1 ? '/' : '-';
			var sep2 = fecha2.indexOf('/') != -1 ? '/' : '-';
			var aFecha1 = fecha1.split(sep1);
			var aFecha2 = fecha2.split(sep2);
			
			var firstDate1 = new Date(aFecha1[2], aFecha1[1], aFecha1[0]);
			var firstDate2 = new Date(aFecha2[2], aFecha2[1], aFecha2[0]);
			var diffDays = Math.round((firstDate2.getTime() - firstDate1.getTime()) / (oneDay));
			if(interval == "d") {				
				return diffDays;
			} else if(interval == "m") { 
				var month = 0;
				var continuar = true;
				do {
					var day = new Date(aFecha1[2] || new Date().getFullYear(), aFecha1[1], 0).getDate();
					if(diffDays >= day) {
						month++;
						diffDays = diffDays - day;
						fecha1 = G.Form.dateAdd("d", day, fecha1);
						sep1 = fecha1.indexOf('/') != -1 ? '/' : '-';						
						aFecha1 = fecha1.split(sep1);
						continuar = true;	
						if(diffDays == day) {
							continuar = false;
				        }
					} else {
						month++;
						continuar = false;
					}
				} while(continuar);
				return month;
			} else if(interval == "yyyy") { 
				var year = 0;
				var continuar = true;
				do {
					var day = 365;
					var anio = aFecha1[2];				
					if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))) {
						day = 366;
					}  
					if(diffDays >= day) {
						year++;
						diffDays = diffDays - day;
						fecha1 = G.Form.DateAdd("d", day, fecha1);
						sep1 = fecha1.indexOf('/') != -1 ? '/' : '-';						
						aFecha1 = fecha1.split(sep1);
						continuar = true;
						if(diffDays == day) {
							continuar = false;
				        }
					} else {
						continuar = false;
					}
				} while(continuar);
			}
		},
		DateDiffYears: function DateDiffYears(y,d1, d2) {
			var resultado, f1,f2;
			f1 = d1.substr(6,4);
			f2 = d2.substr(6,4);
			resultado =  G.Val(f2) - G.Val(f1);
			
			return resultado;
		},
		DateAdd : function(interval, d, fecha) {
			var Fecha = new Date();
			var sFecha = fecha || (Fecha.getDate() + "/" + (Fecha.getMonth() +1) + "/" + Fecha.getFullYear());
			var sep = sFecha.indexOf('/') != -1 ? '/' : '-'; 
			var aFecha = sFecha.split(sep);
		 	var fecha = aFecha[2]+'/'+aFecha[1]+'/'+aFecha[0];
		 	fecha= new Date(fecha);
			if(interval == "d") { 
				fecha.setDate(fecha.getDate()+parseInt(d));
				var anno=fecha.getFullYear();
				var mes= fecha.getMonth()+1;
				var dia= fecha.getDate();
				mes = (mes < 10) ? ("0" + mes) : mes;
				dia = (dia < 10) ? ("0" + dia) : dia;
				var fechaFinal = dia+sep+mes+sep+anno;
				return (fechaFinal);
			 } else if (interval == "m") {
				var anno =fecha.getFullYear();
				var mes = fecha.getMonth() + 1;
				var dia = fecha.getDate();
				mes = (mes < 10) ? ("0" + mes) : mes;
				dia = (dia < 10) ? ("0" + dia) : dia;		 
				if((parseInt(mes) + d) > 12) {
					var auxMes = (parseInt(mes) + d) - 12;
			        auxMes = (auxMes < 10) ? ("0" + auxMes) : auxMes;
					var fechaFinal = dia+sep+auxMes+sep+(parseInt(anno)+1);
					return fechaFinal;
				} else {
			        var auxMes = parseInt(mes) + d;
			        auxMes = (auxMes < 10) ? ("0" + auxMes) : auxMes;
			        var fechaFinal = dia+sep+auxMes+sep+anno;
					return fechaFinal;
				}			 
			 } else if (interval == "yyyy") {
				 var anno =fecha.getFullYear();
				 var mes = fecha.getMonth() + 1;
				 var dia = fecha.getDate();
				 mes = (mes < 10) ? ("0" + mes) : mes;
				 dia = (dia < 10) ? ("0" + dia) : dia;
			     var auxMes = parseInt(anno) + d;
				 var fechaFinal = dia+sep+mes+sep+auxMes;
				 return fechaFinal;			 
			}
		},
		DateSerial: function(year, month, day) {
			if(!G.IsEmpty(year + "") && !G.IsEmpty(month + "") && !G.IsEmpty(day + "")) {
				return G.PadLeft(day, "00") + "/" +  G.PadLeft(month, "00") + "/" + G.PadLeft(year, "0000");
			} else {
				return "99/99/9999";
			}
		},
		ValidEmail: function(element) {
			// creamos nuestra regla con expresiones regulares.
			var filter = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
			// utilizamos test para comprobar si el parametro valor cumple la regla
			if (filter.test($.trim(element.val()))) {
				return true;
			}
			else {
				return false;
			}
		},
		ValidEmailTexto: function(texto) {
			// creamos nuestra regla con expresiones regulares.
			var filter = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
			// utilizamos test para comprobar si el parametro valor cumple la regla
			if (filter.test($.trim(texto))) {
				return true;
			} else {
				return false;
			}
		},
		SetCheckBox: function(element, estado) {
			element.attr('checked', estado);
		},
		GetCheckBox: function(element) {
			if(element.is(":checked")) {
				return true;
			}
			else {
				return false;
			}
		},
		validateDecimal: function(valor,msj) {
			var RE =  /^\d*\.?\d*$/;
		    if (RE.test(valor)) {
		        return true;
		    } else {
		    	alert(msj)
		        return false;
		    }
		},
		ValidTextoVacio: function(texto, msj) {
			if ( $.trim(texto) == "" || texto == null )
			{
				alert(msj);
				return false;
			}
			else
			{
				return true;
			}
		},
		ListCount: function(dropDownList) {
			//funcion que retorna la cantidad de options que tiene un dropdownlist
			var resultado;
			resultado=$('#'+ dropDownList+ '').children('option').length;
			return resultado
		},
		ValidateDosDecimal: function(valor) {
			var RE =/^\d+(\,\d{0,2})?$/;
		    if (RE.test(valor)) {
		        return true;
		    } else {
		        return false;
		    }
		},
		FormatearNumero : function(number, decimals, dec_point, thousands_sep) {
            number = (number + '').replace(/[^0-9+\-Ee.]/g, '');
            var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                          s = '',
                          toFixedFix = function(n, prec) {
                   var k = Math.pow(10, prec);
                   return '' + (Math.round(n * k) / k).toFixed(prec);
            };
            s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
            if (s[0].length > 3) {
                   s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
            }
            if ((s[1] || '').length < prec) {
                   s[1] = s[1] || '';
                   s[1] += new Array(prec - s[1].length + 1).join('0');
            }
            return s.join(dec);               
      },
	  mFormatearMonto: function(number, digits) {
		  return G.Form.FormatearNumero(number, digits, ",", ".");
	  },
	  //CUANDO SU SALIDA DEPENDE SI CONTIENE DECIMALES SEPARADOS SOLO CON COMA.
	  mFormatearMontoInDecimal: function(number){
		 var num = number.split(",");
		 var formatearNumber = "";
		 
		 if(num[1]!= "" && num[1]!= undefined)formatearNumber = G.Form.mFormatearMonto(num[0], false) +","+ num[1];
		 else formatearNumber = G.Form.mFormatearMonto(num[0], false); 
		
		 return formatearNumber; 
	  },
	  //CAMBIAR COMA POR PUNTO EN LOS DECIMALES
	  mChangeComaxPunto: function(number){
		var num = number.split(",");
		var formatearNumber = "";
		 
		if(num[1]!= "" && num[1]!= undefined)formatearNumber = num[0] +"."+ num[1];
		else formatearNumber = num[0]; 
		
		return formatearNumber; 
	  },
	},
	Util: {
		EsDigito: function(charnum) {
			var ok = false;			
			 if((charnum >= "0") && (charnum <= "9")) {
		        ok = true;
			 }
			 return ok;
		},	
		FormatoHora:function (cadena) {
			var hora = "";
			var minutos = "";
			var segundos = "";
			var horaFormato = null;

			hora = G.Mid(cadena, 1, 2);
			minutos = G.Mid(cadena, 3, 2); 
			segundos = G.Mid(cadena, 5, 2);

			horaFormato = hora + ":" + minutos + ":" + segundos;
			
			return horaFormato;
		},
		FechaActual: function(separador) {
			var f = new Date();
			var dia = f.getDate();
			var mes = f.getMonth() + 1;
			if ( mes < 10 )
				mes = '0' + mes;
			else
				mes = mes;
			if ( dia < 10 )
				dia = '0' + dia;
			else
				dia = dia;
			if(G.IsEmpty(separador)) {
				separador = "/";
			}
			return dia + separador + mes + separador + f.getFullYear();
		},
		DiaActual: function() {
			var f = new Date();
			var dia = f.getDate();
			if ( dia < 10 ){
				dia = '0' + dia;
			}
			return dia;
		},
		MesActual: function() {
			var f = new Date();
			var mes = f.getMonth() + 1;
			if ( mes < 10 ){
				mes = '0' + mes;
			}
			return mes;
		},
		AnioActual: function() {
			var f = new Date();
			return f.getFullYear();
		},
		HoraActual: function() {
            var f = new Date();
            var hh = f.getHours();
            var ss = f.getMinutes();
            if(hh<10) {hh='0'+hh} 
            if(ss<10) {ss='0'+ss}
            return hh+":"+ss;
	    },
	    HoraActualAMPM: function() {
            var f = new Date();
            var hours = f.getHours();
            var minutes = f.getMinutes();
            var seconds = f.getSeconds();
			var ampm = hours >= 12 ? 'p.m.' : 'a.m.';
			hours = hours % 12;
			hours = hours ? hours : 12; // the hour '0' should be '12'
			hours = hours < 10 ? '0' + hours : hours;
			minutes = minutes < 10 ? '0' + minutes : minutes;
			seconds = seconds < 10 ? '0' + seconds : seconds;
			var strTime = hours + ':' + minutes + ':' + seconds + ' ' + ampm;
			return strTime;
	    },
	    
	    HoraActualConSegundos: function() {
			var auxFec= new Date().toLocaleTimeString('en-US', { hour: 'numeric', minute: 'numeric',second:'numeric', hour12: true });
			return auxFec;
	    },
	    
		FormatearFecha:function(element){			
			var dia = G.Mid(element, 7, 2);
			var mes = G.Mid(element, 5, 2);
			var anio= G.Mid(element, 1, 4);		
			return dia + "/" + mes + "/" + anio;
		},		
		DesformatearFecha:function(element){
			var dia = G.Mid(element, 1, 2);
			var mes = G.Mid(element, 4, 2);
			var anio= G.Mid(element, 7, 4);
			return anio+mes+dia;
		},
		DesformatearFechaAnnioMesDia:function(texto){ 
			var dia = G.Mid(texto, 1, 2);
			var mes = G.Mid(texto, 4, 2);
			var anio= G.Mid(texto, 7, 4);
			return anio + "/" + mes + "/" + dia ;
		},
		DesformatearFechaYYMMDD:function(element){
			var dia = G.Mid(element, 1, 2);
			var mes = G.Mid(element, 4, 2);
			var anio= G.Mid(element, 9, 2);
			return anio+mes+dia;
		},
		DesformatearFechaYYMMDDconSeparador:function(element){
            var dia = G.Mid(element, 1, 2);
            var mes = G.Mid(element, 4, 2);
            var anio= G.Mid(element, 7, 4);
            return anio+mes+dia;
        },
		ObtMes:function(numMes) {
			var resp = "";
			switch (numMes) {
			    case "1":
			    	resp = "ENE";
			        break;
			    case "2":
			    	resp = "FEB";
			        break;
			    case "3":
			    	resp = "MAR";
			        break;
			    case "4":
			    	resp = "ABR";
			        break;
			    case "5":
			    	resp = "MAY";
			        break;
			    case "6":
			    	resp = "JUN";
			        break;
			    case "7":
			    	resp = "JUL";
			        break;
			    case "8":
			    	resp = "AGO";
			        break;
			    case "9":
			    	resp = "SEP";
			        break;
			    case "10":
			    	resp = "OCT";
			        break;
			    case "11":
			    	resp = "NOV";
			        break;
			    case "12":
			    	resp = "DIC";
			        break;
			    default:
			    	resp = "***";
		        break;
			}
	        return resp;
		},
		FormateaFechaDesdeSIC:function(strFecha) {
			var strDia = G.Trim(G.Mid(strFecha, 5, 2));
			var strMes = G.Trim(G.Mid(strFecha, 1, 3));
			var strAnio = G.Trim(G.Mid(strFecha, 7));
			
			if(G.Length(strDia) == 1){
				strDia = "0" + strDia;
			}
			if(G.Length(strMes) == 1){
				strMes = "0" + strMes;
			}
			
			if(strMes == "Jan"){
				strMes = "01";
			} else if(strMes == "Feb"){
				strMes = "02";
			} else if(strMes == "Mar"){
				strMes = "03";
			} else if(strMes == "Apr"){
				strMes = "04";
			} else if(strMes == "May"){
				strMes = "05";
			} else if(strMes == "Jun"){
				strMes = "06";
			} else if(strMes == "Jul"){
				strMes = "07";
			} else if(strMes == "Aug"){
				strMes = "08";
			} else if(strMes == "Sep"){
				strMes = "09";
			} else if(strMes == "Oct"){
				strMes = "10";
			} else if(strMes == "Nov"){
				strMes = "11";
			} else if(strMes == "Dec"){
				strMes = "12";
			}
						
	        return G.Trim(strDia) + "/" + G.Trim(strMes) + "/" + G.Trim(strAnio);
		},
		Espacios:function(cadena,largo,align){
		    if(align == "I"){
		    	cadena=cadena + G.String(" ",largo-cadena.length);
		    }
		    else{
		    	cadena= G.String(" ",largo-cadena.length)+ cadena;
		    }		    
		    return cadena;			
		},
		CompararFecha :function(fecha1,operadorLogico,fecha2){
			var retorno = false;
			if(G.Trim(operadorLogico) == "<"){
				if(Date.parse(fecha1) < Date.parse(fecha2)){
					retorno = true;
				}
			}
			if(G.Trim(operadorLogico) == "<="){
				if(Date.parse(fecha1) <= Date.parse(fecha2)){
					retorno = true;
				}
			}
			if(G.Trim(operadorLogico) == ">"){
				if(Date.parse(fecha1) > Date.parse(fecha2)){
					retorno = true;
				}
			}
			if(G.Trim(operadorLogico) == ">="){
				if(Date.parse(fecha1) >= Date.parse(fecha2)){
					retorno = true;
				}
			}
			if(G.Trim(operadorLogico) == "=="){
				if(Date.parse(fecha1) == Date.parse(fecha2)){
					retorno = true;
				}
			}
			return retorno;			
		},
		MesAnterior:function(fecha){		
			var dia = G.Mid(fecha, 1, 2);
			var mes = G.Mid(fecha, 4, 2);
			var anio= G.Mid(fecha, 7, 4);				
			var fechAnterior= new Date(anio, mes -2,dia);
			dia = fechAnterior.getDate();
			mes = fechAnterior.getMonth() + 1;
			anio=fechAnterior.getFullYear();
			
			dia=dia.toString();
			mes=mes.toString();
			
			if(dia.length==1){
				dia="0"+dia;
			}
			
			if(mes.length==1){
				mes="0"+mes;
			}
			
			return dia + "/" + mes + "/" + anio;			
		},
		ValidarFormatoFecha : function(campo) {
		      var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{4,4}$/;
		      if ((campo.match(RegExPattern)) && (campo!='')) {
		            return true;
		      } else {
		            return false;
		      }
		},
		/**
		 * psDato = "1.135.356,56" 
		 * devuelve "1,135,356.56"
		 * si borraComa = true 1135356.56
		 * */
		DesformatearMonto : function(psDato, borraComa){			
			if(psDato == ""){
				return "0";
			}
			
			psDato = psDato.toString();
			psDato = psDato.replace(/\./g,'a').replace(/,/g,'.').replace(/a/g,',');
			
			if(borraComa && borraComa == true){
				psDato = psDato.replace(/,/g,'');
			}
			
			return psDato; 
		},
		RedondearMonto : function (monto, cantidadDecimal){
			var redondeo= Math.round(monto * Math.pow(10, cantidadDecimal)) / Math.pow(10, cantidadDecimal).toFixed(cantidadDecimal);
			return redondeo
		}
	},
	/* Función que permite agregar 365 dias a una fecha */
	AddYear : function(fechaAnterior) {
		var tt = fechaAnterior;

		var date = new Date(tt);
		var newdate = new Date(date);

		newdate.setDate(newdate.getDate() + 365);

		var dd = newdate.getDate();
		var mm = newdate.getMonth() + 1;
		var anio = newdate.getFullYear();

		var mes = G.Lpad(mm);
		var dia = G.Lpad(dd);

		var someFormattedDate = dia + '/' + mes + '/' + anio;

		return someFormattedDate;
	},
	Lpad : function(cadena) {
		var mystr = cadena;
		var pad = '00';
		var cadenaf = pad + mystr;
		return cadenaf.slice(-pad.length);
	}
};

var RUT = RUT || {};

RUT = {		
		mensajesRutDirectiva: function(idElemento, rut, minCharactersRut, maxCharactersRut, styleInput){
//			if(G.Len(rut) <= minCharactersRut){
//				$("#" + idElemento).addClass(styleInput);
//				alert("Cantidad de d\u00EDgitos de RUT debe ser mayor a " + minCharactersRut);
//				$("#" + idElemento).focus();
//			} else 
			if(G.Len(rut) > maxCharactersRut){
				$("#" + idElemento).addClass(styleInput);
				alert("El rut ingresado está fuera de rango.");
//				$("#" + idElemento).focus();
				return false;
			} else {
				if(!RUT.validarLegalPartner(rut).estado){
	    			$("#" + idElemento).addClass(styleInput);
	    			alert("El rut ingresado no es v\u00E1lido.");
//	    			$("#" + idElemento).focus();
	    			return false;
	    		} else {
	    			$("#" + idElemento).removeClass(styleInput);
	    			return true;
	    		}
			}
		},
		validarLegalPartner:function(rutlocal)
		{
			if(rutlocal != undefined && rutlocal != "" && rutlocal != "0" && rutlocal != "0-0"){
				var factor = 1;
				rutlocal = RUT.QuitaPuntosRut(rutlocal);
				var largo = G.Len(rutlocal);
				var posLin = G.InStr(0, rutlocal, "-");
				var suma = 0;
				
				if ((largo > posLin + 1) || (largo == posLin)) {
					objRpta = {
						estado : false                           
					};
					return objRpta;
				}
				for(var i=(posLin-1); i >= 1; i--) {
					factor = factor + 1;
					if (factor > 7) {
						factor = 2
					}
					suma = suma + factor * G.Val(G.Mid(rutlocal, i, 1));			
				}
				var resto = suma%11;
				var digVerif = G.Trim(G.Str(11 - resto));
			    if (digVerif == "10") {
			        digVerif = "K";
			    } else if (digVerif == "11") {
			        digVerif = "0";
			    }
				if (G.UCase(G.Mid(rutlocal, posLin + 1, 1)) == G.Trim(G.Trim(digVerif))) {
			        objRpta = {
						estado : true                           
					};
					return objRpta;
				} else {
			        objRpta = {
						estado : false                           
					};
					return objRpta;
				}
			} else {
				objRpta = {
					estado : false                           
				};
				return objRpta;
			}
	},
	RutSinDigitoV: function(lcRut){			
	   var rut = lcRut.split("-");
	   return rut[0];
	},		
	GObtenerDigitoVerificador: function(lc_Rut){
		var lc_Digito;
		var li_Acumulado;
		var li_LargoRut;
		var j = 2;
		li_LargoRut = G.Len(lc_Rut);
		li_Acumulado = 0;
		for(i=0 ; i <= lc_Rut.length ; i++){
			li_Acumulado = li_Acumulado + (G.Mid(lc_Rut, li_LargoRut, 1) * j)
			j = j + 1;
			li_LargoRut = li_LargoRut - 1
			if(j=8) j = 2
		}
		lc_Digito = 11 - (li_Acumulado % 11);
		if(lc_Digito == 11) lc_Digito = "0";
		if(lc_Digito == 10) lc_Digito = "K";

		return G.Trim(lc_Digito);	
    },
	PurificarRut: function(numero) {
		var numeroAux = "";
		for(var i = 0; i <= numero.length; i++) {
			var lcNumero = G.Mid(numero,i,1);
			if(G.InStr(undefined, "0123456789K", lcNumero) != 0) {
				numeroAux = numeroAux + lcNumero;
			}
		}
		if(G.Mid(numeroAux,1,1) == "0") {
			numeroAux = G.Mid(numeroAux,2);
		}
		return numeroAux;
	},	
	PoneCeroIzq: function(chars) {
		var blco = "";
		var largo = 10 - G.Len(chars);
		for(var i=0; i<largo; i++) {
			blco = blco + "0";
		}
		return blco + chars
	},		
	QuitaEdicionRut: function(rutlocal) {
		/* QuitaEdicionRut: Recibe un string con formato tipo RUT (99.999.999-9) y retorna otro con
		                    formato (99999999-9). Elimina todos los puntos "." de un string.
		*/
		var charMiles = "."
		if(!G.IsEmpty(rutlocal)) {	
		    while (G.InStr(0, rutlocal, charMiles) > 0) {
		        rutlocal = G.Mid(rutlocal, 1, G.InStr(0, rutlocal, charMiles) - 1) + G.Mid(rutlocal, G.InStr(0, rutlocal, charMiles) + 1);
		    }
		    charMiles = "-";
		    while (G.InStr(0, rutlocal, charMiles) > 0) {
		        rutlocal = G.Mid(rutlocal, 1, G.InStr(0, rutlocal, charMiles) - 1) + G.Mid(rutlocal, G.InStr(0, rutlocal, charMiles) + 1);
		    }
		}
		rutlocal = (!G.IsEmpty(rutlocal) ? rutlocal : "");
		return rutlocal;
	},
	QuitaEdicionRutR: function(rutlocal) {
		/* Quitaedicionrutr: Recibe un string con formato tipo RUT (99999999-9) y retorna otro con
		                     formato (999999999). Elimina la "-" de un string.
		*/
		var charMiles = "-";
		if(!G.IsEmpty(rutlocal)) {	
		    while (G.InStr(0, rutlocal, charMiles) > 0) {
		        rutlocal = G.Mid(rutlocal, 1, G.InStr(0, rutlocal, charMiles) - 1) + G.Mid(rutlocal, G.InStr(0, rutlocal, charMiles) + 1);
			}
		}  
		rutlocal = (!G.IsEmpty(rutlocal) ? rutlocal : "");
	    return rutlocal;
	},
	QuitaPuntosRut: function(rutlocal) {
		var charMiles = ".";
		if(!G.IsEmpty(rutlocal)) {	
			while (G.InStr(0, rutlocal, charMiles) > 0) {
	        	rutlocal = G.Mid(rutlocal, 1, G.InStr(0, rutlocal, charMiles) - 1) + G.Mid(rutlocal, G.InStr(0, rutlocal, charMiles) + 1);
			}
		}
		rutlocal = (!G.IsEmpty(rutlocal) ? rutlocal : "");
	    return rutlocal;
	},	
	ValidaRut: function(rutlocal) {
		var factor = 1;
		rutlocal = RUT.QuitaPuntosRut(rutlocal);
		var largo = G.Len(rutlocal);
		var posLin = G.InStr(0, rutlocal, "-");
		var suma = 0;
		
		if ((largo > posLin + 1) || (largo == posLin)) {
			return false;
		}
		for(var i=(posLin-1); i >= 1; i--) {
			factor = factor + 1;
			if (factor > 7) {
				factor = 2
			}
			suma = suma + factor * G.Val(G.Mid(rutlocal, i, 1));			
		}
		var resto = suma%11;
		var digVerif = G.Trim(G.Str(11 - resto));
	    if (digVerif == "10") {
	        digVerif = "K";
	    } else if (digVerif == "11") {
	        digVerif = "0";
	    }
		if (G.UCase(G.Mid(rutlocal, posLin + 1, 1)) == G.Trim(G.Trim(digVerif))) {
	        return true;
		} else {
	        return false;
		}
	},
	EditaRut: function(rutlocal) {
		/* EditaRut: Toma un string que viene en formato 99999999-9 o 99999999-9 con puntos y lo
		 * 			 tanforma en formato de RUT, 99.999.999-9
		 */
		var charMiles = ".";
		while (G.InStr(0, rutlocal, charMiles) > 0) {
        	rutlocal = G.Mid(rutlocal, 1, G.InStr(0, rutlocal, charMiles) - 1) + G.Mid(rutlocal, G.InStr(0, rutlocal, charMiles) + 1);
		}
	    rutlocal = G.Trim(rutlocal);
	    var punto = 0;
	    rutOk = "";
	    
	    if (G.InStr(0, rutlocal, "-") != 0) {
	        rutAux = G.Left(rutlocal, G.Len(rutlocal) - 1);
	    }
	    var i = G.Len(rutlocal) - 1;
	    if (i == -1) {
	        return;
	    }
	    var digito = "-" + G.Mid(rutlocal, i + 1, 1);
	    if(i > 0) {
	    	while(i > 0) {
	    		var charnum = G.Mid(rutlocal, i, 1);
	            if (G.Util.EsDigito(charnum)) {
	                if (punto == 3) {
	                    var rutOk = "." + rutOk;
	                    var punto = 0;
	                }
	                rutOk = charnum + rutOk;
	                punto = punto + 1;
	            }
	            i--;
	    	}
	    	rutlocal = rutOk + digito;
	    }
	    return rutlocal;
	},		
	FormatCtaCteDisplay: function(ctaCte,  indFmt) {
		var auxCtaCte;
		var numCtaCte;
		
		auxCtaCte = RUT.FormatCtaCteDB(ctaCte, indFmt)
		
		if(G.Trim(auxCtaCte) == ""){
			return "";
		}
		
		if(indFmt != "A" && indFmt !="L" && indFmt !="C"  && indFmt != "V"){
			return "";
		}
		
		if(G.IsNumeric(auxCtaCte)) {
			numCtaCte = G.CDbl(G.Trim(auxCtaCte));
			if(indFmt == "A"){
				return RUT.Format(numCtaCte, "0000-00000-00");
			} else {
				if(indFmt == "C"){
					return RUT.Format(numCtaCte, "00-00000-00");
				} else {
					if(indFmt == "V"){
						return RUT.Format(numCtaCte, "00-00000-00");
					} else {
						return RUT.Format(numCtaCte, "000-000-000-000");
					}
				}
			}
		} else {
			return "";
		}
	},
	Format: function(nroCtaCte, formato) {
		var retorno = "";
		var auxFormato = "";
		var auxNroCtaCte = "";
		
		var auxiliar = "";
		var i = 0;
		
		if(!G.IsEmpty(nroCtaCte)){
			for(i = 0; i < G.Len(formato); i++){
				var  midCadena = G.Mid(formato, i ,1);
				if(midCadena == "-"){
					auxiliar = auxiliar + midCadena;
				}
			}
			
			auxFormato = G.ReplaceAll(formato, "-", "");
			if(auxFormato != null && G.Len(auxFormato) != 0){
				auxNroCtaCte = RUT.Leftpad(nroCtaCte, G.Len(auxFormato), auxFormato.charAt(0))
			}
			
			for(i = 0; i < G.Len(auxiliar); i++){
				auxNroCtaCte = auxNroCtaCte.substring(0, i) + "-" + auxNroCtaCte.substring(i, G.Len(auxNroCtaCte));
			}
			retorno = auxNroCtaCte;
		}
		return retorno;
	},	
	Leftpad : function (str, len, ch) {
		str = String(str);
		var i = -1;
		if (!ch && ch !== 0) ch = ' ';
		len = len - str.length;
		while (++i < len) {
		str = ch + str;
		}
		
		return str;
	},
	FormatCtaCteDB : function (ctaCte,  indFmt){
		var ctaCteDB = "", formatCtaCteDB = "";
		var init = 0;
		var i;
		if(indFmt != "A" && indFmt !="L" && indFmt !="C"  && indFmt != "V") {
			formatCtaCteDB = "";
			return formatCtaCteDB;
		}
		ctaCte = G.Trim(ctaCte);
		for(i=0; i< G.Len(ctaCte);i++){
			 var midCadena = G.Mid(ctaCte, i+1 ,1);			
			if(midCadena != "0" && midCadena != " ") {
				init = i;
				break;
			}
		}
		if(init == 0) {
			formatCtaCteDB = "";
			return formatCtaCteDB;
		}
		for(i=init; i<G.Len(ctaCte);i++){
			var  midCadena = G.Mid(ctaCte, i+1 ,1);	
			if(midCadena >= "0"  && midCadena <= "9") {
				ctaCteDB = ctaCteDB + midCadena;
			}
		}
		if(G.IsEmpty(G.Trim(ctaCteDB))) {
			formatCtaCteDB = "";
			return formatCtaCteDB;
		}		
		if(!G.IsNumeric(ctaCteDB)) {
			formatCtaCteDB = "";
			return formatCtaCteDB;
		}
		if(indFmt == "A") {
			if(G.Len(ctaCteDB) > 11) {
				formatCtaCteDB = "";
				return formatCtaCteDB;
			}
			ctaCteDB = G.Right("00000000000" + ctaCteDB, 11);
		} else {
			if(indFmt == "C") {
				if(G.Len(ctaCteDB) > 9) {
					formatCtaCteDB = "";
					return formatCtaCteDB;
				}
				ctaCteDB = G.Right("000000000" + ctaCteDB, 9);
			} else {
				if(indFmt == "V") {
					if(G.Len(ctaCteDB) > 12) {
						formatCtaCteDB = "";
						return formatCtaCteDB;
					}
					ctaCteDB = G.Right("000000000000" + ctaCteDB, 12);
				}
				else {
					if(G.Len(ctaCteDB) > 11) {
						formatCtaCteDB = "";
						return formatCtaCteDB;
					}
					ctaCteDB = G.Right("00000000000" + ctaCteDB, 11);
				}
			}
		}
		formatCtaCteDB = ctaCteDB;
	    return formatCtaCteDB;
	},
	mCalcularDVer : function(rut){
        var lar,ind,digit,rutAux,digCal,fac,suma;

        digCal = " ";
        rutAux = rut;
        lar = String(rutAux).length;
        //Ajustar largo rut
        if(lar < 9){
                rutAux = G.Left("000000000", 9-lar) + rutAux;
        }

        //Calcular digito verificador
        fac = "432765432";
        suma = 0;
        ind = 9;

        while(ind>0){
                suma = suma + (G.Val(G.Mid(String(rutAux), ind, 1))) * G.Val(G.Mid(String(fac),ind,1));
                ind = ind - 1;
        }

        digit = 11 - (suma - parseInt(suma/11) * 11);

        //Determinar valor dver
        if(digit == 10){
                digCal = "K";
        }else if(digit == 11){
                digCal = "0";
        }else {
                digCal = G.Trim(String(digit));
        }

        return digCal;
	},
	Truncate : function(n){
		  return Math[n > 0 ? "floor" : "ceil"](n);
	},
	Formatear: function(rut) {
		if( G.InStr(undefined, rut, ".") > 0 ) {
			return rut;
		}
		else {
			var ind = G.Len(rut);
			if( ind > 1 ) {
				var digVerificador = G.UCase(G.Right(rut, 1));
				ind = ind - 1;
				var folioAux = G.Left(rut, ind);
				if( ind < 10 )
				{
					var folio = G.String(9 - ind, "0") + folioAux;

					folioAux = String(folio).substring(0, 3) + ".";
					folioAux += String(folio).substring(3, 6) + ".";
					folioAux += String(folio).substring(6, 10);
					return folioAux + "-" + digVerificador;
				}
			}
			else {
				return rut;
			}
		}
	},
	Desformatear: function(rut, sinCompletar) {
		var _rut = G.Trim(rut);

		var result = _rut.replace(".", "");
		result = result.replace(".", "");
		result = result.replace("-", "");
		if(sinCompletar) {
			return result;
		} else {
			return G.String(10 - result.length, "0") + result;
		}	
	},
	Validar: function(rut) {
		var rut = G.Trim(this.Desformatear(rut));
		var dig = rut.slice(-1);
		var folio = rut.substring(0, rut.length - 1);
		// Add Ceros Rut Aux
		var aux_folio = G.String(9 - folio.length, "0") + folio;

		var suma = 0;
		var resto = 0;
		var dv = "";
		var pond = "432765432";
		for ( var j = 0; j < 9; j++ ) {
			suma = suma + pond.charAt(j) * aux_folio.charAt(j);
		}
		resto = 11 - (suma % 11);
		if ( resto == 10 ) {
			dv = "K";
		}
		else if ( resto == 11 ) {
			dv = "0";
		}
		else if ( resto >= 1 && resto <= 9 ) {
			dv = String.fromCharCode(resto + 48);
		}
		if ( dv == dig.toUpperCase() ) {		
			return true;
		}
		else {
			return false;
		}
	},
	FmtRut: function(rut) {
		/*
		 * rut entrada: 0081118868 (cadena de 10 caracteres)
		 * rut salida:  8.111.886-8, ver form BuscarMovimientosAnular
		 */
		var numero;
		var dv;

		if(!G.IsEmpty(rut)) {
			if(G.Len(rut) > 1) {
				numero = G.Mid(rut, 1, G.Len(rut) - 1);
				dv = G.Right(rut, 1);
				numero = RUT.FmtMonto(""+numero);
				numero = G.Mid(numero, 1, G.InStr(1, numero, ",") - 1);

				return numero + "-" + G.UCase(dv);	
			} else {
				return rut;
			}
			return "";
		}
	},
	FormatearRUTSQL : function(Rut_sql){
		if(!G.IsEmpty(Rut_sql)) {
			if(G.Len(Rut_sql) > 1) {
				return G.Trim(String(G.CDbl(G.Mid(G.Trim(Rut_sql),1,G.Len(G.Trim(Rut_sql))-1 )))) + "-" + G.Right(G.Trim(Rut_sql),1);
			} else {
				return Rut_sql;
			}
		}
		return "";
	},
	TransformaRut : function (rut, tipo) {
		var  rr ;
		var rr1 ;
		var rr2;
		var d ;
		rut =G.Right(G.String(10, "0")+rut,10); //AgrCeros(rut, 10)
		if(rut == "0000000000"){
	      return  "        0-0";
		}      
		else if(G.Trim(rut) == "" ){
	      return  "   ";   
		}
		if( rut!= "" ){
	      if( tipo == 1 ){
	         rr = G.Mid(rut, 7, 3);
	         rr1 = G.Mid(rut, 4, 3);
	         rr2 = G.Mid(rut, 1, 3);
	         d = G.Right(rut, 1);
	         rr2 = rr2 + "." + rr1 + "." + rr + "-" + d;
	         while( G.Val(G.Left(rr2, 1)) < 1 || G.Val(G.Left(rr2, 1)) > 9){
	            rr2 = G.Mid(rr2, 2);
	         }   
	         return rr2;
	      }else{
	         rr = G.Mid(rut, 1, Len(rut) - 1);
	         d = G.Right(rut, 1);
	         return  rr + "-" + d;
	      }
		}else{
	      return  " ";
	   }
	},	
	FmtMonto: function(mto) {
		/*
		 *  Recibe un monto texto y lo devuelve con el formato
			99.999.999,99
		 */
		var cuenta;
		var i;
		var largo;
		var parte_entera;
		var parte_dec;
		var aux;

		largo = G.Len(mto);
		if(G.InStr(1, mto, ".") == 0){
			parte_entera = mto;
			parte_dec = "00";
		}
		else{
			parte_entera = G.Mid(mto, 1, G.InStr(1, mto, ".") -1);
			parte_dec = G.Mid(mto, G.InStr(1, mto, ".") + 1, largo - G.Len(parte_entera) -1);
		}
		cuenta = 0;
		aux = "";
		for(i = G.Len(parte_entera); i > 0; i--){
			if(cuenta == 3){
				aux = "." + aux;
				cuenta = 0;
			}
			cuenta = cuenta +1;
			aux = G.Mid(parte_entera, i, 1) + aux;
		}

		return aux + "," + parte_dec;
	},
	FmtMonto4Decimales: function(mto) {
		/*
		 *  Recibe un monto texto y lo devuelve con el formato
			99.999.999,99
		 */
		var cuenta;
		var i;
		var largo;
		var parte_entera;
		var parte_dec;
		var aux;

		largo = G.Len(mto);
		if(G.InStr(1, mto, ".") == 0){
			parte_entera = mto;
			parte_dec = "0000";
		}
		else{
			parte_entera = G.Mid(mto, 1, G.InStr(1, mto, ".") -1);
			parte_dec = G.Mid(mto, G.InStr(1, mto, ".") + 1, largo - G.Len(parte_entera) -1);
		}
		cuenta = 0;
		aux = "";
		for(i = G.Len(parte_entera); i > 0; i--){
			if(cuenta == 3){
				aux = "." + aux;
				cuenta = 0;
			}
			cuenta = cuenta +1;
			aux = G.Mid(parte_entera, i, 1) + aux;
		}

		return aux + "," + parte_dec;
	},
	DisplayRUT: function(rut){
		
		if( G.Trim(rut)!= ""){
			if(G.IsNumeric(G.Left(rut, G.Len(rut)-1 ))){
				var numRut = parseInt(G.Left(rut, G.Len(rut)-1 ));
				var dgv = G.Right(rut, 1);
				
				var aux="";				
				var contador=1;
			    for(var i=G.Len(numRut+"")-1; i>=0 ;i--){
			    	
			    	var caracter =numRut.toString().charAt(i);
			    	aux= caracter + aux;
			    	if(contador==3){
			    		aux="."+aux;
			    		contador=1;
			    	}
			    	else{
			    	    contador++;
			    	}
			    }			    
			    if(aux.charAt(0)==="."){
			    	aux=G.Mid(aux, 2, aux.length);
			    }
			   
			    return aux+"-"+dgv;
			}
			return rut;
		}
		else{
			return "";
		}
	},
	FormatRutDB : function(rut){
		var  AuxChar ;
		var RutDB  ;
		var i=0;
		var Init ;

	    RutDB = "";

	    rut = G.Trim(rut);

	    Init = 0;
	    for( i = 1 ;i< G.Len(rut);i++){
	        if((G.Mid(rut, i, 1) != "0") && (G.Mid(rut, i, 1) != " ")){
	            Init = i;
	            break;
	         }
        }

	    if( Init == 0 ){
	        return "";
	    }

	  
	    for( i = Init ;i<= G.Len(rut);i++){
	         AuxChar = G.UCase(G.Mid(rut, i, 1));
	        if ((AuxChar == "K") || ((AuxChar >= "0") && (AuxChar <= "9"))){
	            RutDB = RutDB + AuxChar;
	        }		    
	     }

	    if (G.Trim(RutDB) == "" ){
	       return "";
	        
	    }
	    
	    if (G.Len(RutDB) > 10){
	    	  return "";
	    }

	    if( !G.IsNumeric(G.Left(RutDB, G.Len(RutDB) - 1))){
	    	 return "";
	    }

	    RutDB = G.Right("0000000000" + RutDB, 10);
	    return RutDB;
	},
	FormatRUTDisplay : function(rut){
		if( G.Trim(rut)!= ""){
			if(G.IsNumeric(G.Left(rut, G.Len(rut)-1 ))){
				var numRut = parseInt(G.Left(rut, G.Len(rut)-1 ));
				var dgv = G.Right(rut, 1);
				
				var aux=""; 
				var contador=1;
				for(var i=G.Len(numRut+"")-1; i>=0 ;i--){
					var caracter =numRut.toString().charAt(i);
					aux= caracter + aux;
					if(contador==3) {
						aux="."+aux;
						contador=1;
					} else {
						contador++;
					}
				}
				
				if(aux.charAt(0)==="."){
					aux=G.Mid(aux, 2, aux.length);
				}
				return aux+"-"+dgv;
			}
			return rut;
		} else {
			return "";
		}
	},
	CompletarRut : function(rut){
		rut = $.trim(rut);
		var long = rut.length;
		var num_ceros = 10 - long;

		for (var i = 1; i <= num_ceros; i++) {
			rut = '0' + rut;
		}
		return rut.toUpperCase();
	}
};