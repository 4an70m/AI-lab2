package kpi.labs.ai.lab1.deprecated;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 4an70m on 09.04.2017.
 */
public class ActionSequence {

    private List<Action> sequence;

    public ActionSequence() {
        this.sequence = new ArrayList<>();
    }

    public void addAction(Action action) {
        this.sequence.add(action);
    }


}
