package Bai1_Tong2So;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(6868);
			Socket socket = serverSocket.accept();
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			while (true) {
				double x = dataInputStream.readDouble();
				double y = dataInputStream.readDouble();
				double tong = x + y;
				dataOutputStream.writeDouble(tong);
				dataOutputStream.flush();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
