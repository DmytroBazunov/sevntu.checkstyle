////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2011  Oliver Burn
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////
package com.github.sevntu.checkstyle.checks.design;


import static com.github.sevntu.checkstyle.checks.design.ChildBlockLengthCheck.MSG_KEY;

import org.junit.Test;

import com.github.sevntu.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;

/**
 * @author <a href="mailto:Daniil.Yaroslavtsev@gmail.com"> Daniil
 *         Yaroslavtsev</a>
 */
public class ChildBlockLengthTest extends BaseCheckTestSupport
{
    private final DefaultConfiguration checkConfig = createCheckConfig(ChildBlockLengthCheck.class);

    @Test
    public void testNPEonAllBlockTypes() throws Exception
    {
        checkConfig.addAttribute("maxChildBlockPercentage", "100");
        checkConfig.addAttribute("blockTypes", "LITERAL_IF, LITERAL_SWITCH, LITERAL_FOR, "
                  + "LITERAL_DO, LITERAL_WHILE, LITERAL_TRY, LITERAL_ELSE, LITERAL_CATCH");

        String[] expected = {
        };

        verify(checkConfig, getPath("InputChildBlockLengthCheck.java"), expected);
    }

    @Test
    public void testNestedConditions() throws Exception
    {
        checkConfig.addAttribute("maxChildBlockPercentage", "100");
        checkConfig.addAttribute("blockTypes", "LITERAL_IF, LITERAL_SWITCH, LITERAL_FOR, "
                 + "LITERAL_DO, LITERAL_WHILE, LITERAL_TRY, LITERAL_ELSE, LITERAL_CATCH");

        String[] expected = {
        };

        verify(checkConfig, getPath("InputChildBlockLengthCheckNested.java"), expected);
    }

    @Test
    public void testManyBadChildBlocks() throws Exception
    {
        checkConfig.addAttribute("maxChildBlockPercentage", "20");
        checkConfig.addAttribute("blockTypes", "LITERAL_IF, LITERAL_SWITCH, LITERAL_FOR, "
                + "LITERAL_DO, LITERAL_WHILE, LITERAL_TRY, LITERAL_ELSE, LITERAL_CATCH");
        checkConfig.addAttribute("ignoreBlockLinesCount", "0");

        String[] expected = {
        		"15:15: " + getCheckMessage(MSG_KEY, 13, 5),
        };

        verify(checkConfig, getPath("InputChildBlockLengthCheckManyBlocksOnOneScope.java"), expected);
    }

    @Test
    public void testManyBadChildBlocks2() throws Exception
    {
        checkConfig.addAttribute("maxChildBlockPercentage", "19");
        checkConfig.addAttribute("blockTypes", "LITERAL_IF, LITERAL_SWITCH, LITERAL_FOR, "
                + "LITERAL_DO, LITERAL_WHILE, LITERAL_TRY, LITERAL_ELSE, LITERAL_CATCH");
        checkConfig.addAttribute("ignoreBlockLinesCount", "0");

        String[] expected = {
        		"15:15: " + getCheckMessage(MSG_KEY, 13, 4),
        		"31:15: " + getCheckMessage(MSG_KEY, 5, 4),
        };

        verify(checkConfig, getPath("InputChildBlockLengthCheckManyBlocksOnOneScope.java"), expected);
    }

    @Test
    public void testNestedBadChildBlocks() throws Exception
    {
        checkConfig.addAttribute("maxChildBlockPercentage", "70");
        checkConfig.addAttribute("blockTypes", "LITERAL_IF, LITERAL_SWITCH, LITERAL_FOR, "
                + "LITERAL_DO, LITERAL_WHILE, LITERAL_TRY, LITERAL_ELSE, LITERAL_CATCH");
        checkConfig.addAttribute("ignoreBlockLinesCount", "0");

        String[] expected = {
        		"41:7: " + getCheckMessage(MSG_KEY, 7, 6),
        		"42:9: " + getCheckMessage(MSG_KEY, 5, 4),
        };

        verify(checkConfig, getPath("InputChildBlockLengthCheckDoubleNested.java"), expected);
    }

    @Test
    public void testIgnoreBlockLinesCountOption() throws Exception
    {
        checkConfig.addAttribute("maxChildBlockPercentage", "19");
        checkConfig.addAttribute("blockTypes", "LITERAL_IF, LITERAL_SWITCH, LITERAL_FOR, "
                + "LITERAL_DO, LITERAL_WHILE, LITERAL_TRY, LITERAL_ELSE, LITERAL_CATCH");
        checkConfig.addAttribute("ignoreBlockLinesCount", "26");

        String[] expected = {
        };

        verify(checkConfig, getPath("InputChildBlockLengthCheckManyBlocksOnOneScope.java"), expected);
    }

    @Test
    public void testBadChildBlocksThatAreDoubleNested2() throws Exception
    {
        checkConfig.addAttribute("maxChildBlockPercentage", "70");
        checkConfig.addAttribute("blockTypes", "LITERAL_IF, LITERAL_SWITCH, LITERAL_FOR, "
                + "LITERAL_DO, LITERAL_WHILE, LITERAL_TRY, LITERAL_ELSE, LITERAL_CATCH");

        String[] expected = {
        };

        verify(checkConfig, getPath("InputChildBlockLengthCheckCheckNPE.java"), expected);
    }

    @Test
    public void testNestedClass() throws Exception
    {
        checkConfig.addAttribute("maxChildBlockPercentage", "19");
		checkConfig.addAttribute("blockTypes", "LITERAL_IF");
        checkConfig.addAttribute("ignoreBlockLinesCount", "0");

        String[] expected = {
        		"9:25: " + getCheckMessage(MSG_KEY, 15, 3),
        };

        verify(checkConfig, getPath("InputChildBlockLengthCheckNestedClass.java"), expected);
    }
}
