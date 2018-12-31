/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.developermobile.sisvenda;

import com.github.developermobile.sisvenda.cliente.ClienteFrame;
import com.github.developermobile.sisvenda.fornecedor.FornecedorFrame;
import com.github.developermobile.sisvenda.produto.ProdutoFrame;
import com.github.developermobile.sisvenda.venda.ConsultaVendaFame;
import com.github.developermobile.sisvenda.venda.RegistraVendaFrame;
import com.github.developermobile.util.DesktopPaneImage;
import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author tiago
 */
public class Main extends javax.swing.JFrame {

    DesktopPaneImage desktopPane = new DesktopPaneImage(getClass().getResource("/img/logo_app.png"));
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        nextFocusEnter();
        this.add(desktopPane, "card1");
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        mnSistema = new javax.swing.JMenu();
        miSair = new javax.swing.JMenuItem();
        mnCadastro = new javax.swing.JMenu();
        miCliente = new javax.swing.JMenuItem();
        miFornecedor = new javax.swing.JMenuItem();
        miProduto = new javax.swing.JMenuItem();
        mnVendas = new javax.swing.JMenu();
        miRegistrarVenda = new javax.swing.JMenuItem();
        miConsultarVenda = new javax.swing.JMenuItem();
        mnAjuda = new javax.swing.JMenu();
        miSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Vendas");
        setMinimumSize(new java.awt.Dimension(800, 700));
        setPreferredSize(new java.awt.Dimension(800, 700));
        getContentPane().setLayout(new java.awt.CardLayout());

        mnSistema.setMnemonic('S');
        mnSistema.setText("Sistema");

        miSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        miSair.setText("Sair");
        miSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSairActionPerformed(evt);
            }
        });
        mnSistema.add(miSair);

        menuBar.add(mnSistema);

        mnCadastro.setMnemonic('C');
        mnCadastro.setText("Cadastro");

        miCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        miCliente.setText("Cliente");
        miCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClienteActionPerformed(evt);
            }
        });
        mnCadastro.add(miCliente);

        miFornecedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        miFornecedor.setText("Fornecedor");
        miFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFornecedorActionPerformed(evt);
            }
        });
        mnCadastro.add(miFornecedor);

        miProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        miProduto.setText("Produto");
        miProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miProdutoActionPerformed(evt);
            }
        });
        mnCadastro.add(miProduto);

        menuBar.add(mnCadastro);

        mnVendas.setMnemonic('V');
        mnVendas.setText("Vendas");

        miRegistrarVenda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        miRegistrarVenda.setText("Registrar Venda");
        miRegistrarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRegistrarVendaActionPerformed(evt);
            }
        });
        mnVendas.add(miRegistrarVenda);

        miConsultarVenda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        miConsultarVenda.setText("Consultar Venda");
        miConsultarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultarVendaActionPerformed(evt);
            }
        });
        mnVendas.add(miConsultarVenda);

        menuBar.add(mnVendas);

        mnAjuda.setMnemonic('A');
        mnAjuda.setText("Ajuda");

        miSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        miSobre.setText("Sobre");
        miSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSobreActionPerformed(evt);
            }
        });
        mnAjuda.add(miSobre);

        menuBar.add(mnAjuda);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_miSairActionPerformed

    private void miSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSobreActionPerformed
        JOptionPane.showMessageDialog(null, "Sistema de Vendas - tiago.pereira.vieira@gmail.com", 
                "Informação", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_miSobreActionPerformed

    private void miClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClienteActionPerformed
        abreInternalFrame(new ClienteFrame());
    }//GEN-LAST:event_miClienteActionPerformed

    private void miFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miFornecedorActionPerformed
        abreInternalFrame(new FornecedorFrame());
    }//GEN-LAST:event_miFornecedorActionPerformed

    private void miProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miProdutoActionPerformed
        abreInternalFrame(new ProdutoFrame());
    }//GEN-LAST:event_miProdutoActionPerformed

    private void miRegistrarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRegistrarVendaActionPerformed
        abreInternalFrame(new RegistraVendaFrame());
    }//GEN-LAST:event_miRegistrarVendaActionPerformed

    private void miConsultarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultarVendaActionPerformed
        abreInternalFrame(new ConsultaVendaFame());
    }//GEN-LAST:event_miConsultarVendaActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Main().setVisible(true);
            }
        });
    }
    
    private void nextFocusEnter() {
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
    } 
    
    private void centralizaJanela(JInternalFrame internalFrame) {
        internalFrame.setLocation((this.getWidth() - internalFrame.getWidth()) / 2, 
                (this.getHeight() - internalFrame.getHeight()) / 2);
    }
    
    private void abreInternalFrame(JInternalFrame internalFrame) {
        desktopPane.add(internalFrame);
        centralizaJanela(internalFrame);
        internalFrame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem miCliente;
    private javax.swing.JMenuItem miConsultarVenda;
    private javax.swing.JMenuItem miFornecedor;
    private javax.swing.JMenuItem miProduto;
    private javax.swing.JMenuItem miRegistrarVenda;
    private javax.swing.JMenuItem miSair;
    private javax.swing.JMenuItem miSobre;
    private javax.swing.JMenu mnAjuda;
    private javax.swing.JMenu mnCadastro;
    private javax.swing.JMenu mnSistema;
    private javax.swing.JMenu mnVendas;
    // End of variables declaration//GEN-END:variables
}
