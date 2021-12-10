package br.com.ialmeida.application;

import br.com.ialmeida.navalbattle.NavalBattlePiece;
import br.com.ialmeida.navalbattle.Player;

public class UI {

    public static void printBoard(NavalBattlePiece[][] pieces, Player player) {

        int rows = pieces.length;
        int columns = pieces[0].length;

        System.out.println("Board for " + player);

        for (int i = 0; i < rows; i++) {

            System.out.print((rows - i - 1) + " ");

            for (int j = 0; j < columns; j++) {

                NavalBattlePiece piece = pieces[i][j];

                printPiece(pieces[i][j]);

            }

            System.out.println();
        }

        System.out.println("  a b c d e f g h i j");

    }

    private static void printPiece(NavalBattlePiece piece) {
        if (piece == null) {
            System.out.print("-");
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
