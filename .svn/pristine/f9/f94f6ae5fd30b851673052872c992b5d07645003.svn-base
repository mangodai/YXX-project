package MapTEst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class map1 {
//	public static void main(String[] args) {
//		Map<Integer, String> map = new HashMap<Integer, String>();
//		   map.put(1, "a");
//		   map.put(2, "b");
//		   map.put(3, "c");
//		   map.put(4, "d");
//		   Set<Integer> keys1 = map.keySet();
//		   System.out.println(keys1);
//	}
	@Test
	public void test1(){
		Map<String, List<Integer>> resMap = new HashMap<String, List<Integer>>();
		String id_num = "622726199304282052";
		List<Integer> hList = new ArrayList<Integer>();
//		hList.add(115);
//		hList.add(100);
//		hList.add(112);
//		hList.add(132);
//		hList.add(178);
//		hList.add(125);
//		hList.add(110);
//		hList.add(15);
//		hList.add(25);
		
		resMap.put(id_num, hList);
		System.out.println(resMap.keySet());
		List<Integer> clist = resMap.get("622726199304282052");
		System.out.println(clist.size()+"@");
		System.out.println(resMap.get("622726199304282052"));
//		System.out.println(resMap.size());
//		System.out.println(resMap.entrySet());	
//		System.out.println(resMap.values()+"@");
//		System.out.println("获取key"+resMap.keySet());
	}
public static	List<Integer> errorList = new ArrayList<Integer>();
	public void test2(){
		errorList.add(123);
	}
//	public static void main(String[] args) {
	@Test
	public void test3(){
		map1 map1 = new map1();
		for (int i = 0; i < 10; i++) {
			map1.test2();
		}
		for (int a :errorList) {
			System.out.println(a);
		}
	}
	
	
	public static void main(String[] args) {
		map1 map1 = new map1();
		for (int i = 0; i < 10; i++) {
			map1.test3();
		}
		System.out.println("@@"+errorList.size());
	}
	
}
