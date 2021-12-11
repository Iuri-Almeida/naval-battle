package br.com.ialmeida.navalbattle;

import br.com.ialmeida.application.ProgramConstants;
import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.boardgame.Piece;
import br.com.ialmeida.boardgame.Position;
import br.com.ialmeida.navalbattle.pieces.Submarine;

public class NavalBattleMatch {

    private int turn;
    private Player currentPlayer;
    private final Board board;
    private final Board computerBoard;

    public NavalBattleMatch() {
        board = new Board(ProgramConstants.ROWS, ProgramConstants.COLUMNS);
        computerBoard = new Board(ProgramConstants.ROWS, ProgramConstants.COLUMNS);
        turn = 1;
        currentPlayer = Player.WHITE;

        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Board getComputerBoard() {
        return computerBoard;
    }

    public NavalBattlePiece[][] getPieces(Board board) {

        int rows = board.getRows();
        int columns = board.getColumns();

        NavalBattlePiece[][] pieces = new NavalBattlePiece[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pieces[i][j] = (NavalBattlePiece) board.piece(i, j);
            }
        }

        return pieces;
    }

    public boolean[][] possibleMoves() {
        return board.possibleMoves();
    }

    public void performMove(NavalBattlePosition targetPosition) {
        Position target = targetPosition.toPosition();
        makeMove(target);
        nextTurn();
    }

    private void makeMove(Position target) {
        Piece piece = (currentPlayer == Player.WHITE) ? new Submarine(board, Player.WHITE) : new Submarine(board, Player.BLACK);
        board.placePiece(piece, target);
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Player.WHITE) ? Player.BLACK : Player.WHITE;
    }

    private void placeNewPiece(char column, int row, NavalBattlePiece piece) {
        computerBoard.placePiece(piece, new NavalBattlePosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Submarine(computerBoard, Player.BLACK));
        placeNewPiece('b', 2, new Submarine(computerBoard, Player.BLACK));
        placeNewPiece('c', 3, new Submarine(computerBoard, Player.BLACK));
        placeNewPiece('d', 4, new Submarine(computerBoard, Player.BLACK));
        placeNewPiece('e', 5, new Submarine(computerBoard, Player.BLACK));
        placeNewPiece('f', 6, new Submarine(computerBoard, Player.BLACK));
        placeNewPiece('g', 7, new Submarine(computerBoard, Player.BLACK));
        placeNewPiece('h', 8, new Submarine(computerBoard, Player.BLACK));
        placeNewPiece('i', 9, new Submarine(computerBoard, Player.BLACK));
        placeNewPiece('j', 10, new Submarine(computerBoard, Player.BLACK));
    }
}
