import java.util.*;
// Rule N°1 : Never trust the user, these functions check that the inputs are good

public class AskUserForInput {

    public static int askAnInt() {
        boolean isInputCorrect;
        int userInput = 0;
        do {
            isInputCorrect = true;
            Scanner scanner = new Scanner(System.in); 
            try {
                userInput = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Hey, you just entered a wrong Input. Is typing correctly on your keyboard to hard for you ?");
                isInputCorrect = false;
            }
            if (userInput <= 0) {
                System.out.println("Hey, you just entered a wrong Input. Is typing correctly on your keyboard to hard for you ?");
                isInputCorrect = false;
            }

        } while (!isInputCorrect);
        return userInput;
    }

    public static String askAString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
