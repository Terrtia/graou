package graou.algo;

/**
 * Classe de gestion de file de priorité
 *
 */

public class Heap
{
   public int cor[];
   public int item[];
   public int heap[];
   public int size;
   
   /**
    * Crée une file de priorité contenant les entiers de 0 à n-1 avec chacun une priorité de +infini
    * @param N - nombre d'entiers
    */
    public Heap(int N)
	 {
		cor = new int[N+1];
		heap = new int[N+1];
		item = new int[N+1];
		size = N;
		int i;
 		for (i = 0; i < N; i++) cor[i] = i+1;
		for (i = 1; i < N+1; i++) item[i] = i-1;
		for (i = 1; i < N+1; i++) heap[i] = Integer.MAX_VALUE;
	 }

    /**
     * @param x - numéro de l'élément
     * @return la priorité de l'élément numéro x
     */
   public int priority(int x)
	 {
		return heap[cor[x]];
	 }
   
   /**
    * @return true si la file est vide, false sinon
    */
   public boolean isEmpty()
	 {
		return size == 0 ;
	 }
   
   /**
    * @return la taille de la pile
    */
   public int getsize()
	 {
		return size;
	 }
   
   /**
    * Change la valeur de la priorité de l'élément x à la priorité p
    * @param x - élément
    * @param p - nouvelle priorité (< ancienne priorité)
    */
   public void decreaseKey(int x, int p)
	 {
		int pos = cor[x];
		if (pos > size)
		  {
			 
			 System.out.println("erreur pos > size : pos = " + pos + " size = " + size);
			 System.exit(1);
		  }
		
		for (; pos > 1 && p < heap[pos/2]; pos = pos/2)
		  {
			 //System.out.println("hop");
			 heap[pos] = heap[pos/2];
			 item[pos] = item[pos/2];
			 cor[item[pos]] = pos;
		  }
		item[pos] = x;
		heap[pos] = p;
		cor[x] = pos;
	 }

   /**
    * Affiche la file de priorité
    */
   public void affiche()
	 {
		for (int i = 1; i <= size; i++)
		  System.out.print("(" + item[i] + " " + heap[i] + ")");
		System.out.println();
	 }
   
   /**
    * Supprime de la file l'élément dont la priorité est la plus faible et le renvoit
    * @return élément supprimé de la file
    */
   public int pop()
	 {
		int ret = item[1];
		int min = heap[1];
		heap[1] = heap[size];
		heap[size] = min;
		cor[ret] = size;
		item[1] = item[size];
		item[size] = ret;
		cor[item[1]] = 1;
		size--;
		
		
		int d = item[1];
		int k = 1;
		int c;
		int tmp = heap[1];
		for (;2*k <= size; k = c)
		  {
			 c = 2*k;
			 if  ((c != size) && heap[c] > heap[c+1]) c++;
			 if (tmp > heap[c])
			   {
				  heap[k] = heap[c];
				  item[k] = item[c];
				  cor[item[k]] = k;
			   }
			 else break;			 			 			   
		  }
		heap[k] = tmp;
		item[k] = d;
		cor[d] = k;
		return ret;
	 }
   
}

