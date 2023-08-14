package view;
import controller.ClientControll;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 *
 */
public class ClientView {

    JFrame frame;
    JLabel lblPort,lblClient;
    JTextField txtPort,txtSend;
    JButton btnstart,btnSend;
    JTextArea txttinnhan;
    JScrollPane jsp;

    public JTextField getTxtPort() {
        return txtPort;
    }

    public JTextField getTxtSend() {
        return txtSend;
    }

    public JButton getBtnConnect() {
        return btnstart;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public JTextArea getTxtTinnhan() {
        return txttinnhan;
    }

    public ClientView() {
        GUI();
        frame.setVisible(true);
        frame.setTitle("Client");
        ClientControll clienctroll = new ClientControll(this);
        clienctroll.addActionLisener();
        btnSend.setEnabled(false);
        clienctroll.capnhattinnhan();
    }

    public void GUI() {
        frame = new JFrame();
        frame.setSize(450, 700);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(null);
        
        lblPort = new JLabel("Port No:");
        lblPort.setForeground(Color.BLUE);
        lblPort.setFont(new Font("Serif", Font.BOLD, 20));
        lblPort.setBounds(30, 20, 120, 30);
        frame.add(lblPort);
        
        txtPort = new JTextField();
        txtPort.setFont(new Font("Serif", Font.BOLD, 20));
        txtPort.setBounds(130, 20, 150, 30);
        frame.add(txtPort);
        
        btnstart = new JButton("Start");
        btnstart.setFont(new Font("Serif", Font.BOLD, 20));
        btnstart.setBounds(310, 20  , 90, 30);
        frame.add(btnstart);
        
        lblClient = new JLabel("Server Say: ");
        lblClient.setForeground(Color.RED);
        lblClient.setFont(new Font("Serif", Font.BOLD, 20));
        lblClient.setBounds(30, 60, 130, 30);
        frame.add(lblClient);
        
        txttinnhan = new JTextArea();
        txttinnhan.setFont(new Font("Serif", Font.BOLD, 20));
        
        jsp = new JScrollPane(txttinnhan);
        jsp.setBounds(30, 90, 370, 500);
        frame.add(jsp);
        
        txtSend = new JTextField();
        txtSend.setFont(new Font("Serif", Font.BOLD, 20));
        txtSend.setBounds(30, 600, 250, 30);
        frame.add(txtSend);
        
        btnSend = new JButton("Send");
        btnSend.setFont(new Font("Serif", Font.BOLD, 20));
        btnSend.setBounds(310, 600, 90, 30);
        frame.add(btnSend);
    }

    public static void main(String[] args) {
        new ClientView();
    }
}
