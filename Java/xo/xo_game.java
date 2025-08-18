package Java.xo;
import java.util.Scanner;

public class xo_game {

    public static char X,O;
    public static int row,column;
    public static char[][] board = new char[3][3];
    // public static boolean win = false;

    public xo_game() {
            for(row = 0; row < 3; row++) {
                for(column = 0; column < 3; column++) {
                    board[row][column] = '_';
                }            
            }
            printBoard();
    }

    public void printBoard() {
        for(row = 0; row < 3; row++) {
            for(column = 0; column < 3; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkWin() {
        int i;
        //checking rows and columns
        for (i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '_') {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '_') {
                return true;
            }
        }

        //checking diagnal

        // "\"
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '_') {
            return true;
        }
        // "/"
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '_') {
            return true;
        }

        return false;
    }

    public boolean checkDraw() {

        //checking a draw
        int count = 0;
        for (row = 0; row < 3; row++) {
            for (column = 0; column < 3; column++) {
                if (board[row][column] != '_') {
                    count++;
                }
            }
        }
        if (count == 9) {
            return true;
        }

        return false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter row, column, and symbol (e.g., 1 2 X)");
            String line = scanner.nextLine();
            String[] parts = line.trim().split("\\s+");
            if (parts.length != 3) {
                System.out.println("Invalid input. Please enter row, column, and symbol.");
                continue;
            }
            try {
                int r = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                char symbol = parts[2].toUpperCase().charAt(0); //ใช้ charAt เพื่อกันผู้ใช้พิมมาหลายตัวเช่น XX, OO

                if (r < 0 || r > 2 || c < 0 || c > 2) {
                    System.out.println("Invalid position. Try again.");
                    continue;
                }

                if (board[r][c] != '_') {
                    System.out.println("Cell already taken. Try agin.");
                    continue;
                }

                if (symbol != 'X' && symbol != 'O') {
                    System.out.println("Invalid symbol. Tryagain");
                    continue;
                }

                board[r][c] = symbol;
                printBoard();
            } catch (Exception e) {
                System.out.println("Invalid input format. Try again.");
            }

            checkWin();
            if (checkWin() == true) {
                System.out.println("You Win. Congratulation!");
                break;
            }
            checkDraw();
            if (checkDraw() == true) {
                System.out.println("Draw.");
                break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the X/O game.");
        xo_game game = new xo_game();
        game.start();
    }   
}