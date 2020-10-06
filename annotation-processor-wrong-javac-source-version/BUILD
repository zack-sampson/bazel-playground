java_library(
    name = "example",
    srcs = ["ExampleInterface.java"],
    deps = [":immutables_processor"],
)

java_library(
    name = "helloworld",
    srcs = ["HelloWorld.java"],
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
