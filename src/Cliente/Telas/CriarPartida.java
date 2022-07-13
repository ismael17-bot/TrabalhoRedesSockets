/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Telas;

import javax.swing.*;

import Cliente.Cliente;
import Cliente.Conexao;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.StringJoiner;

/**
 *
 * @author ismae
 */
public class CriarPartida extends JFrame {

    private JButton btnConfirmar;
    private JButton cancel;
    private JButton edit;
    private JComboBox<String> opcoes;
    private JLabel labelSala;
    private JLabel labelSenha;
    private JTextField nomeSala;
    private JTextField senhaSala;

    /**
     * Creates new form CriarPartida
     */
    public CriarPartida() {
        setVisible(true);
        initComponents();
    }

    private void initComponents() {

        btnConfirmar = new JButton();
        cancel = new JButton();
        nomeSala = new JTextField();
        senhaSala = new JTextField();
        labelSala = new JLabel();
        labelSenha = new JLabel();
        opcoes = new JComboBox<>();
        edit = new JButton();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new TelaAdm().setVisible(true);
                super.windowClosing(e);
            }
        });

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(e -> {
            confirmarAction(e);
        });

        cancel.setText("Cancelar");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        labelSala.setText("Nome da Sala");

        labelSenha.setText("Senha");

        opcoes.setModel(new DefaultComboBoxModel<>(new String[] { "Padrão", "Customizado" }));

        opcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        edit.setText("Editar");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.LEADING, layout
                                                .createSequentialGroup()
                                                .addGap(176, 176, 176)
                                                .addGroup(layout
                                                        .createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        GroupLayout.Alignment.LEADING)
                                                                .addComponent(labelSala)
                                                                .addComponent(nomeSala,
                                                                        GroupLayout.PREFERRED_SIZE, 279,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(labelSenha))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnConfirmar,
                                                                        GroupLayout.PREFERRED_SIZE, 135,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(cancel,
                                                                        GroupLayout.PREFERRED_SIZE, 130,
                                                                        GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout
                                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(opcoes,
                                                                        GroupLayout.PREFERRED_SIZE, 135,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(edit))
                                                        .addComponent(senhaSala,
                                                                GroupLayout.PREFERRED_SIZE, 279,
                                                                GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(174, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(labelSala)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomeSala, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(labelSenha)
                                .addGap(3, 3, 3)
                                .addComponent(senhaSala, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(opcoes, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(edit))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 120,
                                        Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 41,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancel, GroupLayout.PREFERRED_SIZE, 41,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)));

        nomeSala.getAccessibleContext().setAccessibleName("nomeSala");
        senhaSala.getAccessibleContext().setAccessibleName("senhaSala");
        CustomizaJogo.start();
        pack();
    }

    private void confirmarAction(java.awt.event.ActionEvent evt) {
        String nome = nomeSala.getText();
        String senha = senhaSala.getText();
        if (nome.length() == 0) {
            return;
        }
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (String p : CustomizaJogo.get().palavras) {
            joiner.add("\"" + p + "\"");
        }
        try {
            String status = Conexao.get()
                    .conexao("criar_sala;-;{\"nome\":\"" + nome + "\",\"senha\":\"" + senha + "\",\"palavras\":"
                            + joiner.toString() + "}");
            if (status.equals("ok")) {
                new TelaJogoMulti();
                dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
        new TelaAdm().setVisible(true);
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        CustomizaJogo.get(((String) opcoes.getSelectedItem()).equals("Padrão")).setVisible(true);
    }
}
