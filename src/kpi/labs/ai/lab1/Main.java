package kpi.labs.ai.lab1;

import kpi.labs.ai.lab1.deprecated.Coin;
import kpi.labs.ai.lab1.deprecated.CoinPileChain;

/**
 * Created by 4an70m on 09.04.2017.
 */
public class Main {
    public static void main(String[] args) {

        CoinPileChain cpc = new CoinPileChain();
        for (int i = 0; i < 15; i++) {
            cpc.addNewPileWithCoin(new Coin());
        }
        System.out.println(cpc);
        System.out.println(cpc);
    }
}
