package atec.poo.mediateca.core;

import java.io.Serializable;

public class Requisicao implements Comparable<Requisicao>, Serializable {
    private static int nextID = 1; /** Inicializa o próximo ID como 1 */
    private final int id;
    private final int user;
    private final int obra;
    private final int diaReq;
    private int diasEntrega;

    private int multa;

    public Requisicao(int id, int user, int obra, int diaReq, int diasEntrega, int multa) {
        this.id = id;
        this.user = user;
        this.obra = obra;
        this.diaReq = diaReq;
        this.diasEntrega = diasEntrega;
        this.multa = multa;
    }

    public static int getNextID() {
        return nextID;
    }

    public int getId() {
        return id;
    }
    public int getUser() {
        return user;
    }

    public int getObra() {
        return obra;
    }

    public int getDiaReq() {
        return diaReq;
    }

    public int getDiasEntrega() {
        return diasEntrega;
    }

    public void setDiasEntrega(int diasEntrega) {
        this.diasEntrega = diasEntrega;
    }

    public int getMulta() {
        return multa;
    }
    public void setMulta(int multa) {
        this.multa = multa;
    }

    @Override
    public String toString() {
        return "Utilizador: " + getUser() + " - Obra: " + getObra() + " - Dia que foi requisitada: " + getDiaReq() + " - Dias restantes para entrega: " + getDiasEntrega() + " - Multa do Cabron: " + this.multa;
    }

    @Override
    public int compareTo(Requisicao r) {
        if (this.user == r.getUser())
            return this.id - r.getId();
        return this.user;
    }
}
