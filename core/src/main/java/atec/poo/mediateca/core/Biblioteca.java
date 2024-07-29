package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.BadEntrySpecificationException;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.mediateca.app.requests.Message;
import atec.poo.ui.LerBoolean;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Biblioteca implements Serializable {

    private static int data;
    private HashMap<Integer, User> users;
    private HashMap<Integer, Obra> obras;
    private HashMap<Integer, Requisicao> reqs;

    private int nextUserID;
    private int nextObraID;
    private int nextReqID;
    private int dias = 0;
    private int diaReq = 0;

    public Biblioteca() {
        this.users = new HashMap<>();
        this.obras = new HashMap<>();
        this.reqs = new HashMap<>();
        this.nextUserID = 1;
        this.nextObraID = 1;
        this.nextReqID = 1;
        this.data = 0;
    }

    public int registarUser(String nome, String email) {
        /**  Cria um novo objeto User com os dados fornecidos */
        User u = new User(this.nextUserID, nome, email, 0, 0, 0, 0,0);

        /**  Adiciona o utilizador ao HashMap com o ID como chave */
        this.users.put(u.getId(), u);

        /** Incrementa o próximo ID de utilizador para uso futuro */
        this.nextUserID++;

        /** Retorna o ID do utilizador recém-registado */
        return u.getId();
    }

    public int registarDVD(String titulo, String autor, int preco, String categoria, int ia, int exemplares, int requisitados) {
        DVD dvd = new DVD(titulo, autor, preco, categoria, ia, exemplares, requisitados);
        this.obras.put(dvd.getId(), dvd);
        /** Incrementa o próximo ID de obra para uso futuro */
        this.nextObraID++;
        return dvd.getId();
    }

    public int registarBook(String titulo, String autor, int preco, String categoria, int ia, int exemplares, int requisitados) {
        Book book = new Book(titulo, autor, preco, categoria, ia, exemplares, requisitados);
        this.obras.put(book.getId(), book);
        /** Incrementa o próximo ID de obra para uso futuro */
        this.nextObraID++;
        return book.getId();
    }

    public void registarRequisicao(int id, int user, int obra, int diaReq, int diasEntrega, int multa) {
        /** Cria um novo objeto User com os dados fornecidos */
        Requisicao r = new Requisicao(id, user, obra, diaReq, diasEntrega, multa);

        /** Adiciona o utilizador ao HashMap com o ID como chave */
        this.reqs.put(r.getId(), r);

        /** Incrementa o próximo ID de utilizador para uso futuro */
        this.nextReqID++;

        /** Retorna o ID do utilizador recém-registado */
    }
    public void removerRequisicao(int idUser, int idObra) {
        for (Requisicao requisicao : this.reqs.values()) {
            if (requisicao.getUser() == idUser && requisicao.getObra() == idObra) {
                /** Remove a requisição encontrada */
                this.reqs.remove(requisicao.getId());
                return; /** Retorna após remover a requisição, pois ela é única */
            }
        }
    }

    public ArrayList<User> listUsers() {
        /** Cria uma lista a partir dos valores(users) do HashMap users */
        ArrayList<User> users_array = new ArrayList<>(this.users.values());
        /** Ordena a ArrayList alfabeticamente com base no nome */
        Collections.sort(users_array);
        return users_array;
    }

    public ArrayList<Obra> listObras() {
        /** Cria uma lista a partir dos valores(users) do HashMap users */
        ArrayList<Obra> obras_array = new ArrayList<>(this.obras.values());
        /** Ordena a ArrayList alfabeticamente com base no nome */
        Collections.sort(obras_array);
        return obras_array;
    }

    public ArrayList<Requisicao> listReqs() {
        /** Cria uma lista a partir dos valores(users) do HashMap users */
        ArrayList<Requisicao> reqs_array = new ArrayList<>(this.reqs.values());
        /** Ordena a ArrayList alfabeticamente com base no nome */
        Collections.sort(reqs_array);
        return reqs_array;
    }

    public ArrayList<Obra> pesquisarObras(String autor, String titulo) {
        ArrayList<Obra> resultados = new ArrayList<>();

        /** Iterar sobre todas as obras e verificar os critérios de pesquisa */
        for (Obra obra : this.obras.values()) {
            if ((autor == null || autor.isEmpty() || obra.getAutor().toLowerCase().contains(autor.toLowerCase()))
                    && (titulo == null || titulo.isEmpty() || obra.getTitulo().toLowerCase().contains(titulo.toLowerCase()))) {
                resultados.add(obra);
            }
        }

        return resultados;
    }

    public String mostrarUtente(int id) throws UserNotFoundException {
        /** Verifica se o HashMap de utilizadores contém o ID especificado */
        if (this.users.containsKey(id)) {
            /** Se existir, retorna a representação em string do utilizador */
            User user = this.users.get(id);
            return user.toString();

        } else {
            /** Se não existir, lança uma exceção UserNotFoundException com o ID */
            throw new UserNotFoundException(id);
        }
    }

    public String pagar(int id) throws UserNotFoundException{
        if (this.users.containsKey(id)) {
            /** Se existir, retorna a representação em string do utilizador */
            return this.users.get(id).toString();
        } else {
            /** Se não existir, lança uma exceção UserNotFoundException com o ID */
            throw new UserNotFoundException(id);
        }
    }

    public String mostrarObra(int id) throws WorkNotFoundException {
        /** Verifica se o HashMap de utilizadores contém o ID especificado */
        if (this.obras.containsKey(id)) {
            /** Se existir, retorna a representação em string do utilizador */
            return this.obras.get(id).toString();
        } else {
            /** Se não existir, lança uma exceção UserNotFoundException com o ID */
            throw new WorkNotFoundException(id);
        }
    }


    public ArrayList<Obra> mostrarRequisitadas(int id) throws UserNotFoundException{
        if (this.users.containsKey(id)) {
            ArrayList<Obra> resultados = new ArrayList<>();
            User user = this.users.get(id);
            /** Se existir, retorna a representação em string do utilizador */
            for (Obra obra : this.obras.values()) {
                if(user.requisicao.contains(obra.getId())) {
                    resultados.add(obra);
                }
            }
            return resultados;
        } else {
            /** Se não existir, lança uma exceção UserNotFoundException com o ID */
            throw new UserNotFoundException(id);
        }
    }

    public ArrayList<Obra> mostrarEntregues(int id) throws UserNotFoundException{
        if (this.users.containsKey(id)) {
            ArrayList<Obra> resultados = new ArrayList<>();
            User user = this.users.get(id);
            /** Se existir, retorna a representação em string do utilizador */
            for (Obra obra : this.obras.values()) {
                if(user.entregue.contains(obra.getId())) {
                    resultados.add(obra);
                }
            }
            return resultados;
        } else {
            /** Se não existir, lança uma exceção UserNotFoundException com o ID */
            throw new UserNotFoundException(id);
        }
    }

    //Requesitar obra
    public String requisitarObra(int idUser, int idObra) {
        Obra obra = this.obras.get(idObra); /** Obtém a obra com base no ID */
        if (obra == null) {
            return "Obra não encontrada";
        }
        User user = this.users.get(idUser);

        obra.disponiveis = obra.getExemplares() - obra.requisitados;
        if (obra.disponiveis == 0 || obra.getExemplares() == 0) {
            if(user.listaDeEspera.contains(obra.getId()))
            {
                return "\nJá foi adicionada na lista de espera\n";
            }
            else{
                Scanner s = new Scanner(System.in);
                System.out.println("Obra nao tem mais exemplares disponiveis, deseja ser avisado quando algum exemplar for devolvido (s/n)?");
                String opc = s.nextLine().toLowerCase().replace(" ", "");
                if (opc.equals("s")) {
                    user.listaDeEspera.add(obra.getId());
                    System.out.println(user.listaDeEspera);
                    return "Será informando";
                }
            }

        }

        /** implementar a lógica com base no comportamento do utilizador */
        if (user == null) {
            return "Utilizador não encontrado.";
        }
        if ( user.getEstado().equals(Estado.SUSPENSO)){
            return " -> Atualmente está como utilizador suspenso, pague a sua divida !\n";
        }
        /**verificar se o user ja tem a obra na lista */
        if (!user.getObraNum(idObra)) {
            if (user.getComportamento().toString().equals("NORMAL")) {
                // Lógica para usuário normal
                if (!obra.getCategoria().equals("REFERENCE")) {
                    if (obra.getPreco() <= 25) {
                        if (user.maxRequest < 3) {
                            diaReq = getData();
                            if (obra.getExemplares() == 1) {
                                dias = 3;
                            } else if (obra.getExemplares() <= 5 && obra.getExemplares() > 1) {
                                dias = 8;
                            } else {
                                dias = 15;
                            }
                            user.maxRequest++;
                            obra.requisitados++;
                            user.requisicao.add(idObra);
                            registarRequisicao(nextReqID, user.getId(), obra.getId(), diaReq, dias, 0);
                            System.out.println(user.requisicao);
                            return "Obra requisitada com sucesso: " + obra.toString();
                        } else {
                            return "Chegou ao limite de requisições !";
                        }
                    } else {
                        return "Obras com o preco superior a 25€ não lhe estão disponiveis";
                    }
                } else {
                    return "Esta obra é de referencia logo não a poderá requisitar";
                }

            } else if (user.getComportamento().toString().equals("CUMPRIDOR")) {
                /** Lógica para usuário cumpridor */
                if (!obra.getCategoria().equals("REFERENCE")) {
                    if (user.maxRequest < 5) {
                        diaReq = getData();
                        if (obra.getExemplares() == 1) {
                            dias = 8;
                        } else if (obra.getExemplares() <= 5 && obra.getExemplares() > 1) {
                            dias = 15;
                        } else {
                            dias = 30;
                        }
                        user.maxRequest++;
                        obra.requisitados++;
                        user.requisicao.add(idObra);
                        registarRequisicao(nextReqID, user.getId(), obra.getId(), diaReq, dias, 0);
                        System.out.println(user.requisicao);
                        return "Obra requisitada com sucesso: " + obra.toString();
                    } else {
                        return "Chegou ao limite de requisições !";
                    }
                } else {
                    return "Esta obra é de referencia logo não a poderá requisitar";
                }

            } else if (user.getComportamento().toString().equals("FALTOSO")) {
                // Lógica para usuário faltoso
                if (!obra.getCategoria().equals("REFERENCE")) {
                    if (obra.getPreco() <= 25) {
                        if (user.maxRequest < 1) {
                            diaReq = getData();
                            dias = 2;
                            user.maxRequest++;
                            obra.requisitados++;
                            user.requisicao.add(idObra);
                            registarRequisicao(nextReqID, user.getId(), obra.getId(), diaReq, dias, 0);
                            System.out.println(user.requisicao);
                            return "Obra requisitada com sucesso: " + obra.toString();
                        } else {
                            return "Chegou ao limite de requisições !";
                        }
                    } else {
                        return "Obras com o preco superior a 25€ não lhe estão disponiveis";
                    }
                } else {
                    return "Esta obra é de referencia logo não a poderá requisitar";
                }

            } else if (user.getEstado().toString().equals("SUSPENSO")) {
                // Lógica para usuário suspenso
                return "Usuário suspenso não pode requisitar obras.";
            } else
                return "Comportamento de usuário não reconhecido.";
        }
        return "A obra já se encontra requisitada !";
    }

    public ArrayList<Obra> show_listaDeEspera(int id) {
        ArrayList<Obra> resultados = new ArrayList<>();
        if (this.users.containsKey(id)) {
            User user = this.users.get(id);
            for (Obra obra : this.obras.values()) {
                if(user.listaDeEspera.contains(obra.getId())) {
                    resultados.add(obra);
                }
            }
        }
        return resultados;
    }

    public String devolverObra(int idUser, int idObra) {
        Obra obra = this.obras.get(idObra); /** Obtém a obra com base no ID */
        if (obra == null || idObra <= 0) {
            return "Obra não encontrada";
        }

        obra.disponiveis = obra.getExemplares() - obra.requisitados;


        /**implementar a lógica com base no comportamento do utilizador */
        User user = this.users.get(idUser); /** Maneira de obter o usuário com base no número da obra requisitada */
        if (user == null) {
            return "Utilizador não encontrado.";
        }

        if (user.requisicao.contains(idObra)) {
            if(user.getMulta() > 0 ){
                user.setEstado(Estado.SUSPENSO);
            }

            /** Remover a requisição com base no idUser e idObra */
            removerRequisicao(idUser, idObra);

            /** Tornar as outras ações necessárias */
            user.maxRequest--;
            obra.requisitados--;
            user.entregue.add(idObra);
            user.requisicao.remove(Integer.valueOf(idObra));

            for (User users: this.users.values()){
                if(users.listaDeEspera.contains(idObra)){
                    user.listaDeEspera.add(Integer.valueOf(idObra));
                }
            }

            return "Obra devolvida com sucesso: " + obra.toString();
        }

        return "A Obra não foi requisitada por si";
    }


    public static int getData() {
        return data;
    }

    public void setData(int data) {
        if (data > 0) {
            this.data = data;
        }
    }

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename of the file to load
     * @throws BadEntrySpecificationException A especificação do ficheiro não é correta
     * @throws IOException                    Erro na abertura e/ou Leitura do ficheiro
     */
    void importFile(String filename) throws BadEntrySpecificationException, IOException {
        Scanner s = new Scanner(new File(filename));
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] elementos = line.split(":", 0);
            switch (elementos[0]) {
                case "USER":
                    /** Chama o método para registar um utilizador com base nos elementos do array.
                     A implementação depende dos métodos na classe Biblioteca. */
                    this.registarUser(elementos[1], elementos[2]);
                    break;
                case "BOOK":
                    /** Chama o método para registrar um livro com base nos elementos do array.
                    A implementação depende dos métodos na classe Biblioteca. */
                    this.registarBook(elementos[1], elementos[2], Integer.parseInt(elementos[3]), elementos[4], Integer.parseInt(elementos[5]), Integer.parseInt(elementos[6]), 0);
                    break;
                case "DVD":
                    /** Chama o método para registar um DVD com base nos elementos do array.
                    A implementação depende dos métodos na classe Biblioteca. */
                    this.registarDVD(elementos[1], elementos[2], Integer.parseInt(elementos[3]), elementos[4], Integer.parseInt(elementos[5]), Integer.parseInt(elementos[6]), 0);
                    break;
                default:
                    throw new BadEntrySpecificationException("Tipo de categoria desconhecido");
            }
        }
        s.close();
    }


}
