package com.ssm.bo;

/**
 * @Author: Think
 * @Date: 2019/3/28
 * @Time: 16:15
 */
public class Student {
    private String name;
    private String subject;
    private double score;

    public Student(String name, String subject, double score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                '}';
    }
}
