

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

public class newOneSocket {

	/**
	 * @param args
	 */
	
	public static String ip="211.162.125.34";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Socket> set = new LinkedList<Socket>();
		int i = 1;
		int all=1;

		try {
			while (i<=all) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				202.85.219.241
				Socket s = new Socket(ip, 80);
				
//				Socket s = new Socket("192.168.2.102", 8000);
				set.add(s);
				s.setSoTimeout(10000);
				System.out.println(i + "  " + s.getLocalPort() + "  "
						+ set.get(0).getLocalPort() + "  °ó¶¨ "
						+ set.get(0).isBound() + " isConnected "
						+ set.get(0).isConnected() + " isClosed "
						+ set.get(0).isClosed());
				
				
				
				BufferedWriter br=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				int k=0;
				while(true){
				
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if(s.isConnected())
					System.out.println("isLink"+k);
					
					if(s.isInputShutdown())
						System.out.println("isInputShutdown"+k);
					
					if(s.isBound())
						System.out.println("isBound"+k);
					
					if(s.isClosed())
						System.out.println("isClosed"+k);
					
					if(s.isOutputShutdown())
						System.out.println("isClosed"+k);
					
					
					br.write("SHUTDOWN");
					br.flush();
					k++;
				}
			
//				br.close();
//				s.close();
				
				
				
				
				
				
				
				
				
				
				
				
//				i;

			}
				try {
					Thread.sleep(1000*800);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

