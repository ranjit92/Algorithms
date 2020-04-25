import com.ranjit.algo.datastructures.dynamicarray.DynamicArray;

public class MainClass {


	public static void main(String[] args) {
		

//		check Dynamic Array
		DynamicArray<Integer> da = new DynamicArray<>();
		da.add(1);
		da.add(2);
		da.add(10);
		System.out.println(da.indexOf(10));
		System.out.println(da.contains(13));
		da.remove(10);
		System.out.println(da.indexOf(10));
	}
}
