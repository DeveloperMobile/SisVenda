
package com.github.developermobile.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;

/**
 *
 * @author tiago
 */
public class DesktopPaneImage extends JDesktopPane {
    
    private String caminhoImagem;
    
    public DesktopPaneImage(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(caminhoImagem);
        g.drawImage(img.getImage(), 50, 50, this);
    }
    
}
