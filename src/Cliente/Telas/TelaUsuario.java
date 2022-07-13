
package Cliente.Telas;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import Cliente.Cliente;
import Cliente.Conexao;

/**
 *
 * @author ismae
 */
public class TelaUsuario extends JFrame {

    private JButton entrar;
    private JLabel textoNome;
    private JLabel titulo;

    private JTextField campoNome;

    private static TelaUsuario _this;

    public TelaUsuario() {
        this.setVisible(true);
        initComponents();
    }

    public static TelaUsuario inicio() {
        if (_this == null) {
            _this = new TelaUsuario();
        }
        return _this;
    }

    private void initComponents() {
        // return;

        entrar = new JButton();
        campoNome = new JTextField();
        textoNome = new JLabel();
        titulo = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        entrar.setText("Entrar");
        entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String nome = campoNome.getText();
                Pattern p = Pattern.compile("[^A-Za-z]");
                Matcher matcher = p.matcher(nome);
                if (matcher.find()) {
                    return;
                }
                Cliente.get().nome = nome;
                System.out.println(Cliente.get().nome);
                try {
                    Conexao conexao = Conexao.get();
                    if (conexao != null) {
                        String aqui = conexao.conexao("{\"nome\":\"" + Cliente.get().nome + "\"}");
                        System.out.println("AQUI ----------> " + aqui);
                    }
                    new TelaAdm();
                } catch (IOException e) {
                    Conexao conexao = Conexao.get();
                    if (conexao.isConnected()) {
                        setVisible(false);
                        TelaInicial.inicio().setVisible(true);
                    }

                    e.printStackTrace();
                }
                // jButton1ActionPerformed(evt);
            }
        });

        campoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        textoNome.setText("nome:");

        titulo.setFont(new java.awt.Font("Vivaldi", 2, 36)); // NOI18N
        titulo.setText("  Jogo da Forca");
        titulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(62, Short.MAX_VALUE)
                                .addComponent(textoNome)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNome, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(campoNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textoNome))
                                .addGap(18, 18, 18)
                                .addComponent(entrar)
                                .addGap(42, 42, 42)));

        entrar.getAccessibleContext().setAccessibleName("Entrar");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }
}
