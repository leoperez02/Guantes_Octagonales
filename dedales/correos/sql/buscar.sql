USE Reporte_correos;
/*
-------------------------------------------------------------------------------
-- Enviados = 749 
*/
SELECT idEnviado FROM correoEnviado;
/* 
-- Enviados con folio en cualquier parte del asunto = 539
*/
SELECT idEnviado,asunto FROM correoEnviado WHERE asunto regexp '^.*[1234567890]{6,6}.*$';  
/*
-- Enviados restantes (sin folio) = 210
*/
SELECT idEnviado,asunto 
FROM correoEnviado 
WHERE idEnviado NOT IN(
	SELECT idEnviado FROM correoEnviado	WHERE asunto regexp '^.*[1234567890]{6,6}.*$'
); 

	/*
	-- Enviados con folio al principio del asunto (candidatos a relacionar) = 469
	*/
	SELECT idEnviado,SUBSTRING(asunto,1,6) Folio FROM correoEnviado WHERE asunto regexp '^[1234567890]{6,6}.*$';

	/*
	-- Enviados con folio no relacionables = 70
	*/
	SELECT idEnviado,asunto FROM(
		SELECT idEnviado,asunto FROM correoEnviado WHERE asunto regexp '^.*[1234567890]{6,6}.*$'
	) AS tb1
	WHERE idEnviado NOT IN(
		SELECT idEnviado FROM correoEnviado WHERE asunto regexp '^[1234567890]{6,6}.*$'
	);
	
/*	
-------------------------------------------------------------------------------
-- Recibidos = 901
*/  
SELECT idRecibido FROM correoRecibido;

/*
-- Recibidos con folio en cualquier parte del asunto = 675
*/
SELECT idRecibido,asunto FROM correoRecibido WHERE asunto regexp '^.*[1234567890]{6,6}.*$';  

/*
-- Recibidos restantes (sin folio) = 226
*/
SELECT idRecibido,asunto FROM correoRecibido WHERE idRecibido NOT IN(
	SELECT idRecibido FROM correoRecibido WHERE asunto regexp '^.*[1234567890]{6,6}.*$'
); 

	/*
	-- Recibidos con folio al principio del asunto (candidatos a relacionar) = 545
	*/
	SELECT idRecibido,SUBSTRING(asunto,1,6) Folio FROM correoRecibido WHERE asunto regexp '^[1234567890]{6,6}.*$';

	/*
	-- Recibidos con folio no relacionables = 130
	*/
	SELECT idRecibido,asunto FROM(
		SELECT idRecibido,asunto FROM correoRecibido WHERE asunto regexp '^.*[1234567890]{6,6}.*$'
	) AS tb1
	WHERE idRecibido NOT IN(
		SELECT idRecibido FROM correoRecibido WHERE asunto regexp '^[1234567890]{6,6}.*$'
	);
 
/* 
-------------------------------------------------------------------------------
-- Relacionados con duplicados = 517
*/
SELECT DISTINCT enviados.idEnviado,enviados.fecha Fecha_Enviado,enviados.Folio,recibidos.idRecibido,recibidos.fecha Fecha_Recibido,recibidos.Folio 
FROM( 
	SELECT idEnviado,destinatario,fecha,asunto,SUBSTRING(asunto,1,6) Folio 
	FROM correoEnviado 
	WHERE asunto regexp '^[1234567890]{6,6}.*$' 
) AS enviados 
JOIN(  
	SELECT idRecibido,remitente,fecha,asunto,SUBSTRING(asunto,1,6) Folio 
	FROM correoRecibido 
	WHERE asunto regexp '^[1234567890]{6,6}.*$' 
) AS recibidos 
ON enviados.Folio=recibidos.Folio;

/*
-- Relacionados sin duplicados en Enviados = 395
*/
SELECT COUNT(relaciones.idEnviado), relaciones.idEnviado FROM
(SELECT enviados.idEnviado,enviados.fecha fecha1,recibidos.idRecibido,recibidos.fecha FROM( SELECT idEnviado,destinatario,fecha,asunto,SUBSTRING(asunto,1,6) Folio FROM correoEnviado WHERE asunto regexp '^[1234567890]{6,6}.*$' ) AS enviados JOIN(  SELECT idRecibido,remitente,fecha,asunto,SUBSTRING(asunto,1,6) Folio FROM correoRecibido WHERE asunto regexp '^[1234567890]{6,6}.*$' ) AS recibidos ON enviados.Folio=recibidos.Folio) AS relaciones GROUP BY relaciones.idEnviado;

/*
--------------------------------------------------------------------------------
-- REPORTE = 395 relacionados
*/
SELECT DISTINCT enviados.idEnviado,enviados.destinatario,enviados.asunto,enviados.Folio,enviados.fecha,recibidos.idRecibido,recibidos.remitente,recibidos.asunto,recibidos.Folio,recibidos.fecha,DATEDIFF(enviados.fecha,recibidos.fecha) Dias
FROM( 
	SELECT idEnviado,destinatario,fecha,asunto,SUBSTRING(asunto,1,6) Folio 
	FROM correoEnviado 
	WHERE asunto regexp '^[1234567890]{6,6}.*$' 
) AS enviados 
JOIN(  
	SELECT idRecibido,remitente,fecha,asunto,SUBSTRING(asunto,1,6) Folio 
	FROM correoRecibido 
	WHERE asunto regexp '^[1234567890]{6,6}.*$' 
) AS recibidos 
ON enviados.Folio=recibidos.Folio

