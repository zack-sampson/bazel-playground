# Kotlin Bazel Rule Failure Cases

These are some examples of failing cases for the kt_jvm_library rule. They're about as simple as I can make them (afaik).

## JDK 12 Issue

The first case is a simple compilation of a Java + Kotlin file with JDK 12. It fails with no explanation.

```
cd jdk-12-issue
bazel build ...
```

## Annotation Processor Issue

The second case shows an issue I found trying to get annotation processors to work. In this example, I attempt to use the popular [Immutables library](https://immutables.github.io/).

Working correctly, we should see a few generated classes based on the annotations present in the Java file. In practice, we see a mysterious failure.

```
cd annotation-processor-issue
bazel build ...
```
