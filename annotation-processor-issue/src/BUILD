load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

kt_jvm_library(
    name = "src",
    srcs = [
        "HelloWorldKotlin.kt",
        "User.java",
    ],
    deps = [
        "//:immutables_processor",
        "@maven//:org_immutables_value",
        "@maven//:org_immutables_value_processor",
        "@maven//:org_immutables_generator",
        "@maven//:org_immutables_dependency_utility",
    ],
)
