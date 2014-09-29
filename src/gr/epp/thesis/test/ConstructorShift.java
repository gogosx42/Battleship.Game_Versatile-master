/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gr.epp.thesis.test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import static java.lang.System.out;

public class ConstructorShift {
    public static void main(String... args) {
	try {
           Class cArg = Class.forName("gr.epp.thesis.api.GenericValues");

	    Class c = Class.forName("gr.epp.thesis.GameControl");
	    Constructor[] allConstructors = c.getDeclaredConstructors();
	    for (Constructor ctor : allConstructors) {
		Class<?>[] pType  = ctor.getParameterTypes();
                for (Class<?> pType1 : pType) {
                    if (pType1.equals(cArg)) {
                        out.format("%s%n", ctor.toGenericString());
                        
                        Type[] gpType = ctor.getGenericParameterTypes();
                        for (int j = 0; j < gpType.length; j++) {
                            char ch = (pType[j].equals(cArg) ? '*' : ' ');
                            out.format("%7c%s[%d]: %s%n", ch,
                                    "GenericParameterType", j, gpType[j]);
                        }
                        break;
                    }
                }
	    }

        // production code should handle this exception more gracefully
	} catch (ClassNotFoundException x) {
	           System.out.println("Class not found");
	}
    }
}
 
