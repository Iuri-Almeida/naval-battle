package br.com.ialmeida.navalbattle;

import br.com.ialmeida.application.ProgramConstants;
import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.boardgame.Position;
import br.com.ialmeida.navalbattle.pieces.*;

import java.util.Random;

public class NavalBattleMatch {

    private int turn;
    private Player currentPlayer;
    private final Board playerBoard;
    private final Board computerBoard;
    private boolean gameEnded;

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

    public boolean isGameEnded() {
        return gameEnded;
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
        makeMove(target, playerBoard);

        gameEnded = testEndOfGame(computerBoard);

        if (!gameEnded) {
            nextTurn();
        }
    }

    private void performComputerMove() {

        Random random = new Random();

        char column = (char) (random.nextInt((ProgramConstants.LAST_COLUMN + 1) - ProgramConstants.FIRST_COLUMN) + ProgramConstants.FIRST_COLUMN);
        int row = random.nextInt(ProgramConstants.ROWS) + 1;

        Position target = new NavalBattlePosition(column, row).toPosition();

        makeMove(target, computerBoard);

        gameEnded = testEndOfGame(playerBoard);
    }

    private void makeMove(Position target, Board board) {

        Board otherBoard;
        Player player;
        if (board.equals(playerBoard)) {
            otherBoard = computerBoard;
            player = Player.PERSON;
        } else {
            otherBoard = playerBoard;
            player = Player.COMPUTER;
        }

        if (otherBoard.thereIsAPiece(target) && otherBoard.piece(target) instanceof Submarine) {
            if (board.thereIsAPiece(target) && board.piece(target) instanceof Submarine) {
                board.placePieceWithoutException(new RightShotWithSubmarine(board, player), target);
            } else {
                board.placePieceWithoutException(new RightShot(board, player), target);
            }
        } else {
            if (board.thereIsAPiece(target) && (board.piece(target) instanceof Submarine || board.piece(target) instanceof WrongShotWithSubmarine)) {
                board.placePieceWithoutException(new WrongShotWithSubmarine(board, player), target);
            } else {
                board.placePieceWithoutException(new WrongShot(board, player), target);
            }
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Player.PERSON) ? Player.COMPUTER : Player.PERSON;

        performComputerMove();

        turn++;
    }

    private boolean testEndOfGame(Board opponentBoard) {

        int hits = 0;

        NavalBattlePiece[][] pieces = getPieces(opponentBoard);

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[0].length; j++) {
                NavalBattlePiece piece = pieces[i][j];
                if (piece instanceof RightShot || piece instanceof RightShotWithSubmarine) {
                    hits++;
                }
            }
        }

        return hits == 10;
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
