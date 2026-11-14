# 🎮 ESFOT RPG: La Batalla de los Reinos

## 📖 Planteamiento del problema

Un estudio de videojuegos necesita un prototipo de juego de rol (RPG) por consola que permita registrar personajes con distintas características, consultar sus datos y enfrentarlos en combates por turnos. Actualmente, el equipo mantiene información y comportamientos de cada personaje por separado, lo que provoca duplicación de código y dificulta incorporar nuevos tipos de héroes.
El objetivo de este proyecto es construir una aplicación Java que resuelva este problema aplicando **herencia, encapsulamiento, constructores, `super`, sobrescritura, sobrecarga, polimorfismo y colecciones**.

---

## 📝 Descripción del Proyecto

El juego cuenta con un menú interactivo por consola que permite crear cuatro tipos de personajes: **Guerrero, Mago, Arquero y Paladín**. Cada uno tiene atributos únicos y habilidades especiales.
Los personajes se almacenan en dos colecciones:
*   Una lista (`ArrayList`) para recorrerlos de forma polimórfica.
*   Un mapa (`HashMap`) para realizar búsquedas rápidas por nombre.

---

## 🚀 Instrucciones de Ejecución

Para ejecutar este proyecto, necesitas tener instalado el JDK de Java (versión 17 o superior, ya que se utiliza *pattern matching* con `instanceof`).

**Opción A: Desde una terminal**
1. Abre la terminal en la carpeta raíz del proyecto (`ESFOT-RPG`).
2. Compila todos los archivos con el siguiente comando:
   ```bash
   javac -d out src/personajes/*.java src/juego/*.java src/Main.java
   java -cp out Main


## 🧬 Explicación de la Herencia en el Proyecto

El proyecto aplica los tres tipos de herencia solicitados para evitar la duplicación de código y facilitar la escalabilidad:

*   **Herencia Simple:** Se aplica en las clases `Guerrero`, `Mago` y `Arquero`, las cuales utilizan la palabra clave `extends` para heredar de una única clase base: `Personaje`.
*   **Herencia Jerárquica:** Varias clases comparten un mismo padre. `Guerrero`, `Mago` y `Arquero` son hijos directos de `Personaje`, formando una estructura de árbol.
*   **Herencia Multinivel:** La clase `Paladin` hereda de `Guerrero`, que a su vez hereda de `Personaje`. Esto permite que el Paladín herede tanto los atributos y métodos de `Personaje` como la fuerza y el ataque del `Guerrero`.

Además, se utiliza la palabra clave `super` en los constructores de las clases hijas para inicializar los atributos heredados, y en métodos como `mostrarInformacion()` o `recibirDanio()` (en el caso del Paladín) para reutilizar la lógica de la clase padre.

## 🧠 Respuestas a las Preguntas de Análisis

**1. ¿Dónde se aplica herencia simple en el proyecto?**
Se aplica cuando las clases `Guerrero`, `Mago` y `Arquero` usan la palabra `extends` para heredar de una sola clase base, que en este caso es `Personaje`. Solo tienen un padre directo.

**2. ¿Por qué Personaje → Guerrero → Paladin representa herencia multinivel?**
Porque la cadena de herencia tiene más de un nivel. El `Paladin` no hereda directamente de `Personaje`, sino que hereda primero de `Guerrero`, y el `Guerrero` a su vez heredó de `Personaje`. Es como un abuelo, un padre y un hijo.

**3. ¿Qué relación ejemplifica herencia jerárquica?**
La relación que hay entre `Personaje` y sus tres hijos directos (`Guerrero`, `Mago`, `Arquero`). Todos comparten el mismo padre, creando una estructura de ramas (jerarquía).

**4. ¿Cuál es la diferencia entre sobrescritura y sobrecarga de atacar()?**
La **sobrescritura** ocurre cuando una clase hija (ej. `Guerrero`) modifica un método que ya existía en el padre (`Personaje`). Usamos `@Override` y el método se llama igual y recibe los mismos parámetros. La **sobrecarga** ocurre en la misma clase, donde tenemos dos métodos con el mismo nombre pero diferentes parámetros: `atacar(Personaje)` y `atacar(Personaje, int bonificacion)`.

**5. ¿Qué aporta super en constructores y métodos?**
En los constructores, `super(...)` sirve para llamar al constructor de la clase padre y así no tener que volver a escribir la inicialización de los atributos base. En los métodos, `super.metodo()` nos permite reutilizar lo que ya hace el padre (por ejemplo, imprimir la información base) y luego añadir lo nuevo de la clase hija.

**6. ¿Por qué List<Personaje> admite guerreros, magos, arqueros y paladines?**
Por el principio de polimorfismo en Java. Una variable de tipo `Personaje` puede guardar un objeto de cualquier clase que herede de ella, porque "un guerrero ES un personaje". Por eso podemos meterlos a todos juntos en una misma lista.

**7. ¿Qué versión de mostrarInformacion() se ejecuta al recorrer la lista y por qué?**
Se ejecuta la versión de la clase específica (la del Guerrero, Mago, etc.). Esto se debe al polimorfismo en tiempo de ejecución (enlace dinámico). Java revisa qué tipo de objeto es realmente (no de qué tipo es la variable) y ejecuta su método correspondiente.

**8. ¿Cuándo se necesita instanceof antes de hacer casting?**
Cuando queremos usar un método exclusivo de una clase hija, pero tenemos el objeto guardado como si fuera de la clase padre. Usamos `instanceof` para comprobar si realmente es de esa clase antes de convertirlo (hacer casting) y así evitar un error en tiempo de ejecución. Por ejemplo, verificar si un `Personaje` es un `Mago` antes de llamar a `lanzarHechizo()`.

**9. ¿Qué ventaja aporta HashMap para localizar un personaje por nombre?**
Es mucho más rápido. En una lista normal tendríamos que recorrer a todos los personajes uno por uno hasta encontrar el nombre (búsqueda lineal). El `HashMap` guarda los datos usando el nombre como clave, por lo que puede ir directo al personaje que buscamos de forma casi instantánea.

**10. ¿Cómo se protege la consistencia de los atributos mediante encapsulamiento?**
Haciendo que los atributos sean `private`. Nadie puede modificarlos directamente desde fuera. Para cambiarlos, deben usar los métodos `set`. Ahí es donde están las validaciones (por ejemplo, que la vida no pase de la vida máxima o que el ataque no sea negativo). Así evitamos que el objeto tenga datos ilógicos.