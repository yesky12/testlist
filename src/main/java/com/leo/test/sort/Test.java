package com.leo.test.sort;

/**
 * User: lin.gong
 * Date: 12-10-14
 * Time: 上午12:13
 */
public class Test {
    public static void main(String[] args){
        Integer[] a={5,23,25,7,30,83,45,6,4,56};
        Sort.quicksort(a);
        PrintSort.print(a);
    }
}
