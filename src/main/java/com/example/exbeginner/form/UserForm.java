package com.example.exbeginner.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserForm {
    @NotBlank(message="名前は必須です。")
    private String name;
    @NotNull(message = "年齢は必須です。")
    private Integer age;
    @NotBlank(message="コメントは必須です。")
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "UserForm [name=" + name + ", age=" + age + ", comment=" + comment + "]";
    }

}
