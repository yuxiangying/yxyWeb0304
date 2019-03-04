/**
 * 
 */
package com.freemarker;

/**
 * <p>
 * Title: BubbleSort
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author PC
 * @date 2019年1月15日上午10:11:55
 */
public class BubbleSort {
	public static void main(String[] args) {
		int[] arr={6,3,8,2,9,1};
		System.out.println("排序前数组为：");
		for(int num:arr){
			System.out.print(num+" ");
		}
		int[] arrLast = bubbleSort(arr);
		System.out.println();
		System.out.println("排序后的数组为：");
		 for(int num:arrLast){
		 System.out.print(num+" ");
		 } 
	}
	
	private static int[] bubbleSort(int arr[]){
		for(int i=0;i<arr.length-1;i++){//外层循环控制排序趟数
			for(int j=0;j<arr.length-1-i;j++){//内层循环控制每一趟排序多少次
				if(arr[j]>arr[j+1]){
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		} 
		return arr;
	}
}
