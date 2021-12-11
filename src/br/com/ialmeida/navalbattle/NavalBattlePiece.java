package br.com.ialmeida.navalbattle;

import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.boardgame.Piece;

public abstract class NavalBattlePiece extends Piece {
    private final Player player;

    public NavalBattlePiece(Board board, Player player) {
        super(board);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
