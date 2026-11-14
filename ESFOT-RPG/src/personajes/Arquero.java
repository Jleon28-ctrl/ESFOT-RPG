package personajes;

// HERENCIA
public class Arquero extends Personaje {
    private int flechas;

    //CONSTRUCTOR
    public Arquero(String nombre, int nivel, int vida, int vidaMaxima, int ataque, int defensa, int flechas) {
        super(nombre, nivel, vida, vidaMaxima, ataque, defensa);
        setFlechas(flechas);
    }

    //GETTER Y SETTER
    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        if (flechas < 0) {
            System.out.println("Las flechas no pueden ser negativas.");
        }else {
            this.flechas = flechas;
        }
    }

    // HABILIDAD ESPECIAL
    public void dispararFlecha(Personaje enemigo) {
        // Validaciones
        if (!this.estaVivo()) {
            System.out.println(this.getNombre() + " está derrotado y no puede disparar.");
            return;
        }
        if (enemigo == null || !enemigo.estaVivo()) {
            System.out.println("El objetivo no es válido o ya está derrotado.");
            return;
        }

        // Validación de recursos
        if (this.flechas <= 0) {
            System.out.println(this.getNombre() + " no tiene flechas disponibles.");
            return;
        }

        // Consumimos la flecha
        this.flechas--;

        // Potencia de la flecha = ataque normal + 5
        int potencia = this.getAtaque() + 5;

        // Calculamos el daño y lo aplicamos
        int danioReal = enemigo.calcularDanioReal(potencia);
        enemigo.recibirDanio(potencia);

        System.out.println(this.getNombre() + " (Arquero) dispara una flecha a " + enemigo.getNombre() +
                " | Daño efectivo: " + danioReal +
                " | Vida restante de " + enemigo.getNombre() + ": " + enemigo.getVida() +
                " | Flechas restantes: " + this.flechas);
    }

    // 5. SOBRESCRITURA DE MOSTRAR INFORMACIÓN
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Flechas: " + flechas);
        System.out.println("Tipo: Arquero");
    }
}