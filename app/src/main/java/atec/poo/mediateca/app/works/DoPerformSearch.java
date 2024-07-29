package atec.poo.mediateca.app.works;

import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.users.Message;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Obra;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

import java.util.ArrayList;

/**
 * Conforme Enunciado
 * 4.3.3. Pesquisar Obras
 */

/** Faz a procura das obras */
public class DoPerformSearch extends Comando<LibraryManager> {

    private LerString autor;
    private LerString titulo;

    /**
     * @param receiver
     */
    public DoPerformSearch(LibraryManager receiver) {
        super(receiver, Label.PERFORM_SEARCH);
        this.autor = new LerString("Autor da obra (ou deixe em branco para ignorar):", null);
        this.titulo = new LerString("Título da obra (ou deixe em branco para ignorar):", null);
    }

    @Override
    public final void executar() throws NoSuchWorkException {
        ui.lerInput(this.autor);
        ui.lerInput(this.titulo);

        /** Chama o método de pesquisa com base nos critérios fornecidos */
        ArrayList<Obra> obras = this.getReceptor().pesquisarObras(
                autor.getValor(),
                titulo.getValor()
        );

        if (obras.isEmpty()) {
            ui.escreveLinha("Nenhuma obra encontrada com os critérios de pesquisa fornecidos.");
        } else {
            ui.escreveLinha("Obras encontradas:");
            for (Obra obra : obras) {
                String info = null;
                try {
                    info = this.getReceptor().mostrarObra(obra.getId());
                } catch (WorkNotFoundException e) {
                    throw new RuntimeException(e);
                }
                ui.escreveLinha(info);
            }
        }
    }
}

