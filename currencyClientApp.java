[ERROR] Errors: 
[ERROR]   CurrencyWebControllerTest.testControllerHandlesInvalidConversion » Mockito 
Mockito cannot mock this class: class currencyClientApp.CurrencyService.

If you're not sure why you're getting this error, please open an issue on GitHub.


Java               : 24
JVM vendor name    : Homebrew
JVM vendor version : 24.0.1
JVM name           : OpenJDK 64-Bit Server VM
JVM version        : 24.0.1
JVM info           : mixed mode, sharing
OS name            : Mac OS X
OS version         : 15.5


You are seeing this disclaimer because Mockito is configured to create inlined mocks.
You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.

Underlying exception : org.mockito.exceptions.base.MockitoException: Could not modify all classes [class currencyClientApp.CurrencyService, class java.lang.Object]
[ERROR]   CurrencyWebControllerTest.testControllerHandlesValidConversion » Mockito 
Mockito cannot mock this class: class currencyClientApp.CurrencyService.

If you're not sure why you're getting this error, please open an issue on GitHub.


Java               : 24
JVM vendor name    : Homebrew
JVM vendor version : 24.0.1
JVM name           : OpenJDK 64-Bit Server VM
JVM version        : 24.0.1
JVM info           : mixed mode, sharing
OS name            : Mac OS X
OS version         : 15.5


You are seeing this disclaimer because Mockito is configured to create inlined mocks.
You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.

Underlying exception : org.mockito.exceptions.base.MockitoException: Could not modify all classes [class currencyClientApp.CurrencyService, class java.lang.Object]
[INFO] 
[ERROR] Tests run: 8, Failures: 0, Errors: 2, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.832 s
[INFO] Finished at: 2025-06-24T17:11:41-06:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.1.2:test (default-test) on project Projects: 
[ERROR] 
[ERROR] Please refer to /Users/kateryna/Documents/CodeFish project/Projects/target/surefire-reports for the individual test results.
[ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
Error: Unable to access jarfile target/Projects-1.0-SNAPSHOT.jar
kateryna@Mac Projects % 
