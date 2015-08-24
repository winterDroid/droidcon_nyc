This is a template project which shows how to include custom Lint and Checkstyle checks into your Gradle based Android project.
You can execute your Lint checks with:

```
./gradlew lint
```

The Checkstyle checks can be executed with:
```
./gradlew checkstyle
```

Besides that it shows how to write tests for the custom checks.
You can execute our custom test with:

```
./gradlew :lintrules:cleanTest :lintrules:test --tests de.mprengemann.customlint.lintrules.issues.WrongTimberUsageTest
```

You can find the information about the talk here:
http://droidcon.nyc/2015/dcnyc/25/
