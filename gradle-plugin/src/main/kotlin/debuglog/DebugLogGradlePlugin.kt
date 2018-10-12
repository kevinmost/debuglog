package debuglog

import org.gradle.api.Plugin
import org.gradle.api.Project

class DebugLogGradlePlugin : Plugin<Project> {
  override fun apply(project: Project) {
    /*
     * Users can configure this extension in their build.gradle like this:
     * debugLog {
     *   enabled = false
     *   // ... set other members on the DebugLogGradleExtension class
     * }
     */
    project.extensions.create(
        "debugLog",
        DebugLogGradleExtension::class.java
    )
  }
}