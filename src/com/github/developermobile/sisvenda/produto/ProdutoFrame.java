package com.github.developermobile.sisvenda.produto;

import com.github.developermobile.sisvenda.fornecedor.Fornecedor;
import com.github.developermobile.sisvenda.fornecedor.FornecedorFrame;
import com.github.developermobile.sisvenda.venda.RegistraVendaFrame;
import com.github.developermobile.util.Constantes;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author tiago
 */
public class ProdutoFrame extends javax.swing.JInternalFrame {

    private DefaultTableModel tableModel;
    private ListSelectionModel listModel;
    private List<Produto> produtos;
    private int modo;
    private Fornecedor fornecedor;
    private RegistraVendaFrame registraVendaFrame;
    
    /**
     * Creates new form ProdutoFrame
     */
    public ProdutoFrame() {
        initComponents();
        defineModelo();
        btnSelecionaProduto.setVisible(false);
    }
    
    public ProdutoFrame(RegistraVendaFrame registraVendaFrame) {
        initComponents();
        defineModelo();
        btnSelecionaProduto.setVisible(true);
        this.registraVendaFrame = registraVendaFrame;
    }

    private void defineModelo() {
        tableModel = (DefaultTableModel) tbProduto.getModel();
        listModel = tbProduto.getSelectionModel();
        listModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostraDetalheProduto();
                }
            }
        });
        try {
            DecimalFormat formatoValor = new DecimalFormat("#,###.00");
            NumberFormatter formatterValor = new NumberFormatter(formatoValor);
            formatterValor.setValueClass(Double.class);
            ftfValor.setFormatterFactory(new DefaultFormatterFactory(formatterValor));
            
            DecimalFormat formatoEstoque = new DecimalFormat("#,###");
            NumberFormatter formatterEstoque = new NumberFormatter(formatoEstoque);
            formatterEstoque.setValueClass(Integer.class);
            ftfEstoque.setFormatterFactory(new DefaultFormatterFactory(formatterEstoque));
            
            tbProduto.getColumnModel().getColumn(1).setPreferredWidth(700);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void atualizaTela() {
        if (tfFiltroProduto.getText().trim().equals("")) {
            produtos = ServiceProduto.consultaProduto();
        } else {
            produtos = ServiceProduto.consultaProduto(tfFiltroProduto.getText().trim());
        }
        int numeroLinha = tableModel.getRowCount();
        for (int i = 0; i < numeroLinha; i++) {
            tableModel.removeRow(0);
        }
        for (int i = 0; i < produtos.size(); i++) {
            tableModel.insertRow(i, new Object[]{ produtos.get(i).getId(), produtos.get(i).getNome() });
        }
    }
    
    private void mostraDetalheProduto() {
        if (tbProduto.getSelectedRow() != -1) {
            int indice = tbProduto.getSelectedRow();
            tfNomeProduto.setText(produtos.get(indice).getNome());
            tfNomeFornecedor.setText(produtos.get(indice).getFornecedor().getNome());
            fornecedor = produtos.get(indice).getFornecedor();
            ftfEstoque.setValue(produtos.get(indice).getQtdeEstoque());
            ftfValor.setValue(produtos.get(indice).getValor());
        } else {
            tfNomeProduto.setText("");
            tfNomeFornecedor.setText("");
            fornecedor = null;
            ftfEstoque.setText("");
            ftfValor.setText("");
        }
    }
    
    private void incluiProduto() {
        if (tfNomeProduto.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            tfNomeProduto.requestFocus();
        } else {
             Produto produto = new Produto();
            produto.setNome(tfNomeProduto.getText().trim());
            produto.setFornecedor(fornecedor);
            produto.setQtdeEstoque((Integer)ftfEstoque.getValue());
            produto.setValor((Double)ftfValor.getValue());
            if (ServiceProduto.incluiProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                atualizaTela();
                desabilitaBotoes();
                desabilitaCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao  cadastrar produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void alteraProduto() {
        if (tfNomeProduto.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            tfNomeProduto.requestFocus();
        } else {
            Produto produto = new Produto();
            produto.setId(produtos.get(tbProduto.getSelectedRow()).getId());
            produto.setNome(tfNomeProduto.getText().trim());
            produto.setFornecedor(fornecedor);
            produto.setQtdeEstoque((Integer)ftfEstoque.getValue());
            produto.setValor((Double)ftfValor.getValue());

            if (ServiceProduto.alteraProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Dados alterados com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                atualizaTela();
                desabilitaBotoes();
                desabilitaCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao alterar o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void excluiProduto() {
        if (ServiceProduto.excluiProduto(produtos.get(tbProduto.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Dados excluídos com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            atualizaTela();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void habilitaCampos() {
        tfNomeProduto.setEnabled(true);
        ftfEstoque.setEnabled(true);
        ftfValor.setEnabled(true);
        btnSelecionaFornecedor.setEnabled(true);
    }

    private void desabilitaCampos() {
        tfNomeProduto.setEnabled(false);
        tfNomeFornecedor.setEnabled(false);
        ftfEstoque.setEnabled(false);
        ftfValor.setEnabled(false);
        btnSelecionaFornecedor.setEnabled(false);
    }

    private void habilitaBotoes() {
        btnSalvar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }

    private void desabilitaBotoes() {
        btnSalvar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnNovo.setEnabled(true);
        btnAlterar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        tfNomeFornecedor.setText(fornecedor.getNome());
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbFiltro = new javax.swing.JLabel();
        tfFiltroProduto = new javax.swing.JTextField();
        btnFiltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProduto = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        tfNomeProduto = new javax.swing.JTextField();
        lbFornecedor = new javax.swing.JLabel();
        tfNomeFornecedor = new javax.swing.JTextField();
        btnSelecionaFornecedor = new javax.swing.JButton();
        lbEstoque = new javax.swing.JLabel();
        ftfEstoque = new javax.swing.JFormattedTextField();
        lbValor = new javax.swing.JLabel();
        ftfValor = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        btnSelecionaProduto = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Produto");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(java.awt.SystemColor.activeCaption);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produtos");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        lbFiltro.setText("Filtro Pelo Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(lbFiltro, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(tfFiltroProduto, gridBagConstraints);

        btnFiltar.setText("Pesquisar");
        btnFiltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnFiltar, gridBagConstraints);

        tbProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbProduto);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        lbNome.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbNome, gridBagConstraints);

        tfNomeProduto.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(tfNomeProduto, gridBagConstraints);

        lbFornecedor.setText("Fornecedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbFornecedor, gridBagConstraints);

        tfNomeFornecedor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(tfNomeFornecedor, gridBagConstraints);

        btnSelecionaFornecedor.setText("...");
        btnSelecionaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaFornecedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnSelecionaFornecedor, gridBagConstraints);

        lbEstoque.setText("Estoque");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbEstoque, gridBagConstraints);

        ftfEstoque.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(ftfEstoque, gridBagConstraints);

        lbValor.setText("Valor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbValor, gridBagConstraints);

        ftfValor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(ftfValor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        btnSelecionaProduto.setText("Seleciona Produto");
        btnSelecionaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaProdutoActionPerformed(evt);
            }
        });
        jPanel4.add(btnSelecionaProduto);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNovo);

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        jPanel4.add(btnAlterar);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(btnExcluir);

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(btnSalvar);

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltarActionPerformed
        atualizaTela();
    }//GEN-LAST:event_btnFiltarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        habilitaCampos();
        habilitaBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (tbProduto.getSelectedRow() != -1) {
            habilitaCampos();
            habilitaBotoes();
            modo = Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (tbProduto.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirmar exclusão de produto:", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluiProduto();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (modo == Constantes.INSERT_MODE) {
            incluiProduto();
        } else if (modo == Constantes.EDIT_MODE) {
            alteraProduto();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        desabilitaBotoes();
        desabilitaCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSelecionaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaFornecedorActionPerformed
        FornecedorFrame fornecedorFrame = new FornecedorFrame(this);
        fornecedorFrame.setVisible(true);
        this.getDesktopPane().add(fornecedorFrame);
        fornecedorFrame.toFront();
    }//GEN-LAST:event_btnSelecionaFornecedorActionPerformed

    private void btnSelecionaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaProdutoActionPerformed
        if (tbProduto.getSelectedRow() != -1) {
            registraVendaFrame.setProduto(produtos.get(tbProduto.getSelectedRow()));
            this.dispose();
            registraVendaFrame.toFront();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto na lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelecionaProdutoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFiltar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSelecionaFornecedor;
    private javax.swing.JButton btnSelecionaProduto;
    private javax.swing.JFormattedTextField ftfEstoque;
    private javax.swing.JFormattedTextField ftfValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbEstoque;
    private javax.swing.JLabel lbFiltro;
    private javax.swing.JLabel lbFornecedor;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbValor;
    private javax.swing.JTable tbProduto;
    private javax.swing.JTextField tfFiltroProduto;
    private javax.swing.JTextField tfNomeFornecedor;
    private javax.swing.JTextField tfNomeProduto;
    // End of variables declaration//GEN-END:variables
}
