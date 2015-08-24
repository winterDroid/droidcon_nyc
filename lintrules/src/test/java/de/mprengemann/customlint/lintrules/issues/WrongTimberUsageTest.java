package de.mprengemann.customlint.lintrules.issues;


import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

public class WrongTimberUsageTest extends LintDetectorTest {

    @Override
    protected Detector getDetector() {
        return new WrongTimberUsageDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Arrays.asList(WrongTimberUsageDetector.ISSUE);
    }

    public void testLogTest() throws Exception {
        final String expected = "src/test/pkg/WrongTimberTest.java:16: Warning: Using 'Log' instead of 'Timber' [LogNotTimber]\n" +
                                "        Log.d(TAG, \"Test android logging\");\n" +
                                "        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                                "0 errors, 1 warnings\n";
        final TestFile testFile =
            java("src/test/pkg/WrongTimberTest.java",
                 "package test.pkg;\n" +
                 "\n" +
                 "import android.os.Bundle;\n" +
                 "import android.support.v4.app.FragmentActivity;\n" +
                 "import android.util.Log;\n" +
                 "import timber.log.Timber;\n" +
                 "\n" +
                 "public class WrongTimberTest extends FragmentActivity {\n" +
                 "\n" +
                 "    private static final String TAG = \"WrongTimberTest\";\n" +
                 "\n" +
                 "    @Override\n" +
                 "    protected void onCreate(Bundle savedInstanceState) {\n" +
                 "        super.onCreate(savedInstanceState);\n" +
                 "        setContentView(R.layout.main);\n" +
                 "        Log.d(TAG, \"Test android logging\");\n" +
                 "        Timber.d(\"Test timber logging\");\n" +
                 "    }\n" +
                 "}");
        assertEquals(expected, lintProject(testFile));
    }
}
