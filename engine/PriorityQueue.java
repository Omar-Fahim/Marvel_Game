package engine;

import java.util.ArrayList;

import model.world.Champion;

public class PriorityQueue {

	@SuppressWarnings("rawtypes")
	private Comparable[] elements;
	private int nItems;
	private int maxSize;
	private String s = "";

	/*public String getS() {
		return s;
	}*/

	public PriorityQueue(int size) {
		maxSize = size;
		elements = new Comparable[maxSize];
		nItems = 0;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insert(Comparable item) {

		int i;
		for (i = nItems - 1; i >= 0 && item.compareTo(elements[i]) > 0; i--)
			elements[i + 1] = elements[i];

		elements[i + 1] = item;
		nItems++;
	}

	@SuppressWarnings("rawtypes")
	public Comparable remove() {
		nItems--;
		return elements[nItems];
	}

	public boolean isEmpty() {
		return (nItems == 0);
	}

	public boolean isFull() {
		return (nItems == maxSize);
	}

	@SuppressWarnings("rawtypes")
	public Comparable peekMin() {
		return elements[nItems - 1];
	}

	public int size() {
		return nItems;
	}
	/*public String toString() {
		//String m = "Turn Order : ";
		String s = "";
		PriorityQueue tmp = new PriorityQueue(this.size());
		while(!tmp.isFull()&&!this.isEmpty()) {
			Champion c = (Champion) this.remove();
			s += ""+c.getName()+" , "; 
		}
       while(!this.isFull()&&!tmp.isEmpty()) {
			this.insert(tmp.remove());
			
		}
		return s;*/
	/*PriorityQueue tmp = new PriorityQueue(this.size());
	while(!tmp.isFull()) {
		Champion c = (Champion) this.remove();
		s += "\n"+c.getName()+" , ";
		tmp.insert(c);
		
	}
   while(!this.isFull()) {
		this.insert(tmp.remove());
		
	}*/

/*	@Override
	public String toString() {
		String s = "";
		
		ArrayList<Champion> t = new ArrayList();
		while(!this.isEmpty()) {
			Champion c = (Champion) this.remove();
			s += "\n"+c.getName()+" , ";
			t.add(c);
			
		}
		for(int i =0;i<t.size();i++) {
			this.insert(t.remove(i));
		}
		return s;
	}*/
	
}
