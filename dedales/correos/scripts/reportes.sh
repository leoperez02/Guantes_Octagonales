#!/bin/bash
###############################################################################
# Archivo: reportes.sh
############################################################################### 
# Script para mover archivos de reportes
sudo rm reportes/relacionados.csv
sudo cp /tmp/relacionados.csv reportes/
sudo rm /tmp/relacionados.csv
