package sunshaoqiang.banzi;import java.net.Socket;import java.text.DateFormat;import java.text.SimpleDateFormat;import java.util.Date;import java.util.LinkedList;import java.util.List;public class OneThreadToLinkServers {	/**	 * 	 * @param args	 */	public static void main(String[] args) {		// System.out.println(format(new Date()));		try {			String host = "192.168.2.109";			Integer port = 10003;			Integer size = 65000;			List<Socket> list = new LinkedList<Socket>();			Long last = System.currentTimeMillis();			for (int i = 1; i <= size; i++) {				Date d = new Date();				Socket socket = new Socket(host, port);				socket.setSoTimeout(110);				System.out.println(i + "\t" + host + ":" + port + "\t isBound:"						+ socket.isBound() + "\t time:" + format(d) + "\t ��ʱ:"						+ (d.getTime() - last));				list.add(socket);				last = d.getTime();//				Thread.sleep(1L);			}		} catch (Exception e) {			e.printStackTrace();		}		try {			Thread.sleep(1000 * 3600);		} catch (InterruptedException e) {			e.printStackTrace();		}	}	static String format(Date d) {		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");		return df.format(d);	}}