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
sudo cp *.txt /var/lib/mysql/Reporte_correos/
cd ..
# Cargar datos iniciales a la BD en tablas: correoRecibido y correoEnviado
mysql -u root -p < carga_datos.sql
# Elimina CVS del directorio MySQL
sudo rm /var/lib/mysql/Reporte_correos/correos_enviados.txt
sudo rm /var/lib/mysql/Reporte_correos/correos_recibidos.txt
# Inicia consola MYSQL
mysql -u root -p < buscar.sql
