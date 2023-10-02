import dev.tolana.utils.Color;
import dev.tolana.utils.table.*;
public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Adventure game = new Adventure();
//        UserInterface ui = new UserInterface(game);
//        ui.start();
//        Table table = new Table("Commands", new ArrayList<String>(List.of(
//                "Command","Description")),true);
//        table.addRow(new Row().addCell("consume").addCell("consumes the consumable, giving health or an effect."));
        progressBar();
    }

    public static void progressBar() throws InterruptedException {
        String bar = " ";
        String whitespace =" ";
        //System.out.print("\u001b[?25l");
        System.out.print("│"+whitespace.repeat(20)+"│\r");
        for (int i = 0; i < 20; i++){
            Thread.sleep(300);
            System.out.print("│"+Color.bgBrightGreen(bar.repeat(i+1))+whitespace.repeat(19-i)+ "|\r");
        }
        //System.out.print("\u001b[?25h");

    }
}