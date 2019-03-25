package com.topbloc.app;

public class Student {
    private String studentId;
    private int testScore;
    private int testRetakeScore;
    private String gender;
    private String major;


    public Student(String studentId, String major, String gender) {
        this.studentId = studentId;
        this.major = major;
        this.gender = gender;
        this.testRetakeScore = 0;
    }

    public int getMaxScore() {
        return testScore > testRetakeScore ? testScore : testRetakeScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getTestScore() {
        return testScore;
    }

    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }

    public int getTestRetakeScore() {
        return testRetakeScore;
    }

    public void setTestRetakeScore(int testRetakeScore) {
        this.testRetakeScore = testRetakeScore;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return studentId + " " + major + " " + gender + " " + testScore + " " +  testRetakeScore + " " + getMaxScore();
    }
}
