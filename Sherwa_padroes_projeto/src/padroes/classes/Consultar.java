package padroes.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Consultar extends javax.swing.JInternalFrame {

    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtPlayer;
    private javax.swing.JTextField txtNome;

    private JDesktopPane jdpPrincipal;
    public Consultar(JDesktopPane jdp) {
        initComponents();
        jdpPrincipal = jdp;
    }

    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPlayer = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Partida Marcada");

        jLabel2.setText("Nome:");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jButton1.setText("Todos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btNovo.setText("Nova");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        jtPlayer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id","Nome", "Nick", "Jogo", "Plataforma", "Data da partida", "Nº Pessoas", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtPlayer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        pack();
    }

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {
        Cadastrar cadastrarPlayer= new Cadastrar();
        jdpPrincipal.add(cadastrarPlayer);
        cadastrarPlayer.setVisible(true);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","qwert12345");
            String sql = "select * from player ";
            if(!txtNome.getText().equals(""))
                sql = sql + " where Nome LIKE ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            if(!txtNome.getText().equals(""))
                stmt.setString(1, "%"+txtNome.getText()+"%");
            ResultSet rs = stmt.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jtPlayer.getModel();
            model.setNumRows(0);
            while(rs.next()){
                String[] linha = {rs.getString("idPlayer"), rs.getString("nome"), rs.getString("nickName"),
                    rs.getString("jogo"),rs.getString("plataforma"),rs.getString("data_partida"),
                    rs.getString("qtd_pessoa"),rs.getString("categoria") };
                model.addRow(linha);
            }
            
            stmt.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
            
    }

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + "test","root","qwert12345");  
            String sql = "delete from player where idPlayer = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            int linha = this.jtPlayer.getSelectedRow();
            stmt.setInt(1, Integer.parseInt(jtPlayer.getValueAt(linha, 0).toString()));
            stmt.execute();
            stmt.close();
            con.close();
            DefaultTableModel model = (DefaultTableModel) jtPlayer.getModel();
            model.removeRow(linha);
            JOptionPane.showMessageDialog(this, "Partida Excluida com Sucesso!");
            this.setClosable(true);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
}
