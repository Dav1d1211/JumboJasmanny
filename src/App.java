import BussinesLogic.JJCoordenadas;
import BussinesLogic.JJHorarios;
import BussinesLogic.JJTipoArsenal;
import DTO.GetAll;
import UserInterface.login;


public class App {
    public static void main(String[] args) throws Exception {
        login.jjLoginProcedure();

        System.out.println("\n [+] Leyendo:");
        System.out.println("\n\t -Coordenada...");
        Thread.sleep(1000);
        System.out.println("\n\t -Arsenal...");
        Thread.sleep(1000);
        System.out.println("\n\t -Horarios...");
        Thread.sleep(1000);
        System.out.println("\n [+] Guardando:");
        System.out.println("\n\t -Coordenada...");
        JJCoordenadas.jjcoordenadas();
        System.out.println("\n\t -Arsenal...");
        JJTipoArsenal.jjtipoArsenal();
        System.out.println("\n\t -Horarios...");
        JJHorarios.jjhorarios();
        GetAll.main(args);
    }
}
