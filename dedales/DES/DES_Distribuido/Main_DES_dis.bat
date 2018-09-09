::Compilación automatica de las clases
cd C:\Users\Leo\Progra\Java\DES\DES_Distribuido
javac -cp C:\Users\Leo\Progra\Java\DES\DES_Distribuido *.java
::Compilado
cls
@echo off
color 30
title Algoritmo DES (Data Encryption Standar) - Corporaci¢n Pandicornio 
echo ================================================================================================
echo =											       =
echo =   Algoritmo de cifrado/descifrado DES programado en lenguaje Java por el Se¤or Osito.        =
echo =											       =
echo =  Propiedad de sus respectivos due¤os, usado bajo licencia por la Corporaci¢n Pandicornio.    =
echo =											       =
echo ================================================================================================
echo.
echo Este programa lleva a cabo el proceso de cifrado o descifrado de manera distribuida. 
echo.
echo Para ejecutar el programa se requiere de dos o mas computadoras conectadas en una red.
echo.
echo La primer computadora debe ejecutar el programa Emisor_DES, el programa recibe como argumentos separados por espacios:
echo.
echo 	+ La IP de la segunda computadora.
echo 	+ Un puerto disponible de la segunda computadora.
echo 	+ La clave.
echo 	+ El mensaje.
echo.
echo.
echo La segunda computadora debe ejecutar el programa Puente_DES, el programa recibe como argumentos separados por espacios:
echo.
echo 	+ La IP de la tercera computadora.
echo 	+ Un puerto disponible de la tercera computadora.
echo.
echo.
echo La tercer computadora debe ejecutar el programa Receptor_DES, el programa recibe como argumentos:
echo.
echo 	+ Un puerto disponible de la tercera computadora.
echo.
echo Aqu¡ se muestra el resultado final.
echo NOTA: los tres programas pueden ejecutarse en la misma computadora, en tres ventanas de consolas de comandos. 
echo.
echo El algoritmo de cifrado/descifrado DES recibe como entrada un n£mero a cifrar y una clave en formato hexadecimal. El programa devuelve el resultado del cifrado en binario y en decimal.
echo.
echo Presiona una tecla para iniciar el programa...
pause>nul