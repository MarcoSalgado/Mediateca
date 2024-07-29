package atec.poo.mediateca.app.works;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Obra;
import atec.poo.ui.Comando;

import java.util.ArrayList;

/**
 * 4.3.2. Listar Obras
 */
public class DoDisplayWorks extends Comando<LibraryManager> {

    /**
     * @param receiver
     */
    public DoDisplayWorks(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORKS);
    }

    @Override
    public final void executar() {
        /** Obtém a lista de utilizadores do LibraryManager. */
        ArrayList<Obra> obras=this.getReceptor().listObras();
        /** Itera sobre a lista de utilizadores e imprime as informações de cada um. */
        for (Obra o : obras) {
            ui.escreveLinha(o.toString());
        }
    }
}