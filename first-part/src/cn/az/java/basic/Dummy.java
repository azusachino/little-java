package cn.az.java.basic;

import java.util.HashMap;

/**
 * Dummy
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see module-info.java
 * @since 2020-03-18
 */
public class Dummy {


    interface A{

    }

    public static void main(String[] args) {
        System.out.println(new A() {
            /**
             * Returns a hash code value for the object. This method is
             * supported for the benefit of hash tables such as those provided by
             * {@link HashMap}.
             * <p>
             * The general contract of {@code hashCode} is:
             * <ul>
             * <li>Whenever it is invoked on the same object more than once during
             *     an execution of a Java application, the {@code hashCode} method
             *     must consistently return the same integer, provided no information
             *     used in {@code equals} comparisons on the object is modified.
             *     This integer need not remain consistent from one execution of an
             *     application to another execution of the same application.
             * <li>If two objects are equal according to the {@code equals(Object)}
             *     method, then calling the {@code hashCode} method on each of
             *     the two objects must produce the same integer result.
             * <li>It is <em>not</em> required that if two objects are unequal
             *     according to the {@link Object#equals(Object)}
             *     method, then calling the {@code hashCode} method on each of the
             *     two objects must produce distinct integer results.  However, the
             *     programmer should be aware that producing distinct integer results
             *     for unequal objects may improve the performance of hash tables.
             * </ul>
             * <p>
             * As much as is reasonably practical, the hashCode method defined
             * by class {@code Object} does return distinct integers for
             * distinct objects. (The hashCode may or may not be implemented
             * as some function of an object's memory address at some point
             * in time.)
             *
             * @return a hash code value for this object.
             * @see Object#equals(Object)
             * @see System#identityHashCode
             */
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
    }
}
