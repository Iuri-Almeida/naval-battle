package br.com.ialmeida.application;

import br.com.ialmeida.boardgame.BoardException;
import br.com.ialmeida.navalbattle.NavalBattleMatch;
import br.com.ialmeida.navalbattle.NavalBattlePiece;
import br.com.ialmeida.navalbattle.NavalBattlePosition;
import br.com.ialmeida.navalbattle.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    private static final char[] NUMBER_ROWS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static NavalBattlePosition readNavalBattlePosition(Scanner sc) {

        try {
            String s = sc.nextLine();

            char row = s.toLowerCase().charAt(0);
            int column = 10 - Integer.parseInt(s.substring(1));

            return new NavalBattlePosition(row, column);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error reading Position. Valid values are from a0 to j9.");
        }

    }

    public static void setupPlayerBoard(NavalBattleMatch match, Scanner sc) {

        int i = 0;

        while (i < ProgramConstants.TOTAL_SUBMARINES) {
            try {
                clearScreen();
                System.out.println("Player Initial Setup");
                printBoard(match.getPieces(match.getPlayerBoard()), ProgramConstants.PLAYER, ProgramConstants.PERSON_PIECE_COLOR);

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
        printBoard(match.getPieces(match.getPlayerBoard()), ProgramConstants.PLAYER, ProgramConstants.PERSON_PIECE_COLOR);
        System.out.println();
        printBoard(match.getPieces(match.getComputerBoard()), ProgramConstants.COMPUTER, ProgramConstants.COMPUTER_PIECE_COLOR);

        Player winner = match.getCurrentPlayer();
        String color = (winner == Player.PERSON) ? ProgramConstants.PERSON_PIECE_COLOR : ProgramConstants.COMPUTER_PIECE_COLOR;

        System.out.println("\nWinner: " + color + winner + ProgramConstants.RESET_COLOR);
    }

    public static void printMatch(NavalBattleMatch match) {

        printBoard(match.getPieces(match.getPlayerBoard()), ProgramConstants.PLAYER, ProgramConstants.PERSON_PIECE_COLOR);

        System.out.println();
        System.out.println("Turn: " + match.getTurn());
    }

    private static void printBoard(NavalBattlePiece[][] pieces, String title, String color) {

        int rows = pieces.length;
        int columns = pieces[0].length;

        printTitle(title, color);

        System.out.println(ProgramConstants.INDICATORS_COLOR + "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |" + ProgramConstants.RESET_COLOR);

        for (int i = 0; i < rows; i++) {

            printLine(ProgramConstants.INDICATORS_COLOR);

            System.out.printf("%s| %s |%s ", ProgramConstants.INDICATORS_COLOR, NUMBER_ROWS[i], ProgramConstants.RESET_COLOR);

            for (int j = 0; j < columns; j++) {

                printPiece(pieces[i][j]);

            }

            System.out.println();
        }

        printLine(ProgramConstants.INDICATORS_COLOR);

    }

    private static void printPiece(NavalBattlePiece piece) {

        if (piece == null) {
            System.out.print(ProgramConstants.INDICATORS_COLOR + "  |" + ProgramConstants.RESET_COLOR);
        } else {

            if (piece.getPlayer() == Player.PERSON) {
                System.out.print(ProgramConstants.PERSON_PIECE_COLOR + piece + ProgramConstants.INDICATORS_COLOR + " |" + ProgramConstants.RESET_COLOR);
            } else {
                System.out.print(ProgramConstants.COMPUTER_PIECE_COLOR + piece + ProgramConstants.INDICATORS_COLOR + " |" + ProgramConstants.RESET_COLOR);
            }

        }

        System.out.print(" ");
    }

    private static void printTitle(String title, String color) {

        int titleLength = title.length();
        int numberOfSpaces = (45 - titleLength) / 2;

        printLine(color);
        printSpaces(numberOfSpaces);
        System.out.print(color + title + ProgramConstants.RESET_COLOR);
        printSpaces(numberOfSpaces);
        System.out.println();
        printLine(color);
    }

    private static void printLine(String color) {
        System.out.println(color + "---------------------------------------------" + ProgramConstants.RESET_COLOR);
    }

    private static void printSpaces(int numberOfSpaces) {
        for (int i = 0; i < numberOfSpaces; i++) {
            System.out.print(" ");
        }
    }
}
