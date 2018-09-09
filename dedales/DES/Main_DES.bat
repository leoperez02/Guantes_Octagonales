::Compilación automatica de la clase principal
cd C:\Users\Leo\Progra\Java\DES\
javac -cp C:\Users\Leo\Progra\Java\DES\ Main_DES.java
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
echo El algoritmo de cifrado/descifrado DES recibe como entrada un n£mero a cifrar y una clave en formato hexadecimal. El programa devuelve el resultado del cifrado en binario y en decimal.
echo.
echo Presiona una tecla para iniciar el programa...
pause>nul
echo.
echo.
java -cp C:\Users\Leo\Progra\Java\DES Main_DES
echo.
echo Presiona una tecla para volver al men£.
pause>nul