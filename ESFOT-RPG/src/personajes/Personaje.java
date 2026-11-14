package personajes;

import java.util.Objects;

public class Personaje {
    //declaración de atributos
    private String nombre;
    private int nivel, vida, vidaMaxima, ataque, defensa;

    // contsructor con parámetros
    public Personaje(String nombre, int nivel, int vida, int vidaMaxima, int ataque, int defensa) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    //geters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (Objects.equals(nombre, " ")) {
            System.out.println("Este campo no puede estar vacío");
        } else {
            this.nombre = nombre;
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel < 0){
            System.out.println("El nivel no puede ser negativo");
        } else {
            this.nivel = nivel;
        }
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
         if (vida < 0 || vida > vidaMaxima){
            System.out.println("La vida debe estar entre 0 y " + vidaMaxima);
        } else {
            this.vida = vida;
        }
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
         if (vidaMaxima <= 0){
            System.out.println("La vida máxima debe ser mayor a cero");
        } else {
            this.vidaMaxima = vidaMaxima;
        }
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if (ataque < 0){
            System.out.println("El ataque no puede ser negativo");
        } else {
            this.ataque = ataque;
        }
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if (defensa < 0){
            System.out.println("La defensa no puede ser negativa");
        } else {
            this.defensa = defensa;
        }
    }

    //metodo para mostrar info

    public void mostrarInformacion(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Nivel: " + nivel);
        System.out.println("Vida: " + vida + "/" + vidaMaxima);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defensa: " + defensa);
    }

    //metodo estavivo
    public boolean estaVivo(){
        return this.vida > 0;
    }

    // Metodo auxiliar para calcular el daño real
    public int calcularDanioReal(int cantidad) {
        return Math.max(1, cantidad - defensa);
    }

    //metodo recibirDanio()
    public void recibirDanio(int cantidad) {
        int danioReal = calcularDanioReal(cantidad);
        this.vida = Math.max(0, this.vida - danioReal);
    }

    //Metodo de ataque
    public void atacar(Personaje enemigo) {
        if (!this.estaVivo()) {
            System.out.println(this.nombre + " está derrotado y no puede atacar.");
            return;
        }
        if (enemigo == null || !enemigo.estaVivo()) {
            System.out.println("El objetivo no es válido o ya está derrotado.");
            return;
        }

        int potencia = this.ataque;
        int danioReal = enemigo.calcularDanioReal(potencia);
        enemigo.recibirDanio(potencia);

        System.out.println(this.nombre + " ataca a " + enemigo.getNombre() +
                " | Daño efectivo: " + danioReal +
                " | Vida restante de " + enemigo.getNombre() + ": " + enemigo.getVida());
    }

    //Sobrecarga del metodo ataque
    public void atacar(Personaje enemigo, int bonificacion) {
        if (bonificacion < 0) {
            System.out.println("La bonificación no puede ser negativa.");
            return;
        }
        if (!this.estaVivo()) {
            System.out.println(this.nombre + " está derrotado y no puede atacar.");
            return;
        }
        if (enemigo == null || !enemigo.estaVivo()) {
            System.out.println("El objetivo no es válido o ya está derrotado.");
            return;
        }

        int potencia = this.ataque + bonificacion;
        int danioReal = enemigo.calcularDanioReal(potencia);
        enemigo.recibirDanio(potencia);

        System.out.println(this.nombre + " ataca con bonificación (+" + bonificacion + ") a " +
                enemigo.getNombre() + " | Daño efectivo: " + danioReal +
                " | Vida restante de " + enemigo.getNombre() + ": " + enemigo.getVida());
    }
}
