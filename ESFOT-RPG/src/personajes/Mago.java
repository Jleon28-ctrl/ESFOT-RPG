package personajes;

// HERENCIA
public class Mago extends Personaje {
    private int mana;

    // CONSTRUCTOR
    public Mago(String nombre, int nivel, int vida, int vidaMaxima, int ataque, int defensa, int mana) {
        super(nombre, nivel, vida, vidaMaxima, ataque, defensa);
        setMana(mana);
    }

    // GETTER Y SETTER
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            System.out.println("El maná no puede ser negativo.");
        }else {
            this.mana = mana;
        }
    }

    //  HABILIDAD ESPECIAL
    public void lanzarHechizo(Personaje enemigo) {
        if (!this.estaVivo()) {
            System.out.println(this.getNombre() + " está derrotado y no puede lanzar hechizos.");
            return;
        }
        if (enemigo == null || !enemigo.estaVivo()) {
            System.out.println("El objetivo no es válido o ya está derrotado.");
            return;
        }

        // Validación de recursos (Si no hay maná, no hace nada y no consume)
        if (this.mana < 10) {
            System.out.println(this.getNombre() + " no tiene maná suficiente (necesita 10, tiene " + this.mana + ").");
            return;
        }

        this.mana -= 10;
        int potenciaHechizo = 20;
        int danioReal = enemigo.calcularDanioReal(potenciaHechizo);
        enemigo.recibirDanio(potenciaHechizo);
        System.out.println(this.getNombre() + " (Mago) lanza un hechizo a " + enemigo.getNombre() +
                " | Daño efectivo: " + danioReal +
                " | Vida restante de " + enemigo.getNombre() + ": " + enemigo.getVida() +
                " | Maná restante: " + this.mana);
    }

    // Sobrescritura del metodo mostrarInformacion()
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion(); // Imprime lo básico (nombre, vida, etc.)
        System.out.println("Maná: " + mana);
        System.out.println("Tipo: Mago");
    }
}