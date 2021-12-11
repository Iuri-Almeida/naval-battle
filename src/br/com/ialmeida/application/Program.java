package br.com.ialmeida.application;

import br.com.ialmeida.navalbattle.NavalBattleMatch;
import br.com.ialmeida.navalbattle.NavalBattlePosition;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        NavalBattleMatch match = new NavalBattleMatch();

        while (true) {

            UI.printBoard(match.getPieces());

            System.out.println();
            System.out.print("Target: ");

            NavalBattlePosition target = UI.readNavalBattlePosition(sc);

            match.performMove(target);

        }


//        sc.close();
    }
}
