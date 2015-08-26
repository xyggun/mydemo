package com.xyggun.baselibrary.inject.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtil {
	
	public static <E> ArrayList<E> Collection2List(Collection<? extends E> elements) {
		if (elements == null) {
			return null;
		}
		List<E> list = new ArrayList<E>();
		list.addAll(elements);

		return (ArrayList<E>) list;
	}
	
	public static <E> Collection<E> List2Collection(List<E> elements) {
		if (elements == null || elements.size() <= 0) {
			return null;
		}
		Collection<E> collections = elements;
		for (E e : elements) {
			collections.add(e);
		}
		
		return collections;
	}
}
