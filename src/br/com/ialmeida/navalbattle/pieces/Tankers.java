package br.com.ialmeida.navalbattle.pieces;

import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.navalbattle.NavalBattlePiece;
import br.com.ialmeida.navalbattle.Player;

public class Tankers extends NavalBattlePiece {
    public Tankers(Board board, Player player) {
        super(board, player);
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] possibleMoves() {
        return new boolean[getBoard().getRows()][getBoard().getColumns()];
    }
}
