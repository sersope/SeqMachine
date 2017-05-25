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
import com.cedarsoftware.util.io.JsonWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import ssp.seqmachine.*;

/**
 *
 * @author sergio
 */
public class TestSeqMachine {
    public static void main(String[] args) throws FileNotFoundException {
        
        SeqMachine maquina = new SeqMachine("Maniobra para encender la luz");
        // Señales
        Signal di0 = new Signal("DI0", "Interruptor de pared", 0);
        System.out.println(di0);
        maquina.addSignal(di0);
        
        Signal do0 = new Signal("DO0", "Luz del pasillo", 0);
        System.out.println(do0);
        maquina.addSignal(do0);
        
        // Etapas
        State etapa0 = new State("E0", "Luz apagada");
        System.out.println(etapa0);
        maquina.addState(etapa0);
        
        State etapa1 = new State("E1", "Luz encendida");
        System.out.println(etapa1);
        maquina.addState(etapa1);
        
        //Acciones
        Action a = new Action(do0, SignalValue.OFF);
        System.out.println(a);
        etapa0.addAction(a);
        a = new Action(do0, SignalValue.ON);
        System.out.println(a);
        etapa1.addAction(a);
        // Transiciones
        Transition t = new Transition(etapa1);
        Condition c = new Condition(di0, SignalValue.ON);
        System.out.println(c);
        t.addCondition(c);
        etapa0.addTransition(t);

        t = new Transition(etapa0);
        c = new Condition(di0, SignalValue.OFF);
        System.out.println(c);
        t.addCondition(c);
        etapa1.addTransition(t);

        System.out.println("Transitions:");
        System.out.print(etapa0.transitionsToString());
        System.out.print(etapa1.transitionsToString());
        
        System.out.println();
        System.out.println(do0);
        maquina.init(etapa0);
        System.out.println(do0);
        di0.setValue(1);
        maquina.update();
        System.out.println(do0);
        di0.setValue(0);
        maquina.update();
        System.out.println(do0);
        
        HashMap options = new HashMap();
        options.put(JsonWriter.PRETTY_PRINT, true);
        JsonWriter jsonw = new JsonWriter(new FileOutputStream("maquina.json"), options);
        jsonw.write(maquina);
        jsonw.close();
    }
    
}
