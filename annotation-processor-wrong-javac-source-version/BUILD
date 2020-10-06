load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "define_kt_toolchain")

define_kt_toolchain(
    name = "kotlin_toolchain",
    api_version = "1.3",  # "1.1", "1.2", or "1.3"
    jvm_target = "11",  # "1.6", "1.8", "9", "10", "11", or "12",
    language_version = "1.3",  # "1.1", "1.2", or "1.3"
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

java_library(
    name = "example",
    srcs = ["ExampleInterface.java"],
    deps = ["immutables_processor"],
)

kt_jvm_library(
    name = "helloworld",
    srcs = ["HelloWorld.kt"],
    deps = [":example"],
)

java_plugin(
    name = "immutables-plugin",
    generates_api = 1,
    processor_class = "org.immutables.value.processor.Processor",
    deps = [
        "@maven//:org_immutables_generator",
        "@maven//:org_immutables_value",
        "@maven//:org_immutables_value_processor",
    ],
)

java_library(
    name = "immutables_processor",
    exported_plugins = [":immutables-plugin"],
    visibility = ["//visibility:public"],
    exports = [
        "@maven//:com_google_code_findbugs_jsr305",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:org_immutables_generator",
        "@maven//:org_immutables_value",
        "@maven//:org_immutables_value_processor",
    ],
)
