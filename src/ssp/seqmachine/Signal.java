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
 * Implementa una señal (de entrada, salida o interna) de valor int.
 * Tiene un identificador y una descripción.
 * Para señal digital usar como valores Signal.ON o Signal.OFF.
 * @author sergio
 */
public class Signal {

    /**
     * Miembro estático que representa el valor ON de una señal digital.
     */
    public static final int ON = 1;

    /** Miembro estático que representa el valor OFF de una señal digital
     *
     */
    public static final int OFF = 0;

    private final String id;
    private final String description;
    private int value;

    /**
     * 
     * @param i Identificador de la señal
     * @param d Breve descripción
     * @param v Valor inicial
     */
    public Signal(String i, String d, int v) {
        id = i;
        description = d;
        value = v;
    }

    /**
     * Obtener el identificador de la señal
     * @return identificador
     */
    public String getId() {
        return id;
    }

    /**
     * Obtener la descripción
     * @return descripción
     */
    public String getDescription() {
        return description;
    }

    /**
     * Cambiar el valor de la señal
     * @param v valor
     */
    public void setValue(int v) {
        value = v;
    }

    /**
     * Obtener el valor de la señal
     * @return valor
     */
    public int getValue() {
        return value;
    }

    /**
     * Override toString method
     * @return "id (descripción) value= valor" 
     */
    @Override
    public String toString() {
        return id + " (" + description + ") value= " + value;
    }
}
