/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Telas;

import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

import Cliente.Conexao;

/**
 *
 * @author ismae
 */
public class CustomizaJogo extends JFrame {

    private static CustomizaJogo _this;
    private boolean padrao = true;

    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JLabel jLabel1;
    private JList<String> jList1;
    private JScrollPane jScrollPane1;
    private JTextField campoPalavra;

    public Vector<String> palavras = new Vector<String>();

    public static CustomizaJogo start() {
        get(true);
        _this.getPalavras();
        return _this;
    }

    public static CustomizaJogo get() {
        return get(true);
    }

    public static CustomizaJogo get(boolean padrao) {
        if (_this == null) {
            _this = new CustomizaJogo(padrao);
        }
        if (_this.padrao != padrao) {
            _this.padrao = padrao;
            _this.getPalavras();
        }

        return _this;
    }

    private CustomizaJogo(boolean padrao) {
        this.padrao = padrao;
        initComponents();
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        campoPalavra = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        getPalavras();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jScrollPane1.setViewportView(jList1);

        campoPalavra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Adicionar");
        jButton1.addActionListener(e -> {
            onAdd(e);
        });

        jButton2.setText("Excluir");
        jButton2.addActionListener(e -> {
            onExcluir(e);
        });

        jButton3.setText("Ok");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onOk(evt);
            }
        });

        jButton4.setText("Fechar");
        jButton4.addActionListener(e -> {
            onFechar(e);
        });

        jLabel1.setText("Palavra:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(campoPalavra, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 87,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(campoPalavra, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        432, Short.MAX_VALUE)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton3)
                                                        .addComponent(jButton4))))
                                .addContainerGap()));

        pack();
    }

    private void onAdd(java.awt.event.ActionEvent evt) {
        String palavra = campoPalavra.getText();
        if (palavra.length() > 3 && !palavras.contains(palavra)) {
            try {
                Conexao.get().conexao("add_palavra;-;{\"palavra\":\"" + palavra + "\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
            palavras.add(palavra);
            jList1.setListData(palavras);
        }
    }

    private void onExcluir(java.awt.event.ActionEvent evt) {
        int index = jList1.getSelectedIndex();
        String palavra = jList1.getSelectedValue();
        // System.out.println("Index: " + index + " Palavra: " + palavra);
        if (index != -1 && palavra.length() > 3 && palavras.contains(palavra)) {
            try {
                Conexao.get().conexao("excluir_palavra;-;{\"palavra\":\"" + palavra + "\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
            palavras.remove(index);
            jList1.setListData(palavras);
        }

    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void onOk(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }

    private void onFechar(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }

    private void getPalavras() {
        try {
            String dados = Conexao.get().conexao("lista_palavras;-;{\"p\":" + padrao + "}");
            palavras.clear();
            if (dados.length() > 0) {
                for (String palavra : dados.split(",")) {
                    palavras.add(palavra);
                }
            }
        } catch (IOException e) {

        }
        jList1.setListData(palavras);
    }

}
