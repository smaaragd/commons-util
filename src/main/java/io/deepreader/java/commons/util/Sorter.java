package io.deepreader.java.commons.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * by LinkedList: http://www.leveluplunch.com/java/examples/sort-order-map-by-values/
 * * User: Danyang
 * Date: 12/31/2014
 * Time: 14:08
 */
public class Sorter {
    public static abstract class ValueComparator<K, V> implements Comparator<K> {
        protected Map<K, V> base;

        public ValueComparator(Map<K, V> base) {
            this.base = base;
        }

        /**
         * return 0 would merge keys.
         * return 0 when keys are equal; otherwise never returns 0, indicating equality, causing the Map.get() method
         * to not find matches.
         *
         * Catch the null pointer exception when calling .containsKey(K)
         * @param a
         * @param b
         * @return
         */
        @Override
        public int compare(K a, K b) {
            try {
                if ((Integer) base.get(a)<(Integer) base.get(b))
                    return 1;
                else if(a.equals(b))
                    return 0 ;
                else
                    return -1;
            }
            catch (NullPointerException e) {
                return -1;
            }

        }
    }

    /**
     * 
     * @param map
     * @param vc the ValueComparator implemented, you need to implement the compare() method
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> TreeMap<K, V> sortByValues(Map<K, V>map, ValueComparator<K, V> vc) {
        TreeMap<K, V> sortedMap = new TreeMap<>(vc);
        sortedMap.putAll(map);
        return sortedMap;
    }
}
