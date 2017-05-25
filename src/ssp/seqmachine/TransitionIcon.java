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

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

/**
 *
 * @author Sergio Soriano Peiró <sersope@gmail.com>
 */
public class TransitionIcon {
    private final StateIcon nOrigen;
    private final StateIcon nDestino;
    private final transient GeneralPath path = new GeneralPath();
    private final transient Polygon arrow = new Polygon();
    private final transient Line2D.Float linea = new Line2D.Float();
    private final Transition transition;

    public TransitionIcon(Transition transition, StateIcon nOrigen, StateIcon nDestino) {
        this.transition = transition;
        this.nOrigen = nOrigen;
        this.nDestino = nDestino;
    }

    public Transition getTransition() {
        return transition;
    }

    private void updatePath() {
        Rectangle r1 = nOrigen.getRect();
        Rectangle r2 = nDestino.getRect();
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
