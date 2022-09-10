package org.smirnov.boardLogic;

import org.smirnov.domains.Piece;
import org.smirnov.domains.*;

import java.util.HashMap;

public class Boards {

    public static HashMap<Coordinate, Piece> getChessBoard() {

        HashMap <Coordinate, Piece> pieces = new HashMap<>();

        int pawnBRank = 9;
        int bRank = 10;
        int pawnWRank = 2;
        int wRank = 1;

        //________________________________________________Black Pawns________________________________________________

        Pawn pawnBa = new Pawn(COLOUR.B, new Coordinate('a', pawnBRank));
        Pawn pawnBb = new Pawn(COLOUR.B, new Coordinate('b', pawnBRank));
        Pawn pawnBc = new Pawn(COLOUR.B, new Coordinate('c', pawnBRank));
        Pawn pawnBd = new Pawn(COLOUR.B, new Coordinate('d', pawnBRank));
        Pawn pawnBe = new Pawn(COLOUR.B, new Coordinate('e', pawnBRank));
        Pawn pawnBf = new Pawn(COLOUR.B, new Coordinate('f', pawnBRank));
        Pawn pawnBg = new Pawn(COLOUR.B, new Coordinate('g', pawnBRank));
        Pawn pawnBh = new Pawn(COLOUR.B, new Coordinate('h', pawnBRank));
        Pawn pawnBi = new Pawn(COLOUR.B, new Coordinate('i', pawnBRank));
        Pawn pawnBj = new Pawn(COLOUR.B, new Coordinate('j', pawnBRank));

        //________________________________________________Black Rooks________________________________________________

        Rook rookBa = new Rook(COLOUR.B, new Coordinate('a', bRank));
        Rook rookBj = new Rook(COLOUR.B, new Coordinate('j', bRank));

        //________________________________________________Black Jokers________________________________________________
        Joker jokerBb = new Joker(COLOUR.B, new Coordinate('d',bRank));
        Joker jokerBi = new Joker(COLOUR.B, new Coordinate('g',bRank));

        //org.smirnov.domains.Joker jokerBb = new org.smirnov.domains.Joker(org.smirnov.boardLogic.COLOUR.B, new org.smirnov.boardLogic.Coordinate('a', 9));
        //org.smirnov.domains.Joker jokerBi = new org.smirnov.domains.Joker(org.smirnov.boardLogic.COLOUR.B, new org.smirnov.boardLogic.Coordinate('j', 1));

        //________________________________________________Black Knights________________________________________________

        Knight knightBc = new Knight(COLOUR.B, new Coordinate('b', bRank));
        Knight knightBh = new Knight(COLOUR.B, new Coordinate('i', bRank));

        //________________________________________________Black Bishops________________________________________________

        Bishop bishopBd = new Bishop(COLOUR.B, new Coordinate('c', bRank));
        Bishop bishopBg = new Bishop(COLOUR.B, new Coordinate('h', bRank));


        //________________________________________________Black Queen________________________________________________

        Queen queenB = new Queen(COLOUR.B, new Coordinate('f', bRank));

        //________________________________________________Black King________________________________________________

        King kingB = new King(COLOUR.B, new Coordinate('e', bRank));

        //________________________________________________White Pawns________________________________________________

        Pawn pawnWa = new Pawn(COLOUR.W, new Coordinate('a', pawnWRank));
        Pawn pawnWb = new Pawn(COLOUR.W, new Coordinate('b', pawnWRank));
        Pawn pawnWc = new Pawn(COLOUR.W, new Coordinate('c', pawnWRank));
        Pawn pawnWd = new Pawn(COLOUR.W, new Coordinate('d', pawnWRank));
        Pawn pawnWe = new Pawn(COLOUR.W, new Coordinate('e', pawnWRank));
        Pawn pawnWf = new Pawn(COLOUR.W, new Coordinate('f', pawnWRank));
        Pawn pawnWg = new Pawn(COLOUR.W, new Coordinate('g', pawnWRank));
        Pawn pawnWh = new Pawn(COLOUR.W, new Coordinate('h', pawnWRank));
        Pawn pawnWi = new Pawn(COLOUR.W, new Coordinate('i', pawnWRank));
        Pawn pawnWj = new Pawn(COLOUR.W, new Coordinate('j', pawnWRank));

        //________________________________________________White Rooks________________________________________________

        Rook rookWa = new Rook(COLOUR.W, new Coordinate('a', wRank));
        Rook rookWj = new Rook(COLOUR.W, new Coordinate('j', wRank));

        //________________________________________________White Jokers________________________________________________
        Joker jokerWb = new Joker(COLOUR.W, new Coordinate('d',wRank));
        Joker jokerWi = new Joker(COLOUR.W, new Coordinate('g',wRank));

        //org.smirnov.domains.Joker jokerWb = new org.smirnov.domains.Joker(org.smirnov.boardLogic.COLOUR.W, new org.smirnov.boardLogic.Coordinate('j',2));
        //org.smirnov.domains.Joker jokerWi = new org.smirnov.domains.Joker(org.smirnov.boardLogic.COLOUR.W, new org.smirnov.boardLogic.Coordinate('j',10));

        //________________________________________________White Knights________________________________________________

        Knight knightWc = new Knight(COLOUR.W, new Coordinate('b', wRank));
        Knight knightWh = new Knight(COLOUR.W, new Coordinate('i', wRank));

        //________________________________________________White Bishops________________________________________________

        Bishop bishopWd = new Bishop(COLOUR.W, new Coordinate('c', wRank));
        Bishop bishopWg = new Bishop(COLOUR.W, new Coordinate('h', wRank));

        //________________________________________________White Queen________________________________________________

        Queen queenW = new Queen(COLOUR.W, new Coordinate('f', wRank));

        //________________________________________________White King________________________________________________

        King kingW = new King(COLOUR.W, new Coordinate('e', wRank));

        //________________________________________________Place Black in HashMap________________________________________________

        pieces.put(pawnBa.getCoords(), pawnBa);
        pieces.put(pawnBb.getCoords(), pawnBb);
        pieces.put(pawnBc.getCoords(), pawnBc);
        pieces.put(pawnBd.getCoords(), pawnBd);
        pieces.put(pawnBe.getCoords(), pawnBe);
        pieces.put(pawnBf.getCoords(), pawnBf);
        pieces.put(pawnBg.getCoords(), pawnBg);
        pieces.put(pawnBh.getCoords(), pawnBh);
        pieces.put(pawnBi.getCoords(), pawnBi);
        pieces.put(pawnBj.getCoords(), pawnBj);

        pieces.put(rookBa.getCoords(), rookBa);
        pieces.put(rookBj.getCoords(), rookBj);

        pieces.put(jokerBb.getCoords(), jokerBb);
        pieces.put(jokerBi.getCoords(), jokerBi);

        pieces.put(knightBc.getCoords(), knightBc);
        pieces.put(knightBh.getCoords(), knightBh);

        pieces.put(bishopBd.getCoords(), bishopBd);
        pieces.put(bishopBg.getCoords(), bishopBg);

        pieces.put(queenB.getCoords(), queenB);

        pieces.put(kingB.getCoords(), kingB);

        //________________________________________________Place White in HashMap________________________________________________

        pieces.put(pawnWa.getCoords(), pawnWa);
        pieces.put(pawnWb.getCoords(), pawnWb);
        pieces.put(pawnWc.getCoords(), pawnWc);
        pieces.put(pawnWd.getCoords(), pawnWd);
        pieces.put(pawnWe.getCoords(), pawnWe);
        pieces.put(pawnWf.getCoords(), pawnWf);
        pieces.put(pawnWg.getCoords(), pawnWg);
        pieces.put(pawnWh.getCoords(), pawnWh);
        pieces.put(pawnWi.getCoords(), pawnWi);
        pieces.put(pawnWj.getCoords(), pawnWj);

        pieces.put(rookWa.getCoords(), rookWa);
        pieces.put(rookWj.getCoords(), rookWj);

        pieces.put(jokerWb.getCoords(), jokerWb);
        pieces.put(jokerWi.getCoords(), jokerWi);

        pieces.put(knightWc.getCoords(), knightWc);
        pieces.put(knightWh.getCoords(), knightWh);

        pieces.put(bishopWd.getCoords(), bishopWd);
        pieces.put(bishopWg.getCoords(), bishopWg);

        pieces.put(queenW.getCoords(), queenW);

        pieces.put(kingW.getCoords(), kingW);

        return pieces;
    }

}
