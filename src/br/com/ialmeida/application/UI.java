package br.com.ialmeida.application;

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

    public static void printMatch(NavalBattleMatch match) {
        System.out.println();
        System.out.println("Turn: " + match.getTurn());
        System.out.println("Waiting player: " + match.getCurrentPlayer() + "\n");
    }

    public static void printBoard(NavalBattlePiece[][] pieces) {

        int rows = pieces.length;
        int columns = pieces[0].length;

        for (int i = 0; i < rows; i++) {

            System.out.print((rows - i) + " ");

            for (int j = 0; j < columns; j++) {

                NavalBattlePiece piece = pieces[i][j];

                printPiece(pieces[i][j], false);

            }

            System.out.println();
        }

        System.out.println("  a b c d e f g h i j");

    }

    public static void printBoard(NavalBattlePiece[][] pieces, boolean[][] possibleMoves) {

        int rows = pieces.length;
        int columns = pieces[0].length;

        for (int i = 0; i < rows; i++) {

            System.out.print(String.format("%2d", rows - i) + " ");

            for (int j = 0; j < columns; j++) {

                NavalBattlePiece piece = pieces[i][j];

                printPiece(pieces[i][j], possibleMoves[i][j]);

            }

            System.out.println();
        }

        System.out.println("  a b c d e f g h i j");

    }

    private static void printPiece(NavalBattlePiece piece, boolean background) {

        if (background) {
            System.out.print(ProgramConstants.BACKGROUND_COLOR);
        }

        if (piece == null) {
            System.out.print("-" + ProgramConstants.RESET_COLOR);
        } else {

            if (piece.getPlayer() == Player.WHITE) {
                System.out.print(ProgramConstants.WHITE_PIECE_COLOR + piece + ProgramConstants.RESET_COLOR);
            } else {
                System.out.print(ProgramConstants.BLACK_PIECE_COLOR + piece + ProgramConstants.RESET_COLOR);
            }

        }

        System.out.print(" ");
    }
}
