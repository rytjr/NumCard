package NumCard;

import java.util.*;
import java.io.*;

/*숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다. 
 * 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 가지고 있는지 아닌지를 구하는 프로그램을 작성하시오.*/

// 단순 비교를 통해 해결하면 시간이 오래 걸림으로 탐색 알고리즘을 사용함
//이분 탐색을 제외한 알고리즘을 사용하려 했지만 힘들 것으로 판단하고 이분탐색을 사용함

public class NumCard {
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n1 = Integer.parseInt(bf.readLine());
		
		int[] arr1 = new int[n1];
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		for(int i = 0; i < n1; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		
        int n2 = Integer.parseInt(bf.readLine());
		
		int[] arr2 = new int[n2];
		int[] arr3 = new int[n2];
		
		st = new StringTokenizer(bf.readLine());
		
		for(int i = 0; i < n2; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		// 병합정렬을 이용해 정렬함
		mergeSort(arr1, 0, n1 - 1);
		
		for(int i = 0; i < n2; i++) {
			//이분 탐색을 통해 원하는 값을 찾고 return을 사용해 0과 1을 arr3에 넣어줌
			arr3[i] = TwoSearch(arr2[i], arr1);
		}
		
		for(int i : arr3) {
			bw.write(i + " ");
		}
		bw.flush();
		bw.close();
	}
	
	//정렬된 배열에서만 사용가능 때문에 병합정렬을 사용해 미리 정렬을 하고 넘기게 되었다.
	public static int TwoSearch(int key, int[] arr1) {
		
		int left = 0;
		int right = arr1.length - 1;
		
		while(left <= right) {
			int middle = (right - left) / 2 + left;
			if(arr1[middle] < key) {
				left = middle + 1;
			}
			else if(arr1[middle] == key) {
				return 1;
			}
			else {
				right = middle - 1;

			}
		}

		return 0;
	}
	
//재귀적 호출을 이용해 정렬한다
	public static void mergeSort(int[] arr1, int left, int right) {
		
		if(left < right) {
			
			int middle = (left + right) / 2;
			
			mergeSort(arr1, left, middle);
			mergeSort(arr1, middle + 1, right);
			
			merge(arr1, left, middle, right);
		}
		
	}
	
	public static void merge(int[] arr1, int left, int middle, int right) {
		
		int n1 = middle - left + 1;
		int n2 = right - middle;
		
		int[] arrleft = new int[n1];
		int[] arrright = new int[n2];
		
		for(int i = 0; i < n1; i++) {
			arrleft[i] = arr1[i + left];
		}
		
		for(int i = 0; i < n2; i++) {
			arrright[i] = arr1[i + middle + 1];
		}
		
		int i = 0; int j = 0; int k = left;
		while(i < n1 && j < n2) {
			if(arrleft[i] <= arrright[j]) {
				arr1[k] = arrleft[i];
				i++;
			}
			else {
				arr1[k] = arrright[j];
				j++;
			}
			k++;
		}
		
		while(i < n1) {
			arr1[k] = arrleft[i];
			i++;
			k++;
		}
		
		while(j < n2) {
			arr1[k] = arrright[j];
			j++;
			k++;
		}
	}

}
