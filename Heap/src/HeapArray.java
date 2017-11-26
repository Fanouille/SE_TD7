import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import heap.Heap;

public class HeapArray<E> implements Heap<E>{
	
	private int maxCapacity;
	private Object liste[];
	private int size;
	private Comparator <? super E> comparateur;

	private HeapArray(int max, Comparator <? super E> comp){
		maxCapacity = max;
		size = 0;
		comparateur = comp;
	}

	@Override
	public HeapArrayIterator<E> iterator() {
		return new HeapArrayIterator<E>(liste);
	}
	
	public class HeapArrayIterator<E> implements Iterator<E> {
		private int index;
		private Object lis[];
		
		public HeapArrayIterator(Object liste[]){
			index = -1;
			this.lis = liste;
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean hasNext() {
			return !(this.lis.length==index);
			//si size=index true, il y a plus rien après -> return false
		}
		
		@SuppressWarnings("unchecked")
		public E get(int ind){
			return (E)lis[ind];
		}

		@Override
		public E next() throws NoSuchElementException {
			if (hasNext()){
				index +=1;
				return get(index);
			}
			else{
				throw new NoSuchElementException("No next element");
			}
		}
		
		@SuppressWarnings({ "unchecked", "null" })
		@Override
		public void remove(){
			if(index <= 0) {
	            throw new IllegalStateException("You can't delete element before first next() method call");
	        }
			else{
				Object interm[]=null;
				System.arraycopy(lis, index+1, interm, 0, size()-index-1);
				for (Object e : interm){
					insertElement((E)e);					
				}
			}
		}
		
	}

	
	@SuppressWarnings("unchecked")
	private void up(int place){ //remonte la derniere valeur
		int place_parent = (place-1)/2;
		while(comparateur.compare((E)liste[place_parent],(E)liste[place])<0){
			//parent < fils
			//inversion pere fils
			E inter = (E)liste[place_parent];
			liste[place_parent] = (E)liste[place];
			liste[place]= inter;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void down(){
		int k = 0; // place dans couche
		int i = 0; //couche
		int place = (int)(k+(Math.pow(2,i) -1));
		int place_fils1 = (int)(2*k+(Math.pow(2,i+1) -1));
		int place_fils2 = (int)(2*k+1+(Math.pow(2,i+1) -1));
		
		while(comparateur.compare((E)liste[place],(E)liste[place_fils1])<0 || comparateur.compare((E)liste[place],(E)liste[place_fils2])<0){
			// echange avec le fils le plus grand 
			if(comparateur.compare((E)liste[place_fils2],(E)liste[place_fils1])<0){//fils1 + grand
				E inter = (E)liste[place];
				liste[place] = (E)liste[place_fils1];
				liste[place_fils1]= inter;
			}
			else{
				E inter = (E)liste[place];
				liste[place] = (E)liste[place_fils2];
				liste[place_fils2]= inter;
			}
		}
	}
	
	@Override
	public boolean insertElement(Object e) {
		if (this.size()<this.maxCapacity){ //on ajoute au bout
			liste[this.size()]= e;
			
			//remonter la valeur au bon endroit 
			up(size());
			return true;
		}
		else{
			//l'arbre des déjà plein, on part du haut, l'elt commence au sommet
			liste[0] = e;
			down();//descendre au bon endroit
			return true;
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E element() throws NoSuchElementException {
		if (this.isEmpty()){
			throw new NoSuchElementException("Object is empty") ;
		}
		else{
			return (E)liste[0];
		}
	}

	@Override
	public boolean isEmpty() {
		if (size()!=0){
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public int size() {
		return this.size;
		
	}


}
