package br.com.ialmeida.navalbattle;

import br.com.ialmeida.application.ProgramConstants;
import br.com.ialmeida.boardgame.Position;

public class NavalBattlePosition {

    private final char row;
    private final int column;

    public NavalBattlePosition(char row, int column) {

        if (row < ProgramConstants.FIRST_ROW || row > ProgramConstants.LAST_ROW ||
                column < 0 || column > ProgramConstants.COLUMNS) {
            throw new NavalBattleException("Error instantiating Position. Valid values are from a0 to j9.");
        }

        this.row = row;
        this.column = column;
    }

    public Position toPosition() {

        int row = this.row - ProgramConstants.FIRST_ROW;
        int column = ProgramConstants.COLUMNS - this.column;

        return new Position(row, column);
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
