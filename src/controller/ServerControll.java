package controller;

import view.ServerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 *
 */
public class ServerControll {

	public ServerView serverView;
	public final JTextField txtPort;
	public final JTextField txtSend;
	public final JTextArea txtTinnhan;
	public final JButton btnConnect;
	public final JButton btnSend;

	ServerSocket server = null;
	Socket client = null;
	OutputStream out;
	PrintStream ps;
	int port;
	String path = "D:\\chatthuan1.txt";

	public ServerControll(ServerView serverView) {
		this.serverView = serverView;
		this.txtPort = serverView.getTxtPort();
		this.txtSend = serverView.getTxtSend();
		this.txtTinnhan = serverView.getTxtSay();
		this.btnConnect = serverView.getBtnConnect();
		this.btnSend = serverView.getBtnSend();
	}

	public void addActionLisener() {
		btnConnect.addActionListener(lis);
		btnSend.addActionListener(lis);
	}

	ActionListener lis = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnConnect) {
				try {
					port = Integer.parseInt(txtPort.getText().trim());
					server = new ServerSocket(port);

					client = server.accept();
					btnConnect.setEnabled(false);
					btnSend.setEnabled(true);

					out = client.getOutputStream();
					ps = new PrintStream(out);
					Thread t = new Thread(new daluongserver(client, txtTinnhan));
					t.start();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			if (e.getSource() == btnSend) {
				Calendar muigiohthong = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				String formattedTime = dateFormat.format(muigiohthong.getTime());
				String s = txtSend.getText().trim();
				ps.println("Server : " + s + "\t     Tgian : " + formattedTime);
				txtSend.setText("");
			}
		}
	};

	class daluongserver implements Runnable {

		Socket client;
		JTextArea txtClient;
		InputStream in;
		BufferedInputStream bi;
		DataInputStream dis;
		private BufferedWriter fileWriter;
		private String fileName = path;

		public daluongserver(Socket client, JTextArea txtClient) {
			this.client = client;
			this.txtClient = txtClient;
			try {
				in = client.getInputStream();
				bi = new BufferedInputStream(in);
				dis = new DataInputStream(bi);
				fileWriter = new BufferedWriter(new FileWriter(fileName, true));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void run() {
			String s = "";
			while (true) {
				try {
					s = dis.readLine();
					txtClient.append(s + "\n\r");
					ghitinnhanxuongfile(s);
				} catch (IOException ex) {
				}
			}
		}

		private void ghitinnhanxuongfile(String tn) {
			try {
				fileWriter.write(tn);
				fileWriter.newLine();
				fileWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void dongfile() {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void capnhattinnhan() {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				txtTinnhan.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
