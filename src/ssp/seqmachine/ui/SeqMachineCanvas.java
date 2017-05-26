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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.event.SwingPropertyChangeSupport;
import ssp.seqmachine.*;

/**
 *
 * @author sergio
 */
public class SeqMachineCanvas extends JPanel {

    private final Dimension grid;
    private StateIcon movingIcon, selectedIcon;
    private String statusText = new String();
    private SeqMachine machine;
    private final SwingPropertyChangeSupport pcs;

    public SeqMachineCanvas() {
        this.grid = StateIcon.getSIZE();
        initComponent();
        pcs = new SwingPropertyChangeSupport(this);
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
    }

    // TODO Definir la gestion con el raton
    private void onMouseDragged(MouseEvent e) {
        if (this.contains(e.getX(), e.getY())) {
            if (movingIcon != null) {
                int x = (e.getX() / grid.width) * grid.width;
                int y = (e.getY() / grid.height) * grid.height;
                movingIcon.moveTo(x, y);
                repaint();
            }
        }
    }

    private void onMouseMoved(MouseEvent e) {
    }

    private void onMousePressed(MouseEvent e) {
        boolean deseleccion = true;
        // Pulsado el botón derecho
        if (e.getButton() == MouseEvent.BUTTON3) {
            return;
        }
        //Determina si se ha pulsado sobre un node
        movingIcon = null;
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
            movingIcon = unSicon;
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
                if (selectedIcon != null) {
                    //Crea transición
                    Transition t = new Transition(unSicon.getState());
                    selectedIcon.getState().addTransition(t);
                    machine.getImage().add(new TransitionIcon(t, selectedIcon, unSicon));
                    selectedIcon.setSelected(false);
                    setSelectedIcon(null);
                } else {
                    setSelectedIcon(unSicon);
                    selectedIcon.setSelected(true);
                    setStatusText("Pulse otro nodo para crear un edge");
                    deseleccion = false;
                }
                repaint();
            }
        }
        if (deseleccion == true && selectedIcon != null) {
            selectedIcon.setSelected(false);
            setSelectedIcon(null);
            repaint();
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l
    ) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l
    ) {
        pcs.removePropertyChangeListener(l);
    }

    public void setStatusText(String newstatusText) {
        String old = statusText;
        this.statusText = newstatusText;
        pcs.firePropertyChange("statusText", old, statusText);
    }

    public String getStatusText() {
        return statusText;
    }

    public void setSelectedIcon(StateIcon selectedIcon) {
        this.selectedIcon = selectedIcon;
        boolean old = (selectedIcon == null) ? true : false;
        pcs.firePropertyChange("iconSelected", old, !old);
    }

    /**
     * Set a machine
     *
     * @param machine SeqMachine
     */
    public void setMachine(SeqMachine machine) {
        movingIcon = null;
        this.machine = machine;
        setStatusText(this.machine.getDescription());
        pcs.firePropertyChange("machineSelected", false, true);
        repaint();
    }

    public SeqMachine getMachine() {
        return machine;
    }

    @Override
    protected void paintComponent(Graphics g) {
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
            if (movingIcon != null) {
                movingIcon.draw(g2);
            }
        }
    }

    public void createMachine() {
        JDialogEditMachine dlg = new JDialogEditMachine(null, true);
        dlg.setVisible(true);
        String desc = dlg.getTextDescription();
        if (desc != null) {
            setMachine(new SeqMachine(desc));
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
            if (selectedIcon != null) {
                machine.getImage().add(new StateIcon(selectedIcon.getState(), 0, 0, true));
                selectedIcon.setSelected(false);
                setSelectedIcon(null);
                repaint();
            }
        }
    }
}
