package br.com.ialmeida.navalbattle;

import br.com.ialmeida.application.ProgramConstants;
import br.com.ialmeida.boardgame.Position;

public class NavalBattlePosition {

    private char row;
    private int column;

    public NavalBattlePosition(char row, int column) {

        if (row < ProgramConstants.FIRST_COLUMN || row > ProgramConstants.LAST_COLUMN ||
                column < 0 || column > ProgramConstants.COLUMNS) {
            throw new NavalBattleException("Error instantiating ChessPosition. Valid values are from a0 to j9.");
        }

        this.row = row;
        this.column = column;
    }

    public char getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Position toPosition() {

        int row = this.row - ProgramConstants.FIRST_COLUMN;
        int column = ProgramConstants.COLUMNS - this.column;

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
