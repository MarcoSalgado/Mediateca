package atec.poo.mediateca.core;


/** Class do livro */
class Book extends Obra {

    public Book(String titulo, String autor, int preco, String categoria, int ia, int exemplares, int requisitados) {
        super(titulo, autor, preco, categoria, ia, exemplares, requisitados);
    }

    public String getTipo() {
        return "Livro";
    }
}




