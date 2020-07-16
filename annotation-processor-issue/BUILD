load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "define_kt_toolchain")

define_kt_toolchain(
    name = "kotlin_toolchain",
    api_version = "1.3",  # "1.1", "1.2", or "1.3"
    jvm_target = "1.8", # "1.6", "1.8", "9", "10", "11", or "12",
    language_version = "1.3",  # "1.1", "1.2", or "1.3"
)

java_plugin(
    name = "immutables-plugin",
    generates_api = 1,
    processor_class = "org.immutables.value.processor.Processor",
    visibility = ["//visibility:public"],
    deps = [
        "@maven//:org_immutables_value",
        "@maven//:org_immutables_value_processor",
        "@maven//:org_immutables_generator",
    ],
)

java_library(
    name = "immutables_processor",
    exported_plugins = [":immutables-plugin"],
    exports = [
        "@maven//:org_immutables_value",
        "@maven//:org_immutables_value_processor",
        "@maven//:org_immutables_generator",
    ],
    visibility = ["//visibility:public"],
)
