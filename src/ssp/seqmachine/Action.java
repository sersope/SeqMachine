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

/**
 *
 * @author sergio
 * 
 */
public class Action {
    private final String id;
    private final String description;
    private final Signal signal;
    private int setpoint;
    
    public Action(String i, String d, Signal s, int sp) {
        id = i;
        description = d;
        signal = s;
        setpoint = sp;
    }
    
    public void setPoint(int sp) {
        setpoint = sp;
    }
    public void execute() {
        signal.setValue(setpoint);
    }
    
    public boolean isTrue() {
        return (signal.getValue() == setpoint);
    }

    @Override
    public String toString() {
        return id + " (" + description + ") " + "sp = " + setpoint;
    }
    
}
