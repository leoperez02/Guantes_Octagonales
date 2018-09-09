import time,pygame,sys
from pygame.locals import *

listaTipos = ["0", "1", "2", "3", "4"]
listaRutas = ["pared", "tierra", "arena", "agua", "pasto"]
numTerrenos = 5

##############################################################################
### Clase Zelda. 
##		
##		
##		
##############################################################################
class Zelda(pygame.sprite.Sprite):
	"""Clase para Zelda"""
	#CREACION DEL SPRITE DE LA IMAGEN ZELDA
	def __init__(self,nodo):
		pygame.sprite.Sprite.__init__(self)
		#AGREGAMOS DOS IMAGENES PARA LA ANIMACION DE MOVIMIENTO
		self.imagenZeldaUno = pygame.image.load("img/zelda_uno.png")
		self.imagenZeldaDos = pygame.image.load("img/zelda_dos.png")
		#CREAMOS UNA LISTA EN DONDE ESTAN LAS DOS IMAGENES DE LA ANIMACION
		self.listaImagenes = [self.imagenZeldaUno, self.imagenZeldaDos]
		self.posImagen = 0
		#CREAMOS LA IMAGEN A PARTIR DE LA LISTA EN LA POSICION 0
		self.imagenZelda = self.listaImagenes[self.posImagen]

		self.rect = self.imagenZelda.get_rect() #40x46
		self.rect.x=nodo.coordenada_x+(50-self.rect.width)/2#(50-self.rect.width)/2 es el offset porque LINK es de otro tamano
		self.rect.y=nodo.coordenada_y+(50-self.rect.height)/2

		self.velocidad = 50

		#ESTE VA A SER EL CAMBIO DE LA ANIMACION DE LA IMAGEN
		self.tiempoCambio = 1

	def dibujar(self, superficie):
		#SE CARGA LA LISTA DE IMAGENES DE LA ANIMACION
		self.imagenZelda = self.listaImagenes[self.posImagen]
		superficie.blit(self.imagenZelda,self.rect)

	#ANIMACION DEL JUGADOR
	def animacion(self, tiempo):
		if self.tiempoCambio == tiempo:
			#AVANZA A LA IMAGEN DOS
			self.posImagen +=1
			self.tiempoCambio +=1
			#REGRESA A LA IMAGEN UNO
			if self.posImagen > len(self.listaImagenes)-1:
				self.posImagen = 0
		
	#AQUI SE DEFINEN QUE NO PASE DE LOS LIMITES DE LA VENTANA
	def movimiento(self,ancho,alto):
		if self.rect.left <= 5:
			self.rect.left = 5

		if self.rect.right >= ancho - 4:
			self.rect.right = 5

		if self.rect.top < 2:
			self.rect.top = alto - 2

		if self.rect.bottom >= alto - 2:
			self.rect.bottom = alto - 2
	
	#def vision(self, ventana,listaGeneral):
	#	pos_jugador_x = self.rect.x - 5
	#	pos_jugador_y = self.rect.y - 2
	#	for casillas in listaGeneral:
	#		if pos_jugador_x == casillas.rect.x and pos_jugador_y == casillas.rect.y: # Posicion del jugador
	#			casillas.setOpacity(255, ventana)
	#		if pos_jugador_x-50 == casillas.rect.x and pos_jugador_y == casillas.rect.y: # Izquierda
	#			casillas.setOpacity(255, ventana)
	#		elif pos_jugador_x+50 == casillas.rect.x and pos_jugador_y == casillas.rect.y: # Derecha
	#			casillas.setOpacity(255, ventana)
	#		elif pos_jugador_x == casillas.rect.x and pos_jugador_y-50 == casillas.rect.y: # Arriba
	#			casillas.setOpacity(255, ventana)
	#		elif pos_jugador_x == casillas.rect.x and pos_jugador_y+50 == casillas.rect.y: # Abajo
	#			casillas.setOpacity(255, ventana)
			
	#Metodo para mover a Link a una cordenada del tablero
	def muevete(self,nodo):
		self.rect.x = nodo.coordenada_x + (50-self.rect.width)/2 
		self.rect.y = nodo.coordenada_y + (50-self.rect.height)/2
			
##############################################################################
### Clase CualquierTerreno. 
##		
##		
##		
##############################################################################
class CualquierTerreno(pygame.sprite.Sprite):
	#AQUI SE CREA EL SPRITE TIERRA
	def __init__(self,posx,posy,tipo):
		
		pygame.sprite.Sprite.__init__(self)
		self.tipo = tipo
		self.posx = posx
		self.posy = posy
		self.updateImagen()
		
		self.rect = self.imagen.get_rect()
		#self.imagen.set_alpha(0)
		self.rect.top = posy
		self.rect.left = posx
	
	def updateImagen(self):
		contador = 0;
		self.imagen = pygame.image.load("img/pared.jpg")
		for cadaTipo in listaTipos:
			if cadaTipo == self.tipo:
				self.imagen = pygame.image.load("img/"+listaRutas[contador]+".jpg")
			contador += 1
				
	def dibujar(self,superficie):
		superficie.blit(self.imagen, self.rect)
		
	def setTipo(self, tipo):
		self.tipo = tipo
		
	#def setOpacity(self, alpha, ventana):
	#	self.imagen.set_alpha(alpha)
	#	self.dibujar(ventana)
