--RUTAS ALIMENTADORAS
--En tabla 
SELECT e.nombre, ra.codigo, ra.nombre, tp.NOMBRE tipo_paradero, p.nombre PARADERO, h.rango DIA, TO_CHAR(h.HORA_COMIENZO, 'HH24:MI:SS') HORA_INICIO, TO_CHAR(h.HORA_FIN, 'HH24:MI:SS')HORA_FIN
FROM ESTACION e
INNER JOIN RUTA_ALIMEN ra ON ra.ID_ESTACION = e.ID_ESTACION
INNER JOIN PAR_RUT_ALIM pra ON pra.ID_RUT_ALIM = ra.ID_RUTA_ALIMEN
INNER JOIN PARADERO p ON p.ID_PARADERO = pra.ID_PARADERO
INNER JOIN TIPO_PARADERO tp ON tp.ID_TIPO_PARADERO = p.ID_TIPO_PARADERO
INNER JOIN PARA_HORA ph ON ph.ID_PARADERO = p.ID_PARADERO
INNER JOIN HORARIO h ON h.ID_HORARIO = ph.ID_HORARIO
WHERE e.ID_ESTACION = 16
AND ra.ID_RUTA_ALIMEN = 20
ORDER BY pra.ORDEN;

SELECT e.nombre, ra.codigo, ra.nombre, tp.NOMBRE tipo_paradero, p.nombre PARADERO, 
DECODE(h.rango, 'E', 'Lunes-Viernes', 'S', 'Sábado', 'Domingo') DIAS,
TO_CHAR(h.HORA_COMIENZO, 'HH24:MI:SS') HORA_INICIO, TO_CHAR(h.HORA_FIN, 'HH24:MI:SS')HORA_FIN
FROM ESTACION e
INNER JOIN RUTA_ALIMEN ra ON ra.ID_ESTACION = e.ID_ESTACION
INNER JOIN PAR_RUT_ALIM pra ON pra.ID_RUT_ALIM = ra.ID_RUTA_ALIMEN
INNER JOIN PARADERO p ON p.ID_PARADERO = pra.ID_PARADERO
INNER JOIN TIPO_PARADERO tp ON tp.ID_TIPO_PARADERO = p.ID_TIPO_PARADERO
INNER JOIN PARA_HORA ph ON ph.ID_PARADERO = p.ID_PARADERO
INNER JOIN HORARIO h ON h.ID_HORARIO = ph.ID_HORARIO
WHERE e.ID_ESTACION = 16
AND ra.ID_RUTA_ALIMEN = 20
ORDER BY pra.ORDEN;

--------------------------------------------
--RUTAS TRONCALES
--En archivo
SELECT o.NOMBRE OPERADOR, e.NOMBRE ESTACION, v.NOMBRE, r.NOMBRE, r.SENTIDO, h.RANGO, h.HORA_COMIENZO, h.HORA_FIN
FROM ESTACION e
INNER JOIN TIPO_ESTACION te ON te.ID_TIPO_ESTA = e.ID_TIPO_ESTA
INNER JOIN TRONCAL t ON t.ID_TRONCAL = e.ID_TRONCAL
INNER JOIN OPERADOR o ON o.ID_OPERADOR = t.ID_OPERADOR
INNER JOIN VAGON v ON v.ID_ESTACION = e.ID_ESTACION
INNER JOIN RUTA_ESTA re ON re.ID_VAGON = v.ID_VAGON
INNER JOIN RUTA r ON r.ID_RUTA = re.ID_RUTA
INNER JOIN RUTA_HORA rh ON rh.ID_RUTA = r.ID_RUTA
INNER JOIN HORARIO h ON h.ID_HORARIO = rh.ID_HORARIO
WHERE e.ID_ESTACION = 28 --26 28
AND r.SENTIDO = 'N'
ORDER BY v.ID_VAGON;

SELECT o.NOMBRE OPERADOR, e.NOMBRE ESTACION, v.NOMBRE, r.NOMBRE, 
DECODE(r.SENTIDO, 'N', 'Norte', 'S','Sur','W','Oeste','E','Este','NW','Noroeste','NE','Noreste','SW','Suroeste','Sureste') sentido, 
DECODE(h.rango, 'E', 'Lunes-Viernes', 'S', 'Sábado', 'Domingo') DIAS,
TO_CHAR(h.HORA_COMIENZO, 'HH24:MI:SS') HORA_INICIO, TO_CHAR(h.HORA_FIN, 'HH24:MI:SS')HORA_FIN
FROM ESTACION e
INNER JOIN TIPO_ESTACION te ON te.ID_TIPO_ESTA = e.ID_TIPO_ESTA
INNER JOIN TRONCAL t ON t.ID_TRONCAL = e.ID_TRONCAL
INNER JOIN OPERADOR o ON o.ID_OPERADOR = t.ID_OPERADOR
INNER JOIN VAGON v ON v.ID_ESTACION = e.ID_ESTACION
INNER JOIN RUTA_ESTA re ON re.ID_VAGON = v.ID_VAGON
INNER JOIN RUTA r ON r.ID_RUTA = re.ID_RUTA
INNER JOIN RUTA_HORA rh ON rh.ID_RUTA = r.ID_RUTA
INNER JOIN HORARIO h ON h.ID_HORARIO = rh.ID_HORARIO
WHERE e.ID_ESTACION = 28 --26 28
AND r.SENTIDO = 'N'
ORDER BY v.ID_VAGON;