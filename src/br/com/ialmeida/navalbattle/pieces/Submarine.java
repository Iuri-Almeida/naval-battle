package br.com.ialmeida.navalbattle.pieces;

import br.com.ialmeida.navalbattle.NavalBattlePiece;
import br.com.ialmeida.navalbattle.Player;

public class Submarine extends NavalBattlePiece {
    public Submarine(Player player) {
        super(player);
    }

    @Override
    public String toString() {
        return "N";
    }
}
