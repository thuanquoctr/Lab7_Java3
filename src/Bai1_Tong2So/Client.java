package Bai1_Tong2So;

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
				double kq = dataInputStream.readDouble();
				System.out.println("Sum: " + kq);
				System.out.println("******************************");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
