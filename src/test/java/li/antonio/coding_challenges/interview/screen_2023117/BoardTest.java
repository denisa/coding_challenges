package li.antonio.coding_challenges.interview.screen_2023117;

import li.antonio.coding_challenges.interview.screen_2023117.Board;

class BoardTest {
    static void caseOne() {
        System.out.println("Case 1");
        Board board = new Board(new char[][]{{'.'}, {' '}});
        board.run();
        board.showResult();
    }

    static void caseTwo() {
        System.out.println("Case 2");
        Board board = new Board(new char[][]{{'.'}, {'.'}});
        board.run();
        board.showResult();
    }

    static void caseThree() {
        System.out.println("Case 3");
        Board board = new Board(new char[][]{{'.'}, {'.'}, {' '}, {':'}, {'.'}});
        board.run();
        board.showResult();
    }

    static void caseFour() {
        System.out.println("Case 4");
        Board board = new Board(new char[][]{{'.'}, {'.'}, {'-'}, {'.'}, {' '}});
        board.run();
        board.showResult();
    }

    static void caseFive() {
        System.out.println("Case 5");
        Board board = new Board(new char[][]{
                {'.', ' ', ' ', ' ', ' ', ' ', ' ', '.'},
                {':', ' ', '.', ' ', ' ', ':', ' ', ' '},
                {'-', ' ', ' ', ' ', '.', ' ', ' ', '-'},
                {'.', ' ', '-', ' ', ' ', ' ', ' ', ' '},
                {'.', ' ', ' ', ' ', ' ', '.', ' ', ' '}});
        board.run();
        board.showResult();
    }

    public static void main(String[] args) {
        caseOne();
        caseTwo();
        caseThree();
        caseFour();
        caseFive();
    }
}
