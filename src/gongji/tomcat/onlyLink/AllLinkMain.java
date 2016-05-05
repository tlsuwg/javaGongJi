package gongji.tomcat.onlyLink;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AllLinkMain {

	public static String IP = "118.145.5.138";
	public static int Port = 3690;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AllLinkMain mAllLinkMain = new AllLinkMain();
		mAllLinkMain.link();

	}

	long checkTime = 3000;

	Set<Socket> set = Collections.synchronizedSet(new HashSet());
	List<Socket> list = new LinkedList<Socket>();

	boolean is = false;

	private void link() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(checkTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					int size1 = set.size();
					System.out.println("check1:" + size1);

					list.clear();

					synchronized (set) {
						Iterator<Socket> it = set.iterator();
						while (it.hasNext()) {
							Socket socket = it.next();
							if (!socket.isConnected()) {
								// set.remove(socket);
								list.add(socket);
							}
						}
					}

					// for (Socket socket : set) {
					//
					// }

					try {
						for (Socket s : list) {
							// synchronized (set) {
							set.remove(s);
							s.close();
							// }
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					int size2 = set.size();
					System.out.println("check2:" + size2);
					is = size2 - size1 < 0;
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SocketAddress address = new InetSocketAddress(IP, Port);

				while (true) {
					if (is) {
						try {
							Thread.sleep(checkTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					Socket s = new Socket();
					// s.setSoTimeout(10);
					try {
						s.connect(address, 500);
						synchronized (set) {
							set.add(s);
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						try {
							s.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
//							e1.printStackTrace();
						}
					}

					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}).start();

	}

}
