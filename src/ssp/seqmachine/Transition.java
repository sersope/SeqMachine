/*
 * The MIT License
 *
 * Copyright 2017 Sergio Soriano Peiró.
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
 * Implementa la transición de la etapa a la siguiente si se cumplen las condiciones
 * @author Sergio Soriano Peiró
 */
public class Transition {
    private final State finalState;
    private final ArrayList<Condition> conditions;
    private Edge edge;
    
    /**
     * Constructor
     * @param fs etapa final
     */
    public Transition(State fs) {
        finalState = fs;
        conditions = new ArrayList<>();
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
    
    public State getFinalState() {
        return finalState;
    }
    
    /**
     * Añade condicion a la lista de condiciones
     * @param c condiciona a añadir
     * @return true si la condición se ha añadido satisfactoriamente.
     */
    public boolean addCondition(Condition c) {
        return conditions.add(c);
    }
    
    /**
     * Comprueba que todas las condiciones se cumplen
     * @return true si todas las condiciones se cumplen.
     */
    public boolean isSuperable() {
        for (Condition c : conditions)
            if (!c.isTrue())
                return false;
        return true;
    }
    
    /**
     * Override toString method
     * @return "id (descripción) value= valor" 
     */
    @Override
    public String toString() {
        return " -> " + finalState;
    }
    
}
