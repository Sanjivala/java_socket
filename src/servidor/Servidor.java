package servidor;

import cliente.PacoteEnvio;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Servidor extends JFrame implements Runnable {

    public Servidor() {
        initComponents();
        servidorGrupo();
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {

            ArrayList<String> listaIp = new ArrayList<>();

            String nome, ip, mensagem;

            PacoteEnvio pacote_recebido;

            ServerSocket servidor = new ServerSocket(9999);

            //Instrução para recebimento de msg no servidor
            while (true) {
                try (Socket socket = servidor.accept()) {

                    //Início da instrução que recebe a mensagem enviada por parte do cliente
                    ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                    pacote_recebido = (PacoteEnvio) entrada.readObject();
                    nome = pacote_recebido.getNome();
                    ip = pacote_recebido.getIp();
                    mensagem = pacote_recebido.getMensagem();

                    if (!mensagem.equalsIgnoreCase("Online")) {

                        txtArea.append(nome + ": " + mensagem + "\n");

                        //Instrução para o servidor enviar msg para outros clientes
                        try (Socket destino = new Socket(ip, 9090)) {
                            ObjectOutputStream pacote_reenvio = new ObjectOutputStream(destino.getOutputStream());
                            pacote_reenvio.writeObject(pacote_recebido);
                        }

                    } else {
                        // Instrução para detectar usuários online
                        InetAddress localiza = socket.getInetAddress();
                        String ipRemoto = localiza.getHostAddress();
//                        System.out.println("Online: " + ipRemoto);
                        //Fim da instrução

                        listaIp.add(ipRemoto);

                        pacote_recebido.setIps(listaIp);

                        //Instrução forEach usada no Lambda
//                        listaIp.forEach((ips) -> {
//                            System.out.println("Lista: " + ips);
//                        });
                        for (String ips : listaIp) {
                            System.out.println("Conectado: " + ips);
                            try (Socket destino = new Socket(ips, 9090); ObjectOutputStream pacote_reenvio = new ObjectOutputStream(destino.getOutputStream())) {
                                pacote_reenvio.writeObject(pacote_recebido);
                            }
                            socket.close();
                        }
                    }
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    //Programação para o recebimento de mensagens do grupo no servidor
    public void servidorGrupo() {
        Socket cliente;
        ServerSocket servidorGrupo;
        Scanner leitor;
        try {
            servidorGrupo = new ServerSocket(10000);
            while (true) {
                cliente = servidorGrupo.accept();
                leitor = new Scanner(cliente.getInputStream());
                System.out.println(leitor.nextLine());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtArea.setEditable(false);
        txtArea.setBackground(new java.awt.Color(0, 51, 51));
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtArea.setForeground(new java.awt.Color(255, 255, 255));
        txtArea.setRows(5);
        txtArea.setEnabled(false);
        jScrollPane1.setViewportView(txtArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(427, 533));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables

}
