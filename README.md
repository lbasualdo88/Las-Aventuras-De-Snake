# Las-Aventuras-De-Snake

## Documentación

- [Trabajo Práctico - Programación Básica 2025](./Trabajo%20Práctico%20-%20Programación%20Básica%202025.pdf)

Trabajo Práctico 1 - Programación Básica 2025

Análisis y Diseño del Sistema

Materia: Programación BásicaLenguaje: JavaContenidos válidos: Introducción a POO, Herencia e InterfacesContenidos excluidos: Colecciones (no usar ArrayList, HashMap, etc.)Fecha de entrega: 12 de junio de 2025

1. Objetivo del Trabajo

Desarrollar una simulación de un juego estilo "Metal Gear" con estructura basada en Programación Orientada a Objetos. El jugador, Snake, debe completar 3 misiones enfrentando enemigos, recogiendo ítems y evitando ser capturado.

2. Requisitos Funcionales

Implementación de clases utilizando herencia, abstracción e interfaces.

Mapa representado por una matriz NxM de celdas.

Sistema de misiones progresivo con objetivos.

Movimiento de enemigos aleatorio (sin diagonales).

Detección de enemigos a 1 celda: Snake es capturado.

Sistema de guardado y carga mediante código de progreso.

Menú principal con opciones: Iniciar misión, Guardar, Cargar.

3. Flujo General del Juego

Mostrar menú principal:

Iniciar nueva partida

Cargar partida existente

Ejecutar misión correspondiente según progreso

Validar éxito o fallo de la misión

Permitir guardar el estado actual

Al finalizar todas las misiones, finalizar el juego

4. Clases y Relaciones

Clase abstracta: Personaje

nombre: String

posicion: Posicion

vida: int

Métodos:

mover(String direccion)

interactuar() (abstracto)

Clase concreta: Snake (hereda de Personaje)

tieneTarjeta: boolean

tieneExplosivo: boolean

Métodos:

agarrarItem(Item item)

usarItem()

atacar(Personaje enemigo)

Interfaz: Enemigo

Métodos:

patrullar()

detectarSnake(Posicion posSnake)

atacar()

Clases concretas:

Guardia (hereda de Personaje, implementa Enemigo)

MetalGear (hereda de Personaje, implementa Enemigo)

Clase abstracta: Mision

descripcion: String

mapa: Mapa

completada: boolean

Métodos:

iniciar()

verificarObjetivo()

Clases concretas:

MisionIntermedia (misión 1 y 2)

MisionFinal (misión 3 sin mapa)

Clase: Mapa

filas: int

columnas: int

celdas: Celda[][]

Métodos:

mostrar()

colocarElemento()

validarMovimiento(Posicion nuevaPos)

Clase: Celda

posicion: Posicion

contenido: String ("Snake", "Guardia", "Item", "Vacio")

Métodos:

esOcupada()

agregarContenido(String)

vaciarContenido()

Clase: Posicion

x: int

y: int

Métodos:

esAdyacente(Posicion otra)

Clase: Item

tipo: String ("Tarjeta", "C4")

posicion: Posicion

Métodos:

usar()

Clase: Juego

misionesCompletadas: int

estadoGuardado: String

Métodos:

mostrarMenu()

iniciarJuego()

guardarEstado()

cargarEstado(String codigo)

5. Reglas del Juego

Movimiento sólo en eje vertical y horizontal.

Enemigos no pueden estar a menos de 2 celdas de Snake al comenzar.

Si un enemigo está a 1 celda de Snake, lo captura.

Snake gana al completar los objetivos de cada misión.

6. Tareas Técnicas



7. Restricciones

No se puede usar ArrayList, HashMap ni ninguna clase de colecciones.

Todos los datos deben gestionarse con arreglos simples y estructuras propias.
