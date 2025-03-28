package com.test.TestTask.util;

import com.test.TestTask.model.Intake;

import java.util.Comparator;

public class LocalDateSortingComparator implements Comparator<Intake> {
    @Override
    public int compare(Intake o1, Intake o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
