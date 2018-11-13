/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Class DebugTool.
 */
public class DebugTool {
    public static List<Integer> removedDuplicateCountList = new ArrayList<>();
    public static float averageRemovedDuplicate = 0f;

    public static float getAverageRemovedDuplicate() {
        float count = 0;
        for (Integer i : removedDuplicateCountList) {
            count += i;
        }
        return count / removedDuplicateCountList.size();
    }
}
