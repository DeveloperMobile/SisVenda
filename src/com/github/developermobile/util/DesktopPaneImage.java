
package com.github.developermobile.util;

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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon(caminhoImagem);
        g.drawImage(img.getImage(), 50, 50, this);
    }
    
}
