package TimNghiem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Socket socket = new Socket("localhost", 6868);
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			while (true) {
				System.out.println("A: ");
				dataOutputStream.writeDouble(sc.nextDouble());
				dataOutputStream.flush();
				System.out.println("B ");
				dataOutputStream.writeDouble(sc.nextDouble());
				dataOutputStream.flush();
				String kq = dataInputStream.readUTF();
				System.out.println("Ket qua: " + kq);
				System.out.println("Tiep tuc (y/n): ");
				sc.nextLine();
				String lc = sc.nextLine();
				if (lc.equalsIgnoreCase("y")) {
					System.out.println("*****************");
				} else if (lc.equalsIgnoreCase("n")) {
					System.out.println("Cam on !");
					break;
				} else {
					System.out.println("Sai cu phap !");
					break;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
