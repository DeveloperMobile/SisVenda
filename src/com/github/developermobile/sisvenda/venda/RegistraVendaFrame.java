
package com.github.developermobile.sisvenda.venda;

import com.github.developermobile.sisvenda.cliente.Cliente;
import com.github.developermobile.sisvenda.cliente.ClienteFrame;
import com.github.developermobile.sisvenda.produto.Produto;
import com.github.developermobile.sisvenda.produto.ProdutoFrame;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author tiago
 */
public class RegistraVendaFrame extends javax.swing.JInternalFrame {

    private DefaultTableModel tableModel;
    private List<ItensVenda> itensVendas;
    private Produto produto;
    private Cliente cliente;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        tfNomeProduto.setText(produto.getNome());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        tfNomeCliente.setText(cliente.getNome());
    }
    
    
    
    /**
     * Creates new form RegistraVendaFrame
     */
    public RegistraVendaFrame() {
        initComponents();
        defineModelo();
        itensVendas = new ArrayList<>();
    }

    private void defineModelo() {
        tableModel = (DefaultTableModel) tbItensVenda.getModel();
        try {
            DecimalFormat formatoValor = new DecimalFormat("#,###.00");
            NumberFormatter formatterValor = new NumberFormatter(formatoValor);
            formatterValor.setValueClass(Double.class);
            ftfValorTotal.setFormatterFactory(new DefaultFormatterFactory(formatterValor));
            
            DecimalFormat formatoQuantidader = new DecimalFormat("#,###");
            NumberFormatter formatterQuantidade = new NumberFormatter(formatoQuantidader);
            formatterQuantidade.setValueClass(Integer.class);
            ftfQuantidade.setFormatterFactory(new DefaultFormatterFactory(formatterQuantidade));
            
            tbItensVenda.getColumnModel().getColumn(0).setPreferredWidth(400);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void atualizaTabela() {
        int numeroLinha = tbItensVenda.getRowCount();
        
        for (int i = 0; i < numeroLinha; i++) {
            tableModel.removeRow(0);
        }
        
        double valorTotal = 0.0;
        
        for (int i = 0; i < itensVendas.size(); i++) {
            tableModel.insertRow(i, new Object[]{itensVendas.get(i).getProduto().getId(), 
                itensVendas.get(i).getQtde(),
                itensVendas.get(i).getProduto().getValor(),
                itensVendas.get(i).getProduto().getValor() * itensVendas.get(i).getQtde()
            });
            valorTotal += itensVendas.get(i).getProduto().getValor() * itensVendas.get(i).getQtde();
        }
        ftfValorTotal.setValue(valorTotal);
    }
    
    private void incluiProduto() {
        if (produto == null) {
            JOptionPane.showMessageDialog(this, "Selecione o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            tfNomeCliente.requestFocus();
        } else if (ftfQuantidade.getValue() == null) {
            JOptionPane.showMessageDialog(this, "Informe a quantidade!", "Erro", JOptionPane.ERROR_MESSAGE);
            ftfQuantidade.requestFocus();
        } else {
            ItensVenda itensVenda = new ItensVenda();
            itensVenda.setProduto(produto);
            itensVenda.setQtde((Integer)ftfQuantidade.getValue());
            itensVenda.setValor(produto.getValor());
            itensVendas.add(itensVenda);
            atualizaTabela();
        }
    }
    
    private void excluiProduto() {
        if (tbItensVenda.getSelectedRow() != -1) {
            itensVendas.remove(tbItensVenda.getSelectedRow());
            atualizaTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void registraVenda() {
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Selecione o cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (itensVendas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Insira itens na venda!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            Venda venda = new Venda();
            venda.setIdCliente(cliente);
            Calendar dataAtual = Calendar.getInstance();
            venda.setDataVenda(new Date(dataAtual.getTime().getTime()));
            for (ItensVenda itensVenda : itensVendas) {
                itensVenda.setVenda(venda);
            } 
            venda.setItensVendas(itensVendas);
            if (ServiceVenda.registraVenda(venda)) {
                JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao registrar venda!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tfNomeProduto = new javax.swing.JTextField();
        btnSelecionaCliente = new javax.swing.JButton();
        lbNomeProduto = new javax.swing.JLabel();
        tfNomeCliente = new javax.swing.JTextField();
        btnBuscarProduto = new javax.swing.JButton();
        lbNomeCliente = new javax.swing.JLabel();
        lbQtde = new javax.swing.JLabel();
        ftfQuantidade = new javax.swing.JFormattedTextField();
        btnIncluiProduto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItensVenda = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbValorTotal = new javax.swing.JLabel();
        ftfValorTotal = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        btnExcluirItem = new javax.swing.JButton();
        btnRegistrarVenda = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registrar Venda");
        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbTitulo.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbTitulo.setText("Registra Venda");
        jPanel1.add(lbTitulo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        tfNomeProduto.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(tfNomeProduto, gridBagConstraints);

        btnSelecionaCliente.setText("...");
        btnSelecionaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaClienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnSelecionaCliente, gridBagConstraints);

        lbNomeProduto.setText("Produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(lbNomeProduto, gridBagConstraints);

        tfNomeCliente.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(tfNomeCliente, gridBagConstraints);

        btnBuscarProduto.setText("...");
        btnBuscarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdutoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnBuscarProduto, gridBagConstraints);

        lbNomeCliente.setText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(lbNomeCliente, gridBagConstraints);

        lbQtde.setText("Qtde");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(lbQtde, gridBagConstraints);

        ftfQuantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(ftfQuantidade, gridBagConstraints);

        btnIncluiProduto.setText("Incluir");
        btnIncluiProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluiProdutoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnIncluiProduto, gridBagConstraints);

        tbItensVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtde", "Vlr. Unit.", "Vlr. Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbItensVenda);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        lbValorTotal.setText("Valor Total");
        jPanel3.add(lbValorTotal);

        ftfValorTotal.setEditable(false);
        ftfValorTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ftfValorTotal.setMinimumSize(new java.awt.Dimension(70, 27));
        ftfValorTotal.setPreferredSize(new java.awt.Dimension(70, 27));
        jPanel3.add(ftfValorTotal);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        btnExcluirItem.setText("Excluir Item");
        btnExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirItemActionPerformed(evt);
            }
        });
        jPanel4.add(btnExcluirItem);

        btnRegistrarVenda.setText("Salvar");
        btnRegistrarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVendaActionPerformed(evt);
            }
        });
        jPanel4.add(btnRegistrarVenda);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaClienteActionPerformed
        ClienteFrame clienteFrame = new ClienteFrame(this);
        clienteFrame.setVisible(true);
        this.getDesktopPane().add(clienteFrame);
        clienteFrame.toFront();
    }//GEN-LAST:event_btnSelecionaClienteActionPerformed

    private void btnBuscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdutoActionPerformed
        ProdutoFrame produtoFrame = new ProdutoFrame(this);
        produtoFrame.setVisible(true);
        this.getDesktopPane().add(produtoFrame);
        produtoFrame.toFront();
    }//GEN-LAST:event_btnBuscarProdutoActionPerformed

    private void btnIncluiProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluiProdutoActionPerformed
        incluiProduto();
    }//GEN-LAST:event_btnIncluiProdutoActionPerformed

    private void btnExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirItemActionPerformed
        excluiProduto();
    }//GEN-LAST:event_btnExcluirItemActionPerformed

    private void btnRegistrarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVendaActionPerformed
        registraVenda();
    }//GEN-LAST:event_btnRegistrarVendaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarProduto;
    private javax.swing.JButton btnExcluirItem;
    private javax.swing.JButton btnIncluiProduto;
    private javax.swing.JButton btnRegistrarVenda;
    private javax.swing.JButton btnSelecionaCliente;
    private javax.swing.JFormattedTextField ftfQuantidade;
    private javax.swing.JFormattedTextField ftfValorTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNomeCliente;
    private javax.swing.JLabel lbNomeProduto;
    private javax.swing.JLabel lbQtde;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbValorTotal;
    private javax.swing.JTable tbItensVenda;
    private javax.swing.JTextField tfNomeCliente;
    private javax.swing.JTextField tfNomeProduto;
    // End of variables declaration//GEN-END:variables
}
