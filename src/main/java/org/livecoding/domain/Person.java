package org.livecoding.domain;

import javax.management.relation.Role;

public class Person {

    private final int age;
    private final String name;
    private final Role role;

    public Person(int age, String name, Role role) {
        this.age = age;
        this.name = name;
        this.role = role;
    }



    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
    public enum Role {
        PROGRAMMER, SCRUMMASTER, ARCHITECT, PROJECT_MANAGER, TESTER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (!name.equals(person.name)) return false;
        if (role != person.role) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + name.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}


