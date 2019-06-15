/*******************************************************************************
 * The MIT License (MIT)
 * 
 * Copyright (C) 2014-2018 Sam Bassett (aka Lothrazar)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.lothrazar.arrowharvest;

import java.util.List;
import net.minecraft.util.ResourceLocation;

public class UtilString {

  /**
   * One day i might make this a setting or an input arg for now i have no use to turn it off
   */
  public static final boolean matchWildcard = true;

  /**
   * If the list has "hc:*_sapling" and input is "hc:whatever_sapling" then match is true
   * 
   * @param list
   * @param toMatch
   * @return
   */
  public static boolean isInList(final List<String> list, ResourceLocation toMatch) {
    if (toMatch == null) {
      return false;
    }
    for (String strFromList : list) {
      if (strFromList == null || strFromList.isEmpty()) {
        continue;//just ignore me
      }

      if (matchWildcard) {
        String[] blockIdArray = strFromList.split(":");
        if (blockIdArray.length <= 1) {
          ChorusArrowMod.logger.error("Invalid config value for block : " + strFromList);
          return false;
        }
        String modIdFromList = blockIdArray[0];
        String blockIdFromList = blockIdArray[1];//has the *
        String modIdToMatch = toMatch.getResourceDomain();
        String blockIdToMatch = toMatch.getResourcePath();
        if (modIdFromList.equals(modIdToMatch) == false) {
          continue;
        }
        String blockIdListWC = blockIdFromList.replace("*", "");
        if (blockIdToMatch.contains(blockIdListWC)) {
          return true;
        }
      }
    }
    return false;
  }
}