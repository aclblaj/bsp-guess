import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {

	static final int port = 55000;
	private static final java.text.DateFormat DF = java.text.DateFormat.getDateTimeInstance();

	private static String getTime() {
		return DF.format(new java.util.Date());
	}

	public static void main(String[] args) {
		System.out.println("Server Start ..");

		int ghicesteNr = 1 + (int) (Math.random() * 99);

		try {

			DatagramSocket ds = new DatagramSocket(port);
			DatagramPacket dp = new DatagramPacket(new byte[120], 120);
			String resp;
			System.out.println("To be guessed:" + ghicesteNr);
			while (true) {
				ds.receive(dp);
				String receivedString = new String(dp.getData()).trim();
				System.out.println("String received from client '" + receivedString + "' on port " + dp.getPort());
				int nr = Integer.parseInt(receivedString);
				if (ghicesteNr < nr) {
					// mai mic
					resp = "<";
				} else {
					if (ghicesteNr > nr) {
						// mai mare
						resp = ">";
					} else {
						// ok
						resp = "ok";
					}
				}
				System.out.println("Response time: " + getTime());
				dp.setData(resp.getBytes());
				ds.send(dp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
