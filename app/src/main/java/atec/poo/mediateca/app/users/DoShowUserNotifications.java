package atec.poo.mediateca.app.users;

import atec.poo.mediateca.app.exceptions.NoSuchUserException;
import atec.poo.mediateca.app.exceptions.NoSuchWorkException;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Obra;
import atec.poo.mediateca.core.User;
import atec.poo.mediateca.core.exceptions.UserNotFoundException;
import atec.poo.mediateca.core.exceptions.WorkNotFoundException;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

import java.util.ArrayList;

/**
 * Conforme enunciado
 * 4.2.3. Mostrar Notificações do Utente
 */
public class DoShowUserNotifications extends Comando<LibraryManager> {

    private LerInteiro id;

    /**
     * @param receiver
     */
    public DoShowUserNotifications(LibraryManager receiver) {
        super(receiver, Label.SHOW_USER_NOTIFICATIONS);
        this.id = new LerInteiro(Message.requestUserId()); /** Cria uma instância de LerInteiro para ler o ID do utilizador. */
    }

    @Override
    public final void executar() throws DialogException {
        ui.lerInput(this.id);
          try {
              ArrayList<Obra> obrareq = this.getReceptor().mostrarRequisitadas(this.id.getValor());
              if(!obrareq.isEmpty()) {
                  String reqString = "Requisitadas: \n";
                  for (Obra o : obrareq) {
                      reqString += o + "\n";
                  }
                  ui.escreveLinha(reqString);
              }

              ArrayList<Obra> obraent = this.getReceptor().mostrarEntregues(this.id.getValor());
              if(!obraent.isEmpty()) {
                  String entString = "Devolvidas: \n";
                  for (Obra o : obraent) {
                      entString += o + "\n";
                  }
                  ui.escreveLinha(entString);
              }

              ArrayList<Obra> obraShowEspera = this.getReceptor().show_listaDeEspera(this.id.getValor());
              if(!obraShowEspera.isEmpty()) {
                  String obraEspera = "Obras Disponiveis: \n";
                  for (Obra o : obraShowEspera) {
                      obraEspera += o + "\n";
                  }
                  ui.escreveLinha(obraEspera);
              }

              if (obrareq.isEmpty() && obraent.isEmpty() && obraShowEspera.isEmpty()){
                  ui.escreveLinha(Message.FeedBack());
              }


          } catch (UserNotFoundException e) {
            throw new NoSuchUserException(e.getId()); /** Lança uma exceção personalizada se o utilizador não for encontrado. */
          }

    }

}
