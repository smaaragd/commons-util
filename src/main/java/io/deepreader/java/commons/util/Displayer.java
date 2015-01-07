package io.deepreader.java.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * User: Danyang
 * Date: 11/16/14
 * Time: 3:39 PM
 */
public class Displayer {
    public static <E> String display(Object obj, E attribute) {
        for(Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if(field.get(obj).equals(attribute)) {
                    return obj.getClass().getName()+"'s "+field.getName()+": "+attribute.toString();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return "Error accessing attribute";
    }
    
    public static <K, V> String display(Map<K, V> map) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<K, V> entry: map.entrySet()) {  // otherwise casting
            sb.append(entry.getKey().toString()+": "+entry.getValue().toString()+"\n");
        }
        return sb.toString();
    }

    public static String toString(Object aObject) {
        return toString(aObject, System.getProperty("line.separator"));
    }

    public static String toString(Object aObject, String delimiter) {
        StringBuilder result = new StringBuilder();
        result.append( aObject.getClass().getName());
        result.append( " Object {" );
        result.append(delimiter);

        //determine fields declared in aObject class only (no fields of superclass)
        Field[] fields = aObject.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append(" ");
            try {
                field.setAccessible(true);
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(aObject) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(delimiter);
        }
        result.append("}");

        return result.toString();
    }

    public static String display(Exception e){
        StringWriter msg = new StringWriter();
        e.printStackTrace(new PrintWriter(msg));
        return msg.toString();
    }
}
