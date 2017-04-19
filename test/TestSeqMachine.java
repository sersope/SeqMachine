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
import ssp.seqmachine.*;

/**
 *
 * @author sergio
 */
public class TestSeqMachine {
    public static void main(String[] args) {
        // Señales
        Signal di0 = new Signal("DI0", "Interruptor de pared", 0);
        Signal do0 = new Signal("DO0", "Luz del pasillo", 0);
        
        // Etapas
        State etapa0 = new State("E0", "Luz apagada");
        State etapa1 = new State("E1", "Luz encendida");
        //Acciones
        etapa0.addAction(new Action(do0, SignalValue.OFF));
        etapa1.addAction(new Action(do0, SignalValue.ON));
        // Transiciones
        Transition t = new Transition(etapa1);
        t.addCondition(new Condition(di0, SignalValue.ON));
        etapa0.addTransition(t);
        
        t = new Transition(etapa0);
        t.addCondition(new Condition(di0, SignalValue.OFF));
        etapa1.addTransition(t);
        
        System.out.println(etapa0);
                
        System.out.println(do0);
                
    }
    
}
