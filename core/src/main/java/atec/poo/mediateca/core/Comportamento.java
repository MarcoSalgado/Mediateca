package atec.poo.mediateca.core;

/**
 * A classe `Comportamento` define três constantes estáticas que representam os comportamentos dos utilizadores da biblioteca,
 * conforme indicado no enunciado.
 */
public class Comportamento {
    public static final Comportamento N = new Comportamento("NORMAL"); // Comportamento NORMAL
    public static final Comportamento C = new Comportamento("CUMPRIDOR"); // Comportamento CUMPRIDOR
    public static final Comportamento F = new Comportamento("FALTOSO"); // Comportamento FALTOSO

    private final String descricao; // Descrição do comportamento

    /** Construtor privado para criar instâncias de Comportamento */
    private Comportamento(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição do comportamento.
     *
     * @return A descrição do comportamento.
     */
    @Override
    public String toString() {
        return descricao;
    }
}
