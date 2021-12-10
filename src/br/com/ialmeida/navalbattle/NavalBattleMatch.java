package br.com.ialmeida.navalbattle;

import br.com.ialmeida.application.ProgramConstants;
import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.boardgame.Position;
import br.com.ialmeida.navalbattle.pieces.Carrier;
import br.com.ialmeida.navalbattle.pieces.Tankers;

public class NavalBattleMatch {
    private final Board board;

    public NavalBattleMatch() {
        board = new Board(ProgramConstants.ROWS, ProgramConstants.COLUMNS);

        initialSetup();
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

    private void placeNewPiece(char column, int row, NavalBattlePiece piece) {
        board.placePiece(piece, new NavalBattlePosition(column, row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Carrier(board, Player.BLACK));
        placeNewPiece('a', 2, new Carrier(board, Player.BLACK));
        placeNewPiece('a', 3, new Carrier(board, Player.BLACK));
        placeNewPiece('a', 4, new Carrier(board, Player.BLACK));
        placeNewPiece('a', 5, new Carrier(board, Player.BLACK));

        placeNewPiece('f', 5, new Tankers(board, Player.BLACK));
        placeNewPiece('f', 6, new Tankers(board, Player.BLACK));
        placeNewPiece('f', 7, new Tankers(board, Player.BLACK));
        placeNewPiece('f', 8, new Tankers(board, Player.BLACK));
    }
}
