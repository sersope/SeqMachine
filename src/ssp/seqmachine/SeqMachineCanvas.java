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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.SwingPropertyChangeSupport;

/**
 *
 * @author sergio
 */
public class SeqMachineCanvas extends JPanel {

    private final Dimension grid;
    private Node movedNode;
    private String statusText = new String();
    private SeqMachine machine;
    private final SwingPropertyChangeSupport pcs;
    private JPopupMenu popup;

    public SeqMachineCanvas() {
        this.grid = Node.getSIZE();
        initComponent();
        pcs =  new SwingPropertyChangeSupport(this);

    }

    public void createPopupMenu() {
        //Create the popup menu.
        JMenuItem menuItem;
        Font mfont = new Font("Dialog", 0, 11);
        popup = new JPopupMenu("SeqMachine");
        
        menuItem = new JMenuItem("Test Machine");
        menuItem.setFont(mfont);
        menuItem.addActionListener((e) -> {
            createTestMachine();
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("New Machine ...");
        menuItem.setFont(mfont);
        menuItem.addActionListener((e) -> {
            createMachine();
        });
        popup.add(menuItem);        
        
        popup.addSeparator();
        
        menuItem = new JMenuItem("New state");
        menuItem.setFont(mfont);
        menuItem.addActionListener((e) -> {
            setStatusText("Popup 1");
        });
        popup.add(menuItem);
    }
    public void createMachine() {
        
        JDialogEditMachine dlg = new JDialogEditMachine(null, true);
        dlg.setVisible(true);
        String desc = dlg.getTextDescription();
        if (desc != null)
            setMachine(new SeqMachine(desc));
    }
    //PRUEBA
    public void createTestMachine() {
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

        State etapa2 = new State("E2", "A media Luz");
        System.out.println(etapa2);
        maquina.addState(etapa2);

        State etapa3 = new State("E3", "Luz máxima");
        System.out.println(etapa3);
        maquina.addState(etapa3);
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
        etapa1.addTransition(new Transition(etapa3));
        System.out.println("Transitions:");
        System.out.print(etapa0.transitionsToString());
        System.out.print(etapa1.transitionsToString());
        
        setMachine(maquina);
    }

    private void initComponent() {
        setBackground(Color.WHITE);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                onMouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                onMouseMoved(e);
            }

        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onMousePressed(e);
            }
        });
        createPopupMenu();
    }

    // TODO Definir la gestion con el raton
    private void onMouseDragged(MouseEvent e) {
        if (this.contains(e.getX(), e.getY())) {
            if (movedNode != null) {
                int x = (e.getX() / grid.width) * grid.width;
                int y = (e.getY() / grid.height) * grid.height;
                movedNode.moveTo(x, y);
                repaint();
            }
        }
    }

    private void onMouseMoved(MouseEvent e) {
        movedNode = null;
        if (machine != null)
            for (State s : machine.getStates()) {
                if (s.getNode().contains(e.getX(), e.getY())) {
                    setStatusText("Seleccionado " + s.getId() + " Clicks: " + e.getClickCount());
                    movedNode = s.getNode();
                    break;
                }
            }
    }

    private void onMousePressed(MouseEvent e) {
        // Muestra el popup menu
        if (e.getButton() == MouseEvent.BUTTON3) {
            popup.show(this, e.getX(), e.getY());
            return;
        }

        int shiftMask = MouseEvent.SHIFT_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK;
        int modifiers = e.getModifiersEx();
        if ((modifiers & shiftMask) == shiftMask) {
            setStatusText("SHIFT PULSADA");
        } else {
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener("statusText", l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener("statusText", l);
    }

    public void setStatusText(String newstatusText) {
        String old = statusText;
        this.statusText = newstatusText;
        pcs.firePropertyChange("statusText", old, statusText);
    }

    public String getStatusText() {
        return statusText;
    }

    /**
     * Set a machine
     *
     * @param machine SeqMachine
     */
    public void setMachine(SeqMachine machine) {
        movedNode = null;
        this.machine = machine;
        setStatusText(this.machine.getDescription());
        int i = 0;
        // Para cada estado de la machine crea su nodo
        for (State s : this.machine.getStates()) {
            Node n = new Node(s.getId(), 16 * grid.width, 2 * grid.height + 4 * grid.height * i);
            s.setNode(n);
            i++;
        }
        // Para cada transicion de la maquina crea su edge
        for (State s : this.machine.getStates()) {
            for (Transition t : s.getTransitions()) {
                Edge e = new Edge(s.getNode(), t.getFinalState().getNode());
                t.setEdge(e);
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (machine != null) {
            //Dibujo de nodos y edges
            for (State s : machine.getStates()) {
                for (Transition t : s.getTransitions()) {
                    t.getEdge().draw(g2);
                }
            }
            for (State s : machine.getStates()) {
                s.getNode().draw(g2);
            }
            if (movedNode != null) {
                movedNode.draw(g2);
            }
        }
    }
}
