package com.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sorting {
	public static String sorting(String s) {
		s=s.toLowerCase();
		char ch[]=s.toCharArray();
		Arrays.sort(ch);
		return String.valueOf(ch);
	}
}
