package atec.poo.mediateca.app.main;

import atec.poo.mediateca.app.exceptions.FileOpenFailedException;
import atec.poo.mediateca.core.LibraryManager;

import atec.poo.ui.Comando;
import atec.poo.ui.LerString;
import atec.poo.ui.exceptions.DialogException;

import java.io.IOException;


/**
 * Conforme enunciado
 * 4.1.2. Guardar o estado da aplicação
 */
public class DoSave extends Comando<LibraryManager> {

  private LerString ficheiro;

  /**
   * @param receiver
   */
  public DoSave(LibraryManager receiver) {
    super(receiver, Label.SAVE);
    /** Cria uma instância de LerString para solicitar o nome do ficheiro onde os dados serão salvos. */
    this.ficheiro = new LerString(Message.saveAs(), null);
  }

  @Override
  public final void executar() throws DialogException {
    /** Solicita ao utilizador o nome do ficheiro onde os dados serão salvos. */
    ui.lerInput(this.ficheiro);

    try {
      /** Chama o método save do LibraryManager para salvar os dados no ficheiro especificado. */
      this.getReceptor().save(this.ficheiro.getValor());
    } catch (IOException e) {
      /** Em caso de exceção, lança uma exceção personalizada FileOpenFailedException. */
      throw new FileOpenFailedException(this.ficheiro.getValor());
    }
  }
}