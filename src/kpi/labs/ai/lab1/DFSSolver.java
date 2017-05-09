package kpi.labs.ai.lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 4an70m on 01.05.2017.
 */
public class DFSSolver {
    private List<Integer> stacks;
    private LinkedList<List<Integer>> history;
    private static final int STEP = 1;
    private static final int STACK_SIZE = 1;

    public DFSSolver(List<Integer> stacks) {
        this.stacks = stacks;
        this.history = new LinkedList<>();
        this.history.add(new ArrayList<>(stacks));
    }

    private int getNextAvailable(int startIndex) {
        if (startIndex >= stacks.size()) {
            startIndex -= stacks.size();
        }
        for (int i = startIndex; i < stacks.size(); i++) {
            if (stacks.get(i) <= STACK_SIZE) {
                return i;
            }
        }
        for (int i = 0; i < startIndex; i++) {
            if (stacks.get(i) <= STACK_SIZE) {
                return i;
            }
        }
        return -1;
    }

    private boolean moveLeft(int index) {
        if (index >= stacks.size() || index < 0 || index - STEP < 0) {
            return false;
        }
        int coins = stacks.get(index - STEP);
        if (coins > STACK_SIZE) {
            return false;
        }
        stacks.set(index - STEP, coins + 1);
        stacks.remove(index);
        return true;
    }

    private boolean moveRight(int index) {
        if (index >= stacks.size() || index < 0 || index + STEP >= stacks.size()) {
            return false;
        }
        int coins = stacks.get(index + STEP);
        if (coins > STACK_SIZE) {
            return false;
        }
        stacks.set(index + STEP, coins + 1);
        stacks.remove(index);
        return true;
    }

    public void solve(int startIndex) {
        Integer index = getNextAvailable(startIndex);
        System.out.println(Arrays.toString(stacks.toArray()));
        if (index == -1) {
            return;
        }
        if (moveLeft(index)) {
            history.push(new ArrayList<>(stacks));
            solve(startIndex);
        }
        stacks = history.pop();
        if (moveRight(index)) {
            history.push(new ArrayList<>(stacks));
            solve(startIndex);
        }
    }

    public static void main(String[] args) {
        List<Integer> stacks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stacks.add(1);
        }
        DFSSolver t = new DFSSolver(stacks);
        t.solve(0);
    }


}