INTO OUTFILE '/tmp/relacionados.csv'
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

/*
-- Correr script reportes.sh
*/
\! ./reportes.sh
\! sudo cp reportes/relacionados.csv /var/lib/mysql/Reporte_correos/

LOAD DATA INFILE 'relacionados.csv'
INTO TABLE relacion 
FIELDS TERMINATED BY ',' (idEnviado,destinatario,asuntoEnvio,folioEnvio,fechaEnvio,idRecibido,remitente,asuntoRecibo,folioRecibo,fechaRecibo,tiempoRespuesta);

ALTER IGNORE TABLE relacion ADD UNIQUE INDEX(idEnviado);
ALTER TABLE relacion ADD UNIQUE INDEX(idEnviado);
ALTER TABLE relacion CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

SELECT * FROM relacion INTO OUTFILE '/tmp/relacionados.csv' FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';

/*
-- Correr script reportes.sh
*/
\! ./reportes.sh
\! sudo rm /var/lib/mysql/Reporte_correos/relacionados.csv
/*
-- Enviados NO relacionados = 354
*/
SELECT idEnviado,destinatario,asunto,fecha FROM correoEnviado WHERE idEnviado NOT IN(
	SELECT idEnviado FROM relacion);
	
	/*
	-- Enviados no relacionados con folio = 144
	*/	
	SELECT idEnviado,destinatario,asunto,fecha FROM (SELECT idEnviado,destinatario,asunto,fecha FROM correoEnviado WHERE idEnviado NOT IN (SELECT idEnviado FROM relacion)) as tb1 WHERE asunto regexp '^.*[1234567890]{6,6}.*$'
INTO OUTFILE '/tmp/enviados_con_folio_sin_relacionar.csv'
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

	
	/*
	-- Enviados no relacionados sin folio = 210
	*/	
	SELECT idEnviado,destinatario,asunto,fecha FROM (SELECT idEnviado,destinatario,asunto,fecha FROM correoEnviado WHERE idEnviado NOT IN( SELECT idEnviado FROM relacion)) AS TB1 WHERE idEnviado NOT IN(SELECT idEnviado FROM (SELECT idEnviado,destinatario,asunto,fecha FROM correoEnviado WHERE idEnviado NOT IN (SELECT idEnviado FROM relacion)) as tb1 WHERE asunto regexp '^.*[1234567890]{6,6}.*$')
INTO OUTFILE '/tmp/enviados_sin_folio_sin_relacionar.csv'
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

\! sudo cp /tmp/enviados_sin_folio_sin_relacionar.csv reportes/
\! sudo cp /tmp/enviados_con_folio_sin_relacionar.csv reportes/
\! sudo rm /tmp/enviados_con_folio_sin_relacionar.csv
\! sudo rm /tmp/enviados_con_folio_sin_relacionar.csv



/*
-- Recibidos NO relacionados = 562
*/
SELECT idRecibido,remitente,asunto,fecha FROM correoRecibido WHERE idRecibido NOT IN(
	SELECT idRecibido FROM relacion);

/*
	-- Recibidos no relacionados con folio = 336
	*/	
	SELECT idRecibido,remitente,asunto,fecha FROM (SELECT idRecibido,remitente,asunto,fecha FROM correoRecibido WHERE idRecibido NOT IN (SELECT idRecibido FROM relacion)) as tb1 WHERE asunto regexp '^.*[1234567890]{6,6}.*$'
INTO OUTFILE '/tmp/recibidos_con_folio_sin_relacionar.csv'
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

	
	/*
	-- Recibidos no relacionados sin folio = 226
	*/	
	SELECT idRecibido,remitente,asunto,fecha FROM (SELECT idRecibido,remitente,asunto,fecha FROM correoRecibido WHERE idRecibido NOT IN( SELECT idRecibido FROM relacion)) AS TB1 WHERE idRecibido NOT IN(SELECT idRecibido FROM (SELECT idRecibido,remitente,asunto,fecha FROM correoRecibido WHERE idRecibido NOT IN (SELECT idRecibido FROM relacion)) as tb1 WHERE asunto regexp '^.*[1234567890]{6,6}.*$')
INTO OUTFILE '/tmp/recibidos_sin_folio_sin_relacionar.csv'
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n';

\! sudo cp /tmp/recibidos_sin_folio_sin_relacionar.csv reportes/
\! sudo cp /tmp/recibidos_con_folio_sin_relacionar.csv reportes/
\! sudo rm /tmp/recibidos_con_folio_sin_relacionar.csv
\! sudo rm /tmp/recibidos_con_folio_sin_relacionar.csv

