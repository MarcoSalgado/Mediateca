package atec.poo.mediateca.core;

/**
 * A classe `Estado` define duas constantes estáticas que representam os estados dos utilizadores da biblioteca,
 * conforme indicado no enunciado.
 */
public class Estado {
    public static final Estado SUSPENSO = new Estado("SUSPENSO"); // Estado SUSPENSO
    public static final Estado ACTIVO = new Estado("ATIVO"); // Estado ATIVO

    private final String descricao; /** Descrição do estado */

    /** Construtor privado para criar instâncias de Estado */
    private Estado(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição do estado.
     *
     * @return A descrição do estado.
     */
    @Override
    public String toString() {
        return descricao;
    }
}
