Nombre: LUIS RODRIGO ROJO MORALES
No. Cuenta: 311177903
Correo: rodrigorojo@ciencias.unam.mx

Para correr el proyecto:
$ make

También se adjuntan imagenes muestra (sin usar blending).
Las fórmulas usadas son:
- Lineal
- Euclidiana
- Riemersma
- Restas


Para hacer un Fotomosaico hay que hacer lo siguiente:
1.- Compilar y correr el proyecto con:
      $ make
2.- Archivo > Cargar Imágenes
      Seleccionar la carpeta donde se encuentra la colección imágenes (Es un proceso tardado).
      Se generará un archivo llamado "promedios.txt" el cual contiene información de la forma:
          ruta --> r g b
          Donde 'ruta' es donde se encuentra la imágen, 'r' es el valor promedio del rojo, 'g' del verde y 'b' del azul.
3.- Fotomosaico > Cambiar tamaño del cuadro
      Esto es opcional, el tamaño por default es de 50x50.
4.- Fotomosaico > Elegir una opción
      Elegir una fórmula con repetición o sin repetición.
5.- Blending > aplicar
      Se puede aplicar blending si se requiere.
