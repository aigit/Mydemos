package org.study.functional;

import java.util.function.Predicate;

public interface ICheckPred<Integer> {

    //boolean check(Predicate<Integer> predicate);

    boolean check(IntPred pred);

}
