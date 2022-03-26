import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class Test {

	public static void main(String[] args) {
		
		ArrayList<String> nameList = new ArrayList<String>();
		String name;
		nameList.add("A");
		nameList.add("B");
		nameList.add("C");
		nameList.add("D");
		nameList.add("E");
		nameList.add("F");
		
		Iterator<String> it = nameList.iterator();
		while(it.hasNext()) {
			name = it.next();
			if("F".equals(name)) {
				it.remove();
			}
		}
		for(String l : nameList) {
			System.out.println(l);
		}
		System.out.println();
		
		Vector<String> vt = new Vector<String>();
		
		vt.addElement("1");
		vt.addElement("2");
		vt.addElement("3");
		vt.addElement("4");
		vt.addElement("5");
		vt.addElement("6");
		
		Enumeration<String> e = vt.elements();
		
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}
		
	}
}
