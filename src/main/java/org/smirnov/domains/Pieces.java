package org.smirnov.domains;

import org.smirnov.boardLogic.Boards;
import org.smirnov.boardLogic.COLOUR;
import org.smirnov.boardLogic.Coordinate;
import org.smirnov.boardLogic.ID;
import org.smirnov.moveLogic.Move;

import java.util.*;

/*
1) Class Constructors
2) Collection Copying
3) Getters & Setters
4) org.smirnov.domains.Piece Related Methods
5) Board Distribution Related Methods
6) Game Logic Methods
7) org.smirnov.domains.Piece Movement Methods
8) Overridden Methods
*/

/**
 * The org.smirnov.domains.Pieces class is used to represent the board that is being played with.
 * It contains the position of all pieces, alongside all the methods that handle game logic,
 * such as checking when a game ends or handling the movement of a piece.
 */

public class Pieces {

    private HashMap<Coordinate, Piece> pieces;
    private HashMap<Coordinate, Piece> previousPieces;
    private boolean isCapture;
    private boolean isGUIGame;
    private ArrayList<HashMap<Coordinate,Piece>> gameProgress = new ArrayList<>();

    //________________________________________________Class Constructors________________________________________________

    public Pieces() {
        pieces = Boards.getChessBoard();
        previousPieces = copyHashMap(pieces);
        gameProgress.add(copyHashMap(pieces));
        updatePotentials(); // when we instantiate org.smirnov.domains.Pieces, we immediately calculate the potential moves to begin the game
    }

    public Pieces(HashMap<Coordinate, Piece> newBoard) {
        pieces = newBoard;
        previousPieces = copyHashMap(pieces);
        gameProgress.add(copyHashMap(pieces));
        updatePotentials();
    }

    public Pieces (Pieces original) { // we create a copy constructor to help calculate potential moves
        this.pieces = copyHashMap(original.getPieces());
        this.previousPieces = original.previousPieces;
        this.isCapture = original.isCapture;
        this.isGUIGame = original.isGUIGame;
        this.gameProgress = copyArrayHash(original.getGameProgress());
    }

    //________________________________________________Collection Copying________________________________________________

    private HashMap<Coordinate, Piece> copyHashMap (HashMap<Coordinate, Piece> original) { // creates a copy of a pieces HashMap

        HashMap<Coordinate, Piece> copyMap = new HashMap<>();
        for (Coordinate key : original.keySet()) {
            Coordinate newKey = new Coordinate(key);
            Piece newPiece = original.get(key).makeCopy();
            copyMap.put(newKey,newPiece);
        }

        return copyMap;
    }

    private ArrayList<HashMap<Coordinate,Piece>> copyArrayHash (ArrayList<HashMap<Coordinate,Piece>> original) { // creates a copy of a gameProgress ArrayList
        ArrayList<HashMap<Coordinate,Piece>> copyList = new ArrayList<>();
        for (HashMap<Coordinate,Piece> game : original) {
            copyList.add(copyHashMap(game));
        }

        return copyList;
    }

    //________________________________________________Getters & Setters________________________________________________

    public HashMap<Coordinate, Piece> getPieces() {
        return pieces;
    }

    public void setPieces(HashMap<Coordinate,Piece> pieces) {this.pieces = pieces;}

    public boolean getIsCapture() {
        return isCapture;
    }

    public void setIsCapture(boolean captureStatus) {
        this.isCapture = captureStatus;
    }

    public HashMap<Coordinate, Piece> getPreviousPieces() {
        return previousPieces;
    }

    public void setPreviousPieces(HashMap<Coordinate, Piece> previousPieces) {
        this.previousPieces = copyHashMap(previousPieces);
    }

    public ArrayList<HashMap<Coordinate, Piece>> getGameProgress() {
        return gameProgress;
    }

    public void setGUIGame (boolean GUIStatus) {
        isGUIGame = GUIStatus;
    }

    //________________________________________________Piece Related Methods________________________________________________

    public void addPiece(Coordinate coordinate, Piece piece) {
        pieces.put(coordinate,piece);
    }

    public Coordinate findPiece(Piece piece) {

        Objects.requireNonNull(piece, "Provide an existing piece. It can't be null.");

        for (Coordinate key : pieces.keySet()) {
            if (pieces.get(key).equals(piece))
                return key;
        }
        System.err.println(piece.getName().toFullString() +" not found.");
        return Coordinate.emptyCoordinate;
    }

    public Coordinate findKing(COLOUR colour) {
        for (Coordinate key : pieces.keySet()) {
            Piece potentialKing = pieces.get(key);
            if (potentialKing.getName().equals(ID.KING) && (potentialKing.getColour() == colour))
                return key;
        }
        String pieceNotInBoard = "org.smirnov.domains.King not found. Assuming it isn't in board. Empty coordinate provided.";
        System.err.println(pieceNotInBoard);
        return Coordinate.emptyCoordinate;
    }

