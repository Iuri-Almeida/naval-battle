package br.com.ialmeida.boardgame;

public class Board {
    private final int rows;
    private final int columns;
    private final Piece[][] pieces;

    public Board(int rows, int columns) {

        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column.");
        }

        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column) {

        if (!positionExists(row, column)) {
            throw new BoardException("Position not on the board.");
        }

        return pieces[row][column];
    }

    public Piece piece(Position position) {

        if (!positionExists(position)) {
            throw new BoardException("Position not on the board.");
        }

        return piece(position.getRow(), position.getColumn());
    }

    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[this.getRows()][this.getColumns()];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                Position position = new Position(i, j);
                if (this.positionExists(position) && !this.thereIsAPiece(position)) {
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

    public void placePiece(Piece piece, Position position) {

        if (thereIsAPiece(position)) {
            throw new BoardException("There is already a piece on position: " + position);
        }

        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {

        if (!positionExists(position)) {
            throw new BoardException("Position not on the board.");
        }

        return piece(position) != null;
    }
}
