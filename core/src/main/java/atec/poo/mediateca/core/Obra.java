package atec.poo.mediateca.core;

import java.io.Serializable;

public abstract class Obra implements Comparable<Obra>, Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextID = 1; /** Inicializa o próximo ID como 1 */
    private final int id; /** Campo de ID da obra */
    private final String titulo;
    private final String autor;
    private final int preco;
    private final String categoria;
    private final int ia;
    private final int exemplares;
    public int requisitados;
    public int disponiveis;


    public Obra(String titulo, String autor, int preco, String categoria, int ia, int exemplares, int requisitados) {
        this.id = nextID++; /** Atribui o próximo ID e depois o incrementa */
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
        this.categoria = categoria;
        this.ia = ia;
        this.exemplares = exemplares;
        this.requisitados = requisitados;
    }

    public abstract String getTipo();

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getIa() {
        return ia;
    }

    public int getExemplares() {
        return exemplares;
    }

    public int getId() {
        return id;
    }

    public static void setNextID(int nextID) {
        Obra.nextID = nextID;
    }

    @Override
    public String toString() {
        disponiveis = exemplares - requisitados;
        return getId() + " - " + disponiveis + " de " + getExemplares() + " - " + getTipo() + " - " +
                getTitulo() + " - " + getPreco() + " - " + getCategoria() + " - " + getAutor() + " - " + getIa() ;
    }

    @Override
    public int compareTo(Obra o) {
        if (this.titulo.equals(o.getTitulo()))
            return this.id - o.getId();
        return this.titulo.compareTo(o.getTitulo());
    }
}