import juego.GestorJuego;
import personajes.*;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final GestorJuego gestor = new GestorJuego();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            try {
                switch (opcion) {
                    case 1 -> crearGuerrero();
                    case 2 -> crearMago();
                    case 3 -> crearArquero();
                    case 4 -> crearPaladin();
                    case 5 -> gestor.mostrarTodos();
                    case 6 -> buscarPersonaje();
                    case 7 -> atacar();
                    case 8 -> usarHabilidadEspecial();
                    case 9 -> simularCombate();
                    case 10 -> gestor.mostrarVivos();
                    case 11 -> System.out.println("Saliendo del juego...");
                    default -> System.out.println("Opcion invalida. Intente de nuevo.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error de validacion: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrio un error inesperado: " + e.getMessage());
            }

        } while (opcion != 11);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("       ESFOT RPG - JAVA POO");
        System.out.println("====================================");
        System.out.println("1. Crear Guerrero");
        System.out.println("2. Crear Mago");
        System.out.println("3. Crear Arquero");
        System.out.println("4. Crear Paladin");
        System.out.println("5. Mostrar personajes");
        System.out.println("6. Buscar personaje por nombre");
        System.out.println("7. Atacar a otro personaje");
        System.out.println("8. Utilizar habilidad especial");
        System.out.println("9. Simular combate por turnos");
        System.out.println("10. Mostrar personajes vivos");
        System.out.println("11. Salir");
    }

    // ==========================================
    // CREACION DE PERSONAJES
    // ==========================================

    private static void crearGuerrero() {
        System.out.println("\n--- CREANDO GUERRERO ---");
        String nombre = leerNombreUnico();
        int nivel = leerEntero("Nivel: ");
        int vida = leerEntero("Vida inicial: ");
        int vidaMaxima = leerEntero("Vida maxima: ");
        int atk = leerEntero("Ataque: ");
        int def = leerEntero("Defensa: ");
        int fuerza = leerEntero("Fuerza: ");

        gestor.agregarPersonaje(new Guerrero(nombre, nivel, vida, vidaMaxima, atk, def, fuerza));
    }

    private static void crearMago() {
        System.out.println("\n--- CREANDO MAGO ---");
        String nombre = leerNombreUnico();
        int nivel = leerEntero("Nivel: ");
        int vida = leerEntero("Vida inicial: ");
        int vidaMaxima = leerEntero("Vida maxima: ");
        int atk = leerEntero("Ataque: ");
        int def = leerEntero("Defensa: ");
        int mana = leerEntero("Mana: ");

        gestor.agregarPersonaje(new Mago(nombre, nivel, vida, vidaMaxima, atk, def, mana));
    }

    private static void crearArquero() {
        System.out.println("\n--- CREANDO ARQUERO ---");
        String nombre = leerNombreUnico();
        int nivel = leerEntero("Nivel: ");
        int vida = leerEntero("Vida inicial: ");
        int vidaMaxima = leerEntero("Vida maxima: ");
        int atk = leerEntero("Ataque: ");
        int def = leerEntero("Defensa: ");
        int flechas = leerEntero("Flechas: ");

        gestor.agregarPersonaje(new Arquero(nombre, nivel, vida, vidaMaxima, atk, def, flechas));
    }

    private static void crearPaladin() {
        System.out.println("\n--- CREANDO PALADIN ---");
        String nombre = leerNombreUnico();
        int nivel = leerEntero("Nivel: ");
        int vida = leerEntero("Vida inicial: ");
        int vidaMaxima = leerEntero("Vida maxima: ");
        int atk = leerEntero("Ataque: ");
        int def = leerEntero("Defensa: ");
        int fuerza = leerEntero("Fuerza: ");
        int energia = leerEntero("Energia sagrada: ");

        gestor.agregarPersonaje(new Paladin(nombre, nivel, vida, vidaMaxima, atk, def, fuerza, energia));
    }

    // ==========================================
    // ACCIONES
    // ==========================================

    private static void buscarPersonaje() {
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = sc.nextLine().trim();
        Personaje p = gestor.buscarPorNombre(nombre);
        if (p == null) {
            System.out.println("No se encontro ningun personaje con ese nombre.");
        } else {
            System.out.println("\n--- PERSONAJE ENCONTRADO ---");
            p.mostrarInformacion();
        }
    }

    private static void atacar() {
        System.out.println("\n--- ATAQUE MANUAL ---");
        Personaje atacante = pedirPersonaje("Nombre del atacante: ");
        if (atacante == null) return;

        Personaje objetivo = pedirPersonaje("Nombre del objetivo: ");
        if (objetivo == null) return;

        System.out.print("Desea usar bonificacion? (s/n): ");
        String r = sc.nextLine().trim().toLowerCase();

        if (r.equals("s")) {
            int bon = leerEntero("Ingrese la bonificacion: ");
            atacante.atacar(objetivo, bon);
        } else {
            atacante.atacar(objetivo);
        }
    }

    private static void usarHabilidadEspecial() {
        System.out.println("\n--- HABILIDAD ESPECIAL ---");
        Personaje p = pedirPersonaje("Nombre del personaje: ");
        if (p == null) return;

        if (p instanceof Mago mago) {
            Personaje obj = pedirPersonaje("Nombre del objetivo del hechizo: ");
            if (obj != null) mago.lanzarHechizo(obj);

        } else if (p instanceof Paladin paladin) {
            System.out.println("1. Curar a alguien");
            System.out.println("2. Activar escudo");
            int op = leerEntero("Elija una opcion: ");

            if (op == 1) {
                Personaje obj = pedirPersonaje("Nombre del personaje a curar: ");
                if (obj != null) paladin.curar(obj);
            } else if (op == 2) {
                paladin.activarEscudo();
            } else {
                System.out.println("Opcion invalida.");
            }

        } else if (p instanceof Arquero arquero) {
            Personaje obj = pedirPersonaje("Nombre del objetivo de la flecha: ");
            if (obj != null) arquero.dispararFlecha(obj);

        } else if (p instanceof Guerrero) {
            System.out.println("El Guerrero no tiene una habilidad especial aparte de su ataque con fuerza. Use la opcion 7.");
        } else {
            System.out.println("Este personaje no tiene habilidades especiales.");
        }
    }

    private static void simularCombate() {
        System.out.println("\n--- COMBATE AUTOMATICO ---");
        Personaje a = pedirPersonaje("Nombre del primer combatiente: ");
        if (a == null) return;

        Personaje b = pedirPersonaje("Nombre del segundo combatiente: ");
        if (b == null) return;

        gestor.combatir(a, b);
    }

    // ==========================================
    // UTILIDADES
    // ==========================================

    private static Personaje pedirPersonaje(String mensaje) {
        System.out.print(mensaje);
        String nombre = sc.nextLine().trim();
        Personaje p = gestor.buscarPorNombre(nombre);
        if (p == null) {
            System.out.println("El personaje '" + nombre + "' no existe.");
        }
        return p;
    }

    private static String leerNombreUnico() {
        while (true) {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacio.");
            } else if (gestor.existeNombre(nombre)) {
                System.out.println("Ya existe un personaje con ese nombre. Intente otro.");
            } else {
                return nombre;
            }
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero entero valido.");
            }
        }
    }
}