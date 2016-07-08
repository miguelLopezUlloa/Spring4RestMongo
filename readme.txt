
Mi mismo. 

Este ejemplo que tengo es el inicio de un mini proyecto para acceder a MondoDB a travéz de Java.

SPRING4RestMongo fue creado para Usar Spring 4 y servicios REST

Recuerda que para que este ejemplo jale debes levantar el Tomcat(ya pusiste el war dentro de Work), 
si haces algun cambio al codigo entonces debes darle rebuild con

mvn update 

En la carpeta target esta el war construido por Maven y lo copias de nuevo a Tomcat

Y recuerda que tienes un test que se encarga de emular la conexion con el servicio Rest

Y ya lo puedes debuggear si lo necesitas.

Ya con esto jala chido.


Levantar Tomcat

En ../bin   
./startup.sh

En Postman ya puedes.

http://localhost:8080/Spring4RestMongo/products/



