package br.com.ialmeida.navalbattle;

import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.boardgame.Position;
import br.com.ialmeida.navalbattle.pieces.Carrier;
import br.com.ialmeida.navalbattle.pieces.Tankers;

public class NavalBattleMatch {
    private final Board board;

    public NavalBattleMatch() {
        board = new Board(10, 10);

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

    private void initialSetup() {
        board.placePiece(new Carrier(board, Player.BLACK), new Position(1, 1));
        board.placePiece(new Carrier(board, Player.BLACK), new Position(2, 1));
        board.placePiece(new Carrier(board, Player.BLACK), new Position(3, 1));
        board.placePiece(new Carrier(board, Player.BLACK), new Position(4, 1));
        board.placePiece(new Carrier(board, Player.BLACK), new Position(5, 1));

        board.placePiece(new Tankers(board, Player.WHITE), new Position(5, 3));
        board.placePiece(new Tankers(board, Player.WHITE), new Position(5, 4));
        board.placePiece(new Tankers(board, Player.WHITE), new Position(5, 5));
        board.placePiece(new Tankers(board, Player.WHITE), new Position(5, 6));
    }
}
