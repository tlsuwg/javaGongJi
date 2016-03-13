package gongji.tomcat.cando;

import java.util.ArrayList;
import java.util.List;

public class LinkerManger {

	private List<Linker> mlist = new ArrayList<Linker>();

	public void addOne(Linker mLinker) {
		this.mlist.add(mLinker);
	}

	public void removeOne(Linker mLinker) {
		this.mlist.remove(mLinker);
	}

	public int size() {

		return mlist.size();
	}

	public void send() {

		for (int i = 0; i < mlist.size(); i++) {
			Linker l = mlist.get(i);
			if (l != null)
				l.send();
		}
	}

}
