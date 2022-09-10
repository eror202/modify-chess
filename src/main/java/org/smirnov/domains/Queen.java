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


public class Queen extends Piece {

    private ImageIcon icon;

    //________________________________________________Class Constructors________________________________________________

    public Queen(COLOUR colour, Coordinate OGcoord) {
        super(ID.QUEEN, colour, OGcoord);
        if (getColour() == COLOUR.B) {
            readBufferedImage("images/BQueen.png");
            icon = new ImageIcon("images/BQueen.png");
        }
        else if (getColour() == COLOUR.W) {
            readBufferedImage("images/WQueen.png");
            icon = new ImageIcon("images/WQueen.png");
        }
    }

    public Queen(Queen original) {
        super(original);
    }

    //________________________________________________Overridden Methods________________________________________________

    @Override
    public Queen makeCopy() {
        return new Queen(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {

        ArrayList<Coordinate> front = Move.frontFree(pieces,this,dimension);
        ArrayList<Coordinate> right = Move.rightFree(pieces,this,dimension);
        ArrayList<Coordinate> back = Move.backFree(pieces,this,dimension);
        ArrayList<Coordinate> left = Move.leftFree(pieces,this,dimension);
        ArrayList<Coordinate> frontRDig = Move.frontRDigFree(pieces, this,dimension);
        ArrayList<Coordinate> backRDig = Move.backRDigFree(pieces, this, dimension);
        ArrayList<Coordinate> backLDig = Move.backLDigFree(pieces, this,dimension);
        ArrayList<Coordinate> frontLDig = Move.frontLDigFree(pieces, this, dimension);

        front.addAll(right);
        back.addAll(left);
        front.addAll(back);

        frontRDig.addAll(backRDig);
        backLDig.addAll(frontLDig);
        frontRDig.addAll(backLDig);

        front.addAll(frontRDig);

        return front;

    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }

    private ImageIcon readBufferedImage (String imagePath) {
        try {
            InputStream is = Queen.class.getClassLoader().getResourceAsStream(imagePath);
            BufferedImage bimage = ImageIO.read(is);
            icon = new ImageIcon(bimage);
            is.close();
            return icon;
        } catch (Exception e) {
            return null;
        }
    }
}
