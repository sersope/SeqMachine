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
import java.awt.Rectangle;
import ssp.seqmachine.State;

/**
 *
 * @author Sergio Soriano Peiró <sersope@gmail.com>
 */
public class StateIcon {

    private final State state;
    static private final Dimension CLIP_OFFSET = new Dimension(1, 1);
    static private final Dimension SIZE = new Dimension(32, 32);
    static private final Color BACKG_COLOR = new Color(240, 240, 240);
    private final Rectangle bounds;
    private final boolean isLink;
    private boolean selected = false;

    public StateIcon(State state, int x0, int y0, boolean isLink) {
        this.state = state;
        bounds = new Rectangle(new Point(x0, y0), SIZE);
        this.isLink = isLink;
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

    public void draw(Graphics2D g2) {
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
}
