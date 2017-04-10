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
 * La clase asocia una señal a un setpoint.
 * Usado como condición compara el valor de la señal con el setpoint.
 * Usado como acción (de etapa) actualiza el valor de la señal al setpoint.
 * @author Sergio Soriano Peiró
 * 
 */
public class Setpoint {
    private final String description;
    private final Signal signal;
    private int setpoint;
    
    // TODO Generar la descripción de forma automática según la señal y el setpoint y si es accion o condicion
    //      Olvidarse del identificador
    
    /**
     * Constructor
     * @param s Señal
     * @param sp Valor de setpoint
     */
    public Setpoint(Signal signal, int setpoint) {
        description = "Setpoint of " + signal.getId() + " = " + setpoint;
        this.signal = signal;
        this.setpoint = setpoint;
    }
    
    /**
     * Actualiza el valor de setpoint
     * @param sp nuevo valor de setpoint
     */
    public void setSetpoint(int sp) {
        setpoint = sp;
    }

    /**
     * Usado como acción en una etapa.
     * Cambia el valor de la señal al setpoint.
     */
    public void apply() {
        signal.setValue(setpoint);
    }
    
    /**
     * Usado como condición en una transición.
     * Devuelve true si el valor de la señal coincide con el setpoint.
     * @return true si el valor de la señal coincide con el setpoint
     */
    public boolean isTrue() {
        return (signal.getValue() == setpoint);
    }

    /**
     *
     * @return "id (descripción) sp = setpoint"
     */
    @Override
    public String toString() {
        return description;
    }
    
}