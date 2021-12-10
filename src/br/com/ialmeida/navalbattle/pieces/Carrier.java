package br.com.ialmeida.navalbattle.pieces;

import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.navalbattle.NavalBattlePiece;
import br.com.ialmeida.navalbattle.Player;

public class Carrier extends NavalBattlePiece {
    public Carrier(Board board, Player player) {
        super(board, player);
    }

    @Override
    public String toString() {
        return "C";
    }
}
