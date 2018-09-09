#!/bin/bash
###############################################################################
# Archivo: run_me.sh
#  copiar datos y cargar archivos txt en tablas
# Se ejecuta desde /home/leo/correos
# NOTA IMPORTANTE. DEBE EJECUTARSE COMO SUDO SU
############################################################################### 
# Script para crear BD
mysql -u root -p < ReporteCorreos.sql
# Copiar archivos CVS de correos enviados y recibidos al directorio de MySQL
cd files
sudo cp *.csv /var/lib/mysql/Reporte_correos/
cd ..
# Cargar datos iniciales a la BD en tablas: correoRecibido y correoEnviado
mysql -u root -p < carga_datos.sql
# Elimina CVS del directorio MySQL
sudo rm /var/lib/mysql/Reporte_correos/correos_enviados.csv
sudo rm /var/lib/mysql/Reporte_correos/correos_recibidos.csv
# java
java -cp :mysql-connector-java-5.1.15-bin.jar Editor ROOT
# Inicia consola MYSQL
mysql -u root -p < buscar.sql
