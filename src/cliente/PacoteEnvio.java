package cliente;

import java.io.*;
import java.util.*;

public class PacoteEnvio implements Serializable {

    private String nome;
    private String ip;
    private String mensagem;
    private ArrayList<String> ips;
//    private ArrayList<String> nomes;

//    public ArrayList<String> getNomes() {
//        return nomes;
//    }

//    public void setNomes(ArrayList<String> nomes) {
//        this.nomes = nomes;
//    }

    public ArrayList<String> getIps() {
        return ips;
    }

    public void setIps(ArrayList<String> ips) {
        this.ips = ips;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
