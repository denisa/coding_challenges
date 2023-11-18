package li.antonio.coding_challenges.interview.screen_2023117;

class Board {
    char[][] board;

    Board(char[][] board) {
        this.board = board;
    }

    void showResult() {
        System.out.println("---");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("---");
    }

    void run() {
        for (int j = 0; j < board[0].length; j++) {
            int floor = board.length - 1;
            for (int i = board.length - 1; i >= 0; i--) {
                if (board[i][j] == ':') {
                    board[i][j] = ' ';
                    if (board[floor][j] == ' ') {
                        board[floor][j] = ':';
                        floor--;
                    } else if (board[floor][j] == '.') {
                        board[floor][j] = ':';
                        floor--;
                        board[floor][j] = '.';
                    } else {
                        throw new IllegalStateException("Unexpected target cell >" + board[floor][j] + "<");
                    }
                } else if (board[i][j] == '-') {
                    floor = i - 1;
                } else if (board[i][j] == ' ') { /* keep going */} else if (board[i][j] == '.') {
                    board[i][j] = ' ';
                    if (board[floor][j] == ' ') {
                        board[floor][j] = '.';
                    } else if (board[floor][j] == '.') {
                        board[floor][j] = ':';
                        floor--;
                    } else {
                        throw new IllegalStateException("Unexpected target cell >" + board[floor][j] + "<");
                    }
                } else {
                    throw new IllegalStateException("Unexpected source cell");
                }
            }
        }
    }
}
