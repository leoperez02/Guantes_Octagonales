import zelda as z
import Nodo as nd
import auxiliares as aux
import time,pygame,sys,threading,logging
from pygame.locals import *
	
	
#Configuracion de logs para monitorear los hilos	
logging.basicConfig( level=logging.DEBUG,
    format='[%(levelname)s] - %(threadName)-10s : %(message)s')
    

# Variables globales
player_list = []    
listaGeneral = None  
  
    
# Funcion del hilo2
# Aqui se lee el archivo GUI.socket para mover a todos los jugadores
def daemon():
	
	time.sleep(1)
	#INICIO DEL JUEGO
	pygame.init()
	flag = True
	# Esperar hasta que se presione la tecla enter
	while flag:
		for evento in pygame.event.get():
			if evento.type == pygame.KEYDOWN:
				if evento.key == K_RETURN:
					flag = False
					#Escribir el comando STAR en el archico cliente.socket
					archi = open("cliente.socket","w")
					archi.write("STAR\n")
					archi.close()
	
	while True:
		archivo = open("GUI.socket", "r")
		logging.debug('Lanzado')	
		for player in player_list:
			comando = archivo.readline()
			set_mov(player,comando)
		
		pygame.display.update()	
		archivo.close()
		time.sleep(0.100)
		#logging.debug('Sleep')    

# set_mov()
# metodo para mover en la GUI cada jugador segun los comandos del archivo
def set_mov(jugador,comando):
	if comando == "":
		#print "sin comando"
		a= "sin comando"
	else:
		#print comando,
		if comando == "ARRI\n": # arriba		
			jugador.rect.top -= jugador.velocidad
			#EVENTO SI CHOCA CON PARED
			for pared in listaGeneral:
				if jugador.rect.colliderect(pared.rect) and pared.tipo == "0":
					jugador.rect.top += jugador.velocidad
		elif comando == "ABAJ\n": # abajo
			jugador.rect.bottom += jugador.velocidad
			#EVENTO SI CHOCA CON PARED
			for pared in listaGeneral:
				if jugador.rect.colliderect(pared.rect) and pared.tipo == "0":
					jugador.rect.bottom -= jugador.velocidad
		elif comando == "DERE\n":
			jugador.rect.right += jugador.velocidad
			#EVENTO SI CHOCA CON PARED
			for pared in listaGeneral:
				if jugador.rect.colliderect(pared.rect) and pared.tipo == "0":
					jugador.rect.right -= jugador.velocidad
		elif comando == "IZQU\n":
			jugador.rect.left -= jugador.velocidad
			#EVENTO SI CHOCA CON PARED
			for pared in listaGeneral:
				if jugador.rect.colliderect(pared.rect) and pared.tipo == "0":
					jugador.rect.left += jugador.velocidad
		elif comando == "WAIT\n":
			a = "esperando"

