package pers.ssp;

public class Exercise0009 {

//    Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
//
//    The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
//
//    Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
//
//    Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
//
//
//
//    Example 1:
//
//    Input: piles = [5,3,4,5]
//    Output: true
//    Explanation:
//    Alex starts first, and can only take the first 5 or the last 5.
//    Say he takes the first 5, so that the row becomes [3, 4, 5].
//    If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
//    If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
//    This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
//
//
//    Constraints:
//
//    2 <= piles.length <= 500
//    piles.length is even.
//    1 <= piles[i] <= 500
//    sum(piles) is odd.

    public static void main(final String[] args) {
        final Exercise0009 ex = new Exercise0009();
        final int[] piles = { 3, 2, 10, 4 };
        final boolean result = ex.stoneGame(piles);
        System.out.println(result);
    }

    private void pickNextPile(final int[] piles, final int startIndex, final int endIndex) {
// TODO complete this
    }

    public boolean stoneGame(final int[] piles) {
        final int startIndex = 0;
        final int endIndex = piles.length - 1;
        pickNextPile(piles, startIndex, endIndex);
        return false;
    }

    public boolean stoneGame1(final int[] piles) {
        int sumAlex = 0;
        int sumLee = 0;

        int remainingPiles = piles.length;
        boolean turnOfAlex = true;
        int startIndex = 0;
        int endIndex = piles.length - 1;
        while (remainingPiles > 0) {
            if (remainingPiles == 1) {
                if (turnOfAlex) {
                    sumAlex += piles[startIndex];
                } else {
                    sumLee += piles[startIndex];
                }
                startIndex++;
            } else if (piles[startIndex] > piles[endIndex]) {
                if (turnOfAlex) {
                    sumAlex += piles[startIndex];
                } else {
                    sumLee += piles[startIndex];
                }
                startIndex++;
            } else if (piles[startIndex] == piles[endIndex]) {
                if (piles.length > 3) {
                    if (piles[startIndex + 1] > piles[endIndex - 1]) {
                        if (turnOfAlex) {
                            sumAlex += piles[endIndex];
                        } else {
                            sumLee += piles[endIndex];
                        }
                        endIndex--;
                    } else {
                        if (turnOfAlex) {
                            sumAlex += piles[startIndex];
                        } else {
                            sumLee += piles[startIndex];
                        }
                        startIndex++;
                    }
                } else {
                    if (turnOfAlex) {
                        sumAlex += piles[startIndex];
                    } else {
                        sumLee += piles[startIndex];
                    }
                    startIndex++;
                }
            } else {
                if (turnOfAlex) {
                    sumAlex += piles[endIndex];
                } else {
                    sumLee += piles[endIndex];
                }
                endIndex--;
            }

            turnOfAlex = !turnOfAlex;
            remainingPiles--;
        }

        return (sumAlex > sumLee);
    }
}
