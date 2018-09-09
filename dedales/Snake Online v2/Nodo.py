tabla1={"A":0,"B":1,"C":2,"D":3,"E":4,"F":5,"G":6,"H":7,"I":8,"J":9,"K":10,"L":11,"M":12,"N":13,"O":14,"P":15,"Q":16,"R":17}
tabla2={0:"A",1:"B",2:"C",3:"D",4:"E",5:"F",6:"G",7:"H",8:"I",9:"J",10:"K",11:"L",12:"M",13:"N",14:"O",15:"P",16:"Q",17:"R"}

##############################################################################
### Clase Nodo. 
##		
##		Esta clase representa un estado dentro del mapa. Tiene una ubicacion en
##		coordenas de fila y columna (numero,letra), el nombre de la casilla, un
##		indicador para saber si es un nodo hoja, las coordenadas equivalentes
##		para la animacion y un conjunto de opciones disponibles desde ese nodo.
##		
##############################################################################
class Nodo():
	
	def __init__(self, fila, columna):
		self.fila = fila
		self.columna = columna
		self.nombre=fila+columna
		self.esHoja = False
		self.x = (int)(fila) - 1	# coordenada  (fila) (numero)
		self.y = tabla1[columna]  	# coordenada (columna) (letra)
		self.coordenada_y = self.x*50
		self.coordenada_x = self.y*50
		self.P = 0
	opciones = []	
