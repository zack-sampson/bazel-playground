# Kotlin Bazel Rule Failure Cases

These are some examples of failing cases for the kt_jvm_library rule. They're about as simple as I can make them (afaik).

## JDK 12 Issue

The first case is a simple compilation of a Java + Kotlin file with JDK 12. It fails with no explanation.

```
cd jdk-12-issue
bazel build ...
```

## Annotation Processor Issue

The second case shows an issue I found involving Java annotation processors and `javac` target versions. I've got a simple Java interface that uses the Immutables annotation processor; this interface also has a private method, which is a feature that is supported in Java version 9 and above.

Working correctly, we should see the class compile. In practice, we see the following error:

```
 $ bazel build --sandbox_debug --verbose_failures -s //...                                                                                   121ms 
INFO: Analyzed 5 targets (0 packages loaded, 0 targets configured).
INFO: Found 5 targets...
SUBCOMMAND: # //:example [action 'Compiling Java headers libexample-hjar.jar (1 source file) and running annotation processors (Processor)', configuration: a91c7808393666533450fc367ef01237e386105113de88495783064fa74bdd82, execution platform: @local_config_platform//:host]
(cd /private/var/tmp/_bazel_zls/722fe3762f202221aadf1ff5afe5f483/execroot/__main__ && \
  exec env - \
    LC_CTYPE=en_US.UTF-8 \
    PATH=/Users/zls/opt/google-cloud-sdk/bin:/Users/zls/bin:/Applications/Postgres.app/Contents/Versions/latest/bin:/Users/zls/opt/google-cloud-sdk/bin:/Users/zls/bin:/Users/zls/bin:/usr/local/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Applications/Postgres.app/Contents/Versions/latest/bin:/opt/ts/bin:/usr/local/opt/fzf/bin \
  external/remotejdk11_macos/bin/java -Xverify:none '--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED' '--add-opens=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED' '--patch-module=java.compiler=external/remote_java_tools_darwin/java_tools/java_compiler.jar' '--patch-module=jdk.compiler=external/remote_java_tools_darwin/java_tools/jdk_compiler.jar' '--add-opens=java.base/java.nio=ALL-UNNAMED' '--add-opens=java.base/java.lang=ALL-UNNAMED' -jar external/remote_java_tools_darwin/java_tools/turbine_deploy.jar @bazel-out/darwin-fastbuild/bin/libexample-hjar.jar-0.params @bazel-out/darwin-fastbuild/bin/libexample-hjar.jar-1.params)
ERROR: /Users/zls/contexts/bazel-kotlin-issue-examples/annotation-processor-wrong-javac-source-version/BUILD:12:13: Compiling Java headers libexample-hjar.jar (1 source file) and running annotation processors (Processor) failed (Exit 1): sandbox-exec failed: error executing command 
  (cd /private/var/tmp/_bazel_zls/722fe3762f202221aadf1ff5afe5f483/sandbox/darwin-sandbox/11/execroot/__main__ && \
  exec env - \
    LC_CTYPE=en_US.UTF-8 \
    PATH=/Users/zls/opt/google-cloud-sdk/bin:/Users/zls/bin:/Applications/Postgres.app/Contents/Versions/latest/bin:/Users/zls/opt/google-cloud-sdk/bin:/Users/zls/bin:/Users/zls/bin:/usr/local/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Applications/Postgres.app/Contents/Versions/latest/bin:/opt/ts/bin:/usr/local/opt/fzf/bin \
    TMPDIR=/var/folders/_7/v5fqx8w93f36r1f9j8kxpxsh0000gn/T/ \
  /usr/bin/sandbox-exec -f /private/var/tmp/_bazel_zls/722fe3762f202221aadf1ff5afe5f483/sandbox/darwin-sandbox/11/sandbox.sb /var/tmp/_bazel_zls/install/3f6b57c2e011992922afe70680fb9546/process-wrapper '--timeout=0' '--kill_delay=15' --wait_fix external/remotejdk11_macos/bin/java -Xverify:none '--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED' '--add-opens=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED' '--patch-module=java.compiler=external/remote_java_tools_darwin/java_tools/java_compiler.jar' '--patch-module=jdk.compiler=external/remote_java_tools_darwin/java_tools/jdk_compiler.jar' '--add-opens=java.base/java.nio=ALL-UNNAMED' '--add-opens=java.base/java.lang=ALL-UNNAMED' -jar external/remote_java_tools_darwin/java_tools/turbine_deploy.jar @bazel-out/darwin-fastbuild/bin/libexample-hjar.jar-0.params @bazel-out/darwin-fastbuild/bin/libexample-hjar.jar-1.params) sandbox-exec failed: error executing command 
  (cd /private/var/tmp/_bazel_zls/722fe3762f202221aadf1ff5afe5f483/sandbox/darwin-sandbox/11/execroot/__main__ && \
  exec env - \
    LC_CTYPE=en_US.UTF-8 \
    PATH=/Users/zls/opt/google-cloud-sdk/bin:/Users/zls/bin:/Applications/Postgres.app/Contents/Versions/latest/bin:/Users/zls/opt/google-cloud-sdk/bin:/Users/zls/bin:/Users/zls/bin:/usr/local/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Applications/Postgres.app/Contents/Versions/latest/bin:/opt/ts/bin:/usr/local/opt/fzf/bin \
    TMPDIR=/var/folders/_7/v5fqx8w93f36r1f9j8kxpxsh0000gn/T/ \
  /usr/bin/sandbox-exec -f /private/var/tmp/_bazel_zls/722fe3762f202221aadf1ff5afe5f483/sandbox/darwin-sandbox/11/sandbox.sb /var/tmp/_bazel_zls/install/3f6b57c2e011992922afe70680fb9546/process-wrapper '--timeout=0' '--kill_delay=15' --wait_fix external/remotejdk11_macos/bin/java -Xverify:none '--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED' '--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED' '--add-opens=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED' '--patch-module=java.compiler=external/remote_java_tools_darwin/java_tools/java_compiler.jar' '--patch-module=jdk.compiler=external/remote_java_tools_darwin/java_tools/jdk_compiler.jar' '--add-opens=java.base/java.nio=ALL-UNNAMED' '--add-opens=java.base/java.lang=ALL-UNNAMED' -jar external/remote_java_tools_darwin/java_tools/turbine_deploy.jar @bazel-out/darwin-fastbuild/bin/libexample-hjar.jar-0.params @bazel-out/darwin-fastbuild/bin/libexample-hjar.jar-1.params)
ExampleInterface.java:5: error: private interface methods are not supported in -source 8
  (use -source 9 or higher to enable private interface methods)
INFO: Elapsed time: 0.694s, Critical Path: 0.58s
INFO: 0 processes.
FAILED: Build did NOT complete successfully
```

To repro:
```
cd annotation-processor-wrong-javac-source-version
bazel build ...
```
