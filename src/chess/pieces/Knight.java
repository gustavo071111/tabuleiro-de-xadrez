package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    
        Position p = new Position(0, 0);

    int[][] directions1 = {
        {-2, -1}, {-2, 1}, {2, -1}, {2, 1}
    };
    int[][] directions2 = {
       {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
        
    };
    

    for (int[] d1 : directions1) {
        p.setValues(position.getRow() + d1[0], position.getColumn() + d1[1]);
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    for (int[] d2 : directions2) {
        p.setValues(position.getRow() - d2[0], position.getColumn() + d2[1]);
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    
    }
        return mat;
    }
}