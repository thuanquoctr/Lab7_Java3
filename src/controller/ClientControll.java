package controller;



import view.ClientView;
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
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 *
 */
public class ClientControll {

    public ClientView clientView;
    public final JTextField txtPort;
    public final JTextField txtSend;
    public final JTextArea txtTinnhan;
    public final JButton btnConnect;
    public final JButton btnSend;
    String ip = "localhost";

    int port;
    Socket client;
    OutputStream out;
    PrintStream ps;
    String path = "D:\\chatthuan.txt";

    public ClientControll(ClientView clientView) {
        this.clientView = clientView;
        this.txtPort = clientView.getTxtPort();
        this.txtSend = clientView.getTxtSend();
        this.txtTinnhan = clientView.getTxtTinnhan();
        this.btnConnect = clientView.getBtnConnect();
        this.btnSend = clientView.getBtnSend();
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
                    client = new Socket(ip, port);
                    out = client.getOutputStream();
                    ps = new PrintStream(out);

                    btnConnect.setEnabled(false);
                    btnSend.setEnabled(true);
                    Thread t = new Thread(new daluong(client, txtTinnhan));
                    t.start();

                } catch (Exception ex) {
                }
            }
            if (e.getSource() == btnSend) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String formattedTime = dateFormat.format(calendar.getTime());
                ps.println("Client : "+ txtSend.getText().trim()+"\t     Tgian :" +formattedTime);
                txtSend.setText("");
            }
        }
    };

    class daluong implements Runnable {

        Socket client;
        JTextArea txtServer;
        InputStream in;
        BufferedInputStream bi;
        DataInputStream dis;
        OutputStream out;
        private BufferedWriter fileWriter;
        private String fileName = path;

        public daluong(Socket client, JTextArea txtServer) {
            this.client = client;
            this.txtServer = txtServer;
            try {
                in = client.getInputStream();
                bi = new BufferedInputStream(in);
                dis = new DataInputStream(bi);
                fileWriter = new BufferedWriter(new FileWriter(fileName, true));
            } catch (Exception e) {
            }
        }

        public void run() {
            String s = "";
            while (true) {
                try {
                    s = dis.readLine();
                    txtServer.append(s + "\n\r");
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
        }
    }
}
