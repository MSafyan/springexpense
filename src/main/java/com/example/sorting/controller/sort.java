package com.example.sorting.controller;


import com.example.sorting.Entity.SortedObj;
import com.example.sorting.repositories.sortedrepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping("/api")
@NoArgsConstructor
//@AllArgsConstructor
@Data

public class sort {

    public long idu=0;

    @Autowired
            private sortedrepo repo;


int idglobal;

    public int[] randomArray(@PathVariable int id){
        int[] arr=new int[id];
        for(int a=0;a<id;a++){
            Random rn = new Random();
            int answer = rn.nextInt(10) + 1;
            arr[a]=answer;
        }
        return arr;
    }

    public returnSort bubbleSort(@PathVariable int id){
        idglobal=id;
        int arr[]=new int[idglobal];
        arr=randomArray(idglobal);

        long Stime=System.currentTimeMillis();
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        long Etime=System.currentTimeMillis();
        long time=Etime-Stime;
        returnSort returnBubble= new returnSort("BubbleSort",time);
        System.out.println(returnBubble);
        return returnBubble;
    }

    public void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    public returnSort insertionSort(){
        long Stime=System.currentTimeMillis();
        int arr[]=new int[idglobal];
        arr=randomArray(idglobal);
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        long Etime=System.currentTimeMillis();
        long time=Etime-Stime;
        returnSort returnInsertion= new returnSort("insertionSort",time);
        System.out.println( returnInsertion);
//        printArray(arr);
        return  returnInsertion;
    }

    returnSort selectionSort(){
        long Stime=System.currentTimeMillis();
        int arr[]=new int[idglobal];
        arr=randomArray(idglobal);
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        long Etime=System.currentTimeMillis();
        long time=Etime-Stime;
        returnSort returnInsertion= new returnSort("SelectionSort",time);
        System.out.println( returnInsertion);
        return  returnInsertion;
    }


    void merge(int arr[], int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int [n1];
        int R[] = new int [n2];
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            int m = (l+r)/2;
            sort(arr, l, m);
            sort(arr , m+1, r);
            merge(arr, l, m, r);
        }
    }

    returnSort mergeSort(){
        long Stime=System.currentTimeMillis();
        int arr[]=new int[idglobal];
        arr=randomArray(idglobal);
        sort(arr, 0, arr.length-1);
        long Etime=System.currentTimeMillis();
        long time=Etime-Stime;
        returnSort returnInsertion= new returnSort("MergeSort",time);
        System.out.println( returnInsertion);
        return  returnInsertion;
    }

    public returnSort quickSort(){
        long Stime=System.currentTimeMillis();
        int arr[]=new int[idglobal];
        arr=randomArray(idglobal);
        QuickSort quick=new QuickSort();
        quick.sort(arr, 0, arr.length-1);
        long Etime=System.currentTimeMillis();
        long time=Etime-Stime;
        returnSort returnInsertion= new returnSort("QuickSort",time);
        System.out.println( returnInsertion);
        return  returnInsertion;
    }


//    @Bean
    @GetMapping("/sort/{id}")
    List<returnSort> getSorts(@PathVariable int id){

      returnSort bubble= bubbleSort(id);

        returnSort selection=selectionSort();

        returnSort insertion= insertionSort();

        returnSort merge=mergeSort();

        returnSort quick=quickSort();

        SortedObj sortedobj=new SortedObj(++idu,bubble.getTimeComplexity(),selection.getTimeComplexity(),insertion.getTimeComplexity(),merge.getTimeComplexity(),quick.getTimeComplexity());
        repo.save(sortedobj);
        return Arrays.asList(bubble,selection,insertion,merge,quick);
    }

    @GetMapping("/getsort/{idu}")
    ResponseEntity<?> getSort(@PathVariable Long idu){
        Optional<SortedObj> getsort=repo.findById(idu);
        return getsort.map(response->ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
