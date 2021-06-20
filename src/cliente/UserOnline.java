package cliente;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class UserOnline extends WindowAdapter {

    @Override
    public void windowOpened(WindowEvent e) {
        try {
            try (Socket socket = new Socket("10.113.207.99", 9999)) {
                PacoteEnvio dados = new PacoteEnvio();
                dados.setMensagem("Online");
                ObjectOutputStream pacote_dados = new ObjectOutputStream(socket.getOutputStream());
                pacote_dados.writeObject(dados);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}