package org.smirnov.domains;

import org.smirnov.boardLogic.COLOUR;
import org.smirnov.boardLogic.Coordinate;
import org.smirnov.boardLogic.ID;
import org.smirnov.moveLogic.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;



public class Bishop extends Piece {

    private ImageIcon icon;

    //________________________________________________Class Constructors________________________________________________

    public Bishop(COLOUR colour, Coordinate OGcoord) {
        super(ID.BISHOP, colour, OGcoord);
        if (getColour() == COLOUR.B) {
            icon = new ImageIcon("images/BBishop.png");
            readBufferedImage("images/BBishop.png");
        }
        else if (getColour() == COLOUR.W) {
            icon = new ImageIcon("images/WBishop.png");
            readBufferedImage("images/WBishop.png");
        }

    }

    public Bishop(Bishop original) {
        super(original);
    }

    //________________________________________________Overridden Methods________________________________________________

    @Override
    public Bishop makeCopy() {
        return new Bishop(this);
    }

    /**
     * Produces an ArrayList containing all the raw moves available to a org.smirnov.domains.Bishop within a given board
     * @param pieces the board being played in
     * @return an ArrayList containing all the coordinates produced from the org.smirnov.moveLogic.Move class (all the diagonals)
     */
    //qwe
    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        ArrayList<Coordinate> frontRDig = Move.frontRDigFree(pieces, this,dimension);
        ArrayList<Coordinate> backRDig = Move.backRDigFree(pieces, this, dimension);
        ArrayList<Coordinate> backLDig = Move.backLDigFree(pieces, this,dimension);
        ArrayList<Coordinate> frontLDig = Move.frontLDigFree(pieces, this, dimension);

        frontRDig.addAll(backRDig);
        backLDig.addAll(frontLDig);
        frontRDig.addAll(backLDig);

        return frontRDig;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }
    private ImageIcon readBufferedImage (String imagePath) {
        try {
            InputStream is = Bishop.class.getClassLoader().getResourceAsStream(imagePath);
            BufferedImage bimage = ImageIO.read(is);
            icon = new ImageIcon(bimage);
            is.close();
            return icon;
        } catch (Exception e) {
            return null;
        }
    }
}
