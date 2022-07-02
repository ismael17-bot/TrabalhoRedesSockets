/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package Cliente.Telas;

import javax.swing.*;
/**
 *
 * @author ismae
 */
public class TelaJogoMulti extends JFrame {
    
    private JButton sair;
    private JList<String> listaJogadores;
    private JScrollPane jScrollPane1;

    /** Creates new form TelaJogo */
    public TelaJogoMulti() {
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        listaJogadores = new JList<>();
        sair = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        listaJogadores.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(listaJogadores);
        listaJogadores.getAccessibleContext().setAccessibleName("ListaDeJogadores");

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new TelaAdm().setVisible(true);
    }// GEN-LAST:event_jButton1ActionPerformed


}
