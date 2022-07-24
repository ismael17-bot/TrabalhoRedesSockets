/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Telas;

import java.io.IOException;

import javax.swing.*;

import Cliente.Conexao;

/**
 *
 * @author ismae
 */
public class TelaInicial extends JFrame {

    private JButton entrar;
    private JLabel textoHost;
    private JLabel titulo;

    private JTextField campoHost;

    private static TelaInicial _this;

    private TelaInicial() {
        setVisible(true);
        initComponents();
    }

    public static TelaInicial inicio() {
        return _this == null ? _this = new TelaInicial() : _this;
    }

    private void initComponents() {

        entrar = new JButton();
        campoHost = new JTextField();
        textoHost = new JLabel();
        titulo = new JLabel();
        campoHost.setText("192.168.1.9");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        entrar.setText("Conectar");
        entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String host = campoHost.getText();

                System.out.println(host);
                try {
                    Conexao conexao = Conexao.get(host);
                    conexao = Conexao.get();
                    if (conexao != null) {
                        setVisible(false);
                        System.out.println(TelaUsuario.class);
                        TelaUsuario.inicio();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Deu ruim");
                }
            }
        });

        campoHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        textoHost.setText("Host:");

        titulo.setFont(new java.awt.Font("Vivaldi", 2, 36)); // NOI18N
        titulo.setText("  Jogo da Forca");
        titulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(62, Short.MAX_VALUE)
                                .addComponent(textoHost)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoHost, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(161, 161, 161)
                                                .addComponent(entrar, GroupLayout.PREFERRED_SIZE, 99,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(85, 85, 85)
                                                .addComponent(titulo, GroupLayout.PREFERRED_SIZE, 251,
                                                        GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(82, Short.MAX_VALUE)
                                .addComponent(titulo)
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(campoHost, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textoHost))
                                .addGap(18, 18, 18)
                                .addComponent(entrar)
                                .addGap(42, 42, 42)));

        entrar.getAccessibleContext().setAccessibleName("Entrar");

        pack();
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

}
