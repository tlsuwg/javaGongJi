package com.suyi.findPort;import java.io.IOException;import java.io.OutputStreamWriter;import java.net.InetAddress;import java.net.Socket;import java.net.UnknownHostException;import java.util.Date;import java.util.Iterator;import java.util.Set;import java.util.TreeSet;import java.util.concurrent.ExecutorService;import java.util.concurrent.Executors;public class FindPortSomeThreads2 {	/**	 * @param args	 */	public static int fromPort = 0;	public static int maxPort = 65535;	public static int maxThread = 1000;//这个多线程就是快	// public static String IP = "121.31.30.19";	// public static String IP = "118.123.18.22";	// public static String IP = "223.20.0.1";	public static String IP = "118.145.5.137";	// public static String IP = "180.149.132.47";//百度	// public static String IP ="101.200.130.48";//阿里缺陷	public static Set set = new TreeSet();	static	boolean isShowTime=false;	public static void main(String[] args) {		// TODO Auto-generated method stub		final ExecutorService pool = Executors.newFixedThreadPool(maxThread);		final long startTime=new Date().getTime();		while (fromPort <= maxPort) {			final int port = fromPort;			pool.execute(new Runnable() {				@Override				public void run() {					// TODO Auto-generated method stub					try {						// InetAddress mAddress = InetAddress.getByName(IP);//						 System.out.println(port+"find");						Socket s = new Socket(IP, port);						 s.setSoTimeout(1);						// OutputStreamWriter ow = new OutputStreamWriter(s						// .getOutputStream());						// ow.write("pianzi");						// ow.flush();						System.err.println("对方监听端口:" + s.getPort());						set.add(s.getPort());						s.close();					} catch (UnknownHostException e) {						// TODO Auto-generated catch block						// e.printStackTrace();					} catch (IOException e) {						// TODO Auto-generated catch block						// e.printStackTrace();					}				}			});			fromPort++;		}		pool.shutdown();		// 检查线程执行，		new Thread(new Runnable() {			@Override			public void run() {				// TODO Auto-generated method stub				System.out.println("checking  find  port  thread running ");				int k = 0;				while (true) {					try {						Thread.sleep(1000);					} catch (InterruptedException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}										if(isShowTime){					System.out.print(k++ + " ");					if (k % 10 == 0) {						System.out.println();					}					}					if (pool.isTerminated()) {						Iterator is = set.iterator();						int i = 0;						System.out.println();						long endTime=new Date().getTime();						System.out.println("endTime"+(endTime-startTime)/1000+"s");						System.out.println(IP+"结束，get port time" + k);						while (is.hasNext()) {							System.out.print(is.next() + " ");							i++;							if (i == 10) {								System.out.println("");								i = 0;							}						}						break;					}				}			}		}).start();	}}