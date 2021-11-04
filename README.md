---
- Instrucciones de uso
---

1.- Descomprimir.
2.- Abrir un terminal en la capeta raiz del proyecto.
3.- Ejecutar comando "mvn spring-boot:run".

---

- Consideraciones

---

- El proyecto construye una base de datos al lanzar el proyecto y se almacena en ~/data/car-rental.

- Cada vez que se lanza el proyecto los datos iniciales se sobreescriben con la informacion de src/main/resources/data.sql

- Hay operaciones para crear coches, categorias, alquilar coches, calcular ciertas cosas, ...etc. pero por falta de tiempo no adjunto ejemplos de uso de todo, solo de las operacioens principales haciendo uso de los datos iniciales.

- Con mas tiempo lo primero que actualizaría sería añadir validaciones en las operaciones, como no poder alquilar un coche que ya ha sido alquilado por ejemplo.

- También me hubiera gustado añadir una pequeña app en react para probar de forma visual pero me ha sido imposible.

---

- Algunos ejemplos de uso

---

1.- Obtener los 100 primeros coches

    GET: http://localhost:8080/cars?page=0

2.- Obtener coche 4

    GET: http://localhost:8080/cars/4

4.- Calcular el precio del coche 1 por 10 dias

    GET: http://localhost:8080/cars/1/price?startDate=2021-11-01T00:00:00.000Z&endDate=2021-11-11T00:00:00.000Z

5.- Calcular el sobrecoste de 1 dia para el coche 3

    GET: http://localhost:8080/cars/3/extraCharge?endDate=2021-11-11T00:00:00.000Z&reciveDate=2021-11-12T00:00:00.000Z

6.- Obtener las 100 primeras categorias

    GET: http://localhost:8080/categories?page=0

7.- Alquilar coche 1 y coche 2 para el usuario1 durante un periodo de 10 dias

    POST: http://localhost:8080/rentcars
    BODY-JSON: [
                    {
                        "car": {
                            "carId": 1
                        },
                        "user": {
                            "userId": "user1"
                        },
                        "startDay": "2021-11-01T00:00:00.000Z",
                        "endDay": "2021-11-11T00:00:00.000Z"
                    },
                    {
                        "car": {
                            "carId": 2
                        },
                        "user": {
                            "userId": "user1"
                        },
                        "startDay": "2021-11-01T00:00:00.000Z",
                        "endDay": "2021-11-11T00:00:00.000Z"
                    }

                ]

8.- Obtener todos los primeros 100 coches alquilados

    GET: http://localhost:8080/rentcars?page=0

9.- Devolver el coche 2 con 1 dia de retraso

    PATCH: http://localhost:8080/rentcars/2/return?reciveDate=2021-11-13T00:00:00.000Z

10.- Devolver los 100 primeros usuarios

    GET: http://localhost:8080/users?page=0
