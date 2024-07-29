package atec.poo.mediateca.app.works;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.app.users.Message;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;


/**
 * 4.3.1. Mostrar Obra.
 */
public class DoDisplayWork extends Comando<LibraryManager> {

    private LerInteiro id;

    /**
     * @param receiver
     */
    public DoDisplayWork(LibraryManager receiver) {
        super(receiver, Label.SHOW_WORK);
        this.id = new LerInteiro(Message.requestNumObra()); /** Cria uma instância de LerInteiro para ler o ID do utilizador. */
    }


    @Override
    public final void executar() throws NoSuchWorkException {
        ui.lerInput(this.id); /** Lê o ID da obra a partir do input da obra. */

        try {
            String info = this.getReceptor().mostrarObra(this.id.getValor()); /** Obtém as informações do utilizador a partir do gestor da biblioteca. */
            ui.escreveLinha(info); /** Escreve as informações da obra no output. */
        } catch (WorkNotFoundException e) {
            throw new NoSuchWorkException(e.getId()); /** Lança uma exceção personalizada se a obra não for encontrado. */
        }
    }

}
