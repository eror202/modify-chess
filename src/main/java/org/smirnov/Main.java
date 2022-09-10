package org.smirnov;

import org.smirnov.boardLogic.GUIBoard;
import org.smirnov.domains.Pieces;

public class Main {


    public static void main(String[] args) {

        Pieces pieces = new Pieces();
        pieces.setGUIGame(true);
        new GUIBoard(pieces);

    }
}
