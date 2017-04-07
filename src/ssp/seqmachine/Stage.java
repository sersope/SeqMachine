/*
 * The MIT License
 *
 * Copyright 2017 sergio.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ssp.seqmachine;

import java.util.*;

/**
 *
 * @author sergio
 */
public class Stage {
    private final String id;
    private final String description;
    private long initTime;
    private long duration;
    private final ArrayList<Action> actions;
    private final ArrayList<Transition> transitions;
    
    public Stage(String i, String d) {
        id = i;
        description = d;
        actions = new ArrayList<>();
        transitions = new ArrayList<>();
        duration = 0L;
    }
    
    public String getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    
    // d in miliseconds
    public void setDuration(long d) {
        duration = d * 1000000L; //Convert to nanoseconds
    }
    
    public void resetTime() {
        initTime = System.nanoTime();
    }
    
    public long updateTime() {
        return (System.nanoTime() - initTime);
    }
    
    public boolean addAction(Action a) {
        return actions.add(a);
    }
    
    public boolean addTransition(Transition t) {
        return transitions.add(t);
    }
    
    /*TODO
        processActions
        )
    */
    
}
