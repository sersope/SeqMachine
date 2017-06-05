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
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JPanel;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.tree.DefaultMutableTreeNode;
import ssp.seqmachine.*;

/**
 *
 * @author sergio
 */
public class SeqMachineCanvas extends JPanel {

    private final Dimension grid;
    private Node selectedNode;
    private String statusText = new String();
    private SeqMachine machine;
    private final SwingPropertyChangeSupport pcs;

    public SeqMachineCanvas() {
        grid = Node.getSIZE();
        selectedNode = null;
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

    private void onMouseDragged(MouseEvent e) {
        if (this.contains(e.getX(), e.getY())) {
            int x = e.getX() / grid.width * grid.width;
            int y = e.getY() / grid.height * grid.height;
            selectedNode.moveTo(x, y);
            repaint();
        }
    }

    private void onMouseMoved(MouseEvent e) {
    }

    private void onMousePressed(MouseEvent e) {
//        // Pulsado el botón derecho
//        if (e.getButton() == MouseEvent.BUTTON3) {
//            return;
//        }
        // Deselecciona nodo previamente seleccionado
        if (selectedNode != null) {
            selectedNode.setSelected(false);
            selectedNode = null;
            setSelectedNode(false);
            repaint();
        }
        // Determina si se ha pulsado sobre un node
        boolean nodePulsado = false;
        if (machine != null) {
            for (Enumeration en = machine.tree.preorderEnumeration(); en.hasMoreElements();) {
                Node n = (Node) en.nextElement();
                if (n.contains(e.getX(), e.getY())) {
                    // Se ha pulsado sobre un node
                        setSelectedNode(true);
                        setStatusText("Seleccionado " + n.getState().getId());
                        n.setSelected(true);
                        selectedNode = n;
                    repaint();
                    break;
                }
            }
        }
//        if (nodePulsado != null) {
//            movingNode = nodePulsado;
//            //Pulsacion doble click: edita el estado asociado al nodo
//            if (e.getClickCount() == 2) {
//                JDIalogEditState dlg = new JDIalogEditState(null, true, nodePulsado.getState());
//                dlg.setTitle("State " + nodePulsado.getState().getId());
//                dlg.setVisible(true);
//                return;
//            }
//            // Pulsacion + tecla SHIFT: selecciona como primer nodo de un nuevo edge
//            //                          y por segunda pulsacion crea el nuevo edge
//            int shiftMask = MouseEvent.SHIFT_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK;
//            int modifiers = e.getModifiersEx();
//            if ((modifiers & shiftMask) == shiftMask && e.getClickCount() == 1) {
//                if (selectedNode != null) {
//                    //Crea transición
////                    Transition t = new Transition(unSicon.getState());
////                    selectedNode.getState().addTransition(t);
////                    machine.getImage().add(new TransitionIcon(t, selectedNode, unSicon));
////                    selectedNode.setSelected(false);
////                    setSelectedNode(null);
//                } else {
//                    setSelectedNode(nodePulsado);
//                    selectedNode.setSelected(true);
//                    setStatusText("Pulse otro nodo para crear un edge");
//                    deseleccion = false;
//                }
//                repaint();
//            }
//        }
//        // Se ha pulsado fuera de un nodo: deselecciona
//        if (!nodePulsado) {
//            setSelectedNode(-selectedNodesCount);
//            for (DefaultMutableTreeNode tn : selectedTreeNodes) {
//                Node n = (Node) tn.getUserObject();
//                n.setSelected(false);
//            }
//            selectedTreeNodes.clear();
//            repaint();
//        }
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

    public void setSelectedNode(boolean v) {
        pcs.firePropertyChange("selectedNode", false, v);
    }

    /**
     * Set a machine
     *
     * @param machine SeqMachine
     */
    public void setMachine(SeqMachine machine) {
        selectedNode = null;
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
//            for (TransitionIcon ti : machine.getImage().getTransitionIcons()) {
//                ti.drawEdge(g2);
//            }
            for (Enumeration e = machine.tree.preorderEnumeration(); e.hasMoreElements();) {
                Node n = (Node) e.nextElement();
                n.draw(g2);
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
                Node n = new Node(s, selectedNode.getRect().x, selectedNode.getRect().y + 3 * grid.height, false);
                selectedNode.add(n);
                repaint();
            }
        }
    }

    public void createLink() {
        //TODO createLink
//        if (machine != null) {
//            if (selectedNode != null) {
//                machine.getImage().add(new Node(selectedNode.getState(), 0, 0, true));
//                selectedNode.setSelected(false);
//                setSelectedNode(null);
//                repaint();
//            }
//        }
    }
}
