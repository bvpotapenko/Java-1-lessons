package ru.bvpotapenko.se.j1.oop;

import java.math.BigDecimal;

 /**
 * * Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
 */
 public class Employee{
    String fio;
    String position;
    String email;
    String phone;
    int age;
    BigDecimal salary;

    /**
     * * Конструктор класса должен заполнять эти поля при создании объекта;
     * @param fio
     * @param position
     * @param email
     * @param phone
     * @param age
     * @param salary
     */
    public Employee(String fio, String position, String email, String phone, int age, BigDecimal salary) {
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.salary = salary;
    }

    /**
     * * Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
     */
    public void print(){
        System.out.println(this);
    }
    @Override
    public String toString() {
        return "Employee{" +
                "\n\tFIO='" + fio + '\'' +
                ",\n\tposition='" + position + '\'' +
                ",\n\temail='" + email + '\'' +
                ",\n\tphone='" + phone + '\'' +
                ",\n\tage=" + age +
                ",\n\tsalary=" + salary +
                "\n}";
    }
}