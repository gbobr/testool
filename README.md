testool
=======

Herramienta de testing para la materia Analisis de Software de la Universidad Nacional de La Matanza - Año 2014

Requisitos:
=======

* Apache Maven 2.x
* Todas las demás dependencias se bajan automáticamente por maven:
** Apache Velocity 1.7 
** Spring Context 4.1.1-RELEASE
** javaparser 1.0.8 (com.google.code.javaparser)
** slf4j-log4j12 1.7.7
** junit 3.8.1

Eclipse
=======

Para configurar el proyecto en eclipse hay que convertirlo a proyecto maven (Botón derecho sobre el proyecto -> Configure -> Convert to maven project.

Ejecución
=======

Se le debe pasar como único parámetro de ejecución la ruta a la carpeta del proyecto a analizar. El programa recorre recursivamente la carpeta en busca de clases java.

Configuración
=======

Esta pendiente la configuración desde un .properties y la posibilidad de especificar un directorio de salida. Por default la salida se hace en un directorio target/testtool relativo al path desde donde se corre el programa.
