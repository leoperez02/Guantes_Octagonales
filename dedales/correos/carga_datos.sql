##Script para cargar los datos de los catálogos desde archivos txt
USE Reporte_correos;

LOAD DATA INFILE 'correos_recibidos.csv'
INTO TABLE correoRecibido 
FIELDS TERMINATED BY ',' (remitente,asunto,fecha);

LOAD DATA INFILE 'correos_enviados.csv'
INTO TABLE correoEnviado 
FIELDS TERMINATED BY ',' (destinatario,asunto,fecha);
