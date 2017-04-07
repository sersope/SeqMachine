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
 * Implementa una etapa de la lista
 * TODO Desarrollar y explicar un poco 
 * @author Sergio Soriano Peiró
 */
public class Stage {
    private final String id;
    private final String description;
    private long initTime;
    private long duration;
    private final ArrayList<SignalSetpoint> actions;
    private final ArrayList<Transition> transitions;
    
    /**
     *
     * @param i ID de la etapa
     * @param d Descripción de la etapa
     */
    public Stage(String i, String d) {
        id = i;
        description = d;
        actions = new ArrayList<>();
        transitions = new ArrayList<>();
        duration = 0L;
    }
    
    /**
     *
     * @return Devuele el ID
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return Devuelve la descripción
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Cambia la duración de la etapa
     * @param d Duración en miliseconds
     */
    public void setDuration(long d) {
        duration = d * 1000000L; //Convert to nanoseconds
    }
    
    /**
     * Señala el comienzo de la etapa.
     */
    public void resetTime() {
        initTime = System.nanoTime();
    }
    
    /**
     * Actualiza el tiempo transcurrido de la etapa, desde que se inició.
     * @return Tiempo en nanosegundos transcurridos desde el inicio de la etapa.
     */
    public long uptime() {
        return (System.nanoTime() - initTime);
    }
    
    /**
     * Añade una acción a la etapa.
     * @param a Objeto SignalSetpoint como acción.
     * @return true si la acción se ha añadido correctamente.
     */
    public boolean addAction(SignalSetpoint a) {
        return actions.add(a);
    }
    
    /**
     * Añade una transición a la etapa.
     * @param t Objeto Transition añadido.
     * @return true si la transición se ha añadido correctamente.
     */
    public boolean addTransition(Transition t) {
        return transitions.add(t);
    }
    
    /*TODO
        processActions
        )
    */
    public String toString() {
        return id + " (" + description + ")";
    }
}
