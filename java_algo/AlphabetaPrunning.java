package JavaAlgo;

public class AlphabetaPrunning {
    public int alphabeta(GameState inState, int depth, int alpha, int beta) {
        boolean isMyTurn = true;

        if (depth == 0 || inState.gameIsOver()) {
            return getEvaluation(inState);
        }

        else {
            if (isMyTurn) {
                // A clone is needed to keep the game "instance" similar.
                GameState temp = inState.clone();

                int runningMax = alphabeta(temp, depth - 1, alpha, beta);
                    
                if (runningMax > alpha)
                    alpha = runningMax;

                if (beta <= alpha)
                    return alpha;
                return alpha;
            }
           
            else {
                GameState temp = inState.clone();
                int runningMin = alphabeta(temp, depth - 1, alpha, beta);
                    
                if (runningMin < beta)
                    beta = runningMin;

                if (beta <= alpha)
                    return beta;
                return beta;
            }
        }
    }

    public int getEvaluation(GameState inState) {
        int totalScore = 0, blackScore = 0, whiteScore = 0;
        boolean isMyTurn = true;

        if (isMyTurn)
            totalScore = blackScore - whiteScore;
        else
            totalScore = whiteScore - blackScore;

        return totalScore;
    }
}

interface GameState {
    public GameState getCell(int row, int col);

    public boolean isBlackTurn();

    public boolean gameIsOver();

    public boolean isValidMove(int row, int col);

    public void makeMove(int row, int col);

    public int getBlackScore();

    public int getWhiteScore();

    public GameState clone();
}
