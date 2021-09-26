package com.syed.core;

public final class Grade {

    private final int total;
    private final int count;
    private final int grade;
    private int achieved;

    public Grade(int total, int count) {
        this.achieved = 0;
        this.total = total;
        this.count = count;
        this.grade = (count == 0) ? this.total : this.total / this.count;
    }
    
    public void increment() {
        this.achieved += this.achieved < this.total ? this.grade : 0;
    }

    public int getCount() {
        return this.count;
    }

    public int getTotal() {
        return this.total;
    }

    public int finalGrade() {
        return this.achieved;
    }
}