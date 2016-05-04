/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import model.Categorie;
import model.Etablissement;

/**
 *
 * @author Thomas
 */
public class validator {

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String email) {
        return pattern.matcher(email).find();
    }

    public static abstract class superComparator implements Comparator<Object> {
/*
        public static <T> BiPredicate<T, T> match(Comparator<T> f) {
            return (a, b) -> f.compare(a, b) == 0;
        }

        public static <T, U> Predicate<U> bind(BiPredicate<T, U> f, T t) {
            return u -> f.test(t, u);
        }

        public static <T> boolean contains(List<T> list, T item, Comparator<? super T> comparator) {
            return list.stream().anyMatch(bind(match(comparator), item));
        }
        */
         public static <T> boolean contains(List<T> list, T item, Comparator<? super T> comparator) {
         for (T o : list) {
         if (comparator.compare(o, item) == 0) {
         return true;
         }
         }
         return false;
         }
         
    }

    public static class comparatorEtab extends superComparator {

        @Override
        public int compare(Object o1, Object o2) {
            return ((Etablissement) o1).getId().compareTo(((Etablissement) o2).getId());
        }
    }

    public static class comparatorCateg extends superComparator {

        @Override
        public int compare(Object o1, Object o2) {
            return ((Categorie) o1).getId().compareTo(((Categorie) o2).getId());
        }

    }
}
