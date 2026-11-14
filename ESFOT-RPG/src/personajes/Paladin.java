package personajes;

// HERENCIA MULTINIVEL
public class Paladin extends Guerrero {
    private int energiaSagrada;
    private boolean escudoActivo;

    // CONSTRUCTOR
    public Paladin(String nombre, int nivel, int vida, int vidaMaxima, int ataque, int defensa, int fuerza, int energiaSagrada) {
        super(nombre, nivel, vida, vidaMaxima, ataque, defensa, fuerza);

        setEnergiaSagrada(energiaSagrada);
        this.escudoActivo = false;
    }

    // GETTERS Y SETTERS
    public int getEnergiaSagrada() {
        return energiaSagrada;
    }

    public void setEnergiaSagrada(int energiaSagrada) {
        if (energiaSagrada < 0) {
            System.out.println("La energía sagrada no puede ser negativa.");
        }
        this.energiaSagrada = energiaSagrada;
    }

    public boolean isEscudoActivo() {
        return escudoActivo;
    }

    // HABILIDADES ESPECIALES

    // CURAR: Consume 10 de energía y recupera hasta 20 de vida
    public void curar(Personaje objetivo) {
        if (!this.estaVivo()) {
            System.out.println(this.getNombre() + " está derrotado y no puede curar.");
            return;
        }
        if (objetivo == null) {
            System.out.println("Objetivo inválido.");
            return;
        }
        if (!objetivo.estaVivo()) {
            System.out.println("No se puede curar a un personaje derrotado.");
            return;
        }
        if (this.energiaSagrada < 10) {
            System.out.println(this.getNombre() + " no tiene energía suficiente (necesita 10, tiene " + this.energiaSagrada + ").");
            return;
        }

        // Consumimos energía
        this.energiaSagrada -= 10;

        // Calculamos cuánto se puede curar sin superar la vida máxima
        int vidaActual = objetivo.getVida();
        int curacion = Math.min(20, objetivo.getVidaMaxima() - vidaActual);

        // Aplicamos la curación
        objetivo.setVida(vidaActual + curacion);

        System.out.println(this.getNombre() + " (Paladín) cura a " + objetivo.getNombre() +
                " | Vida recuperada: " + curacion +
                " | Vida actual de " + objetivo.getNombre() + ": " + objetivo.getVida() + "/" + objetivo.getVidaMaxima() +
                " | Energía restante: " + this.energiaSagrada);
    }

    // ESCUDO: Protege del siguiente ataque
    public void activarEscudo() {
        this.escudoActivo = true;
        System.out.println("🛡️ " + this.getNombre() + " ha activado su escudo sagrado (reducirá 5 de daño al próximo ataque).");
    }

    // 5. SOBRESCRITURA DE RECIBIR DAÑO (Para el escudo)
    @Override
    public void recibirDanio(int cantidad) {
        if (this.escudoActivo) {
            int cantidadReducida = Math.max(0, cantidad - 5);
            System.out.println("¡El escudo de " + this.getNombre() + " absorbe 5 puntos de daño!");
            this.escudoActivo = false;
            super.recibirDanio(cantidadReducida);
        } else {
            // Si no hay escudo, recibe el daño normal
            super.recibirDanio(cantidad);
        }
    }

    // 6. SOBRESCRITURA DE MOSTRAR INFORMACIÓN
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion(); // Imprime lo de Guerrero y Personaje
        System.out.println("Energía Sagrada: " + energiaSagrada);
        System.out.println("Escudo Activo: " + (escudoActivo ? "Sí" : "No"));
        System.out.println("Tipo: Paladín");
    }
}