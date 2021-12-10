package br.com.ialmeida.navalbattle;

import br.com.ialmeida.boardgame.Board;

public class NavalBattleMatch {
    private final Board board;

    public NavalBattleMatch() {
        board = new Board(10, 10);
    }

    public NavalBattlePiece[][] getPieces() {

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
}
