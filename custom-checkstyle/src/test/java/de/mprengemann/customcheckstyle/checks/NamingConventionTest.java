package de.mprengemann.customcheckstyle.checks;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import de.mprengemann.customcheckstyle.BaseCheckTestSupport;
import de.mprengemann.customcheckstyle.NamingConventionCheck;
import org.junit.Test;

public class NamingConventionTest extends BaseCheckTestSupport {

    @Test
    public void testConstantNaming() throws Exception {
        final DefaultConfiguration checkConfig =
            createCheckConfig(NamingConventionCheck.class);
        final String[] expected = {
            "7:5: Constant test4 should be " +
            "named in all uppercase with underscores."
        };
        verify(checkConfig, getPath("ConstantName.java"), expected);
    }
}
