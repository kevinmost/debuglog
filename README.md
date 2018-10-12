DebugLog: An exploratory Compiler Plugin for Kotlin
---

This repository contains a Kotlin Compiler Plugin that automagically adds
method tracing (to stdout) to any function annotated with a user-specified
"DebugLog" annotation.

Currently, there are _many_ bugs in this code, as it was a toy project made
specifically for a talk I gave on Kotlin Compiler plugins at KotlinConf
([video here](https://youtu.be/w-GMlaziIyo),
[slides here](https://speakerdeck.com/kevinmost/writing-your-first-kotlin-compiler-plugin),
[talk abstract here](https://kotlinconf.com/schedule/#date=4-october&session=41262)).

Usage
---

Build the artifacts for both the `:gradle-plugin` and `:kotlin-plugin`
modules, and publish them to a Maven repo.

Add a dependency on the `:gradle-plugin` artifact to your root `build.gradle`:

```groovy
buildscript {
  dependencies {
    classpath "debuglog:gradle-plugin:0.0.1"
  }
}
```

and then in your module-specific `build.gradle`, apply/configure the plugin:

```groovy
apply plugin: "debuglog.plugin"

debugLog {
  enabled = true
  annotations = [ "com.sample.myapp.annotations.DebugLog" ]
}
```

Bugs
---

You tell me!
