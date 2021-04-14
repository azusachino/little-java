package cn.az.java.basic.reflect;

import java.util.Objects;

/**
 * @author az
 * @since 2020-04-09
 */
public class Dummy {

    private String name;

    public Dummy() {
    }

    public Dummy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Dummy dummy = (Dummy) o;

        return Objects.equals(name, dummy.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Dummy{" +
            "name='" + name + '\'' +
            '}';
    }
}
