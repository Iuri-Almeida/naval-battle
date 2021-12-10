package br.com.ialmeida.navalbattle;

import br.com.ialmeida.application.ProgramConstants;
import br.com.ialmeida.boardgame.Position;

public class NavalBattlePosition {

    private int row;
    private char column;

    public NavalBattlePosition(char column, int row) {

        if (column < ProgramConstants.FIRST_COLUMN || column > ProgramConstants.LAST_COLUMN ||
                row < 0 || row > ProgramConstants.ROWS) {
            throw new NavalBattleException("Error instantiating ChessPosition. Valid values are from a0 to j9.");
        }

        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }

    public Position toPosition() {

        int row = ProgramConstants.ROWS - this.row;
        int column = this.column - ProgramConstants.FIRST_COLUMN;

        return new Position(row, column);
    }

    public static NavalBattlePosition fromPosition(Position position) {

        int row = ProgramConstants.ROWS - position.getRow();
        char column = (char) (ProgramConstants.FIRST_COLUMN - position.getColumn());

        return new NavalBattlePosition(column, row);
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
