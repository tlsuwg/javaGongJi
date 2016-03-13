package gongji.tomcat.cando;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Linker {

	LinkerManger manger;

	Socket s;
	BufferedWriter br;



	public Linker(String ip, int port, LinkerManger manger2) {
		// TODO Auto-generated constructor stub
		
		manger=manger2;

		try {
			s = new Socket(ip, port);
			br = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			manger.addOne(this);
			send();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		
		
	}

	public void send() {

		if (s.isConnected()) {
			try {
				br.write("SHUTDOWN");
				br.flush();
			} catch (IOException e) {
				// e.printStackTrace();
				remove();
			}
		} else {
			remove();
		}
	}

	private void remove() {
		// TODO Auto-generated method stub
		manger.removeOne(this);

		try {
			br.close();
			s.close();
		} catch (IOException e1) {

		}

	}

}
