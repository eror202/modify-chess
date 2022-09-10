package org.smirnov.domains;

import org.smirnov.boardLogic.BOARD;
import org.smirnov.boardLogic.COLOUR;
import org.smirnov.boardLogic.Coordinate;
import org.smirnov.boardLogic.ID;
import org.smirnov.moveLogic.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.*;


public class King extends Piece {

    // the coordinate to which the king goes after castling org.smirnov.domains.King side
    private Coordinate castleCoordKingK;
    // the coordinate to whcih the king goes after castling org.smirnov.domains.Queen side
    private Coordinate castleCoordKingQ;
    // the coordinate through which the king goes when castling org.smirnov.domains.King side
    private Coordinate transitionCoordKingK;
    // the coordinate through which the king goes when castling org.smirnov.domains.Queen side
    private Coordinate transitionCoordKingQ;
    // the rook on the org.smirnov.domains.King side
    private Rook rookKing;
    // the rook on the org.smirnov.domains.Queen side
    private Rook rookQueen;
    private ImageIcon icon;

    //________________________________________________Class Constructors________________________________________________

    public King(COLOUR colour, Coordinate OGcoord) {
        super(ID.KING, colour, OGcoord);
        if (getColour() == COLOUR.B) {
            readBufferedImage("images/BKing.png");
            icon = new ImageIcon("images/BKing.png");
        }
        else if (getColour() == COLOUR.W) {
            readBufferedImage("images/WKing.png");
            icon = new ImageIcon("images/WKing.png");
        }
    }

    public King(King original) {
        super(original);
    }

    //________________________________________________Getters & Setters________________________________________________

    public Coordinate getCastleCoordKingK() {
        return castleCoordKingK;
    }

    public Coordinate getCastleCoordKingQ() {
        return castleCoordKingQ;
    }

    public Coordinate getTransitionCoordKingK() {
        return transitionCoordKingK;
    }

    public Coordinate getTransitionCoordKingQ() {
        return transitionCoordKingQ;
    }

    public Rook getRookKing() {
        return rookKing;
    }

    public Rook getRookQueen() {
        return rookQueen;
    }

    //________________________________________________Castling Validation Methods________________________________________________

    /**
     * Determines whether a org.smirnov.domains.King can castle king side (short castling)
     * @param pieces the board being played in
     * @return true if and only if:
     * the king isn't in check
     * there is a rook on the king side (h1 for whites, h8 for blacks)
     * there are no pieces between the king and the rook
     * neither the rook nor the king have moved
     * If the king can castle, then the coordinate to which the king goes to castle is added to castleCoordKingk
     * It sets the corresponding coordinate to the king side rook
     * It also saves the coordinate through which the king goes when castling.
     * This is used in "removeOwnCheck" to ascertain that castling is possible
     * ("One may not castle out of, through, or into check.")
     */

    public boolean canCastleKing (Pieces pieces) {

        if (pieces.isCheck(getColour()))
            return false;

        HashMap<Coordinate, Piece> colouredPieces = pieces.getColourPieces(getColour());

        for (Piece value : colouredPieces.values()) {
            if (value.getName() == ID.ROOK && value.getFile() == BOARD.LAST_FILE.getFileVal())
                rookKing = (Rook) value;
        }

        int distanceRookKing = 3;
        ArrayList<Coordinate> castleCoords;

        if (getColour() == COLOUR.B)
            castleCoords = Move.leftFree(pieces, this, dimension);
        else
            castleCoords = Move.rightFree(pieces, this, dimension);

        boolean isSpace = castleCoords.size() == distanceRookKing;

        boolean canCastle = rookKing != null &&
                !rookKing.getHasMoved() &&
                !getHasMoved() &&
                isSpace;

        if (canCastle) {
            castleCoordKingK = castleCoords.get(1);
            transitionCoordKingK = castleCoords.get(0);
            rookKing.setCastleCoordRook(castleCoords.get(0));
            return true;
        }
        return false;
    }

