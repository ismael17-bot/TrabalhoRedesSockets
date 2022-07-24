/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cliente.Telas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import Cliente.Cliente;
import Cliente.Conexao;
import Cliente.Jogadores;
import Cliente.Listener.Aviso;
import Cliente.Therd.Vez;

/**
 *
 * @author ismae
 */
public class TelaJogoMulti extends JFrame {

    private Aviso aviso = new Aviso();
    private Vez vezT = new Vez(aviso);

    private JButton sair;
    private JList<Jogadores> jogadoresList;
    private JScrollPane jScrollPane1;
    private JButton enviarLP;
    private JLabel labelLetra;
    private JLabel labelTLetras;
    private JLabel labelAcertadas;
    private JLabel totalLetras;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JTextField textoLetra;

    private Vector<Jogadores> jogadores = new Vector<>();

    private boolean vez = false;
    private int tamanhoPalavra;
    private Jogadores jogadorVez;
    private ArrayList<String> letras = new ArrayList<>();
    private ArrayList<Letra> acertadas = new ArrayList<>();

    /** Creates new form TelaJogo */
    public TelaJogoMulti() {
        setVisible(true);
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();

        labelAcertadas = new JLabel();
        labelTLetras = new JLabel();
        totalLetras = new JLabel();
        labelLetra = new JLabel();

        textoLetra = new JTextField();

        enviarLP = new JButton();
        sair = new JButton();

        jogadoresList = new JList<>();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jogadoresList);
        jogadoresList.getAccessibleContext().setAccessibleName("Lista De Jogadores");

        sair.setText("Sair");
        sair.addActionListener(e -> {
            vezT.off();
            onSair(e);
        });

        jScrollPane1.setViewportView(jogadoresList);

        labelAcertadas.setText("");

        totalLetras.setText("");

        labelTLetras.setText("Letras");

        textoLetra.setText("");

        enviarLP.setText("Enviar");

        enviarLP.addActionListener(e -> {
            String t = textoLetra.getText();
            int l = t.length();
            if (l == 0) {
                return;
            }
            if (l == 1) {
                chutarLetra();
                return;
            }
            chutarPalavra();

        });

        labelLetra.setText("letra/palavra");

