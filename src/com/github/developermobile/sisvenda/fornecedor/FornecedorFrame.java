
package com.github.developermobile.sisvenda.fornecedor;

import com.github.developermobile.sisvenda.produto.ProdutoFrame;
import com.github.developermobile.util.Constantes;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author tiago
 */
public class FornecedorFrame extends javax.swing.JInternalFrame {

    private DefaultTableModel tableModel;
    private ListSelectionModel listModel;
    private List<Fornecedor> fornecedores;
    private int modo;
    private ProdutoFrame produtoFrame;
    
    public FornecedorFrame() {
        initComponents();
        defineModelo();
        btnSelecionaFornecedor.setVisible(false);
    }
    
    public FornecedorFrame(ProdutoFrame produtoFrame) {
        initComponents();
        defineModelo();
        btnSelecionaFornecedor.setVisible(true);
        this.produtoFrame = produtoFrame;
    }

    private void defineModelo() {
        tableModel = (DefaultTableModel) tbFornecedor.getModel();
        listModel = tbFornecedor.getSelectionModel();
        listModel.addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                mostraDetalheCliente();
            }
        });
        try {
            MaskFormatter formatter = new MaskFormatter("##.###-###");
            formatter.setValueContainsLiteralCharacters(false);
            DefaultFormatterFactory cepFormatter = new DefaultFormatterFactory(formatter);
            ftfCep.setFormatterFactory(cepFormatter);
            
            MaskFormatter formatter2 = new MaskFormatter("(##)####-####");
            formatter2.setValueContainsLiteralCharacters(false);
            DefaultFormatterFactory foneFormater = new DefaultFormatterFactory(formatter2);
            ftfTelefone.setFormatterFactory(foneFormater);
            
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void atualizaTabela() {
        if (tfFiltroFornecedor.getText().trim().equals("")) {
            fornecedores = ServiceFornecedor.consultaFornecedor();
        } else {
            fornecedores = ServiceFornecedor.consultaFornecedor(tfNomeFornecedor.getText().trim());
        }
        int numeroLinha = tableModel.getRowCount();
        for (int i = 0; i < numeroLinha; i++) {
            tableModel.removeRow(0);
        }
        for (int i = 0; i < fornecedores.size(); i++) {
            tableModel.insertRow(i, new Object[]{ fornecedores.get(i).getId(), fornecedores.get(i).getNome() });
        }
    }
    
    private void mostraDetalheCliente() {
        if (tbFornecedor.getSelectedRow() != -1) {
            int indice = tbFornecedor.getSelectedRow();
            tfNomeFornecedor.setText(fornecedores.get(indice).getNome());
            tfEndereco.setText(fornecedores.get(indice).getEndereco());
            tfBairro.setText(fornecedores.get(indice).getBairro());
            tfCidade.setText(fornecedores.get(indice).getCidade());
            cbUf.setSelectedItem(fornecedores.get(indice).getUf());
            ftfCep.setText(fornecedores.get(indice).getCep());
            ftfTelefone.setText(fornecedores.get(indice).getTelefone());
            tfEmail.setText(fornecedores.get(indice).getEmail());
        } else {
            tfNomeFornecedor.setText("");
            tfEndereco.setText("");
            tfBairro.setText("");
            tfCidade.setText("");
            cbUf.setSelectedItem("");
            ftfCep.setText("");
            ftfTelefone.setText("");
            tfEmail.setText("");
        }
    }
    
    private void incluiCliente() {
        if (tfNomeFornecedor.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do fornecedor!", "Erro", JOptionPane.ERROR_MESSAGE);
            tfNomeFornecedor.requestFocus();
        } else {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(tfNomeFornecedor.getText().trim());
            fornecedor.setEndereco(tfEndereco.getText().trim());
            fornecedor.setBairro(tfBairro.getText().trim());
            fornecedor.setCidade(tfCidade.getText().trim());
            fornecedor.setUf(cbUf.getSelectedItem().toString());
            fornecedor.setCep((String)ftfCep.getValue());
            fornecedor.setTelefone((String)ftfTelefone.getValue());
            fornecedor.setEmail(tfEmail.getText().trim());
            if (ServiceFornecedor.incluiFornecedor(fornecedor)) {
                JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabela();
                desabilitaBotoes();
                desabilitaCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar fornecedor!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void alteraFornecedor() {
        if (tfNomeFornecedor.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do fornecedor!", "Erro", JOptionPane.ERROR_MESSAGE);
            tfNomeFornecedor.requestFocus();
        } else {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId(fornecedores.get(tbFornecedor.getSelectedRow()).getId());
            fornecedor.setNome(tfNomeFornecedor.getText().trim());
            fornecedor.setEndereco(tfEndereco.getText().trim());
            fornecedor.setBairro(tfBairro.getText().trim());
            fornecedor.setCidade(tfCidade.getText().trim());
            fornecedor.setUf(cbUf.getSelectedItem().toString());
            fornecedor.setCep((String)ftfCep.getValue());
            fornecedor.setTelefone((String)ftfTelefone.getValue());
            fornecedor.setEmail(tfEmail.getText().trim());
            if (ServiceFornecedor.alteraFornecedor(fornecedor)) {
                JOptionPane.showMessageDialog(this, "Dados alterados com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                atualizaTabela();
                desabilitaBotoes();
                desabilitaCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao alterar o fornecedor!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void excluiFornecedor() {
        if (ServiceFornecedor.excluiFornecedor(fornecedores.get(tbFornecedor.getSelectedRow()))) {
            JOptionPane.showMessageDialog(this, "Dados excluídos com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            atualizaTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir o fornecedor!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void habilitaCampos() {
        tfNomeFornecedor.setEnabled(true);
        tfEndereco.setEnabled(true);
        tfBairro.setEnabled(true);
        tfCidade.setEnabled(true);
        cbUf.setEnabled(true);
        ftfCep.setEnabled(true);
        ftfTelefone.setEnabled(true);
        tfEmail.setEnabled(true);
    }
    
    private void desabilitaCampos() {
        tfNomeFornecedor.setEnabled(false);
        tfEndereco.setEnabled(false);
        tfBairro.setEnabled(false);
        tfCidade.setEnabled(false);
        cbUf.setEnabled(false);
        ftfCep.setEnabled(false);
        ftfTelefone.setEnabled(false);
        tfEmail.setEnabled(false);
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
        lbFiltro = new javax.swing.JLabel();
        tfFiltroFornecedor = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFornecedor = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        tfNomeFornecedor = new javax.swing.JTextField();
        lbEndereco = new javax.swing.JLabel();
        tfEndereco = new javax.swing.JTextField();
        lbBairro = new javax.swing.JLabel();
        tfBairro = new javax.swing.JTextField();
        lbCidade = new javax.swing.JLabel();
        tfCidade = new javax.swing.JTextField();
        lbUf = new javax.swing.JLabel();
        cbUf = new javax.swing.JComboBox<>();
        lbCep = new javax.swing.JLabel();
        ftfCep = new javax.swing.JFormattedTextField();
        lbTelefone = new javax.swing.JLabel();
        ftfTelefone = new javax.swing.JFormattedTextField();
        lbEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnSelecionaFornecedor = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Fornecedor");
        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbTitulo.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbTitulo.setText("Fornecedor");
        jPanel1.add(lbTitulo);

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
        jPanel2.add(tfFiltroFornecedor, gridBagConstraints);

        btnFiltrar.setText("Pesquisar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnFiltrar, gridBagConstraints);

        tbFornecedor.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbFornecedor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
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

        tfNomeFornecedor.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        tfNomeFornecedor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(tfNomeFornecedor, gridBagConstraints);

        lbEndereco.setText("Endereço");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbEndereco, gridBagConstraints);

        tfEndereco.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        tfEndereco.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(tfEndereco, gridBagConstraints);

        lbBairro.setText("Bairro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbBairro, gridBagConstraints);

        tfBairro.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        tfBairro.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(tfBairro, gridBagConstraints);

        lbCidade.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbCidade, gridBagConstraints);

        tfCidade.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        tfCidade.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(tfCidade, gridBagConstraints);

        lbUf.setText("UF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbUf, gridBagConstraints);

        cbUf.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        cbUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        cbUf.setAutoscrolls(true);
        cbUf.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cbUf, gridBagConstraints);

        lbCep.setText("Cep");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbCep, gridBagConstraints);

        ftfCep.setEnabled(false);
        ftfCep.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(ftfCep, gridBagConstraints);

        lbTelefone.setText("Telefone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbTelefone, gridBagConstraints);

        ftfTelefone.setEnabled(false);
        ftfTelefone.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(ftfTelefone, gridBagConstraints);

        lbEmail.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(lbEmail, gridBagConstraints);

        tfEmail.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(tfEmail, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnSelecionaFornecedor.setText("Seleciona Fornecedor");
        btnSelecionaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaFornecedorActionPerformed(evt);
            }
        });
        jPanel4.add(btnSelecionaFornecedor);

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
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        atualizaTabela();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        habilitaCampos();
        habilitaBotoes();
        modo = Constantes.INSERT_MODE;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (tbFornecedor.getSelectedRow() != -1) {
            habilitaCampos();
            habilitaBotoes();
            modo = Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor na lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (tbFornecedor.getSelectedRow() != -1) {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirmar exclusão de fornecedor?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                excluiFornecedor();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (modo == Constantes.INSERT_MODE) {
            incluiCliente();
        } else if (modo == Constantes.EDIT_MODE) {
            alteraFornecedor();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        desabilitaBotoes();
        desabilitaCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSelecionaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaFornecedorActionPerformed
        if (tbFornecedor.getSelectedRow() != -1) {
            produtoFrame.setFornecedor(fornecedores.get(tbFornecedor.getSelectedRow()));
            this.dispose();
            produtoFrame.toFront();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um fornecedor da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelecionaFornecedorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSelecionaFornecedor;
    private javax.swing.JComboBox<String> cbUf;
    private javax.swing.JFormattedTextField ftfCep;
    private javax.swing.JFormattedTextField ftfTelefone;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbCep;
    private javax.swing.JLabel lbCidade;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEndereco;
    private javax.swing.JLabel lbFiltro;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUf;
    private javax.swing.JTable tbFornecedor;
    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCidade;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfEndereco;
    private javax.swing.JTextField tfFiltroFornecedor;
    private javax.swing.JTextField tfNomeFornecedor;
    // End of variables declaration//GEN-END:variables
}
