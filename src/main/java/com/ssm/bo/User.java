package com.ssm.bo;

import java.util.List;

/**
 * @Author: Think
 * @Date: 2019/3/28
 * @Time: 15:40
 */
public class User {
    private String name;
    private String gender;
    private int age;
    private List<Student> studentList;

    public User(String name, String gender, int age, List<Student> studentList) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.studentList = studentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", studentList=" + studentList +
                '}';
    }
}
