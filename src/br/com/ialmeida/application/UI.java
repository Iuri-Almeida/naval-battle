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

                System.out.print(((piece == null) ? "-" : piece) + " ");

            }

            System.out.println();
        }

        System.out.println("  a b c d e f g h i j");

    }
}
