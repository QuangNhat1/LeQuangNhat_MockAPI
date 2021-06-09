package com.example.crud_api;

public class Student {
    private int id;
    private String stName;
    private Boolean sex;
    private int age;
    private int height;
    private int weight;
    private int imgStudent;

    public Student(int id, String stName, Boolean sex, int age, int height, int weight, int imgStudent) {
        this.id = id;
        this.stName = stName;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.imgStudent = imgStudent;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getImgStudent() {
        return imgStudent;
    }

    public void setImgStudent(int imgStudent) {
        this.imgStudent = imgStudent;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stName='" + stName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", imgStudent=" + imgStudent +
                '}';
    }
}
