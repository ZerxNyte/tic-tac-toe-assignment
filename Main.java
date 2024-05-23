import java.util.*;

class Main {
    public static void main(String args[]) {
        Scanner jeff = new Scanner(System.in);
        Board board = new Board();
        System.out.println("Player 1 name? Your symbol is X!");
        Player player1 = new Player(jeff.nextLine(), "X");
        System.out.println("Player 2 name? Your symbol is O!");
        Player player2 = new Player(jeff.nextLine(), "O");

        Player currentPlayer = player1;
        boolean gameWon = false;
        //i am in mental agony, save my soul
        while (!gameWon) {
            board.printBoard();
            System.out.println(currentPlayer.getName() + ", enter your move (TYPE ROW, ENTER, THEN COLUMN!!): ");
            int row = jeff.nextInt();
            int col = jeff.nextInt();

            if (board.setBoard(row, col, currentPlayer.getSymbol())) {
                if (board.checkWin()) {
                    board.printBoard();
                    System.out.println("Player " + currentPlayer.getName() + " wins!!");
                    gameWon = true;
                } else if (board.isFull()) {
                    board.printBoard();
                    System.out.println("It's a draw!");
                    gameWon = true;
                } else {
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                }
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }
}
//board stuffs
class Board {
    private String[][] board = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
//my math teacher should give me an a+ for this ;-;
    public boolean setBoard(int row, int col, String symbol) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col].equals("-")) {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("-")) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("-")) {
                return true;
            }
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("-")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("-")) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }
}
// O-<-<
class Player {
    private String name;
    private String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
