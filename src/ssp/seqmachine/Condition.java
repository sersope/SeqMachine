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

/**
 * La clase asocia una señal a una señal setpoint.
 * Usado como condición compara el valor de la señal con el de la setpoint.
 * Usado como acción (de etapa) actualiza el valor de la señal al de la setpoint.
 * @author Sergio Soriano Peiró
 * 
 */
public class Condition {
    private final String description;
    private final Signal signal;
    private final Signal setpoint;
    
    // TODO Generar la descripción de forma automática según la señal y el setpoint y si es accion o condicion
    //      Olvidarse del identificador
    
    /**
     * Constructor
     * @param signal Señal
     * @param setpoint  Signal setpoint
     */
    public Condition(Signal signal, Signal setpoint) {
        description = "Setpoint of " + signal.getId() + " = " + setpoint.getId();
        this.signal = signal;
        this.setpoint = setpoint;
    }
    
    /**
     * Usado como acción en una etapa.
     * Cambia el valor de la señal al de la señal de setpoint.
     */
    public void apply() {
        signal.setValue(setpoint.getValue());
    }
    
    /**
     * Usado como condición en una transición.
     * Devuelve true si el valor de la señal coincide con el setpoint.
     * @return true si el valor de la señal coincide con el setpoint
     */
    public boolean isTrue() {
        return (signal.getValue() == setpoint.getValue());
    }

    /**
     *
     * @return "id - descripción"
     */
    @Override
    public String toString() {
        return description;
    }
    
}
