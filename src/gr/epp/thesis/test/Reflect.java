package gr.epp.thesis.test;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 *
 * @author George
 */
public class Reflect {

    private static Object cArg;

    public static void main(String[] args) {
        Constructor cn[];
//        Class cc[];
        Method mt[];
//        Field ff[];
        Class pt[];

        Class c = null;

        String className = "gr.epp.thesis.GameControl";

        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            System.out.println("Couldn't find class: '" + className + "'");
        }

//        ff=c.getFields();
//        
//        for (Field z:ff) {
//            System.out.println(z.getName());
//        }
        mt = c.getMethods();
        
        for (Method n : mt) {
            System.out.println("Method:" + n.getName());
        }

        cn = c.getDeclaredConstructors();

        for (Constructor x : cn) {
            System.out.println(x.getName());

            pt = x.getParameterTypes();

            for (Class m : pt) {
//                if("".equals(m.getName().toString())){
//                    System.out.println("No parameter");
//                }
                System.out.println("Parameter Type: " + m.getName());
            }
            System.out.println("-------------------");
        }

    }

}
