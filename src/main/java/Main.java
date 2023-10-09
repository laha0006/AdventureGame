import game.Adventure;
import ui.UserInterface;
public class Main {
    public static void main(String[] args) {
        Adventure game = new Adventure();
        UserInterface ui = new UserInterface(game);
        ui.start();
    }
}