
package mike.exercise.graph.core.impl;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Edge type
 */
public class Edge implements Cloneable, Serializable {
    private static final long serialVersionUID = -3223208452177932855L;
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PACKAGE)
    private Object source;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PACKAGE)
    private Object target;



    @Override
    public String toString() {
        return "(" + source + " : " + target + ")";
    }
}

