package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Info_Frame extends JFrame {
	private JTextPane textPane;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info_Frame frame = new Info_Frame();
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
	public Info_Frame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		textPane = new JTextPane();
		textPane.setEditable(false);
		contentPane.setLayout(null);
	//	contentPane.add(textPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 568, 37);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(SystemColor.text);
		contentPane.add(panel);
		
		JButton Back_Button = new JButton("\u8FD4\u56DE");
		Back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(Back_Button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 42, 568, 417);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textPane);
	}
	public void setText(String text)
	{
		textPane.setText(text);
	}
}
