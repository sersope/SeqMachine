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
public class SeqMachine {
    private final String description;
    private final ArrayList<State> estados;
    private final ArrayList<Signal> senyales;
    private State initState;
    private State actualState;
    
    /**
     *
     * @param description
     */
    public SeqMachine(String description) {
        this.description = description;
        estados = new ArrayList<>();
        senyales = new ArrayList<>();
    }
    
    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public boolean addSignal(Signal s) {
        return senyales.add(s);
    }
    
    /**
     *
     * @param e
     * @return
     */
    public boolean addState(State e) {
        return estados.add(e);
    }
    
    /**
     *
     * @param e
     */
    public void setInitState(State e) {
        initState = e;
    }
    
}
