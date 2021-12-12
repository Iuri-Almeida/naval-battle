package br.com.ialmeida.navalbattle;

import br.com.ialmeida.boardgame.Piece;

public class NavalBattlePiece extends Piece {
    private final Player player;

    public NavalBattlePiece(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
