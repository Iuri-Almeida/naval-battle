package br.com.ialmeida.navalbattle;

import br.com.ialmeida.application.ProgramConstants;
import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.boardgame.Position;
import br.com.ialmeida.navalbattle.pieces.*;

import java.util.ArrayList;
import java.util.Random;

public class NavalBattleMatch {

    private int turn;
    private Player currentPlayer;
    private final Board playerBoard;
    private final Board computerBoard;
    private boolean gameEnded;
    private final ArrayList<String> positionsAlreadyVerified = new ArrayList<>();
    private final Random random = new Random();

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

    public void performFirstMove(NavalBattlePosition targetPosition) {
        Position target = targetPosition.toPosition();
        playerBoard.placePiece(new Submarine(Player.PERSON), target);
    }

    public void performMove(NavalBattlePosition targetPosition) {
        Position target = targetPosition.toPosition();
        makeMove(target, playerBoard);

        gameEnded = testEndOfGame(playerBoard);

        if (!gameEnded) {
            nextTurn();
            performComputerMove();
        }
    }

    private void performComputerMove() {

        char column;
        int row;
        String pos;
        do {
            column = generateRandomChar();
            row = generateRandomInt();

            pos = "" + column + row;

        } while (positionsAlreadyVerified.contains(pos));

        positionsAlreadyVerified.add(pos);

        Position target = new NavalBattlePosition(column, row).toPosition();

        makeMove(target, computerBoard);

        gameEnded = testEndOfGame(computerBoard);

        if (!gameEnded) {
            nextTurn();
        }
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

        if (otherBoard.thereIsAPiece(target) && (otherBoard.piece(target) instanceof Submarine || otherBoard.piece(target) instanceof WrongShotWithSubmarine)) {
            if (board.thereIsAPiece(target) && board.piece(target) instanceof Submarine) {
                board.placePieceWithoutException(new RightShotWithSubmarine(player), target);
            } else {
                board.placePieceWithoutException(new RightShot(player), target);
            }
        } else {
            if (board.thereIsAPiece(target) && (board.piece(target) instanceof Submarine || board.piece(target) instanceof WrongShotWithSubmarine)) {
                board.placePieceWithoutException(new WrongShotWithSubmarine(player), target);
            } else {
                board.placePieceWithoutException(new WrongShot(player), target);
            }
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Player.PERSON) ? Player.COMPUTER : Player.PERSON;
    }

    private boolean testEndOfGame(Board board) {

        int hits = 0;

        NavalBattlePiece[][] pieces = getPieces(board);

        for (NavalBattlePiece[] navalBattlePieces : pieces) {
            for (int j = 0; j < pieces[0].length; j++) {
                NavalBattlePiece piece = navalBattlePieces[j];
                if (piece instanceof RightShot || piece instanceof RightShotWithSubmarine) {
                    hits++;
                }
            }
        }

        return hits == ProgramConstants.TOTAL_SUBMARINES;
    }

    private char generateRandomChar() {
        return (char) (random.nextInt((ProgramConstants.LAST_ROW + 1) - ProgramConstants.FIRST_ROW) + ProgramConstants.FIRST_ROW);
    }

    private int generateRandomInt() {
        return random.nextInt(ProgramConstants.COLUMNS) + 1;
    }

    private void placeNewPiece(char row, int column, NavalBattlePiece piece) {
        computerBoard.placePiece(piece, new NavalBattlePosition(row, column).toPosition());
    }

    private void initialSetup() {
        int i = 0;

        while (i < ProgramConstants.TOTAL_SUBMARINES) {
            char row = generateRandomChar();
            int column = generateRandomInt();

            Position position = new NavalBattlePosition(row, column).toPosition();

            if (!computerBoard.thereIsAPiece(position)) {
                placeNewPiece(row, column, new Submarine(Player.COMPUTER));
                i++;
            }
        }
    }
}
