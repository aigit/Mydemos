package org.study.collection.map;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;


@Data
@AllArgsConstructor
public class Employee {

    private String name;
    private int age;
    private MyDate birthday;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name) && birthday.equals(employee.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (age != employee.age) return false;
        if (!name.equals(employee.name)) return false;
        return birthday.equals(employee.birthday);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + birthday.hashCode();
        return result;
    }
}
