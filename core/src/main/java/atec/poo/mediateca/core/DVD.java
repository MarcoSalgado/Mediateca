package atec.poo.mediateca.core;

class DVD extends Obra {
    public DVD(String titulo, String autor, int preco, String categoria, int ia, int exemplares, int requisitados) {
        super(titulo, autor, preco, categoria, ia, exemplares, requisitados);
    }

    public String getTipo() {
        return "DVD";
    }
}
