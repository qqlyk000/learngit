package com.test;

import java.util.Arrays;

/**
 * Author: XianDaLi
 * Date: 2020/9/7 3:31
 * Remark:
 * Sort1 快排
 * Sort2 堆排
 * Sort3 归并
 */
public class Sort_QHM {
	public static void main(String[] args) {
		Sort_QHM p = new Sort_QHM();
		int[] arr = {7,9,4,5,1,6,3,2};
		p.Sort1(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

	public void Sort1(int[] arr,int l,int r){
		if(l<r){
			int p = QuickSort1(arr,l,r);
			Sort1(arr,l,p-1);
			Sort1(arr,p+1,r);
		}
	}

	public int QuickSort1(int[] arr,int l,int r){
		int p = arr[l];
		while (l<r){
			while (l<r && arr[r]>=p) r--;
			arr[l]=arr[r];
			while (l<r && arr[l]<=p) l++;
			arr[r]=arr[l];
		}
		arr[l]=p;
		return l;
	}

	public void Sort2(int[] arr){
		for (int i = arr.length/2-1; i >= 0; i--)
			Heap(arr,i,arr.length);
		for (int i = arr.length-1; i > 0; i--) {
			swap(arr,0,i);
			Heap(arr,0,i);
		}
	}
	void Heap(int[] arr, int i, int length) {
		int temp = arr[i]; // 先取出当前元素i
		for (int k = i*2+1; k < length; k=2*k+1) { // 从i结点的左子结点开始，也就是2i+1处开始
			if(k+1<length && arr[k]<arr[k+1]) k++;
			if(arr[k]>temp){
				arr[i]=arr[k];
				i=k;
			}else break;
		}
		arr[i]=temp;
	}
	void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public void MergeSort(int[] arr,int l,int m,int r,int[] temp){
		int i = l;
		int j = m+1;
		int t = 0;
		while (i<=m && j<=r){
			if(arr[i]<=arr[j]) temp[t++]=arr[i++];
			else temp[t++]=arr[j++];
		}
		while (i<=m) temp[t++]=arr[i++];
		while (j<=r) temp[t++]=arr[j++];
		t=0;
		while (l<=r){
			arr[l++]=temp[t++];
		}
	}
	public void Sort3(int[] arr){
		int[]temp = new int[arr.length];
		int r = arr.length-1;
		Sort3(arr,0,r,temp);
	}
	public void Sort3(int[] arr,int l,int r,int[] temp){
		if(l<r){
			int m = (l+r)/2;
			Sort3(arr,l,m,temp);
			Sort3(arr,m+1,r,temp);
			MergeSort(arr,l,m,r,temp);
		}
	}




}
