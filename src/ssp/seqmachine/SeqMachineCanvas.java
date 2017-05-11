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
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author sergio
 */
public class SeqMachineCanvas extends JPanel {

    private Dimension grid;
    private final ArrayList<Node> nodes = new ArrayList<>();
    private Node actualForma;
    private final ArrayList<Edge> edges = new ArrayList<>();
    private String statusText = new String();
    private SeqMachine machine;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private JPopupMenu popup;

    public SeqMachineCanvas() {
        this.grid = Node.getSIZE();
        initComponent();
    }

    public void createPopupMenu() {
        //Create the popup menu.
        JMenuItem menuItem;
        Font mfont = new Font("Dialog", 0, 11);
        popup = new JPopupMenu();
        menuItem = new JMenuItem("New Machine ...");
        menuItem.setFont(mfont);
        menuItem.addActionListener((e) -> {
            creaMaquinaTest();
        });
        popup.add(menuItem);
        menuItem = new JMenuItem("New state");
        menuItem.setFont(mfont);
        menuItem.addActionListener((e) -> {
            setStatusText("Popup 1");
        });
        popup.add(menuItem);
    }

    //PRUEBA
    public void creaMaquinaTest() {
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

        System.out.println("Transitions:");
        System.out.print(etapa0.transitionsToString());
        System.out.print(etapa1.transitionsToString());
        setStatusText(maquina.getDescription());
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
            if (actualForma != null) {
                int x = (e.getX() / grid.width) * grid.width;
                int y = (e.getY() / grid.height) * grid.height;
//                repaint(actualForma.getClipRect());
                actualForma.moveTo(x, y);
//                repaint(actualForma.getClipRect());
                //PRUEBA
                repaint();
            }
        }
    }

    private void onMouseMoved(MouseEvent e) {
        if (actualForma != null) {
//            actualForma.setSelected(false);
//            repaint(actualForma.getClipRect());
            actualForma = null;
        }
        for (Node f : nodes) {
            if (f.contains(e.getX(), e.getY())) {
//                setStatusText("Seleccionado " + f.getId() + " Clicks: " + e.getClickCount());
//                f.setSelected(true);
                actualForma = f;
//                repaint(actualForma.getClipRect());
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
    public void addPropertyChangeListener(PropertyChangeListener l
    ) {
//        super.addPropertyChangeListener(l);
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l
    ) {
//        super.removePropertyChangeListener(l);
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

    /**
     * Set a machine
     *
     * @param machine SeqMachine
     */
    public void setMachine(SeqMachine machine) {
        nodes.clear();
        edges.clear();
        actualForma = null;
        this.machine = machine;
        int i = 0;
        // Para cada estado de la machine crea su forma
        for (State s : this.machine.getStates()) {
            Node f = new Node(s.getId(), 16 * grid.width, 2 * grid.height + 4 * grid.height * i);
            nodes.add(f);
            i++;
        }
//        edges.add(new Edge(nodes.get(0), nodes.get(1)));
//        edges.add(new Edge(nodes.get(1), nodes.get(0)));
        repaint();
    }

//    private void drawState(Graphics2D g2, String id, float x0, float w) {
//        float ys = g2.getFontMetrics(g2.getFont()).getHeight() / 2;
//        float xs = g2.getFontMetrics(g2.getFont()).stringWidth(id) / 2;
//        float semilado = (2 * xs + 2 * 4) / 2;
//        g2.drawRect((int)(x0 - semilado), (int)(w - semilado), (int)(2 * semilado), (int)(2 * semilado));
//        g2.drawString(id, x0 - xs, w + ys);
//    }
//
//    private void drawTransition(State init, State dest, Graphics2D g) {
//
//    }
//
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (!nodes.isEmpty()) {
            // Dibuja la descripcion de la machine
//            String maqdesc = machine.getDescription();
//            float x = this.getBounds().width / 2;
//            float xs = g2.getFontMetrics(g2.getFont()).stringWidth(maqdesc) / 2;
//            g2.drawString(maqdesc, x - xs, 15);
            //Dibujo de estados
            for (Edge c : edges) {
                c.draw(g2);
            }
            for (Node f : nodes) {
                f.draw(g2);
            }
            if (actualForma != null) {
                actualForma.draw(g2);
            }
        }
    }
}

class Node {

    static private final Dimension CLIP_OFFSET = new Dimension(1, 1);
    static private final Dimension SIZE = new Dimension(32, 32);
    static private final Color BACKG_COLOR = new Color(240, 240, 240);

    private final String id;
    private final Rectangle bounds;
    private boolean selected;
//    private boolean firstDraw = true;

    public static Dimension getSIZE() {
        return SIZE;
    }

    public Node(String id, int x0, int y0) {
        this.id = id;
        bounds = new Rectangle(new Point(x0, y0), SIZE);
        selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean v) {
        selected = v;
    }

    public Rectangle getRect() {
        return bounds;
    }

    public Rectangle getClipRect() {
        return new Rectangle(bounds.x,
                bounds.y,
                bounds.width + CLIP_OFFSET.width,
                bounds.height + CLIP_OFFSET.height);
    }

    // Mueve el rectangulo un desplazamiento dx,dy respecto de la pos.actual
    public void move(int dx, int dy) {
        bounds.translate(dx, dy);
    }

    // Mueva a la posicion x,y
    public void moveTo(int x, int y) {
        bounds.setLocation(x, y);
    }

    // Determina si la forma es seleccioanda
    public boolean contains(int x, int y) {
        return bounds.contains(x, y);
    }

    public void draw(Graphics2D g2) {
        int textGap = 8;
        g2.setColor(BACKG_COLOR);
        g2.fill(bounds);
        g2.setColor(Color.BLACK);
        g2.draw(bounds);
        g2.drawString(id, bounds.x + textGap, (int) bounds.getCenterY());
    }
}

class Edge {

    private final Node fOrigen;
    private final Node fDestino;
    private final GeneralPath path = new GeneralPath();
    private final Polygon arrow = new Polygon();
    private final Line2D.Float linea = new Line2D.Float();

    public Edge(Transition transition, Node fOrigen, Node fDestino) {
        this.fOrigen = fOrigen;
        this.fDestino = fDestino;
        updatePath();
    }

    private void updatePath() {
        Rectangle r1 = fOrigen.getRect();
        Rectangle r2 = fDestino.getRect();
        int x0 = r1.x + r1.width / 2;
        int y0 = r1.y + r1.height;
        int w = r2.x - r1.x;
        int h = r2.y - r1.y - r1.height;
        int ymed = y0 + h / 4;
        int xfin = x0 + w;
        int yfin = y0 + h;
        path.reset();
        path.moveTo(x0, y0);
        path.lineTo(x0, ymed);
        path.lineTo(xfin, ymed);
        path.lineTo(xfin, yfin);
        arrow.reset();
        arrow.addPoint(xfin, yfin);
        arrow.addPoint(xfin + 4, yfin - 8);
        arrow.addPoint(xfin - 4, yfin - 8);
        linea.setLine(xfin - r2.width / 2, ymed + 3 * h / 8 - 4, xfin + r2.width / 2, ymed + 3 * h / 8 - 4);
    }

    public void draw(Graphics2D g2) {
        updatePath();
        g2.draw(path);
        g2.fill(arrow);
        g2.draw(linea);
    }
}
