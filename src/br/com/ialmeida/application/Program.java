package br.com.ialmeida.application;

import br.com.ialmeida.boardgame.Board;
import br.com.ialmeida.boardgame.Position;

public class Program {

    public static void main(String[] args) {
        Board board = new Board(2, 1);
        System.out.println(board);
    }
}