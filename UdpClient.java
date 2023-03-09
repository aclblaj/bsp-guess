import java.io.IOException;
import java.net.*;

public class UdpClient {
	java.text.DateFormat DF = null;

	public static void main(String[] args) {
		try {

			// correct the server address
			// InetAddress inetAddress = InetAddress.getByName( "10.90.101.191" );
			InetAddress inetAddress = InetAddress.getLocalHost();

			DatagramPacket dp = new DatagramPacket(new byte[20], 20, inetAddress, 55000);
			DatagramSocket ds = new DatagramSocket();

			if (args.length != 1) {
				System.out.println("syntax: java UdpClient number");
				System.exit(1);
			}
			String s = args[0];

			dp.setData(s.getBytes());

			ds.send(dp);

			ds.receive(dp);

			String retStr = new String(dp.getData(), dp.getOffset(), dp.getLength());

			System.out.println(retStr);

		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
