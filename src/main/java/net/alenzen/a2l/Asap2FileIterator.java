package net.alenzen.a2l;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Asap2FileIterator implements Iterator<IAsap2TreeElement> {
	private Queue<IAsap2TreeElement> a2lQueue;
	private Set<Object> queued;

	public Asap2FileIterator(IAsap2TreeElement root) {
		this.queued = Collections.newSetFromMap(new IdentityHashMap<>());
		this.a2lQueue = new LinkedList<IAsap2TreeElement>();
		a2lQueue.add(root);
	}

	@Override
	public boolean hasNext() {
		return !a2lQueue.isEmpty();
	}

	@Override
	public IAsap2TreeElement next() {
		IAsap2TreeElement element = a2lQueue.poll();
		
		if(element == null) {
			return null;
		}
		
		List<IAsap2TreeElement> subNodes = element.collectSubNodes();
		if(subNodes != null) {
			for(IAsap2TreeElement n : subNodes) {
				if(!queued.contains(n)) {
					a2lQueue.add(n);
					queued.add(n);
				}
			}
		}
		
		return element;
	}

	static void addIfNotNull(List<IAsap2TreeElement> subNodes, List<? extends IAsap2TreeElement> treeElements) {
		if(treeElements != null) {
			subNodes.addAll(treeElements);
		}
	}

	static void addIfNotNull(List<IAsap2TreeElement> subNodes, IAsap2TreeElement treeElement) {
		if(treeElement != null) {
			subNodes.add(treeElement);
		}
	}

}
