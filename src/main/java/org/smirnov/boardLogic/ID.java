package org.smirnov.boardLogic;

public enum ID {
    KING {
        @Override
        public String toString() {
            return "K";
        }
        public String toFullString() {
            return "org.smirnov.domains.King";
        }
    },
    QUEEN {
        @Override
        public String toString() {
            return "Q";
        }
        public String toFullString() {
            return "org.smirnov.domains.Queen";
        }
    },
    ROOK {
        @Override
        public String toString() {
            return "R";
        }
        public String toFullString() {
            return "org.smirnov.domains.Rook";
        }
    },
    BISHOP {
        @Override
        public String toString() {
            return "B";
        }
        public String toFullString() {
            return "org.smirnov.domains.Bishop";
        }
    },
    KNIGHT {
        @Override
        public String toString() {
            return "N";
        }
        public String toFullString() {
            return "org.smirnov.domains.Knight";
        }
    },
    PAWN {
        @Override
        public String toString() {
            return "";
        }
        public String toFullString() {
            return "org.smirnov.domains.Pawn";
        }
    },

    JOKER {
        @Override
        public String toString(){return "J";}

        public String toFullString(){return "org.smirnov.domains.Joker";}
    };

    public abstract String toFullString();

}
