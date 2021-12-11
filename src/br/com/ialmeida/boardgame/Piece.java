package br.com.ialmeida.boardgame;

public class Piece {
    protected Position position;
    private final Board board;

    public Piece(Board board) {
        this.board = board;
        this.position = null;
    }

    protected Board getBoard() {
        return board;
    }

    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                Position position = new Position(i, j);
                if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
                    mat[i][j] = true;
                }
            }
        }

        return mat;
    }

    public boolean possibleMove(Position position) {

        int row = position.getRow();
        int column = position.getColumn();

        return possibleMoves()[row][column];
    }

    public boolean isThereAnyPossibleMove() {

        boolean[][] mat = possibleMoves();

        int row = mat.length;
        int column = mat[0].length;

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < column; j++) {

                if (mat[i][j]) {
                    return true;
                }

            }

        }

        return false;
    }
}
