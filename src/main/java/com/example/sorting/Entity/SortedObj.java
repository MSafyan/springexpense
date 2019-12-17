package com.example.sorting.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="sortedobj")


public class SortedObj {




        @Id
        private long idu;

    private long BubbleSort;
    private long insertionSort;
    private long SelectionSort;
    private long MergeSort;
    private long QuickSort;





    }


