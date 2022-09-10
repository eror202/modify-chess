package org.smirnov.moveLogic;

import org.smirnov.boardLogic.COLOUR;
import org.smirnov.boardLogic.Coordinate;
import org.smirnov.domains.Piece;
import org.smirnov.domains.Pieces;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Move {

    //________________________________________________Null Messages________________________________________________

    private static final String nullPieces = "You can't play with a null board.";
    private static final String nullCoord = "Board coordinates must not be null.";

    //________________________________________________Support Methods________________________________________________

    public static boolean isNotTileColour(Pieces pieces, Coordinate destination, COLOUR colour) {
        Objects.requireNonNull(pieces,nullPieces);
        Objects.requireNonNull(destination,nullCoord);

        return !(pieces.getPieces().get(destination).getColour() == (colour));
    }

    public static boolean tileFull (Pieces pieces, Coordinate destination) {

        Objects.requireNonNull(pieces,nullPieces);
        Objects.requireNonNull(destination,nullCoord);

        return pieces.getPieces().get(destination) != null;
    }

    //________________________________________________Vertical Movement Methods________________________________________________

    // checks vertical moves directly in front (that is, moves along the same file)

    public static ArrayList<Coordinate> frontFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = -1;
        else
            factor = 1;

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor*advance;
            Coordinate checkCoord = new Coordinate(piece.getFile(), piece.getRank() + change);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }

        return moves;
    }

    // checks vertical moves directly behind (that is, moves along the same file)

    public static ArrayList<Coordinate> backFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = 1;
        else
            factor = -1;

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor*advance;
            Coordinate checkCoord = new Coordinate(piece.getFile(),piece.getRank()+change);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    //________________________________________________Horizontal Movement Methods________________________________________________

    // checks horizontal moves directly to the right (that is, moves along the same rank)

    public static ArrayList<Coordinate> rightFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = -1;
        else
            factor = 1;

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+change),piece.getRank());
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    // checks horizontal moves directly to the left (that is, moves along the same rank)

    public static ArrayList<Coordinate> leftFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = 1;
        else
            factor = -1;

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+change),piece.getRank());
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    //________________________________________________Diagonal Right Movement Methods________________________________________________

    // checks diagonal moves to the front and right

    public static ArrayList<Coordinate> frontRDigFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factorV;
        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {
            factorV = -1;
            factorH = -1;
        }
        else {
            factorV = 1;
            factorH = 1;
        }

        for (int advance = 1; advance <= limit; advance++) {
            int changeV = factorV*advance;
            int changeH = factorH*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+changeV),piece.getRank()+changeH);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {

                        moves.add(checkCoord);
                        return moves;

                }
                else if (occupiedTile)
                    return moves;
                else if (!tileFull(pieces, checkCoord))
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    // checks diagonal moves to the back and right

    public static ArrayList<Coordinate> backRDigFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factorV;
        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {
            factorV = -1;
            factorH = 1;
        }
        else {
            factorV = 1;
            factorH = -1;
        }

        for (int advance = 1; advance <= limit; advance++) {
            int changeV = factorV*advance;
            int changeH = factorH*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+changeV),piece.getRank()+changeH);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    //________________________________________________Diagonal Left Movement Methods________________________________________________

    // checks diagonal moves to the back and left

    public static ArrayList<Coordinate> backLDigFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factorV;
        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {
            factorV = 1;
            factorH = 1;
        }
        else {
            factorV = -1;
            factorH = -1;
        }

        for (int advance = 1; advance <= limit; advance++) {
            int changeV = factorV*advance;
            int changeH = factorH*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+changeV),piece.getRank()+changeH);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    // checks diagonal moves to the front and left

    public static ArrayList<Coordinate> frontLDigFree(Pieces pieces, Piece piece, int limit) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factorV;
        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {
            factorV = 1;
            factorH = -1;
        }
        else {
            factorV = -1;
            factorH = 1;
        }

        for (int advance = 1; advance <= limit; advance++) {
            int changeV = factorV*advance;
            int changeH = factorH*advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+changeV),piece.getRank()+changeH);
            if (Coordinate.inBoard(checkCoord)) {
                boolean occupiedTile = tileFull(pieces, checkCoord);
                if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())) {
                    moves.add(checkCoord);
                    return moves;
                }
                else if (occupiedTile)
                    return moves;
                else
                    moves.add(checkCoord);
            }
        }
        return moves;
    }

    //________________________________________________Horizontal and Diagonal org.smirnov.domains.Joker Movement Methods____________________

    public static ArrayList<Coordinate> frontHorizontRDigFree(Pieces pieces, Piece piece, int limit){
        ArrayList<Coordinate> moves = new ArrayList<>();

        int factorH;
        int factorH1;

        if (piece.getColour().equals(COLOUR.B)) {

            factorH = 1;
            factorH1 = -1;
        }
        else {

            factorH = 1;
            factorH1 = -1;
        }
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = -1;
        else
            factor = 1;

        Coordinate checkCoord2 = new Coordinate((char)(piece.getFile()-factorH1),piece.getRank());


        for (int advance = 1; advance <= limit; advance++) {
            int change = factor * advance;

            Coordinate checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
            if (advance==1) {
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
                    if (advance==2){
                        checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank()+change);
                        if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2) ) {
                            boolean occupiedTile = tileFull(pieces, checkCoord);
                            if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                            ) {
                                moves.add(checkCoord);
                                return moves;

                            } else if (occupiedTile)
                                return moves;
                            else if (!occupiedTile)
                                moves.add(checkCoord);

                        }
                    }
            if (advance==3){
                checkCoord = new Coordinate((char) (piece.getFile()+factorH), piece.getRank()+change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance==4){
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank()+change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2) ) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance==5){
                checkCoord = new Coordinate((char) (piece.getFile()+factorH), piece.getRank()+change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance==6){
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank()+change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2) ) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance==7){
                checkCoord = new Coordinate((char) (piece.getFile()+factorH), piece.getRank()+change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    boolean occupiedTile2 = tileFull(pieces, checkCoord2);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile && occupiedTile2)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);

                }
            }
            if (advance==8){
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank()+change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2) ) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance==9){
                checkCoord = new Coordinate((char) (piece.getFile()+factorH), piece.getRank()+change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);

                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                   else if (!occupiedTile)
                        moves.add(checkCoord);

                }
            }
        }
        return moves;
            }
    public static ArrayList<Coordinate> frontHorizontLDigFree(Pieces pieces, Piece piece, int limit) {
        ArrayList<Coordinate> moves = new ArrayList<>();


        int factorH;
        int factorH1;

        if (piece.getColour().equals(COLOUR.B)) {

            factorH = -1;
            factorH1 = -1;
        } else {

            factorH = -1;
            factorH1 = 1;
        }
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = -1;
        else
            factor = 1;

        Coordinate checkCoord2 = new Coordinate((char)(piece.getFile()-factorH1),piece.getRank());


        for (int advance = 1; advance <= limit; advance++) {
            int change = factor * advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
            if (advance == 1) {
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 2) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 3) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 4) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 5) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 6) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 7) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 8) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 9) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
        }
        return moves;
    }

    public static ArrayList<Coordinate> backHorizontRDigFree(Pieces pieces, Piece piece, int limit){
        ArrayList<Coordinate> moves = new ArrayList<>();


        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {

            factorH = 1;
        }
        else {

            factorH = 1;
        }
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = 1;
        else
            factor = -1;

        Coordinate checkCoord2 = new Coordinate((char)(piece.getFile()+1),piece.getRank());

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor * advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
            if (advance == 1) {
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 2) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 3) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 4) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 5) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 6) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 7) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 8) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 9) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
        }
        return moves;
    }

    public static ArrayList<Coordinate> backHorizontLDigFree(Pieces pieces, Piece piece, int limit){
        ArrayList<Coordinate> moves = new ArrayList<>();


        int factorH;

        if (piece.getColour().equals(COLOUR.B)) {

            factorH = -1;
        }
        else {

            factorH = -1;
        }
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = 1;
        else
            factor = -1;

        Coordinate checkCoord2 = new Coordinate((char)(piece.getFile()-1),piece.getRank());

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor * advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
            if (advance == 1) {
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 2) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 3) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 4) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 5) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 6) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 7) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 8) {
                checkCoord = new Coordinate((char) (piece.getFile()), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 9) {
                checkCoord = new Coordinate((char) (piece.getFile() + factorH), piece.getRank() + change);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
        }
        return moves;
    }
    public static ArrayList<Coordinate> rightHorizontUpDigFree(Pieces pieces, Piece piece, int limit){
        ArrayList<Coordinate> moves = new ArrayList<>();

        int factorV;


        if (piece.getColour().equals(COLOUR.B)) {
            factorV = 1;

        }
        else {
            factorV = 1;

        }
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = 1;
        else
            factor = 1;

        Coordinate checkCoord2 = new Coordinate((char)(piece.getFile()),piece.getRank()+1);

        for (int advance = 1; advance <= limit; advance++) {
            int change = factor * advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
            if (advance == 1) {
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 2) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 3) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 4) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 5) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 6) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 7) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 8) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 9) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
        }
        return moves;
    }

    public static ArrayList<Coordinate> rightHorizontDownDigFree(Pieces pieces, Piece piece, int limit){
        ArrayList<Coordinate> moves = new ArrayList<>();

        int factorV;


        if (piece.getColour().equals(COLOUR.B)) {
            factorV = -1;

        }
        else {
            factorV = -1;

        }
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = 1;
        else
            factor = 1;

        Coordinate checkCoord2 = new Coordinate((char)(piece.getFile()),piece.getRank()-1);


        for (int advance = 1; advance <= limit; advance++) {
            int change = factor * advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
            if (advance == 1) {
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 2) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 3) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 4) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 5) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 6) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 7) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 8) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 9) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
        }
        return moves;
    }
    public static ArrayList<Coordinate> leftHorizontUpDigFree(Pieces pieces, Piece piece, int limit){
        ArrayList<Coordinate> moves = new ArrayList<>();

        int factorV;


        if (piece.getColour().equals(COLOUR.B)) {
            factorV = 1;

        }
        else {
            factorV = 1;

        }
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = -1;
        else
            factor = -1;

        Coordinate checkCoord2 = new Coordinate((char)(piece.getFile()),piece.getRank()+1);


        for (int advance = 1; advance <= limit; advance++) {
            int change = factor * advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
            if (advance == 1) {
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 2) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 3) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 4) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 5) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 6) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 7) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 8) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 9) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
        }

        return moves;
    }

    public static ArrayList<Coordinate> leftHorizontDownDigFree(Pieces pieces, Piece piece, int limit){
        ArrayList<Coordinate> moves = new ArrayList<>();

        int factorV;

        if (piece.getColour().equals(COLOUR.B)) {
            factorV = -1;

        }
        else {
            factorV = -1;

        }
        int factor;

        if (piece.getColour().equals(COLOUR.B))
            factor = -1;
        else
            factor = -1;

        Coordinate checkCoord2 = new Coordinate((char)(piece.getFile()),piece.getRank()-1);


        for (int advance = 1; advance <= limit; advance++) {
            int change = factor * advance;
            Coordinate checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
            if (advance == 1) {
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 2) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 3) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 4) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 5) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 6) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 7) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 8) {
                checkCoord = new Coordinate((char) (piece.getFile()+change), piece.getRank());
                if (Coordinate.inBoard(checkCoord) && Coordinate.inBoard(checkCoord2)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
            if (advance == 9) {
                checkCoord = new Coordinate((char) (piece.getFile()+ change), piece.getRank() +factorV);
                if (Coordinate.inBoard(checkCoord)) {
                    boolean occupiedTile = tileFull(pieces, checkCoord);
                    if (occupiedTile && isNotTileColour(pieces, checkCoord, piece.getColour())
                    ) {
                        moves.add(checkCoord);
                        return moves;

                    } else if (occupiedTile)
                        return moves;
                    else if (!occupiedTile)
                        moves.add(checkCoord);
                    else moves.add(checkCoord);
                }
            }
        }
        return moves;
    }

    //________________________________________________Vertical org.smirnov.domains.Knight Movement Methods________________________________________________

    // checks org.smirnov.domains.Knight moves directly in front

    public static ArrayList<Coordinate> frontKnight(Pieces pieces, Piece piece) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;
        int sideDistance = 1;

        if (piece.getColour().equals(COLOUR.B)) {
            factor = -1;
        }
        else {
            factor = 1;
        }

        int changeV = 2*factor;
        int newRank = piece.getRank() + changeV;

        //right and left taken with reference to white pieces
        Coordinate frontRight = new Coordinate((char) (piece.getFile() + sideDistance),newRank);
        Coordinate frontLeft = new Coordinate((char) (piece.getFile() - sideDistance),newRank);

        if (Coordinate.inBoard(frontLeft)) {
            boolean occupiedTile = tileFull(pieces, frontLeft);
            if (!occupiedTile || isNotTileColour(pieces, frontLeft, piece.getColour()))
                moves.add(frontLeft);
        }

        if (Coordinate.inBoard(frontRight)) {
            boolean occupiedTile = tileFull(pieces, frontRight);
            if (!occupiedTile || isNotTileColour(pieces, frontRight, piece.getColour()))
                moves.add(frontRight);
        }
        return moves;
    }

    // checks org.smirnov.domains.Knight moves directly behind

    public static ArrayList<Coordinate> backKnight(Pieces pieces, Piece piece) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;
        int sideDistance = 1;

        if (piece.getColour().equals(COLOUR.B)) {
            factor = 1;
        }
        else {
            factor = -1;
        }

        int changeV = 2*factor;
        int newRank = piece.getRank() + changeV;

        //right and left taken with reference to white pieces
        Coordinate backRight = new Coordinate((char) (piece.getFile() + sideDistance),newRank);
        Coordinate backLeft = new Coordinate((char) (piece.getFile() - sideDistance),newRank);

        if (Coordinate.inBoard(backLeft)) {
            boolean occupiedTile = tileFull(pieces, backLeft);
            if (!occupiedTile || isNotTileColour(pieces, backLeft, piece.getColour()))
                moves.add(backLeft);
        }

        if (Coordinate.inBoard(backRight)) {
            boolean occupiedTile = tileFull(pieces, backRight);
            if (!occupiedTile || isNotTileColour(pieces, backRight, piece.getColour()))
                moves.add(backRight);
        }
        return moves;
    }

    //________________________________________________Horizontal org.smirnov.domains.Knight Movement Methods________________________________________________

    // checks org.smirnov.domains.Knight moves directly to the right

    public static ArrayList<Coordinate> rightKnight(Pieces pieces, Piece piece) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;
        int sideDistance = 1;

        if (piece.getColour().equals(COLOUR.B)) {
            factor = -1;
        }
        else {
            factor = 1;
        }

        int changeH = 2*factor;
        char newFile = (char) (piece.getFile() + changeH);

        //right and left taken with reference to white pieces
        Coordinate rightTop = new Coordinate(newFile,piece.getRank() + sideDistance);
        Coordinate rightBottom = new Coordinate(newFile, piece.getRank() - sideDistance);

        if (Coordinate.inBoard(rightTop)) {
            boolean occupiedTile = tileFull(pieces, rightTop);
            if (!occupiedTile || isNotTileColour(pieces, rightTop, piece.getColour()))
                moves.add(rightTop);
        }

        if (Coordinate.inBoard(rightBottom)) {
            boolean occupiedTile = tileFull(pieces, rightBottom);
            if (!occupiedTile || isNotTileColour(pieces, rightBottom, piece.getColour()))
                moves.add(rightBottom);
        }
        return moves;
    }

    // checks org.smirnov.domains.Knight moves directly to the left

    public static ArrayList<Coordinate> leftKnight(Pieces pieces, Piece piece) {

        ArrayList<Coordinate> moves = new ArrayList<>();
        int factor;
        int sideDistance = 1;

        if (piece.getColour().equals(COLOUR.B)) {
            factor = 1;
        }
        else {
            factor = -1;
        }

        int changeH = 2*factor;
        char newFile = (char) (piece.getFile() + changeH);

        //right and left taken with reference to white pieces
        Coordinate leftTop = new Coordinate(newFile,piece.getRank() + sideDistance);
        Coordinate leftBottom = new Coordinate(newFile, piece.getRank() - sideDistance);

        if (Coordinate.inBoard(leftBottom)) {
            boolean occupiedTile = tileFull(pieces, leftBottom);
            if (!occupiedTile || isNotTileColour(pieces, leftBottom, piece.getColour()))
                moves.add(leftBottom);
        }

        if (Coordinate.inBoard(leftTop)) {
            boolean occupiedTile = tileFull(pieces, leftTop);
            if (!occupiedTile || isNotTileColour(pieces, leftTop, piece.getColour()))
                moves.add(leftTop);
        }
        return moves;
    }



    //________________________________________________Move Input Methods________________________________________________
    
    public static String[] moveQuery(Scanner sc) {
        String[] move = new String[2];
        boolean validFormat = false;

        while (!validFormat) {
            System.out.println("Enter a piece and its destination. For example, to move a piece in g1 to h3, write \"g1 h3\".");
            String userInput = sc.nextLine();

            if (userInput.length() != 5 || !userInput.contains(" ")) {
                System.out.println("org.smirnov.moveLogic.Move instructions provided in an incorrect format. Try again!");
            }
            else {
                move = userInput.split(" ");
                validFormat = true;
            }
        }
        return move;
    }

}
