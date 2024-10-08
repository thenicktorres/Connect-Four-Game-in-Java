import java.util.Scanner;
import java.io.*;
public class ConnectFour {
    private char [][] board;
    private char token;
    private String turn;

    public ConnectFour(){
        board = new char[6][7];
        for(int rows = 0; rows < 6; rows++){
            for(int columns = 0; columns < 7; columns++){
                board[rows][columns] = ' ';
            }
        }

        turn = "Red";
        token = 'R';
    }

    public String getTurn() {
        return turn;
    }

    public char getToken() {
        return token;
    }

    public void nextTurn(){
        if(turn.equals("Red")){
            turn = "Yellow";
            token = 'Y';
        }else if(turn.equals("Yellow")){
            turn = "Red";
            token = 'R';
        }
    }

    public int nextAvailablePostion (int column){
        for(int rows = 5; rows >= 0; rows--){
            if(board[rows][column] == ' '){
                return rows;
            }
        }
        return -1;
    }

    public void dropPiece (int column, char token) throws ColumnFullException{
        int row = nextAvailablePostion(column);
        if(row == -1){
                throw new ColumnFullException("That column is full try again");
            }
        board[row][column] = token;
        }


    @Override
    public String toString() {
        String to_return= "  0   1   2   3   4   5  6 ";
        for(int i=0; i<6; i++) {
            to_return+="\n-----------------------------\n";
            to_return+="| ";

            for(int j=0; j<7; j++) {
                to_return += board[i][j]+" | ";
            }
        }
        to_return+="\n-----------------------------\n";
        return to_return;
    }

    public void saveGame(){
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter a filename");
        String fileName = sc.nextLine();

        try {
            File myGame = new File(fileName);
            PrintWriter myWriter = new PrintWriter(myGame);

            for (int rows = 0; rows < 6; rows++) {
                for (int columns = 0; columns < 7; columns++) {
                    if (columns == 6) {
                        myWriter.print(board[rows][columns]);
                    } else {
                        myWriter.print(board[rows][columns] + ",");
                    }
                }
                myWriter.println();
            }
            myWriter.close();
        } catch(IOException ioex){
                System.out.println(ioex.getMessage());
            }
    }
    public void loadGame(){
        Scanner sc = new Scanner (System.in);
        System.out.println("Enter a filename ");
        String filename = sc.nextLine();

        try{
            File myGame = new File (filename);
            Scanner fileScanner = new Scanner(myGame);

            int row = 0;
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String [] parts = line.split(",");
                for(int column = 0; column < parts.length; column++){
                    board [row][column] = parts[column].charAt(0);
                }
                row++;
            }
            fileScanner.close();
        } catch (IOException ioex){
            System.out.println("Unable to read that file");
        }
    }
}
