package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;

public class GUI extends JFrame {

	private JPanel getContentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		getContentPane = new JPanel();
		getContentPane.setBackground(SystemColor.inactiveCaption);
		getContentPane.setForeground(Color.BLACK);
		getContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(getContentPane);
		
		JLabel jLabel1 = new JLabel("Informa\u00E7\u00E3o da Linha de Produ\u00E7\u00E3o");
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JTabbedPane jTabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_getContentPane = new GroupLayout(getContentPane);
		gl_getContentPane.setHorizontalGroup(
			gl_getContentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_getContentPane.createSequentialGroup()
					.addComponent(jTabbedPane1, GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_getContentPane.createSequentialGroup()
					.addContainerGap(316, Short.MAX_VALUE)
					.addComponent(jLabel1)
					.addGap(284))
		);
		gl_getContentPane.setVerticalGroup(
			gl_getContentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_getContentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabel1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jTabbedPane1)
					.addContainerGap())
		);
		
		JPanel jPanel1 = new JPanel();
		jPanel1.setBackground(new Color(230, 230, 250));
		jTabbedPane1.addTab("               ORDENS               ", null, jPanel1, null);
		
		JLabel jLabel2 = new JLabel("ORDENS N\u00C3O FINALIZADAS (PENDENTES/EXECU\u00C7\u00C3O)");
		jLabel2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JScrollPane jScrollPane1 = new JScrollPane();
		
		JLabel jLabel3 = new JLabel("ORDENS FINALIZADAS");
		jLabel3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JScrollPane jScrollPane2 = new JScrollPane();
		GroupLayout gl_jPanel1 = new GroupLayout(jPanel1);
		gl_jPanel1.setHorizontalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addComponent(jLabel3)
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
						.addComponent(jLabel2)
						.addComponent(jScrollPane2))
					.addContainerGap())
		);
		gl_jPanel1.setVerticalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabel2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jLabel3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id#Ordem", "Tipo", "Hora Registo", "Hora In\u00EDcio", "Hora Fim", "Quantidade"
			}
		));
		table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(108);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(100);
		jScrollPane2.setViewportView(table_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Hora Registo", "id#Ordem", "Tipo", "Estado", "Hora In\u00EDcio", "N\u00BA Pe\u00E7as Pendentes", "N\u00BA Pe\u00E7as em Execu\u00E7\u00E3o", "N\u00BA Pe\u00E7as Produzidas"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(89);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(108);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(110);
		jScrollPane1.setViewportView(table);
		jPanel1.setLayout(gl_jPanel1);
		
		JPanel jPanel2 = new JPanel();
		jTabbedPane1.addTab("               ESTAT\u00CDSTICAS               ", null, jPanel2, null);
		
		JLabel jLabel4 = new JLabel("M\u00E1quinas");
		jLabel4.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_jPanel2 = new GroupLayout(jPanel2);
		gl_jPanel2.setHorizontalGroup(
			gl_jPanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel2.createSequentialGroup()
					.addGap(26)
					.addComponent(jLabel4)
					.addContainerGap(887, Short.MAX_VALUE))
		);
		gl_jPanel2.setVerticalGroup(
			gl_jPanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel2.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabel4)
					.addContainerGap(547, Short.MAX_VALUE))
		);
		jPanel2.setLayout(gl_jPanel2);
		getContentPane.setLayout(gl_getContentPane);
	}
}
