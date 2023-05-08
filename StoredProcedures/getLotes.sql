-----------------------------------------------------------
-- Autor: Daniel Granados
-- Fecha: 08/05/2023
-- Descripcion: Este Stored procedure obtiene los canales
-----------------------------------------------------------
DROP PROCEDURE IF EXISTS  [dbo].[SP_getLotes];
GO

CREATE PROCEDURE [dbo].[SP_getLotes]
AS 
BEGIN
	SET NOCOUNT ON -- no retorne metadatos
	SELECT lpl.loteId, lpl.productoId, lpl.prodContratoId, lpl.plantaId, lpl.cantidad - SUM(itemsProductos.cantidadProductos), lpl.costoProduccion, lpl.monedaId  FROM lotesProduccionLogs lpl
	INNER JOIN preciosProductosContrato ON lpl.prodContratoId = preciosProductosContrato.prodContratoId
	INNER JOIN productos ON lpl.productoId = productos.productoId
	LEFT JOIN itemsProductos ON itemsProductos.loteId = lpl.loteId
	GROUP BY itemsProductos.cantidadProductos, lpl.loteId, lpl.productoId, lpl.cantidad, lpl.prodContratoId, lpl.plantaId, lpl.costoProduccion, lpl.monedaId

END
RETURN 0
GO

WITH productosVendidos (loteId, cantidadVendida) AS 
(	
	SELECT lpl.loteId, COALESCE(sum(cantidadProductos), 0) cantidadVendida FROM lotesProduccionLogs lpl LEFT JOIN itemsPRoductos on lpl.loteId = itemsProductos.loteID
	GROUP BY itemsProductos.loteId, lpl.loteId
)
SELECT lpl.loteId, lpl.fecha, lpl.productoId, lpl.prodContratoId, lpl.plantaId, lpl.cantidad, lpl.cantidad - productosVendidos.cantidadVendida, lpl.costoProduccion, lpl.monedaId
FROM lotesProduccionLogs lpl
INNER JOIN productosVendidos ON productosVendidos.loteId = lpl.loteId
INNER JOIN productos ON lpl.productoId = productos.productoId
INNER JOIN preciosProductosContrato ON lpl.prodContratoId = preciosProductosContrato.prodContratoId
ORDER BY lpl.loteId

SELECT lpl.loteId, COALESCE(sum(cantidadProductos), 0) FROM lotesProduccionLogs lpl LEFT JOIN itemsPRoductos on lpl.loteId = itemsProductos.loteID
GROUP BY itemsProductos.loteId, lpl.loteId
ORDER BY lpl.loteID

SELECT loteId, sum(cantidadProductos) FROM itemsProductos GROUP BY loteId
ORDER BY loteID

SELECT * FROM preciosProductosContrato ORDER BY prodContratoId, productoId

Go

INSERT INTO [dbo].[lotesProduccionLogs]
           ([productoId]
           ,[plantaId]
           ,[cantidad]
           ,[prodContratoId]
           ,[costoProduccion]
           ,[monedaId]
           ,[fecha]
           ,[enabled]
           ,[createdAt]
           ,[computer]
           ,[username]
           ,[checksum])
     VALUES
           ((SELECT TOP 1 productoId FROM productos ORDER BY NEWID())
           ,(SELECT TOP 1 plantaId FROM plantas ORDER BY NEWID())
           ,FLOOR(1 + RAND() * 10000)
           ,(SELECT TOP 1 prodContratoId FROM contratosProduccion ORDER BY NEWID())
           ,FLOOR(1 + RAND() * 10000)
           ,(SELECT TOP 1 monedaId FROM monedas ORDER BY NEWID())
           ,DATEADD(minute, FLOOR(1 + RAND()*518400), '2022-01-01 00:00:00')
           ,1
           ,GETDATE()
		   ,'computer'
		   ,'user1'
		   ,1234)
GO


IF OBJECT_ID(N'tempdb..#objectTypeQuantities') IS NOT NULL
BEGIN
DROP TABLE #objectTypeQuantities
END
GO

CREATE TABLE #objectTypeQuantities (
	objectTypeId TINYINT IDENTITY(1,1) NOT NULL,
	cantidad INT NULL
);

INSERT INTO #objectTypeQuantities (cantidad) VALUES
((SELECT COUNT(direccionId) FROM direcciones)),
((SELECT COUNT(ciudadId) FROM ciudades)),
((SELECT COUNT(estadoId) FROM estados)),
((SELECT COUNT(paisId) FROM paises)),
((SELECT COUNT(regionId) FROM regiones)),
((SELECT COUNT(localId) FROM locales)),
((SELECT COUNT(productorId) FROM productores)),
((SELECT COUNT(recolectorId) FROM recolectores)),
((SELECT COUNT(participanteId) FROM participantes)),
((SELECT COUNT(recipienteId) FROM recipientes)),
((SELECT COUNT(productoId) FROM productos)),
((SELECT COUNT(loteId) FROM lotesProduccionLogs))

DECLARE @computer VARCHAR(20), @username VARCHAR(20), @checksum VARBINARY(150);

DECLARE @contador int;

SET @computer = 'computer1'
SET @username = 'user1'
SET @checksum = 1234

DECLARE @max INT, @objectTypeId TINYINT, @geographicObjects TINYINT, @minObjects TINYINT, @maxObjects TINYINT;

SET @geographicObjects = 5
SET @minObjects = 7
SET @maxObjects = 10

DECLARE @productosContador INT, @productosMax INT;
		   
SET @contador = 1
SET @max = (SELECT COUNT(*) FROM itemsProductos)
SET @productosMax = (SELECT COUNT(*) from productos)

WHILE @contador <= @max
BEGIN

SET @objectTypeId = FLOOR(1 + RAND()*@geographicObjects)
SET @productosContador = 1
WHILE @productosContador <= @productosMax
BEGIN
INSERT INTO [dbo].[preciosProductosContrato]
           ([prodContratoId]
           ,[productoId]
           ,[precio]
           ,[areaEfectoId]
           ,[objectTypeId]
           ,[fechaInicio]
           ,[fechaFin]
           ,[monedaId]
           ,[enabled]
           ,[createdAt]
           ,[computer]
           ,[username]
           ,[checksum])
     VALUES
           (@contador
           ,@productosContador
           ,FLOOR(1 + RAND() * 150)
           ,FLOOR(1 + RAND() * (SELECT cantidad FROM #objectTypeQuantities WHERE objectTypeId = @objectTypeId))
           ,@objectTypeId
           ,DATEADD(minute, FLOOR(1 + RAND()*518400), '2022-01-01 00:00:00')
           ,DATEADD(minute, FLOOR(1 + RAND()*518400), '2022-01-01 00:00:00')
           ,(SELECT TOP 1 monedaId FROM monedas ORDER BY NEWID())
           ,1
           ,GETDATE()
		   ,@computer
		   ,@username
		   ,@checksum)
SET @productosContador = @productosContador + 1
END

SET @contador = @contador + 1

END