package com.mj.nttdata.assignment.manikjain.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.mj.nttdata.assignment.manikjain.enums.MatchType;

public class ValidationUtil {
	
	public static Set<String> allowedMatchTypes() {
		return Arrays.asList(MatchType.values()).stream().map(matchType -> matchType.name()).collect(Collectors.toSet());
	}

}
