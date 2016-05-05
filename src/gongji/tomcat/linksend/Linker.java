package gongji.tomcat.linksend;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Linker {

	LinkerManger manger;

	Socket s;
//	BufferedWriter br;
//	OutputStreamWriter mOutputStreamWriter;
	OutputStream mOutputStream;



	public Linker(String ip, int port, LinkerManger manger2) {
		// TODO Auto-generated constructor stub
		
		manger=manger2;

		try {
			s = new Socket(ip, port);
			mOutputStream=s.getOutputStream();
//			mOutputStreamWriter=	new OutputStreamWriter(mOutputStream);
//			br = new BufferedWriter(mOutputStreamWriter);
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
	
	byte[] bs=new byte[10*1];

	public void send() {

		if (s.isConnected()) {
			try {
				mOutputStream.write(bs);
//				br.flush();
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
//			br.close();
//			mOutputStreamWriter.close();
			mOutputStream.close();
			s.close();
		} catch (IOException e1) {

		}

	}

}
