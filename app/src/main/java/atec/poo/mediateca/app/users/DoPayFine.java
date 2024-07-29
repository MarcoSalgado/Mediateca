package atec.poo.mediateca.app.users;

import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.User;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.exceptions.DialogException;

import java.util.ArrayList;

/**
 * Conforme enunciado
 * 4.2.5. Pagar Multa
 */


/** função para pagar a multa */
public class DoPayFine extends Comando<LibraryManager> {

    private LerInteiro id; /** Ler o ID do utilizador a ser mostrado */
    /**
     * @param receiver
     */
    public DoPayFine(LibraryManager receiver) {
        super(receiver, Label.PAY_FINE);
        this.id = new LerInteiro(Message.requestUserId()); /** Cria uma instância de LerInteiro para ler o ID do utilizador. */

    }

    @Override
    public final void executar() throws DialogException {
        ArrayList<User> users=this.getReceptor().listUsers();
        ui.lerInput(this.id); /** Lê o ID do utilizador a partir do input do utilizador. */
        for (User user : users) {
            if (user.getId() == this.id.getValor()) {
                if(user.getMulta() > 0){
                    user.setMulta(0);
                    ui.escreveLinha("Multa paga com sucesso");
                }else{
                    ui.escreveLinha("Nao tem multas burro");
                }
            }
        }
    }

}
