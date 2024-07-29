package atec.poo.mediateca.app.main;

import atec.poo.mediateca.core.Biblioteca;
import atec.poo.mediateca.core.LibraryManager;
import atec.poo.mediateca.core.Requisicao;
import atec.poo.mediateca.core.User;
import atec.poo.ui.Comando;
import atec.poo.ui.LerInteiro;

import java.util.ArrayList;
import java.util.List;

/**
 * Conforme Enunciado
 * 4.1.4. Avançar data atual
 */
public class DoAdvanceDate extends Comando<LibraryManager> {

  private LerInteiro dias;
  private int multa;

  private int soma = 0;

  private int multa2 = 0;
  int total = 0;
  /**
   * @param receiver
   */
  public DoAdvanceDate(LibraryManager receiver) {
    super( receiver,Label.ADVANCE_DATE);
    this.dias=new LerInteiro(Message.requestDaysToAdvance());
  }


  @Override
  /** Avança os dias e verifica se já passou do dia de entrega, para aplicar a multa. */

  public final void executar(){
    User userCorrespondente = null;
    ui.lerInput(this.dias);
    this.getReceptor().setData(this.dias.getValor() + getReceptor().getData());

    ArrayList<Requisicao> reqs=this.getReceptor().listReqs();
    ArrayList<User> users=this.getReceptor().listUsers();
    for (Requisicao r : reqs) {
      for (User user : users) {
        if (user.getId() == r.getUser()) {
          userCorrespondente = user;
          break;
        }
      }
      r.setDiasEntrega(r.getDiasEntrega() - dias.getValor());
      if(r.getDiasEntrega() < 0){
        multa = (5 * (r.getDiasEntrega() * (-1)));
        r.setMulta(multa);
        total = r.getMulta() + total;
        userCorrespondente.setMulta(total);
      }
      ui.escreveLinha(r.toString());

    }
    total = 0;
}
  
}
