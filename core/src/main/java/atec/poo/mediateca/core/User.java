package atec.poo.mediateca.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User>, Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;          /** Identificador único do utilizador */
    private final String nome;     /** Nome do utilizador */
    private final String email;    /** Endereço de email do utilizador */

    public int multa; /** multa para o utilizador */

    public int maxRequest = 0;
    public List<Integer> requisicao;
    public List<Integer> entregue;

    public List<Integer> listaDeEspera;

    private List<Notificacao> notificacoes;

    private Estado estado;         /** Estado do utilizador (ativo ou suspenso) */

    private Comportamento comportamento; /** Comportamento do utilizador */

    // Construtor da classe User
    public User(int id, String nome, String email, int maxRequest, int requisicao, int multa, int entregue, int listaDeEspera) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.estado = Estado.ACTIVO;          /** Define o estado inicial como ativo */
        this.comportamento = Comportamento.N; /** Define o comportamento inicial como N (normal) */
        this.maxRequest = 0;
        this.requisicao = new ArrayList<>();
        this.entregue = new ArrayList<>();
        this.listaDeEspera = new ArrayList<>();
        this.multa = multa;
    }
    

    /** Métodos para definir o estado e comportamento do utilizador */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    /** Métodos para obter o estado e comportamento do utilizador */
    public Estado getEstado() {
        return estado;
    }

    public Comportamento getComportamento() {
        return comportamento;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    /**
     * @param num Metodo para verificar se a obra ja existe na lista
     * @return
     */
    public boolean getObraNum(int num) {
        if (requisicao != null) {
            for (Integer valor : requisicao) {
                if (valor == num) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Métodos para obter o ID e nome do utilizador */
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }



    /** Método toString para representar o utilizador como uma string */
    @Override
    public String toString() {
        if(this.multa>0){
            return this.id + " - " + this.nome + " - " + this.email + " - " + this.comportamento + " - " + this.estado + " - " + this.multa;
        }else{
            return this.id + " - " + this.nome + " - " + this.email + " - " + this.comportamento + " - " + this.estado + " - " + this.listaDeEspera;
        }

    }

    /** Método compareTo para comparar utilizadores com base no nome e ID */
    @Override
    public int compareTo(User o) {
        if (this.nome.equalsIgnoreCase(o.getNome()))
            return this.id - o.getId();
        return this.nome.toLowerCase().compareTo(o.getNome().toLowerCase());
    }
}
