
package com.github.developermobile.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author tiago
 */
public class DesktopPaneImage extends JDesktopPane {
    
    private URL caminhoImagem;
    
    public DesktopPaneImage(URL caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
        System.out.println(caminhoImagem.getPath());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(caminhoImagem);
        Dimension d = this.getSize();
        int x = (int) (d.getWidth() - img.getIconWidth()) / 2;
        int y = (int) (d.getHeight() - img.getIconHeight()) / 2;
        g.drawImage(img.getImage(), x, y, img.getIconWidth(), img.getIconHeight(), this);
    }
    
}
