
public class Main {

	public static void main(String[] args) {
		Heap heap = new Heap();
		int[] numeros = new int[] { 8, 1, 3, 12, 90, 5, 2 };
		for (int numero : numeros) {
			heap.insert(numero);
			System.out.println(heap);
		}
		System.out.println("Extraigo mínimo: " + heap.extractMin());
		System.out.println("Post-extracción: " + heap);
	}

}
