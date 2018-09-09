import zelda as z
import time


##############################################################################
##	 Funcion: get_mapa()
##				convierte el archivo de texto en un arreglo bidimensional
##		+ Paramentros
## 			mapa: ruta del archivo del mapa
## 	+ Regresa
##				matriz bidimensional de elementos del mapa
##############################################################################		
def get_mapa(mapa):
	contenido = []
	archivo = open(mapa, "r")
	linea = archivo.readline()
	numLinea = 0
	while linea != "":
		longitud = len(linea)
		numColumna = 0
		contenido.append([])
		contenido[numLinea]=list(linea[0:-1])
		linea = archivo.readline()
		numLinea += 1
	archivo.close()
	return contenido


##############################################################################
##	 Funcion: cargarMapa()
##			Crea el terreno para el mapa (grafico)		
##		+ Paramentros
## 			mapa: ruta del archivo del mapa
## 	+ Regresa
##				lista general de los terrenos del mapa
##############################################################################
def cargarMapa(mapa):
	listaGeneral=[]
	archivo = open(mapa, "r")
	linea = archivo.readline()
	ancho=len(linea)-1
	numLinea = 0
	while linea != "":
		longitud = len(linea)
		numColumna = 0
		while numColumna < longitud-1:
			contenido = linea[numColumna:numColumna+1]
			ambiente = z.CualquierTerreno(50*numColumna,50*numLinea,contenido)
			listaGeneral.append(ambiente)
			numColumna += 1
		linea = archivo.readline()
		numLinea += 1
	archivo.close()
	return listaGeneral,ancho*50,numLinea*50
	
##############################################################################
##	 Funcion: salvarMapa()
##			Guarda el mapa en un archivo de texto	
##		+ Paramentros
## 			listaGeneral: terrenos del mapa
##############################################################################	
def salvarMapa(listaGeneral):
	nombreMapa = time.strftime("log/%d%m%Y_%H%M%S.map")
	#print "Mapa guardado "+nombreMapa
	archivo = open(nombreMapa, "w")
	if len(listaGeneral)>0:
		for ambiente in listaGeneral:
			if ambiente.posx == 0 and ambiente.posy != 0:
				archivo.write("\n")
			archivo.write(ambiente.tipo)
	archivo.close()
