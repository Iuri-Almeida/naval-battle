package br.com.ialmeida.application;

import br.com.ialmeida.boardgame.BoardException;
import br.com.ialmeida.navalbattle.NavalBattleMatch;
import br.com.ialmeida.navalbattle.NavalBattlePosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        NavalBattleMatch match = new NavalBattleMatch();

        UI.setupPlayerBoard(match, sc);

        while (!match.isGameEnded()) {

            try {

                UI.clearScreen();
                UI.printMatch(match);

                System.out.println();
                System.out.print("Target: ");

                NavalBattlePosition target = UI.readNavalBattlePosition(sc);

                match.performMove(target);
            } catch (BoardException | InputMismatchException e) {
                System.out.println(e.getMessage() + "\n");
                System.out.println("Click ENTER to continue.");
                sc.nextLine();
            }

        }

        sc.close();

        UI.clearScreen();
        UI.printWinner(match);

    }
}