        {
            GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(labelAcertadas, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
            jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(labelAcertadas, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE));

            GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE,
                                                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(199, 199, 199)
                                                    .addComponent(labelTLetras)
                                                    .addGap(0, 195, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(totalLetras, GroupLayout.DEFAULT_SIZE,
                                                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addContainerGap()));
            jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(labelTLetras)
                                    .addGap(18, 18, 18)
                                    .addComponent(totalLetras, GroupLayout.PREFERRED_SIZE, 44,
                                            GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100,
                                            Short.MAX_VALUE)
                                    .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)));

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(sair)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 139,
                                                            GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout
                                                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGap(107, 107, 107)
                                                                    .addComponent(labelLetra)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(textoLetra,
                                                                            GroupLayout.PREFERRED_SIZE, 90,
                                                                            GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(enviarLP))
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGap(60, 60, 60)
                                                                    .addComponent(jPanel1,
                                                                            GroupLayout.PREFERRED_SIZE,
                                                                            GroupLayout.DEFAULT_SIZE,
                                                                            GroupLayout.PREFERRED_SIZE)))))));
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(sair)
                                    .addGap(18, 18, Short.MAX_VALUE)
                                    .addGroup(layout
                                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                    .createSequentialGroup()
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            482,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addContainerGap())
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                    .createSequentialGroup()
                                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(layout
                                                            .createParallelGroup(
                                                                    javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(textoLetra,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(enviarLP)
                                                            .addComponent(labelLetra))
                                                    .addGap(53, 53, 53)))));

        }

        pack();

        // updatePalavra();
        // updateLetras();
        getJogadores();
        infoPartida();
        // validaVez();
        aviso.addListener(e -> {
            System.out.println("aqui 2");
            validaVez();
        });

    }

    private void validaVez() {
        // while (open) {
        System.out.println("aqui 1");
        infoPartida();
        getJogadores();
        if (vez) {
            System.out.println("Minha vez");
            return;
        }
        getVez();
        // }
    }

    private void updatePalavra() {
        char[] aux = new String(new char[tamanhoPalavra]).replace("\0", "_").toCharArray();

        acertadas.forEach(l -> {
            aux[l.posicao] = l.letra.charAt(0);
        });
        String palavra = "";
        for (char a : aux) {
            palavra += a;
        }

        labelAcertadas.setText(palavra);
    }

    private void updateLetras() {
        String l = "";
        for (String letra : letras) {
            l += " " + letra;
        }

        totalLetras.setText(l);
    }

    private void onSair(java.awt.event.ActionEvent evt) {
        this.dispose();
        try {
            Conexao.get().conexao("sair_sala;-;");
        } catch (IOException e) {
            e.printStackTrace();
        }
        new TelaAdm().setVisible(true);
    }

    private void getJogadores() {
        try {
            String dados = Conexao.get().conexao("lista_jogadores;-;");
            jogadores.clear();
            if (dados.length() > 0) {
                for (String palavra : dados.split(",")) {
                    jogadores.add(new Jogadores(palavra));
                }
            }
        } catch (IOException e) {

        }
        jogadoresList.setListData(jogadores);
    }

    private void getVez() {
        try {
            String dados = Conexao.get().conexao("vez;-;");
            int num = Integer.parseInt(dados);
            System.out.println("JogadorVez" + jogadorVez);
            System.out.println("Jogadores" + jogadores);
            System.out.println("id voce" + Cliente.get().id);
            for (Jogadores j : jogadores) {
                if (j.id == num) {
                    jogadorVez = j;
                    vez = j.id == Cliente.get().id;
                    enviarLP.setVisible(vez);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chutarLetra() {
        if (!vez)
            return;
        try {
            vez = false;
            String letra = textoLetra.getText();
            String dados = Conexao.get().conexao("chutar_letra;-;{\"letra\":\"" + letra + "\"}");
            if (dados.equals("-2")) {
                return;
            }
            for (String l2 : dados.split(",")) {
                int num = Integer.parseInt(l2);
                Letra l = new Letra(letra, num);
                if (num != -1) {
                    acertadas.add(l);
                }
                letras.add(letra);
            }
            updatePalavra();
            updateLetras();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void chutarPalavra() {
        if (!vez)
            return;
        try {
            vez = false;
            String palavra = textoLetra.getText();
            String dados = Conexao.get().conexao("chutar_palavra;-;{\"palavra\":\"" + palavra + "\"}");
            if (dados.equals("ok")) {
                this.fim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void infoPartida() {
        try {
            String dados = Conexao.get().conexao("acertos;-;");
            if (dados.length() != 0) {
                acertadas.clear();
                for (String d : dados.split(",")) {
                    String aux[] = d.split(":");
                    for (String d2 : aux[1].split(";")) {
                        Letra l = new Letra(aux[0], Integer.parseInt(d2));
                        acertadas.add(l);
                    }
                }
            } else {
                if (acertadas.size() != 0) {
                    acertadas.clear();
                    getJogadores();
                }
            }

            dados = Conexao.get().conexao("letras;-;");
            if (dados.length() != 0) {
                letras.clear();
                for (String l : dados.split(",")) {
                    letras.add(l);
                }
            } else if (letras.size() != 0) {
                letras.clear();
                getJogadores();
            }

            dados = Conexao.get().conexao("palavra_len;-;");
            int num = Integer.parseInt(dados);
            if (tamanhoPalavra != num) {
                getJogadores();
            }
            tamanhoPalavra = num;
            updatePalavra();
            updateLetras();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fim() {

    }

}

class Letra {
    Letra(String letra, int posicao) {
        this.letra = letra;
        this.posicao = posicao;
    }

    public String letra;
    public int posicao;
}