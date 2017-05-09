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
    private LinkedList<HistoryPoint> history;
    private static final int STEP = 2;
    private static final int STACK_SIZE = 1;

    class HistoryPoint {
        private int index;
        private final List<Integer> stack;

        public HistoryPoint(int index, List<Integer> stack) {
            this.index = index;
            this.stack = new ArrayList<>(stack);
        }

        @Override
        public String toString() {
            return "HistoryPoint{" +
                    "index=" + index +
                    ", stack=" + stack +
                    '}';
        }

        public int getIndex() {
            return index;
        }

        public List<Integer> getStack() {
            return new ArrayList<>(this.stack);
        }
    }

    public DFSSolver(List<Integer> stacks) {
        this.stacks = stacks;
        this.history = new LinkedList<>();
        this.history.add(new HistoryPoint(0, stacks));
    }

    private int getNextAvailable(int startIndex) {
        for (int i = startIndex; i < stacks.size(); i++) {
            if (stacks.get(i) <= STACK_SIZE && (canMoveLeft(i) || canMoveRight(i))) {
                return i;
            }
        }
        return -1;
    }

    private boolean canMoveLeft(int index) {
        if (index >= stacks.size() || index < 0 || index - STEP < 0) {
            return false;
        }
        int coins = stacks.get(index - STEP);
        if (coins > STACK_SIZE) {
            return false;
        }
        return true;
    }

    private boolean moveLeft(int index) {
        if (!canMoveLeft(index)) {
            return false;
        }
        int coins = stacks.get(index - STEP);
        stacks.set(index - STEP, coins + 1);
        stacks.remove(index);
        return true;
    }

    private boolean canMoveRight(int index) {
        if (index >= stacks.size() || index < 0 || index + STEP >= stacks.size()) {
            return false;
        }
        int coins = stacks.get(index + STEP);
        if (coins > STACK_SIZE) {
            return false;
        }
        return true;
    }

    private boolean moveRight(int index) {
        if (!canMoveRight(index)) {
            return false;
        }
        int coins = stacks.get(index + STEP);
        stacks.set(index + STEP, coins + 1);
        stacks.remove(index);
        return true;
    }

    public void solve2(int q) {
        int startIndex = 0;

        while (true) {
            System.out.println(stacks);
            int index = getNextAvailable(startIndex);
            if (index == -1) {
                stacks = history.pop().stack;
                startIndex = 0;
                continue;
            }
            if (moveLeft(index) || moveRight(index)) {
                history.push(new HistoryPoint(0, stacks));
            }
            startIndex++;
        }
    }

    public void solve(int startIndex) {
        System.out.println(stacks);
        int index = getNextAvailable(startIndex);

        if (moveLeft(index)) {
            history.push(new HistoryPoint(index, stacks));
            solve(startIndex + 1);
        }

        if (moveRight(index)) {
            history.push(new HistoryPoint(index, stacks));
            solve(startIndex + 1);
        }

        if (index == -1) {
            HistoryPoint temp = history.pop();
            HistoryPoint hp = history.peek();
            stacks = hp.getStack();
        }
    }

    public static void main(String[] args) {
        List<Integer> stacks = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            stacks.add(1);
        }
        DFSSolver t = new DFSSolver(stacks);
        t.solve(0);
    }


}
