## Las Aventuras de Snake 

Trabajo Práctico - PBA  
Tecnicatura Universitaria en Tecnologías de la Información – UTN

---

## Descripción

Este proyecto es una simulación por consola inspirada en el personaje *Snake*, donde el jugador debe completar 3 misiones en un mapa tipo matriz. El juego aplica principios de Programación Orientada a Objetos (POO), incluyendo herencia, interfaces, encapsulamiento y polimorfismo.

---

## Funcionalidades Implementadas

✅ Menú principal interactivo  
✅ Movimiento de Snake a través de las letras A/W/S/D  
✅ Enemigos generados aleatoriamente en el mapa  
✅ Representación del mapa por consola  
✅ Estructura base de las misiones  
✅ Sistema de detección de proximidad y ocupación de celdas
✅ Se incorpora batalla final contra Metal Gear
✅ Se refactoriza la clase MisionIntermedia para mejorar instanciaciòn y validaciòn de posiciones  
✅ Se aplican ajustes en la herencia y comportamiento de personajes enemigos
---

## Arquitectura y Organización

### Clases Principales: 

⚡ `Juego`: Controla el menú principal, flujo de misiones, y sistema de guardado/carga.  
⚡ `Mapa`: Gestiona la matriz de Celda, colocación de personajes y objetos, movimiento de guardias y detección de proximidad.  
⚡ `Personaje` (abstracta): Base para `Snake`, `Guardia` y `MetalGear`, define posición, vida y movimiento.  
⚡ `Snake`: Personaje principal, maneja movimiento sobre el `Mapa` y recolección de `Objetos`.  
⚡ `Guardia`: Enemigo, se mueve aleatoriamente y detecta a Snake por proximidad.  
⚡ `MetalGear`: Enemigo final, combate por turnos contra Snake.  
⚡ `Mision`, `MisionIntermedia`, `MisionFinal`: Gestionan la lógica de cada misión (objetivos, recolección, acceso y combate).  
⚡ `Objeto` e implementaciones (`Tarjeta`, `C4`, `Puerta`): Representan ítems interactuables en el mapa.

###### El código está separado por responsabilidades.

---

## Flujo de Juego

1. **Menú Principal**: Usuario elige iniciar nueva partida, continuar o salir.
2. **Guardar/Cargar**: Se genera un código de guardado y se conserva en memoria mientras dura la ejecución.
3. **Misión Intermedia**:
    - Se muestran misiones disponibles según misiones completadas.
    - Snake se mueve con w/a/s/d, recolecta Tarjeta (o C4) y abre la Puerta al hangar (o la rompe).
    - `instanceof` se usa para detectar el tipo de objeto en la celda y disparar la lógica de negocio correspondiente (recolectar o bloquear paso).  
4. **Guardias**: Tras cada turno de Snake, mapa.moverGuardias() actualiza posiciones y, en cada movimiento, mapa.hayGuardiaCerca() usa proximidad de Manhattan para capturar al jugador si está demasiado cerca.  
5. **Batalla Final**: Combate por turnos entre Snake y MetalGear, alternando ataques o esquivas hasta que uno quede sin vida.

---
## Organización del Equipo

- Repositorio compartido en GitHub para control de versiones.
- Reuniones por Meet para coordinar tareas y tomar decisiones técnicas.
- Seguimos una planificación modular para desarrollar el juego paso a paso.
- Aplicamos buenas prácticas de POO y separación de responsabilidades.

---

## Principios de Diseño y POO

- **Encapsulamiento**: Atributos privados con getters/setters.
- **Herencia y Polimorfismo**: `Personaje` define la interfaz común; subclases implementan `mover()` y `combateRandom()`.
- **Interfaces**: `Enemigo` marca clases con lógica de combate.
- **Responsabilidad Única**: Cada clase tiene un único propósito: `Mapa` conoce el entorno, `Snake` solo su lógica de jugador, `Mision*` controlan el flujo de misión.
- **Uso de instanceof**: Permite en tiempo de ejecución diferenciar entre objetos de tipo `Tarjeta`, `Puerta` o `C4` sin romper la abstracción de `Objeto`.

---

## Sistema de Guardado/Carga

- **Código de Guardado**: Se genera combinando la misión actual con un valor aleatorio para evitar triviales repeticiones.
- **Carga**: El código ingresado se compara con el guardado en memoria y actualiza el código para reanudar desde la misión correcta.
- **Limitaciones**: Por simplicidad, no persiste tras cerrar la aplicación.

---

## Consideraciones de Negocio

- **Bloqueo de acceso al hangar**: Hasta recolectar el objeto correspondiente, Snake no puede finalizar la misión, y la Puerta permanece visible.

- **Detección de guardias**: Proximidad inmediata al pasar por celdas adyacentes, simulando la “área de visión” del guardia.

- **Secuencia de misiones**: El menú dinámico muestra sólo las misiones desbloqueadas y la opción de volver cuando corresponda.

---

## Posibles mejoras a futuro

- Persistencia real en archivos o base de datos para el guardado permanente.
- Mejor interfaz para mejorar la experiencia de usuario.
- Validación robusta de entrada: evitar que letras o caracteres inválidos rompan el bucle de menús.
- Mapa toróidal: al moverse fuera de un borde, reaparecer en el lado opuesto.
- Posición inicial aleatoria de Snake para mayor rejugabilidad.
- Configuración de dificultad: número de guardias, visión, tamaño de mapa.
- Mejora en los guardias: movimientos, detecciones y/o persecuciones.

---

## Objetivo Académico

Este proyecto nos permite aplicar los conocimientos adquiridos sobre análisis de sistemas, modelado orientado a objetos y desarrollo de aplicaciones en consola.

---

## Feedback

Nos gustaría recibir sugerencias u observaciones sobre cómo venimos trabajando, tanto en la implementación como en la organización general del grupo.

---

## Integrantes

- Leonardo Basualdo  
- Sergio Balbi 
- Matias Caballero
- Juan Manuel Mogliati




## Documentación

- [Trabajo Práctico - Programación Básica 2025](./Trabajo%20Práctico%20-%20Programación%20Básica%202025.pdf)


