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


public class Rook extends Piece {

    private Coordinate castleCoordRook;

    private ImageIcon icon;

    //________________________________________________Class Constructors________________________________________________

    public Rook(COLOUR colour, Coordinate OGcoord) {
        super(ID.ROOK, colour, OGcoord);
        if (getColour() == COLOUR.B) {
            {
                readBufferedImage("images/BRook.png");
                icon = new ImageIcon("images/BRook.png");
            }
        }
        else if (getColour() == COLOUR.W) {
            //readBufferedImage("images/WRook.png");
            icon = new ImageIcon("images/WRook.png");
        }
    }

    public Rook (Rook original) {
        super(original);
    }

    //________________________________________________Getters & Setters________________________________________________

    public Coordinate getCastleCoordRook() {
        return castleCoordRook;
    }

    public void setCastleCoordRook(Coordinate castleCoordRook) {
        this.castleCoordRook = castleCoordRook;
    }

    //________________________________________________Overridden Methods________________________________________________

    @Override
    public Rook makeCopy() {
        return new Rook(this);
    }

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        ArrayList<Coordinate> front = Move.frontFree(pieces,this,dimension);
        ArrayList<Coordinate> right = Move.rightFree(pieces,this,dimension);
        ArrayList<Coordinate> back = Move.backFree(pieces,this,dimension);
        ArrayList<Coordinate> left = Move.leftFree(pieces,this,dimension);

        front.addAll(right);
        back.addAll(left);
        front.addAll(back);

        return front;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }

    private ImageIcon readBufferedImage(String imagePath) {
        try {
            InputStream is = Rook.class.getClassLoader().getResourceAsStream(imagePath);
            BufferedImage bimage = ImageIO.read(is);
            icon = new ImageIcon(bimage);
            is.close();
            return icon;
        } catch (Exception e) {
            return null;
        }
    }
}