    public Piece getPiece(Coordinate coordinate) {

        Objects.requireNonNull(coordinate, "Provide an existing coordinate. It can't be null.");

        for (Coordinate key : pieces.keySet()) {
            if (key.equals(coordinate))
                return pieces.get(key);
        }
        System.err.println("There is no piece in this coordinate. Empty piece provided.");
        return Piece.emptyPiece;
    }

    //________________________________________________Board Distribution Related Methods________________________________________________

    public HashMap<Coordinate, Piece> getColourPieces(COLOUR colour) {
        HashMap<Coordinate,Piece> colours = new HashMap<>();
        for (Coordinate key : pieces.keySet()) {
            Piece piece = pieces.get(key);
            if (piece.getColour() == colour)
                colours.put(key,piece);
        }
        return colours;
    }

    public HashSet<Coordinate> allColouredPotentials (COLOUR colour) {
        HashSet<Coordinate> allMoves = new HashSet<>();
        HashMap<Coordinate, Piece> allColoured = getColourPieces(colour);
        for (Piece piece : allColoured.values()){
            allMoves.addAll(piece.getPotentialMoves());
        }
        return allMoves;
    }
    public HashSet<Coordinate> allColouredRaws (COLOUR colour) {
        HashSet<Coordinate> allMoves = new HashSet<>();
        HashMap<Coordinate, Piece> allColoured = getColourPieces(colour);
        for (Piece piece : allColoured.values()){
            allMoves.addAll(piece.getRawMoves(this));
        }
        return allMoves;
    }


    public void updatePreviousMovePawns () {
        for (Piece potentialPawn : pieces.values()){
            if (potentialPawn.getName() == ID.PAWN) {
                Pawn pawn = (Pawn) potentialPawn;
                pawn.setPreviousCoordinate(pawn.getCoords());
            }
        }
    }

    public boolean pieceInSameFile (Piece piece) {

        if (piece.getName() == ID.KING)
            return false;

        HashMap <Coordinate, Piece> coloured = getColourPieces(piece.getColour());
        for (Piece value : coloured.values()) {
            if (value.getName() == piece.getName() && value.getFile() == piece.getFile() && !value.equals(piece))
                return true;
        }
        return false;
    }

    public boolean pieceInSameRank (Piece piece) {

        if (piece.getName() == ID.KING)
            return false;

        HashMap <Coordinate, Piece> coloured = getColourPieces(piece.getColour());
        for (Piece value : coloured.values()) {
            if (value.getName() == piece.getName() && value.getRank() == piece.getRank() && !value.equals(piece))
                return true;
        }
        return false;
    }

    public boolean pieceToSameCoordinate (Coordinate coordinate, Piece piece) {
        assert piece.getPotentialMoves().contains(coordinate);

        if (piece.getName() == ID.KING)
            return false;

        HashMap <Coordinate, Piece> coloured = getColourPieces(piece.getColour());
        for (Piece value : coloured.values()) {
            if (value.getName() == piece.getName() && value.getPotentialMoves().contains(coordinate) && !value.equals(piece))
                return true;
        }
        return false;
    }

    //________________________________________________Game Logic Methods________________________________________________

    public boolean isCheck(COLOUR colour) { //check against the given colour
        Coordinate kingPosition = findKing(colour);

        if (kingPosition.equals(Coordinate.emptyCoordinate))
            throw new IllegalArgumentException("There is no king in the board. This is an illegal game!");

        HashSet<Coordinate> dangerMoves = allColouredPotentials(COLOUR.not(colour));
        return (dangerMoves.contains(kingPosition));
    }

    public boolean isMate(COLOUR colour) {
        HashSet<Coordinate> allMoves = allColouredPotentials(colour);
        return isCheck(colour) && (allMoves.size() == 0);
    }

    public boolean isDraw() {

        int n = gameProgress.size();

        boolean twoKings = !findKing(COLOUR.B).equals(Coordinate.emptyCoordinate) && !findKing(COLOUR.W).equals(Coordinate.emptyCoordinate);

        if (getPieces().size() == 2) // only 2 kings
            return twoKings;
        else if (getPieces().size() == 3) { // 2 kings and a bishop/knight
            int counter = 0;
            for (Piece piece : this.getPieces().values()) {
                if (piece.getName() == ID.BISHOP || piece.getName() == ID.KNIGHT)
                    counter++;
            }
            return twoKings && counter == 1;
        }
        else if (getPieces().size() == 4) { // 2 kings and 2 bishops with same diagonal colour
            int counterB = 0;
            Bishop bishopB = null;
            int counterW = 0;
            Bishop bishopW = null;
            for (Piece piece : this.getPieces().values()) {
                if (piece.getName() == ID.BISHOP) {
                    if (piece.getColour() == COLOUR.B) {
                        bishopB = (Bishop) piece;
                        counterB++;
                    }
                    else {
                        bishopW = (Bishop) piece;
                        counterW++;
                    }
                }
            }

            boolean sameColourBishops = counterB == 1 &&
                                        counterW == 1 &&
                                        bishopB.getOGcoord().getFile() != bishopW.getOGcoord().getFile();

            return twoKings && sameColourBishops;
        }
        else if (n >= 3){ // threefold repetition
            for (HashMap<Coordinate, Piece> currentGame : gameProgress) {
                int counter = 0;
                for (HashMap<Coordinate, Piece> checkGame : gameProgress) {
                    if (currentGame.equals(checkGame)) {
                        counter++;
                    }
                }
                if (counter == 3)
                    return true;
            }

        }

        return false;

    }

