package syed.code.core;

public final class Grade {

    private final int total;
    private final int count;
    private final int grade;
    private int acheived;

    public Grade(int total, int count) {
        this.acheived = 0;
        this.total = total;
        this.count = count;
        this.grade = this.total/this.count;
    }
    
    public void increment() {
        this.acheived += this.acheived < this.total ? this.grade : 0;
    }

    public int getCount() {
        return this.count;
    }

    public int getTotal() {
        return this.total;
    }

    public int finalGrade() {
        return this.acheived;
    }
}