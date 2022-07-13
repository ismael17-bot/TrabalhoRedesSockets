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

import Cliente.Conexao;
import Cliente.Jogadores;

/**
 *
 * @author ismae
 */
public class TelaJogoMulti extends JFrame {

    private JButton sair;
    private JList<Jogadores> jogadoresList;
    private JScrollPane jScrollPane1;

    private Vector<Jogadores> jogadores = new Vector<>();

    private int vez;
    private int tamanhoPalavra;
    private ArrayList<String> letras = new ArrayList<>();
    private ArrayList<Letra> acertadas = new ArrayList<>();

    /** Creates new form TelaJogo */
    public TelaJogoMulti() {
        setVisible(true);
        initComponents();
    }

    private void initComponents() {

        try {
            String dados = Conexao.get().conexao("info_partida;-;");
            if (!dados.equals("erro")) {
                for (String d : dados.split(",")) {
                    d.split(";");
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        jScrollPane1 = new JScrollPane();
        jogadoresList = new JList<>();
        sair = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jogadoresList);
        jogadoresList.getAccessibleContext().setAccessibleName("Lista De Jogadores");

        sair.setText("Sair");
        sair.addActionListener(e -> {
            onSair(e);
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        {
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(sair)
                                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 181,
                                                    GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(801, Short.MAX_VALUE)));
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(sair)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 563,
                                            GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(29, Short.MAX_VALUE)));
        }

        pack();
        getJogadores();

    }

    private void onSair(java.awt.event.ActionEvent evt) {
        this.dispose();
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

}

class Letra {
    Letra(String letra, int posicao) {
        this.letra = letra;
        this.posicao = posicao;
    }

    public String letra;
    public int posicao;
}