package atec.poo.mediateca.core;

import atec.poo.mediateca.core.exceptions.BadEntrySpecificationException;
import atec.poo.mediateca.core.exceptions.ImportFileException;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;

import java.io.*;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryManager{

    private Biblioteca _biblioteca;

    private Map<Integer, User> users;
    private Map<Integer, Obra> obras;
    private int nextUserID;
    private int nextObraID;

    public LibraryManager() {
        this._biblioteca =new Biblioteca();
        users = new HashMap<>();
        obras = new HashMap<>();
        nextUserID = 1;
    }

    public int getData(){
        return this._biblioteca.getData();
    }

    public void setData(int dias){
        this._biblioteca.setData(dias);
    }

    /**Funcão para registar o user */
    public int registarUser(String nome, String email){
        return this._biblioteca.registarUser(nome,email);
    }

    public int registarDVD(String titulo, String autor, int preco, String categoria, int ia, int exemplares, int requisitados){
        return this._biblioteca.registarDVD(titulo, autor, preco, categoria, ia, exemplares, requisitados);
    }
    public int registarBook(String titulo, String autor, int preco, String categoria, int ia, int exemplares, int requisitados){
        return this._biblioteca.registarBook(titulo, autor, preco, categoria, ia, exemplares, requisitados);
    }

    /** Funcão para mostrar o user */
    public String mostrarUtente(int id) throws UserNotFoundException {
        return this._biblioteca.mostrarUtente(id);
    }

    public String pagar(int id) throws UserNotFoundException{
        return this._biblioteca.pagar(id);
    }

    public String mostrarObra(int id) throws WorkNotFoundException {
        return this._biblioteca.mostrarObra(id);
    }

    public String requisitarObra(int idUser, int idObra) throws WorkNotFoundException {
        return this._biblioteca.requisitarObra(idUser, idObra);
    }

    public String devolverObra(int idUser, int idObra) throws WorkNotFoundException {
        return this._biblioteca.devolverObra(idUser, idObra);
    }

    /** Função para listar users */
    public ArrayList<User> listUsers(){
        return this._biblioteca.listUsers();
    }

    public ArrayList<Obra> listObras(){
        return this._biblioteca.listObras();
    }

    public ArrayList<Requisicao> listReqs(){
        return this._biblioteca.listReqs();
    }

    public ArrayList<Obra> pesquisarObras(String autor, String titulo){
        return this._biblioteca.pesquisarObras(autor, titulo);
    }

    /**Funcão para salvar o ficheiro */
    public void save(String ficheiro) throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ficheiro)));
        oos.writeObject(this._biblioteca);
        oos.close();
    }

    public ArrayList<Obra> mostrarRequisitadas(int id) throws UserNotFoundException{
        return this._biblioteca.mostrarRequisitadas(id);
    }

    public ArrayList<Obra> mostrarEntregues(int id) throws UserNotFoundException{
        return this._biblioteca.mostrarEntregues(id);
    }

    public ArrayList<Obra> show_listaDeEspera(int id) throws UserNotFoundException{
        return this._biblioteca.show_listaDeEspera(id);
    }

    /**Funcão para carregar o ficheiro */
    public void load(String ficheiro) throws IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new BufferedInputStream(new FileInputStream(ficheiro)));
        this._biblioteca=((Biblioteca) ois.readObject());
        ois.close();
    }

    /**
     * Recebe ficheiro de entrada
     * @param datafile Ficheiro de dados
     * @throws ImportFileException A importação do ficheiro deu erro
     */
    public void importFile(String datafile) throws ImportFileException {
        try {
            this._biblioteca.importFile(datafile);
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }
    }
}
