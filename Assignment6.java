import java.util.Scanner;
public class Assignment6 {
    public static void main(String[] args){
        Scanner sc = new Scanner (System.in);
        ConnectFour connectFour = new ConnectFour();

        int answer;
        do {
            System.out.println(connectFour);
            System.out.println("Which column would " + connectFour.getTurn() + " like to go in (7 to save, 8 to load, 9 to quit)");
            answer = sc.nextInt();

            switch (answer) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    try {
                        connectFour.dropPiece(answer, connectFour.getToken());
                        connectFour.nextTurn();
                    } catch (ColumnFullException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    connectFour.saveGame();
                    break;
                case 8:
                    connectFour.loadGame();
                    break;
                case 9:
                    break;
                    default:
                    System.out.println("Invalid input. Please try again.");
            }
        }while(answer!=9);
    }
}