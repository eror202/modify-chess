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

public class Joker extends Piece {
    private ImageIcon icon;


    public Joker(COLOUR colour, Coordinate OGcoord) {
        super(ID.KNIGHT, colour, OGcoord);
        if (getColour() == COLOUR.B) {
            readBufferedImage("images/BJoker.png");
            icon = new ImageIcon("images/BJoker.png");
        }
        else if (getColour() == COLOUR.W) {
            readBufferedImage("images/WJoker.png");
            icon = new ImageIcon("images/WJoker.png");
        }
    }
    public Joker(Joker original){super(original);}

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {

        ArrayList<Coordinate> frontRDig = Move.frontHorizontRDigFree(pieces,this,dimension);
        ArrayList<Coordinate> frontLDig = Move.frontHorizontLDigFree(pieces,this,dimension);
        ArrayList<Coordinate> backRDig = Move.backHorizontRDigFree(pieces,this,dimension);
        ArrayList<Coordinate> backLDig = Move.backHorizontLDigFree(pieces,this,dimension);
        ArrayList<Coordinate> rightUpDig = Move.rightHorizontUpDigFree(pieces,this,dimension);
        ArrayList<Coordinate> rightDownDig = Move.rightHorizontDownDigFree(pieces,this,dimension);
        ArrayList<Coordinate> leftUpDig = Move.leftHorizontUpDigFree(pieces,this,dimension);
        ArrayList<Coordinate> leftDownDig = Move.leftHorizontDownDigFree(pieces,this,dimension);

        frontRDig.addAll(backRDig);
        backLDig.addAll(frontLDig);
        frontRDig.addAll(backLDig);

        rightUpDig.addAll(leftUpDig);
        leftDownDig.addAll(rightDownDig);
        rightUpDig.addAll(leftDownDig);



        frontRDig.addAll(rightUpDig);

        return frontRDig;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }

    @Override
    public Piece makeCopy() {
        return new Joker(this);
    }

    private ImageIcon readBufferedImage (String imagePath) {
        try {
            InputStream is = Joker.class.getClassLoader().getResourceAsStream(imagePath);
            BufferedImage bimage = ImageIO.read(is);
            icon = new ImageIcon(bimage);
            is.close();
            return icon;
        } catch (Exception e) {
            return null;
        }
    }
}
