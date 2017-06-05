/*
 * The MIT License
 *
 * Copyright 2017 Sergio Soriano Peiró <sersope@gmail.com>.
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
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;


// TODO hacer clase derivada de DefaultMutableTreeNode
/**
 *
 * @author Sergio Soriano Peiró <sersope@gmail.com>
 */
public class Node extends DefaultMutableTreeNode {
    private final ArrayList<Condition> conditions;
    private final State state;
    
    static private final Dimension CLIP_OFFSET = new Dimension(1, 1);
    static private final Dimension SIZE = new Dimension(32, 32);
    static private final Color BACKG_COLOR = new Color(255, 144, 134);
    
    private final transient GeneralPath edgePath = new GeneralPath();
    private final transient Polygon EdgeArrow = new Polygon();
    private final transient Line2D.Float edgeLinea = new Line2D.Float();
    private final Rectangle bounds;
    
    private final boolean isLink;
    private transient boolean selected = false;

    public Node(State state, int x0, int y0, boolean isLink) {
        super();
        conditions = new ArrayList<>();
        this.state = state;
        bounds = new Rectangle(new Point(x0, y0), SIZE);
        this.isLink = isLink;
    }
    
    /**
     * Añade condicion a la lista de condiciones
     * @param c condiciona a añadir
     * @return true si la condición se ha añadido satisfactoriamente.
     */
    public boolean addCondition(Condition c) {
        return conditions.add(c);
    }
    
    /**
     * Comprueba que todas las condiciones se cumplen
     * @return true si todas las condiciones se cumplen.
     */
    public boolean isSuperable() {
        for (Condition c : conditions)
            if (!c.isTrue())
                return false;
        return true;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getID() {
        return state.getId();
    }

    public State getState() {
        return state;
    }

    public static Dimension getSIZE() {
        return SIZE;
    }

    public boolean isLink() {
        return isLink;
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


    public void drawEdge(Graphics2D g2) {
        if (getParent() == null)
            return;
        Rectangle r1 = ((Node)getParent()).getRect();
        Rectangle r2 = this.bounds;
        int x0 = r1.x + r1.width / 2;
        int y0 = r1.y + r1.height;
        int w = r2.x - r1.x;
        int h = r2.y - r1.y - r1.height;
        int ymed = y0 + h / 4;
        int xfin = x0 + w;
        int yfin = y0 + h;
        g2.setColor(Color.BLACK);
        edgePath.reset();
        edgePath.moveTo(x0, y0);
        edgePath.lineTo(x0, ymed);
        edgePath.lineTo(xfin, ymed);
        edgePath.lineTo(xfin, yfin);
        EdgeArrow.reset();
        EdgeArrow.addPoint(xfin, yfin);
        EdgeArrow.addPoint(xfin + 4, yfin - 8);
        EdgeArrow.addPoint(xfin - 4, yfin - 8);
        edgeLinea.setLine(xfin - r2.width / 2, ymed + 3 * h / 8 - 4, xfin + r2.width / 2, ymed + 3 * h / 8 - 4);
        g2.draw(edgePath);
        g2.fill(EdgeArrow);
        g2.draw(edgeLinea);
    }

    public void draw(Graphics2D g2) {
        drawEdge(g2);
        int textGap = 8;
        g2.setColor(BACKG_COLOR);
        if (isLink) {
            g2.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            g2.fill(bounds);
        }

        if (selected) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLACK);
        }
        if (isLink) {
            g2.drawOval(bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            g2.draw(bounds);
        }
        g2.drawString(state.getId(), bounds.x + textGap, (int) bounds.getCenterY());
    }
    
    @Override
    public String toString() {
        return state.toString();
    }
}
