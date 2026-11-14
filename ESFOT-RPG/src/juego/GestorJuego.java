package juego;

import personajes.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorJuego {

    // COLECCIONES
    private List<Personaje> personajes;
    private Map<String, Personaje> registro;

    // CONSTRUCTOR
    public GestorJuego() {
        this.personajes = new ArrayList<>();
        this.registro = new HashMap<>();
    }

    // MÉTODOS DE GESTIÓN
    public boolean existeNombre(String nombre) {
        return registro.containsKey(nombre.trim());
    }

    // Agregar un personaje (mantiene las dos colecciones sincronizadas)
    public void agregarPersonaje(Personaje p) {
        if (p == null) return;

        if (existeNombre(p.getNombre())) {
            System.out.println("Error: Ya existe un personaje con el nombre '" + p.getNombre() + "'.");
            return;
        }

        personajes.add(p);
        registro.put(p.getNombre(), p);
        System.out.println("Personaje registrado exitosamente: " + p.getNombre());
    }

    // Buscar un personaje por nombre usando el HashMap
    public Personaje buscarPorNombre(String nombre) {
        return registro.get(nombre.trim());
    }

    // Mostrar todos los personajes (usando polimorfismo en el List)
    public void mostrarTodos() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados en el reino.");
            return;
        }

        System.out.println("\n=== LISTA DE PERSONAJES ===");
        for (Personaje p : personajes) {
            System.out.println("------------------------------------");
            // Aquí ocurre el POLIMORFISMO: se ejecuta el mostrarInformacion() de cada clase hija
            p.mostrarInformacion();
        }
        System.out.println("------------------------------------");
    }

    // Mostrar solo los personajes vivos
    public void mostrarVivos() {
        boolean hayVivos = false;
        System.out.println("\n=== PERSONAJES VIVOS ===");
        for (Personaje p : personajes) {
            if (p.estaVivo()) {
                System.out.println("- " + p.toString());
                hayVivos = true;
            }
        }
        if (!hayVivos) {
            System.out.println("No hay personajes vivos en este momento.");
        }
    }

    // COMBATE POR TURNOS
    public void combatir(Personaje a, Personaje b) {
        // Validaciones
        if (a == null || b == null) {
            System.out.println("Personajes inválidos para el combate.");
            return;
        }
        if (a == b) {
            System.out.println("Un personaje no puede pelear contra sí mismo.");
            return;
        }
        if (!a.estaVivo() || !b.estaVivo()) {
            System.out.println("Ambos personajes deben estar vivos para iniciar el combate.");
            return;
        }

        System.out.println("\n====================================");
        System.out.println("       COMBATE RPG");
        System.out.println("====================================");
        System.out.println(a.toString() + " vs " + b.toString());
        System.out.println("------------------------------------");

        Personaje atacante = a;
        Personaje defensor = b;
        int turno = 1;

        while (a.estaVivo() && b.estaVivo()) {
            System.out.println("Turno " + turno + ": " + atacante.getNombre() + " ataca a " + defensor.getNombre());
            atacante.atacar(defensor);

            System.out.println("Vida de " + defensor.getNombre() + ": " + defensor.getVida() + "/" + defensor.getVidaMaxima());
            System.out.println("------------------------------------");

            Personaje temp = atacante;
            atacante = defensor;
            defensor = temp;

            turno++;
        }

        Personaje ganador = a.estaVivo() ? a : b;
        System.out.println("¡GANADOR: " + ganador.getNombre() + " (" + ganador.getClass().getSimpleName() + ")!");
        System.out.println("====================================\n");
    }
}