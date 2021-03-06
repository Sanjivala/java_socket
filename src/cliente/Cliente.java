package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.*;
import java.util.*;

public class Cliente extends JFrame implements Runnable {

    Path path = Paths.get("conversas.txt");
    Charset utf8 = StandardCharsets.UTF_8;

    public Cliente() {

        initComponents();
        addWindowListener(new UserOnline());
        String usuario;
        do {
            usuario = JOptionPane.showInputDialog(null, "Seu nome de Usuário", "Usuário", JOptionPane.INFORMATION_MESSAGE);
        } while (usuario.equals(""));
        lblNome.setText(usuario);
        new Thread(this).start();
//        enviarMensagemGrupo();
    }

    //Método para enviar as mensagens no servidor
    public void enviar() {

        txtArea.append(txtMensagem.getText() + "\n");

        try {
            Socket socket = new Socket("10.113.207.99", 9999);

            PacoteEnvio dados = new PacoteEnvio();

            dados.setNome(lblNome.getText());
            dados.setIp(cbIp.getSelectedItem().toString());
            dados.setMensagem(txtMensagem.getText());
            txtMensagem.requestFocus();
            txtMensagem.setText("");

            try (ObjectOutputStream dados_saida = new ObjectOutputStream(socket.getOutputStream())) {
                dados_saida.writeObject(dados);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    } //Fim do método para enviar mensagens

    //Método para criar servidor na área do cliente
    @Override
    public void run() {

        try {

            ServerSocket servidor_cliente = new ServerSocket(9090);
            Socket cliente;
            PacoteEnvio pacoteRecebido;
            while (true) {

                cliente = servidor_cliente.accept();
                try (ObjectInputStream fluxoEntrada = new ObjectInputStream(cliente.getInputStream())) {
                    pacoteRecebido = (PacoteEnvio) fluxoEntrada.readObject();

                    if (!pacoteRecebido.getMensagem().equalsIgnoreCase("Online")) {
                        txtArea.append(pacoteRecebido.getNome() + " : " + pacoteRecebido.getMensagem() + "\n");

                    } else {

//                        String nome;
//                        nome = pacoteRecebido.getNome();
                        //txtArea.append(pacoteRecebido.getIps() + "\n");
                        ArrayList<String> ipsMenu = new ArrayList<>();
//                        ArrayList<String> nomes = new ArrayList<>();

                        ipsMenu = pacoteRecebido.getIps();
//                        nomes = pacoteRecebido.getNomes();

                        cbIp.removeAllItems();

                        for (String menu : ipsMenu) {
                            cbIp.addItem(menu);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Programação para o envio de mensagem para o grupo por parte do cliente
//    Socket clientes;
//    PrintWriter mensagemGrupo;
//
//    public class EnviarListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            mensagemGrupo.println(txtMensagem.getText());
//            mensagemGrupo.flush();
//            txtMensagem.setText("");
//            txtMensagem.requestFocus();
//        }
//    }

//    public void enviarMensagemGrupo() {
//        try {
//            clientes = new Socket("127.0.0.1", 10000);
//            mensagemGrupo = new PrintWriter(clientes.getOutputStream());
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
    
    public void enviarGrupo() {

        txtArea.append(txtMensagem.getText() + "\n");

        try {
            Socket socket = new Socket("10.113.207.99", 9999);

            PacoteEnvio dados = new PacoteEnvio();

            dados.setNome(lblNome.getText());
//            dados.setIp(cbIp.getSelectedItem().toString());
            dados.setMensagem(txtMensagem.getText());
            txtMensagem.requestFocus();
            txtMensagem.setText("");

            try (ObjectOutputStream dados_saida = new ObjectOutputStream(socket.getOutputStream())) {
                dados_saida.writeObject(dados);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    } //Fim do método para enviar mensagens para o grupo

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pPrivado = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        cbIp = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        txtMensagem = new javax.swing.JTextField();
        btnVoltar = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        btnGrupo = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SChat - v1.0");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        pPrivado.setBackground(new java.awt.Color(153, 153, 255));
        pPrivado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Privado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Usuário:");

        lblNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(0, 0, 153));
        lblNome.setToolTipText("Nome do usuário");

        cbIp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbIp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbIp.setToolTipText("Lista de Endereços IP´s");

        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtArea.setForeground(new java.awt.Color(255, 255, 255));
        txtArea.setRows(5);
        txtArea.setEnabled(false);
        jScrollPane1.setViewportView(txtArea);

        txtMensagem.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnVoltar.setBackground(new java.awt.Color(204, 0, 0));
        btnVoltar.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setText("Sair");
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnEnviar.setBackground(new java.awt.Color(51, 0, 204));
        btnEnviar.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        btnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviar.setText("Enviar Privado");
        btnEnviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnGrupo.setBackground(new java.awt.Color(0, 102, 51));
        btnGrupo.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        btnGrupo.setForeground(new java.awt.Color(255, 255, 255));
        btnGrupo.setText("Enviar Grupo");
        btnGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrupoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pPrivadoLayout = new javax.swing.GroupLayout(pPrivado);
        pPrivado.setLayout(pPrivadoLayout);
        pPrivadoLayout.setHorizontalGroup(
            pPrivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPrivadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbIp, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPrivadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPrivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPrivadoLayout.createSequentialGroup()
                        .addGroup(pPrivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMensagem)
                            .addGroup(pPrivadoLayout.createSequentialGroup()
                                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGrupo)))
                        .addContainerGap())))
        );
        pPrivadoLayout.setVerticalGroup(
            pPrivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPrivadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPrivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNome)
                    .addComponent(jLabel2)
                    .addComponent(cbIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pPrivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPrivadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGrupo))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pPrivadoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbIp, jLabel2, lblNome});

        pPrivadoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnEnviar, btnGrupo, btnVoltar});

        jPanel5.setBackground(new java.awt.Color(102, 102, 255));

        jLabel3.setFont(new java.awt.Font("Ebrima", 1, 35)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LSoft Comunication");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(pPrivado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pPrivado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // TODO add your handling code here:

        enviar();
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        int sair = JOptionPane.showConfirmDialog(null, "Deseja fechar a aplicação?", "Alerta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (sair == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrupoActionPerformed
        // TODO add your handling code here:
        enviarGrupo();
    }//GEN-LAST:event_btnGrupoActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnGrupo;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbIp;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JPanel pPrivado;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtMensagem;
    // End of variables declaration//GEN-END:variables

}
