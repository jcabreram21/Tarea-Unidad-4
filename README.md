# Sistema de Contenido Audiovisual - Unidad 4 (POO)

Aquí presento mi trabajo para la materia de Programación Orientada a Objetos, se mejoró el programa de contenidos audiovisuales (películas, series de TV, documentales, videos musicales y podcasts, junto con actores, temporadas e investigadores) para que no se quedara en algo muy básico y pudiera guardar, cargar y añadir datos, tener código más limpio y estar bien ordenado.

## Proceso de desarrollo

- **Guardar y cargar con archivos CSV:** Agregué el manejo de archivos CSV para poder guardar lo que está en el programa y volverlo a cargar después sin perder los datos, además de poner controles (`try-catch`) para que el programa no se caiga si hay algún error en la ruta, falta el archivo o vienen datos raros.
- **Nuevas opciones en la consola:** A las opciones que ya habían, le coloque una nueva opción para añadir contenido, guardar y cargar desde el menú de la consola, y agregué el método para armar la fila CSV en cada clase principal.
- **Limpieza y orden del código:** Revisé el proyecto para cambiar nombres poco claros por otros más específicos, también dividí métodos muy largos en partes más pequeñas, también busqué evitar repetir código y dejar comentarios que realmente ayuden.
- **Principios SOLID:** Acomodé el diseño de las clases para que cada parte cumpla bien su función, se puedan agregar cosas nuevas sin romper lo que ya funciona.
- **Separación en modelo, vista y controlador:** Organicé las clases en sus respectivos paquetes para tener separados los datos, lo que se muestra o se pide en consola y lo que coordina las acciones y el archivo CSV.
- **Pruebas con JUnit:** Creé la carpeta `test/` con pruebas unitarias para revisar que todo funcione bien tanto en casos normales como en casos límite y cuando ocurren errores.
- **Nuevo diagrama de clases:** Armé un diagrama nuevo desde cero para mostrar cómo quedó el programa ya terminado, con todas las clases, las pruebas, sus datos, métodos y cómo se relacionan entre sí.

## Qué hay en el repositorio

- El código del programa separado en modelo, vista y controlador.
- La carpeta `test/` con las pruebas unitarias.
- El archivo `contenidos.csv` para probar la carga y el guardado.
- El nuevo diagrama de clases (`new_diagrama actualizado.uxf`).
- Este `README.md` con las instrucciones.

## Qué necesitas para probarlo

- Tener **Java** y un entorno como **Eclipse IDE**.
- Tener **JUnit 5** agregado para correr las pruebas.
- Tener **Git** instalado si vas a clonar el repositorio.
- 
## Cómo clonar y ejecutar el proyecto

1. Clona el repositorio en tu computadora usando el comando `git clone` con el enlace del repositorio.
2. Abre **Eclipse**, entra a **File → Import → Existing Projects into Workspace** y selecciona la carpeta del proyecto que clonaste.
3. Revisa que el proyecto no tenga errores de compilación.
4. Una vez descargado y abierto el proyecto, busca y abre la clase `PruebaAudioVisual`, ya que es la que contiene el método main, y ejecútala para usar las opciones del menú en la consola (añadir, guardar o cargar contenidos desde `contenidos.csv`).
5. Si quieres correr las pruebas, entra a la carpeta `test/`, haz clic derecho en una clase de prueba y elige **Run As → JUnit Test**.
   
 
