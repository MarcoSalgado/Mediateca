package atec.poo.mediateca.app.requests;

import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.users.Message;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;


/**
 * Conforme enunciado
 * 4.4.1. Rquisitar uma obra
 */
public class DoRequestWork extends Comando<LibraryManager> {

    private LerInteiro idUser; // Ler o ID do utilizador a ser mostrado
    private LerInteiro idObra;

    /**
     * @param receiver
     */

    /** Pede o userID e o numero da obra para fazer um request */
    public DoRequestWork(LibraryManager receiver) {
        super(receiver, Label.REQUEST_WORK);
        this.idUser = new LerInteiro(Message.requestUserId());
        this.idObra = new LerInteiro(Message.requestNumObra());
    }


    @Override
    public final void executar() throws DialogException {
        ui.lerInput(idUser);
        ui.lerInput(idObra);
        try {
            String info = this.getReceptor().requisitarObra(this.idUser.getValor(), this.idObra.getValor());
            System.out.println(info); /** Exibe as informações da obra ou mensagem de erro */
        } catch (WorkNotFoundException e) {
            throw new NoSuchWorkException(e.getId()); /** Lança uma exceção personalizada se a obra não for encontrado. */
        }


//

    }
}
