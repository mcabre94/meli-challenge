# meli-challenge

## Datos de la AP

La api calcula en base a una lista de items de mercadolibre y un monto máximo del cupon, la mejor selección de productos de la lista que maximice el uso del cupón, gastando la mayor cantidad de dinero posible sin exceder el monto del cupón.

 - URL de la API : http://challengemeli-env-1.eba-xmmyx3wq.us-east-2.elasticbeanstalk.com

## Uso de la API

La api expone un endpoint para su uso:
 
  - POST -> /coupon
    - Request 
      -  Parámetros del header
          - Content-Type : application/json
      - Datos del body
        - item_ids : array con los ids de los posibles productos a seleccionar
        - amount : monto del cupón
    - Response
      - Body de response
        - items : array con los ids de los productos seleccionados por la api para usar
        - total : monto máximo resultado de la api (suma de los montos de los productos seleccionados)
        
        
## Ejemplo de uso

El siguiente ejemplo está realizado en el software "POSTMAN".
Se ejecuta una consulta "POST" a la ruta "/coupon"
Le enviamos 3 items de mercadolibre y un monto máximo de 1289.1
Como resultado nos devuelve que debemos seleccionar dos de los tres items y que el monto final gastado es de 1289.0

![alt text](https://i.ibb.co/f16Pj67/meli1.jpg)
![alt text](https://i.ibb.co/yYKHYTg/meli2.jpg)
