/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.developermobile.sisvenda;

import com.github.developermobile.sisvenda.cliente.ClienteFrame;
import com.github.developermobile.sisvenda.fornecedor.FornecedorFrame;
import com.github.developermobile.sisvenda.produto.ProdutoFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sun.swing.UIAction;

/**
 *
 * @author tiago
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
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

        desktopPane = new javax.swing.JDesktopPane();
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

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        getContentPane().add(desktopPane, "card2");

        mnSistema.setText("Sistema");

        miSair.setText("Sair");
        miSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSairActionPerformed(evt);
            }
        });
        mnSistema.add(miSair);

        menuBar.add(mnSistema);

        mnCadastro.setText("Cadastro");

        miCliente.setText("Cliente");
        miCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClienteActionPerformed(evt);
            }
        });
        mnCadastro.add(miCliente);

        miFornecedor.setText("Fornecedor");
        miFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miFornecedorActionPerformed(evt);
            }
        });
        mnCadastro.add(miFornecedor);

        miProduto.setText("Produto");
        miProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miProdutoActionPerformed(evt);
            }
        });
        mnCadastro.add(miProduto);

        menuBar.add(mnCadastro);

        mnVendas.setText("Vendas");

        miRegistrarVenda.setText("Registrar Venda");
        mnVendas.add(miRegistrarVenda);

        miConsultarVenda.setText("Consultar Venda");
        mnVendas.add(miConsultarVenda);

        menuBar.add(mnVendas);

        mnAjuda.setText("Ajuda");

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
    private javax.swing.JDesktopPane desktopPane;
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
