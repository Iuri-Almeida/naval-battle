package br.com.ialmeida.application;

import br.com.ialmeida.boardgame.BoardException;
import br.com.ialmeida.navalbattle.NavalBattleMatch;
import br.com.ialmeida.navalbattle.NavalBattlePiece;
import br.com.ialmeida.navalbattle.NavalBattlePosition;
import br.com.ialmeida.navalbattle.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static NavalBattlePosition readNavalBattlePosition(Scanner sc) {

        try {
            String s = sc.nextLine();

            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));

            return new NavalBattlePosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to j10.");
        }

    }

    public static void setupPlayerBoard(NavalBattleMatch match, Scanner sc) {

        int i = 0;

        while (i < ProgramConstants.TOTAL_SUBMARINES) {
            try {
                clearScreen();
                System.out.println("Player Initial Setup");
                printBoard(match.getPieces(match.getPlayerBoard()));

                System.out.println();
                System.out.print("Target: ");

                NavalBattlePosition target = UI.readNavalBattlePosition(sc);

                match.performFirstMove(target);

                i++;
            } catch (BoardException | InputMismatchException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Click ENTER to continue.");
                sc.nextLine();
            }
        }
    }

    public static void printWinner(NavalBattleMatch match) {
        printBoard(match.getPieces(match.getPlayerBoard()));
        System.out.println();
        printBoard(match.getPieces(match.getComputerBoard()));

        Player winner = match.getCurrentPlayer();
        String color = (winner == Player.PERSON) ? ProgramConstants.PERSON_PIECE_COLOR : ProgramConstants.COMPUTER_PIECE_COLOR;

        System.out.println("\nWinner: " + color + winner + ProgramConstants.RESET_COLOR);
    }

    public static void printMatch(NavalBattleMatch match) {

        printBoard(match.getPieces(match.getPlayerBoard()));

        System.out.println();
        System.out.println("Turn: " + match.getTurn());
    }

    private static void printBoard(NavalBattlePiece[][] pieces) {

        int rows = pieces.length;
        int columns = pieces[0].length;

        for (int i = 0; i < rows; i++) {

            System.out.printf("%s%2d %s", ProgramConstants.INDICATORS_COLOR, rows - i, ProgramConstants.RESET_COLOR);

            for (int j = 0; j < columns; j++) {

                printPiece(pieces[i][j], false);

            }

            System.out.println();
        }

        System.out.println(ProgramConstants.INDICATORS_COLOR + "   a b c d e f g h i j" + ProgramConstants.RESET_COLOR);

    }

    private static void printBoard(NavalBattlePiece[][] pieces, boolean[][] possibleMoves) {

        int rows = pieces.length;
        int columns = pieces[0].length;

        for (int i = 0; i < rows; i++) {

            System.out.printf("%s%2d %s", ProgramConstants.INDICATORS_COLOR, rows - i, ProgramConstants.RESET_COLOR);

            for (int j = 0; j < columns; j++) {

                printPiece(pieces[i][j], possibleMoves[i][j]);

            }

            System.out.println();
        }

        System.out.println(ProgramConstants.INDICATORS_COLOR + "   a b c d e f g h i j" + ProgramConstants.RESET_COLOR);

    }

    private static void printPiece(NavalBattlePiece piece, boolean background) {

        if (background) {
            System.out.print(ProgramConstants.BACKGROUND_COLOR);
        }

        if (piece == null) {
            System.out.print("-" + ProgramConstants.RESET_COLOR);
        } else {

            if (piece.getPlayer() == Player.PERSON) {
                System.out.print(ProgramConstants.PERSON_PIECE_COLOR + piece + ProgramConstants.RESET_COLOR);
            } else {
                System.out.print(ProgramConstants.COMPUTER_PIECE_COLOR + piece + ProgramConstants.RESET_COLOR);
            }

        }

        System.out.print(" ");
    }
}
