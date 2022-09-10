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

public class Knight extends Piece {

    private ImageIcon icon;

    //________________________________________________Class Constructors________________________________________________

    public Knight(COLOUR colour, Coordinate OGcoord) {
        super(ID.KNIGHT, colour, OGcoord);
        if (getColour() == COLOUR.B) {
            readBufferedImage("images/BKnight.png");
            icon = new ImageIcon("images/BKnight.png");
        }
        else if (getColour() == COLOUR.W) {
            readBufferedImage("images/WKnight.png");
            icon = new ImageIcon("images/WKnight.png");
        }
    }

    public Knight(Knight original) {
        super(original);
    }

    //________________________________________________Overridden Methods________________________________________________

    @Override
    public Knight makeCopy() {
        return new Knight(this);
    }

    /**
     * Produces an ArrayList containing all the raw moves available to a org.smirnov.domains.Knight within a given board
     * @param pieces the board being played in
     * @return an ArrayList containing all the coordinates produced from the org.smirnov.moveLogic.Move class (all the org.smirnov.domains.Knight moves)
     */

    @Override
    public ArrayList<Coordinate> getRawMoves(Pieces pieces) {
        ArrayList<Coordinate> front = Move.frontKnight(pieces,this);
        ArrayList<Coordinate> right = Move.backKnight(pieces,this);
        ArrayList<Coordinate> back = Move.rightKnight(pieces,this);
        ArrayList<Coordinate> left = Move.leftKnight(pieces,this);

        front.addAll(right);
        back.addAll(left);
        front.addAll(back);

        return front;
    }

    @Override
    public ImageIcon getImageIcon() {
        return icon;
    }

    private ImageIcon readBufferedImage (String imagePath) {
        try {
            InputStream is = Knight.class.getClassLoader().getResourceAsStream(imagePath);
            BufferedImage bimage = ImageIO.read(is);
            icon = new ImageIcon(bimage);
            is.close();
            return icon;
        } catch (Exception e) {
            return null;
        }
    }

}