    public boolean isStalemate(COLOUR colour) {
        HashSet<Coordinate> allMoves = allColouredPotentials(COLOUR.not(colour));
        return allMoves.size() == 0 && !isCheck(COLOUR.not(colour));

    }

    //________________________________________________Piece Movement Methods________________________________________________


    public void pieceMove (Coordinate coordinate, Piece piece) {
        Coordinate pieceCoord = findPiece(piece);
        addPiece(coordinate, piece);
        piece.setCoords(coordinate);
        piece.setHasMoved();
        pieces.remove(pieceCoord);
    }

    public void makeMove (Coordinate coordinate, Piece piece) {

        if (piece.isValidMove(coordinate, piece.getColour())) {
            setPreviousPieces(this.getPieces());
            isCapture = Move.tileFull(this, coordinate) && Move.isNotTileColour(this,coordinate, piece.getColour());
            if (piece.getName() == ID.KING) {
                King castleKing = (King) piece;
                if (castleKing.canCastleQueen(this) && coordinate.equals(castleKing.getCastleCoordKingQ()) && !isCheck(castleKing.getColour())) {
                    assert !findPiece(castleKing.getRookQueen()).equals(Coordinate.emptyCoordinate);
                    pieceMove(coordinate,castleKing);
                    pieceMove(castleKing.getRookQueen().getCastleCoordRook(),castleKing.getRookQueen());
                }
                else if (castleKing.canCastleKing(this) && coordinate.equals(castleKing.getCastleCoordKingK()) && !isCheck(castleKing.getColour())) {
                    assert !findPiece(castleKing.getRookKing()).equals(Coordinate.emptyCoordinate);
                    pieceMove(coordinate,castleKing);
                    pieceMove(castleKing.getRookKing().getCastleCoordRook(),castleKing.getRookKing());
                }
                else {
                    pieceMove(coordinate, castleKing);
                }
            }
            else if (piece.getName() == ID.PAWN) {
                Pawn pawn = (Pawn) piece;

                updatePreviousMovePawns();
                if (Math.abs(coordinate.getRank() - pawn.getRank()) == 2)
                    pawn.setHasMovedTwo();

                if (pawn.canPromoteBlack(coordinate) || pawn.canPromoteWhite(coordinate)) {
                    Piece toPromote;

                    if (isGUIGame) {
                        pawn.GUIPromotionQuery(coordinate);
                        toPromote = pawn.getPromotedPiece();
                    }
                    else {
                        toPromote = pawn.promotionQuery(coordinate);
                    }
                    Coordinate pieceCoord = findPiece(piece);
                    addPiece(coordinate, toPromote);
                    pieces.remove(pieceCoord);
                }
                else if (pawn.getEnPassantLeft()) {
                    Coordinate left = Move.leftFree(this,pawn,1).get(0);
                    setIsCapture(true);
                    pieces.remove(left);
                    pieceMove(coordinate,pawn);
                }
                else if (pawn.getEnPassantRight()) {
                    Coordinate right = Move.rightFree(this,pawn,1).get(0);
                    setIsCapture(true);
                    pieces.remove(right);
                    pieceMove(coordinate,pawn);
                }
                else {
                    pieceMove(coordinate, pawn);
                }
            }
            else {
                pieceMove(coordinate, piece);
            }
        }
        else
            System.err.println(piece.getName().toFullString() + " to " + coordinate.toString() + " is an invalid move.");

        gameProgress.add(copyHashMap(pieces));
        updatePotentials();

    }


    public void updatePotentials() {

        for (Piece value : pieces.values()) {
            value.clearMoves();
            value.updatePotentialMoves(this);
        }
    }

    //________________________________________________Overridden Methods________________________________________________

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        pieces.forEach((coord, piece) -> str.append(piece.getPieceID())
                                .append(" is at ")
                                .append(coord.toString())
                                .append("\n"));

        return str.toString();
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pieces pieces1 = (Pieces) o;
        return Objects.equals(pieces, pieces1.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}