    /**
     * Determines whether a org.smirnov.domains.King can castle queen side (long castling)
     * @param pieces the board being played in
     * @return true if and only if:
     * the king isn't in check
     * there is a rook on the queen side (a1 for whites, a8 for blacks)
     * there are no pieces between the king and the rook
     * neither the rook nor the king have moved
     * If the king can castle, then the coordinate to which the king goes to castle is added to castleCoordKingQ
     * It sets the corresponding coordinate to the queen side rook
     * It also saves the coordinate through which the king goes when castling.
     * This is used in "removeOwnCheck" to ascertain that castling is possible
     * ("One may not castle out of, through, or into check.")
     */

    public boolean canCastleQueen (Pieces pieces) {

        if (pieces.isCheck(getColour()))
            return false;

        HashMap<Coordinate,Piece> colouredPieces = pieces.getColourPieces(getColour());

        for (Piece value : colouredPieces.values()) {
            if (value.getName() == ID.ROOK && value.getFile() == BOARD.FIRST_FILE.getFileVal())
                rookQueen = (Rook) value;
        }

        int distanceRookQueen = 4;
        ArrayList<Coordinate> castleCoords;

        if (getColour() == COLOUR.W) {
            castleCoords = Move.leftFree(pieces, this, dimension);
        }
        else {
            castleCoords = Move.rightFree(pieces, this, dimension);
        }

        boolean isSpace = castleCoords.size() == distanceRookQueen;


        boolean canCastle = rookQueen != null &&
                !rookQueen.getHasMoved() &&
                !getHasMoved() &&
                isSpace;

        if (canCastle) {
            castleCoordKingQ = castleCoords.get(1);
            transitionCoordKingQ = castleCoords.get(0);
            rookQueen.setCastleCoordRook(castleCoords.get(0));
            return true;
        }
        return false;
    }

    //________________________________________________Overridden Methods________________________________________________

    @Override
    public King makeCopy() {
        return new King(this);
    }

    /**
     * Produces an ArrayList containing all the raw moves available to a org.smirnov.domains.King within a given board
     * @param pieces the board being played in
     * @return an ArrayList containing all the coordinates produced from the org.smirnov.moveLogic.Move class
     * (all the diagonals, all verticals and all horizontals, using 1 as a limit).
     * Also adds coordinates if castling is possible.
     */

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        //get and add all "raw" moves (reachable from position)
        ArrayList<Coordinate> front = Move.frontFree(pieces,this,single);
        ArrayList<Coordinate> right = Move.rightFree(pieces,this,single);
        ArrayList<Coordinate> back = Move.backFree(pieces,this,single);
        ArrayList<Coordinate> left = Move.leftFree(pieces,this,single);
        ArrayList<Coordinate> frontRDig = Move.frontRDigFree(pieces, this,single);
        ArrayList<Coordinate> backRDig = Move.backRDigFree(pieces, this, single);
        ArrayList<Coordinate> backLDig = Move.backLDigFree(pieces, this,single);
        ArrayList<Coordinate> frontLDig = Move.frontLDigFree(pieces, this, single);

        front.addAll(right);
        back.addAll(left);
        front.addAll(back);

        frontRDig.addAll(backRDig);
        backLDig.addAll(frontLDig);
        frontRDig.addAll(backLDig);

        front.addAll(frontRDig);

        // if can castle, add coordinates
        if (canCastleKing(pieces))
            front.add(castleCoordKingK);
        if (canCastleQueen(pieces))
            front.add(castleCoordKingQ);


        return front;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }

    private ImageIcon readBufferedImage (String imagePath) {
        try {
            InputStream is = King.class.getClassLoader().getResourceAsStream(imagePath);
            BufferedImage bimage = ImageIO.read(is);
            icon = new ImageIcon(bimage);
            is.close();
            return icon;
        } catch (Exception e) {
            return null;
        }
    }
}