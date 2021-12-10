package br.com.ialmeida.application;

import br.com.ialmeida.navalbattle.NavalBattleMatch;
import br.com.ialmeida.navalbattle.Player;

public class Program {

    public static void main(String[] args) {
        NavalBattleMatch match = new NavalBattleMatch();
        UI.printBoard(match.getPieces(), Player.WHITE);
        System.out.println();
        UI.printBoard(match.getPieces(), Player.BLACK);
    }
}
