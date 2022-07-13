/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import Cliente.Sala;
import Cliente.Conexao;

/**
 *
 * @author ismae
 */
public class TelaAdm extends javax.swing.JFrame {

	private JButton criar;
	private JButton entrar;
	private JList<Sala> listaSalas;
	private JScrollPane jScrollPane1;
	private JTextField idSala;
	private Label label1;

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
		criar.addActionListener(e -> {
			bntCriarAction(e);
		});

		entrar.setText("Entrar");
		entrar.addActionListener(e -> {
			onEntrar(e);
		});

		idSala.addActionListener(e -> {
			jTextField1ActionPerformed(e);
		});

		label1.setText("ID Sala");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(28, 28, 28)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										680,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										49,
										Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout
														.createSequentialGroup()
														.addComponent(label1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(idSala,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																100,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(81, 81, 81))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout
														.createSequentialGroup()
														.addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(criar,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		126,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(entrar,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		126,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(65, 65, 65)))));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(150, 150, 150)
								.addGroup(layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(idSala,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(label1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(entrar,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										42,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(criar,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										42,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
								.createSequentialGroup()
								.addContainerGap(49, Short.MAX_VALUE)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										544,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(37, 37, 37)));

		idSala.getAccessibleContext().setAccessibleName("id_sala");
		idSala.getAccessibleContext().setAccessibleDescription("");

		pack();
		evento();
	}

	private void evento() {
		listaSalas.addListSelectionListener(e -> {
			Object aux = ((JList) e.getSource()).getSelectedValue();
			if (aux != null) {
				idSala.setText(((Sala) aux).id + "");
			}
		});
	}

	private void jTextField1ActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void onEntrar(ActionEvent evt) {
		// new TelaJogoMulti().setVisible(true);
		String id = idSala.getText();
		if (id.isEmpty()) {
			return;
		}

		try {
			String dados = Conexao.get().conexao("entrar_sala;-;{\"id\":" + id + "}");
			if (dados.equals("True")) {
				new TelaJogoMulti();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void bntCriarAction(ActionEvent evt) {
		setVisible(false);
		new CriarPartida();
	}

	private void updateSalas() {
		Vector<Sala> salas = new Vector<Sala>();
		try {
			String dados = Conexao.get().conexao("lista_sala;-;");
			if (dados.length() > 0) {
				for (String s : dados.split(",")) {
					salas.add(new Sala(s));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		listaSalas.setListData(salas);
	}
}
