package kpi.labs.ai.lab1.coinproblem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 4an70m on 09.04.2017.
 */
public class CoinPileChain {

    public static final int SHIFT_SIZE = 3;

    private int chainSize;
    private Map<Integer, CoinPile> chain;

    public CoinPileChain() {
        this.chainSize = 0;
        this.chain = new HashMap<Integer, CoinPile>();
    }

    /*=====================================*/
    /*       PUBLIC INTERFACE              */
    /*=====================================*/

    public void addNewPileWithCoin(Coin coin) {
        this.chainSize++;
        this.chain.put(chainSize, new CoinPile(coin));
    }

    public Boolean shiftCoinLeft(int pileNumber) {
        int newCoinPlace = pileNumber - SHIFT_SIZE;
        if (newCoinPlace < 0 || newCoinPlace > this.chainSize) {
            return false;
        }
        return this.moveCoinToAnotherPile(pileNumber, newCoinPlace);
    }

    public Boolean shiftCoinRight(int pileNumber) {
        int newCoinPlace = pileNumber + SHIFT_SIZE;
        if (newCoinPlace < 0 || newCoinPlace > this.chainSize) {
            return false;
        }
        return this.moveCoinToAnotherPile(pileNumber, newCoinPlace);
    }

    /*=====================================*/
    /*       PRIVATE INTERFACE             */
    /*=====================================*/
    private Boolean moveCoinToAnotherPile(int fromPile, int toPile) {
        Coin grabbedCoin = this.grabCoin(fromPile);
        if (grabbedCoin == null) {
            return false;
        }
        this.putCoin(toPile, grabbedCoin);
        return true;
    }

    private Coin grabCoin(int pileNumber) {
        return this.chain.get(pileNumber).popCoin();
    }

    private void putCoin(int toPile, Coin coin) {
        this.chain.get(toPile).pushCoin(coin);
    }

    @Override
    public String toString() {
        return "\n" + chain;
    }
}
