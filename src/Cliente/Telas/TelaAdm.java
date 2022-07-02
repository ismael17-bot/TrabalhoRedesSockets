/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Telas;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

import Cliente.Sala;
import Cliente.Conexao;

/**
 *
 * @author ismae
 */
public class TelaAdm extends javax.swing.JFrame {

    /**
     * Creates new form TelaAdm
     */
    public TelaAdm() {
        setVisible(true);
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaSalas = new javax.swing.JList<>();
        criar = new javax.swing.JButton();
        entrar = new javax.swing.JButton();
        idSala = new javax.swing.JTextField();
        label1 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        updateSalas();

        jScrollPane1.setViewportView(listaSalas);

        criar.setText("Criar");
        criar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        entrar.setText("Entrar");
        entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onEntrar(evt);
            }
        });

        idSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        label1.setText("ID Sala");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49,
                                        Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(idSala, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(81, 81, 81))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(criar, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(entrar, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(65, 65, 65)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(idSala, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(criar, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(49, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)));

        idSala.getAccessibleContext().setAccessibleName("id_sala");
        idSala.getAccessibleContext().setAccessibleDescription("");

        pack();
        evento();
    }// </editor-fold>//GEN-END:initComponents

    private void evento(){
        listaSalas.addListSelectionListener(e->{
                Object aux = ((JList) e.getSource()).getSelectedValue();
                if(aux != null){
                        idSala.setText(((Sala) aux).id+"");
                }
        });
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField1ActionPerformed

    private void onEntrar(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // this.dispose();
        // new TelaJogoMulti().setVisible(true);
        String id = idSala.getText();
        if(id.isEmpty()){
                return;
        }

        try {
                String dados = Conexao.get().conexao("entrar_sala;-;{id:"+id+"}");
                if(dados.equals("ok")){
                        new TelaJogoMulti();
                }
                
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
    }// GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new CriarPartida().setVisible(true);
    }// GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton criar;
    private javax.swing.JButton entrar;
    private javax.swing.JList<Sala> listaSalas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField idSala;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables

    private void updateSalas(){
            ArrayList<Sala> salas = new ArrayList<Sala>();
        try {
              String dados = Conexao.get().conexao("lista_sala;-;");

              for(String s : dados.split(",")){
                salas.add(new Sala(s));
              }
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        listaSalas.setModel(new javax.swing.AbstractListModel<Sala>() {

                public int getSize() {
                        return salas.size();
                }

                public Sala getElementAt(int i) {
                        return salas.get(i);
                }
        });
    }
}
