package personajes;

// HERENCIA
public class Guerrero extends Personaje {
    private int fuerza;

    //CONSTRUCTOR
    public Guerrero(String nombre, int nivel, int vida, int vidaMaxima, int ataque, int defensa, int fuerza) {
        super(nombre, nivel, vida, vidaMaxima, ataque, defensa);
        setFuerza(fuerza);
    }

    // 3. GETTER Y SETTER
    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        if (fuerza < 0) {
            System.out.println("La fuerza no puede ser negativa.");
        } else {
            this.fuerza = fuerza;
        }
    }

    // SOBRESCRITURA DE MÉTODOS

    // Sobreescritura del metodo atacar(Personaje)
    @Override
    public void atacar(Personaje enemigo) {
        if (!this.estaVivo()) {
            System.out.println(this.getNombre() + " está derrotado y no puede atacar.");
            return;
        }

        if (enemigo == null || !enemigo.estaVivo()) {
            System.out.println("El objetivo no es válido o ya está derrotado.");
            return;
        }

        int potencia = this.getAtaque() + this.fuerza;

        // Calcular daño
        int danioReal = enemigo.calcularDanioReal(potencia);
        enemigo.recibirDanio(potencia);
        System.out.println(this.getNombre() + " (Guerrero) ataca con su fuerza a " +
                enemigo.getNombre() + " | Daño efectivo: " + danioReal +
                " | Vida restante de " + enemigo.getNombre() + ": " + enemigo.getVida());
    }

    // Sobrescritura del metodo mostrarInformacion()
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Fuerza: " + fuerza);
        System.out.println("Tipo: Guerrero");
    }
}
