package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

/**
 * Este é um comando que implementa a funcionalidade de "Mostrar Utente" conforme o enunciado.
 */
public class DoShowUser extends Comando<LibraryManager> {

    private LerInteiro id; // Ler o ID do utilizador a ser mostrado

    /**
     * Construtor que recebe o gestor da biblioteca como argumento.
     *
     * @param receiver O gestor da biblioteca.
     */
    public DoShowUser(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER); /** Chama o construtor da classe base com o rótulo apropriado. */
        this.id = new LerInteiro(Message.requestUserId()); /** Cria uma instância de LerInteiro para ler o ID do utilizador. */
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id); /** Lê o ID do utilizador a partir do input do utilizador. */

        try {
            String info = this.getReceptor().mostrarUtente(this.id.getValor()); /** Obtém as informações do utilizador a partir do gestor da biblioteca. */
            ui.escreveLinha(info); /** Escreve as informações do utilizador no output. */
        } catch (UserNotFoundException e) {
            throw new NoSuchUserException(e.getId()); /** Lança uma exceção personalizada se o utilizador não for encontrado. */
        }
    }
}
