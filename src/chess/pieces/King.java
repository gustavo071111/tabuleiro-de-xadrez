package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

    private ChessMatch chessMatch;
    

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    
        Position p = new Position(0, 0);


    int[][] directions = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    for (int[] d : directions) {
        p.setValues(position.getRow() + d[0], position.getColumn() + d[1]);
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    //special move castling
    if (getMoveCount() == 0 && !chessMatch.getCheck()) {
        
        Position posT1 = new Position(position.getRow(), position.getColumn() +3);
        if (testRookCastling(posT1)) {
            Position p1 = new Position(position.getRow(), position.getColumn() +1);
             Position p2 = new Position(position.getRow(), position.getColumn() +2);
             if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
                mat[position.getRow()][position.getColumn() +2] = true;
             }
        }//specialmove castling queenside rook
        Position posT2 = new Position(position.getRow(), position.getColumn() -4);
        if (testRookCastling(posT2)) {
            Position p1 = new Position(position.getRow(), position.getColumn() -1);
            Position p2 = new Position(position.getRow(), position.getColumn() -2);
            Position p3 = new Position(position.getRow(), position.getColumn() -3);
            if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
                mat[position.getRow()][position.getColumn() -2] = true;
            }
        }
    }

        return mat;
    }
}