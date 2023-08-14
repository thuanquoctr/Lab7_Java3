package TimNghiem;

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
				if (x == 0 && y == 0) {
					dataOutputStream.writeUTF("pt vo so nghiem");
					dataOutputStream.flush();
				}
				if (x == 0 && y != 0) {
					dataOutputStream.writeUTF("pt vo nghiem");
					dataOutputStream.flush();
				}
				if (x != 0) {
					double kq = -y / x;
					dataOutputStream.writeUTF("pt co 1 nghiem duy nhat " + String.valueOf(kq));
					dataOutputStream.flush();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