# Funcion del hilo1
# Los eventos capturados por la GUI son escritos en cliente.socket
def daemon_lab(mapa_file,cadena_origenes):
	
	# Convertir cadena de origenes en arreglo de nodos origen 
	origenes = cadena_origenes.split("-")
	for i in range(len(origenes)):
		origenes[i] = nd.Nodo(origenes[i][0],origenes[i][1])
	

	lg,ancho,alto = aux.cargarMapa(mapa_file)
	global listaGeneral
	listaGeneral = lg

	ventana = pygame.display.set_mode((ancho,alto))
	pygame.display.set_caption("Snake online (alfa v0)")
	
	
	# Colocar jugadores en la pantalla 
	for origen in origenes:
		global player_list
		player_list.append(z.Zelda(origen))

	
	#AQUI COMIENZA EL CICLO DEL JUEGO
	while True:
		
		time.sleep(0.100)
		
		# Validacion de jugadores dentro de limites
		for player in player_list:
			player.movimiento(ancho,alto)
		
		
		#OBTENER TIEMPO PARA LA ANIMACION
		tiempo = pygame.time.get_ticks()/800
		

		#CARGA TODO EL LABERINTO
		if len(listaGeneral)>0:
			for ambiente in listaGeneral:
				ambiente.dibujar(ventana)


		#CARGAMOS LA ANIMACION DE LOS JUGADORES
		for player in player_list:
			player.animacion(tiempo)
		
		
		#DIBUJA A LOS JUGADORES
		for player in player_list:
			player.dibujar(ventana)

		#EVENTOS	
		for evento in pygame.event.get():
			#CERRAR VENTANA
			if evento.type == QUIT:
				aux.salvarMapa(listaGeneral)
				pygame.quit()
				#print "FINISH"
				sys.exit()
			
			# ---------- CUT-HERE -------------------------------------------
			if evento.type == pygame.KEYDOWN:
				#TECLA IZQUIERDA
				if evento.key == K_LEFT:
					write_comando("IZQU")
					#EVENTO SI CHOCA CON PARED
					#for pared in listaGeneral:
					#	if jugador.rect.colliderect(pared.rect) and pared.tipo == "0":
					#		jugador.rect.left += jugador.velocidad
				#TECLA DERECHA
				elif evento.key == K_RIGHT:
					write_comando("DERE")
					#EVENTO SI CHOCA CON PARED
					#for pared in listaGeneral:
					#	if jugador.rect.colliderect(pared.rect) and pared.tipo == "0":
					#		jugador.rect.right -= jugador.velocidad
				#TECLA ARRIBA
				elif evento.key == K_UP:
					write_comando("ARRI")
					#EVENTO SI CHOCA CON PARED
					#for pared in listaGeneral:
					#	if jugador.rect.colliderect(pared.rect) and pared.tipo == "0":
					#		jugador.rect.top += jugador.velocidad
				#TECLA ABAJO
				elif evento.key == K_DOWN:
					write_comando("ABAJ")
					#EVENTO SI CHOCA CON PARED
					#for pared in listaGeneral:
					#	if jugador.rect.colliderect(pared.rect) and pared.tipo == "0":
					#		jugador.rect.bottom -= jugador.velocidad
			
		pygame.display.update()
		
		
# write_comando()
# funcion para esribir en el archivo cliente.socket los comandos correspondientes
# a los eventos
def write_comando(comand):
	#print "Comando: ",comand
	archivo = open("cliente.socket","w")
	archivo.write(comand+'\n')
	archivo.close()
		
##############################################################################
#
#	Snake online multijugador v1.0
#
#	MAIN
#
##############################################################################
#
#	1. Este script es ejecutado por el cliente Java y recibe como parametro la
#		la ruta del archivo del laberinto del juego y la posicion inicial de todos
#		los jugadores
#		$python snake_ol <path_to_file> p1-p2-p3-p4 ... -pn
#		EJEMPLO: python snake_ol maps/lab01.map 3B-4C-5G
#
#	2. El script muestra la GUI del usuario con el mapa leido y su avatar,
#		si hubiera mas clientes conectados en ese momentos, se veran sus avatares
#		en el mapa y se espera hasta que algun jugador presione la tecla ENTER
#
#	3. Cuando un jugador presiona la tecla ENTER, se escribe el comando STAR
#		en el archivo cliente.socket
#
#	4. El cliente Java lee el archivo y lo envia al servidor. El servidor recibe
#		el comando y envia el archivo GUI.socket a todos los clientes
#
#	5. El archivo GUI.socket determina las acciones de todos los jugadores
#
##############################################################################
mapa = sys.argv[1]
cadena_origenes = sys.argv[2]

##inicializar cliente.socket
archi = open("cliente.socket","w")
archi.write("NULL\n")
archi.close()

##inicializar GUI.socket
archi = open("GUI.socket","w")
archi.write("NULL\nNULL")
archi.close()



# Hilo para cargar la GUI del juego
hilo1 = threading.Thread(target=daemon_lab,name='Daemon_lab',args=(mapa,cadena_origenes))
hilo1.start()

# Hilo para leer el archivo del socket del servidor Java
hilo2 = threading.Thread(target=daemon, name='Daemon')
hilo2.start()
