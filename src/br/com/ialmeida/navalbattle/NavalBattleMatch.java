package br.com.ialmeida.navalbattle;

import br.com.ialmeida.application.ProgramConstants;
import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.boardgame.Piece;
import br.com.ialmeida.boardgame.Position;
import br.com.ialmeida.navalbattle.pieces.*;

public class NavalBattleMatch {

    private int turn;
    private Player currentPlayer;
    private final Board playerBoard;
    private final Board computerBoard;

    public NavalBattleMatch() {
        playerBoard = new Board(ProgramConstants.ROWS, ProgramConstants.COLUMNS);
        computerBoard = new Board(ProgramConstants.ROWS, ProgramConstants.COLUMNS);
        turn = 1;
        currentPlayer = Player.PERSON;

        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getPlayerBoard() {
        return playerBoard;
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
        return playerBoard.possibleMoves();
    }

    public void performFirstMove(NavalBattlePosition targetPosition) {
        Position target = targetPosition.toPosition();
        playerBoard.placePiece(new Submarine(playerBoard, Player.PERSON), target);
    }

    public void performMove(NavalBattlePosition targetPosition) {
        Position target = targetPosition.toPosition();
        makeMove(target);
        nextTurn();
    }

    private void makeMove(Position target) {
        if (computerBoard.thereIsAPiece(target)) {
            if (playerBoard.thereIsAPiece(target)) {
                playerBoard.placePieceWithoutException(new RightShotWithSubmarine(playerBoard, Player.PERSON), target);
            } else {
                playerBoard.placePieceWithoutException(new RightShot(playerBoard, Player.PERSON), target);
            }
        } else {
            if (playerBoard.thereIsAPiece(target)) {
                playerBoard.placePieceWithoutException(new WrongShotWithSubmarine(playerBoard, Player.PERSON), target);
            } else {
                playerBoard.placePieceWithoutException(new WrongShot(playerBoard, Player.PERSON), target);
            }
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Player.PERSON) ? Player.COMPUTER : Player.PERSON;
    }

    private void placeNewPiece(char column, int row, NavalBattlePiece piece) {
        computerBoard.placePiece(piece, new NavalBattlePosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Submarine(computerBoard, Player.COMPUTER));
        placeNewPiece('b', 2, new Submarine(computerBoard, Player.COMPUTER));
        placeNewPiece('c', 3, new Submarine(computerBoard, Player.COMPUTER));
        placeNewPiece('d', 4, new Submarine(computerBoard, Player.COMPUTER));
        placeNewPiece('e', 5, new Submarine(computerBoard, Player.COMPUTER));
        placeNewPiece('f', 6, new Submarine(computerBoard, Player.COMPUTER));
        placeNewPiece('g', 7, new Submarine(computerBoard, Player.COMPUTER));
        placeNewPiece('h', 8, new Submarine(computerBoard, Player.COMPUTER));
        placeNewPiece('i', 9, new Submarine(computerBoard, Player.COMPUTER));
        placeNewPiece('j', 10, new Submarine(computerBoard, Player.COMPUTER));
    }
}
