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
package ssp.seqmachine.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.SwingPropertyChangeSupport;
import ssp.seqmachine.*;

/**
 *
 * @author sergio
 */
public class SeqMachineCanvas extends JPanel {

    private final Dimension grid;
    private StateIcon nodeToMove, selectedNode;
    private String statusText = new String();
    private SeqMachine machine;
    private final SwingPropertyChangeSupport pcs;
    private JPopupMenu popup;
//    private final ArrayList<StateIcon> nodes;
//    private final ArrayList<TransitionIcon> edges;

    public SeqMachineCanvas() {
        this.grid = StateIcon.getSIZE();
        initComponent();
        pcs = new SwingPropertyChangeSupport(this);
//        nodes = new ArrayList<>();
//        edges = new ArrayList<>();
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

        menuItem = new JMenuItem("New state...");
        menuItem.setFont(mfont);
        menuItem.addActionListener((e) -> {
            createState();
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("New link to state");
        menuItem.setFont(mfont);
        menuItem.addActionListener((e) -> {
            createLink();
        });
        popup.add(menuItem);
    }

    public void createMachine() {

        JDialogEditMachine dlg = new JDialogEditMachine(null, true);
        dlg.setVisible(true);
        String desc = dlg.getTextDescription();
        if (desc != null) {
            setMachine(new SeqMachine(desc));
        }
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

//        State etapa3 = new State("E3", "Luz máxima");
//        System.out.println(etapa3);
//        maquina.addState(etapa3);
//        //Acciones
//        Action a = new Action(do0, SignalValue.OFF);
//        System.out.println(a);
//        etapa0.addAction(a);
//        a = new Action(do0, SignalValue.ON);
//        System.out.println(a);
//        etapa1.addAction(a);
//        // Transiciones
//        Transition t = new Transition(etapa1);
//        Condition c = new Condition(di0, SignalValue.ON);
//        System.out.println(c);
//        t.addCondition(c);
//        etapa0.addTransition(t);
//
//        t = new Transition(etapa0);
//        c = new Condition(di0, SignalValue.OFF);
//        System.out.println(c);
//        t.addCondition(c);
//        etapa1.addTransition(t);
//        etapa1.addTransition(new Transition(etapa2));
//        System.out.println("Transitions:");
//        System.out.print(etapa0.transitionsToString());
//        System.out.print(etapa1.transitionsToString());

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
            if (nodeToMove != null) {
                int x = (e.getX() / grid.width) * grid.width;
                int y = (e.getY() / grid.height) * grid.height;
                nodeToMove.moveTo(x, y);
                repaint();
            }
        }
    }

    private void onMouseMoved(MouseEvent e) {
    }

    private void onMousePressed(MouseEvent e) {
        boolean deseleccion = true;
        // Muestra el popup menu
        if (e.getButton() == MouseEvent.BUTTON3) {
            popup.show(this, e.getX(), e.getY());
            return;
        }
        //Determina si se ha pulsado sobre un node
        nodeToMove = null;
        StateIcon unSicon = null;
        if (machine != null) {
            for (StateIcon si : machine.getImage().getStateIcons()) {
                if (si.contains(e.getX(), e.getY())) {
                    setStatusText("Seleccionado " + si.getState().getId() + ". Double click to edit.");
                    unSicon = si;
                    break;
                }
            }
        }
        if (unSicon != null) {
            nodeToMove = unSicon;
            //Si es doble click edita el estado asociado al nodo
            if (e.getClickCount() == 2) {
                JDIalogEditState dlg = new JDIalogEditState(null, true, unSicon.getState());
                dlg.setTitle("State " + unSicon.getState().getId());
                dlg.setVisible(true);
                return;
            }
            // Si es una pulsacion + tecla SHIFT selecciona como primer nodo de un nuevo edge
            // y por segunda pulsacion crea el nuevo edge
            int shiftMask = MouseEvent.SHIFT_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK;
            int modifiers = e.getModifiersEx();
            if ((modifiers & shiftMask) == shiftMask && e.getClickCount() == 1) {
                if (selectedNode != null) {
                    //Crea transición
                    Transition t = new Transition(unSicon.getState());
                    selectedNode.getState().addTransition(t);
                    machine.getImage().add(new TransitionIcon(t, selectedNode, unSicon));
                    selectedNode.setSelected(false);
                    selectedNode = null;
                } else {
                    selectedNode = unSicon;
                    selectedNode.setSelected(true);
                    setStatusText("Pulse otro nodo para crear un edge");
                    deseleccion = false;
                }
                repaint();
            }
        }
        if (deseleccion == true && selectedNode != null) {
            selectedNode.setSelected(false);
            selectedNode = null;
            repaint();
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l
    ) {
        pcs.addPropertyChangeListener("statusText", l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l
    ) {
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
        nodeToMove = null;
        this.machine = machine;
        setStatusText(this.machine.getDescription());
//        int i = 0;
//        // Para cada estado de la machine crea su nodo
//        for (State s : this.machine.getStates()) {
//            machine.getImage().add(new StateIcon(s, 16 * grid.width, 2 * grid.height + 4 * grid.height * i, false));
//            i++;
//        }
        // Para cada transicion de la maquina crea su edge
//        for (State s : this.machine.getStates()) {
//            for (Transition t : s.getTransitions()) {
//                TransitionIcon e = new TransitionIcon(t, s.getNode(), t.getFinalState().getNode());
//                t.setEdge(e);
//            }
//        }
        repaint();
    }

    public SeqMachine getMachine() {
        return machine;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (machine != null) {
            //Dibujo de nodos y edges
            for (TransitionIcon ti : machine.getImage().getTransitionIcons()) {
                ti.draw(g2);
            }
            for (StateIcon si : machine.getImage().getStateIcons()) {
                si.draw(g2);
            }
            if (nodeToMove != null) {
                nodeToMove.draw(g2);
            }
        }
    }

    public void createState() {
        if (machine != null) {
            Dimension d = this.getSize();
            State s = new State();
            JDIalogEditState dlg = new JDIalogEditState(null, true, s);
            dlg.setTitle("New State");
            dlg.setVisible(true);
            if (s.getId() != null) {
                machine.getImage().add(new StateIcon(s, d.width / 2, d.height / 2, false));
                machine.addState(s);
                repaint();
            }

        }
    }

    public void createLink() {
        if (machine != null) {
            if (selectedNode != null) {
                machine.getImage().add(new StateIcon(selectedNode.getState(), 0, 0, true));
                selectedNode.setSelected(false);
                selectedNode = null;
                repaint();
            }

        }
    }
}
