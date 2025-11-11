/* Alper Eken
   aq0647
   Systemutvecklare
 */

package partyController;

import partyModel.GuestManager;

import javax.swing.*;
import java.awt.*;

public class MainProgram
{
    public static void main(String[] args)
    {
        /* Write code to read the number of guests to start with from the user by using one of
         - JOptionPane
         - Scanner and prompt
        */

        String input;
        int maxNbrOfElements = 0;

        while (true) {
            input = JOptionPane.showInputDialog("Enter how many guests that will attend: ");

            if (input == null) {
                JOptionPane.showMessageDialog(null, "Input canceled, exiting the program.");
                System.exit(0);
            }

            input.trim();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Input cannot be empty, please try again.");
                continue;
            }

            if (input.matches("\\d+")) {
                maxNbrOfElements = Integer.parseInt(input);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "The input is invalid, only numbers are available, please try again.");
            }
        }

        Controller controller = new Controller(maxNbrOfElements);
    }
}
