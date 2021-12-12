package br.com.ialmeida.navalbattle.pieces;

import br.com.ialmeida.navalbattle.NavalBattlePiece;
import br.com.ialmeida.navalbattle.Player;

public class WrongShotWithSubmarine extends NavalBattlePiece {
    public WrongShotWithSubmarine(Player player) {
        super(player);
    }

    @Override
    public String toString() {
        return "X";
    }
}
