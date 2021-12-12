package br.com.ialmeida.boardgame;

import br.com.ialmeida.application.ProgramConstants;
import br.com.ialmeida.navalbattle.NavalBattlePosition;

public class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public NavalBattlePosition toNavalBattlePosition() {

        char row = (char) (ProgramConstants.FIRST_ROW - this.row);
        int column = ProgramConstants.COLUMNS - this.column;

        return new NavalBattlePosition(row, column);
    }

    @Override
    public String toString() {
        return row + ", " + column;
    }
}
