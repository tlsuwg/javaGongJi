package com.suyi.findPort;import java.io.IOException;import java.io.OutputStreamWriter;import java.net.InetAddress;import java.net.Socket;import java.net.UnknownHostException;import java.util.ArrayList;import java.util.Iterator;import java.util.List;import java.util.Set;import java.util.TreeSet;import java.util.concurrent.ExecutorService;import java.util.concurrent.Executors;public class FindIPsOnePortSomeThreads2 {	public static int Port = 3306;	// public static int Port = 80;	public static boolean is = true;	public static int maxThread = 100;	public static Set set = new TreeSet();	public static List<String> list = new ArrayList<String>();	public static void main(String[] args) {		// TODO Auto-generated method stub		new Thread(new Runnable() {			@Override			public void run() {				for (int i = 101; i < 102; i++) {					if (i == 192)						continue;					for (int i2 = 0; i2 < 255; i2++) {						for (int i3 = 0; i3 < 255; i3++) {							for (int i4 = 0; i4 < 255; i4++) {								final String ip = i + "." + i2 + "." + i3 + "."										+ i4;								if (list.size() > maxThread) {									try {										synchronized (list) {//											System.out.println("锁死");											list.wait();										}									} catch (InterruptedException e) {										// TODO Auto-generated catch block										e.printStackTrace();									}								}								list.add(ip);							}						}					}				}			}		}).start();				final ExecutorService pool = Executors				.newFixedThreadPool(maxThread);				new Thread(new Runnable() {			@Override			public void run() {				// TODO Auto-generated method stub				while (list.size() != 0) {					if (list.size() > 3) {						String ip = list.remove(0);//						System.out.println(ip);						link(pool, ip, Port);					} else {						synchronized (list) {							list.notify();						}						try {							Thread.sleep(1000);						} catch (InterruptedException e) {							// TODO Auto-generated catch block							e.printStackTrace();						}					}				}				pool.shutdown();			}		}).start();		// 检查线程执行，		new Thread(new Runnable() {			@Override			public void run() {				// TODO Auto-generated method stub				System.out.println("checking  find  port  thread running ");				int k = 0;				while (true) {					try {						Thread.sleep(1000);					} catch (InterruptedException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}//					System.out.print(k++ + " ");//					if (k % 10 == 0) {//						System.out.println();//					}					if (pool.isTerminated()) {						Iterator is = set.iterator();						int i = 0;						System.out.println();						System.out.println("get port used time" + k);						while (is.hasNext()) {							System.out.print(is.next() + " ");							i++;							if (i == 10) {								System.out.println("");								i = 0;							}						}						break;					}				}			}		}).start();	}	private static void link(ExecutorService pool, final String ip, int port2) {		// TODO Auto-generated method stub		pool.execute(new Runnable() {			@Override			public void run() {				// TODO Auto-generated method stub				try {					// InetAddress mAddress = InetAddress.getByName(IP);					// System.out.println(ip+"find");					Socket s = new Socket(ip, Port);					// s.setSoTimeout(10);					// OutputStreamWriter ow = new OutputStreamWriter(s					// .getOutputStream());					// ow.write("pianzi");					// ow.flush();					System.err.println("对方" + ip);					set.add(s.getPort());					s.close();				} catch (UnknownHostException e) {					// TODO Auto-generated catch block					// e.printStackTrace();				} catch (IOException e) {					// TODO Auto-generated catch block					// e.printStackTrace();				}			}		});	}}