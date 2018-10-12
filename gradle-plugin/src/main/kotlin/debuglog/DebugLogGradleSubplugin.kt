package debuglog

import com.google.auto.service.AutoService
import org.gradle.api.Project
import org.gradle.api.tasks.compile.AbstractCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinGradleSubplugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

@AutoService(KotlinGradleSubplugin::class) // don't forget!
class DebugLogGradleSubplugin : KotlinGradleSubplugin<AbstractCompile> {

  override fun isApplicable(project: Project, task: AbstractCompile) =
      project.plugins.hasPlugin(DebugLogGradlePlugin::class.java)

  override fun apply(
      project: Project,
      kotlinCompile: AbstractCompile,
      javaCompile: AbstractCompile?,
      variantData: Any?,
      androidProjectHandler: Any?,
      kotlinCompilation: KotlinCompilation?
  ): List<SubpluginOption> {
    val extension = project.extensions.findByType(DebugLogGradleExtension::class.java)
        ?: DebugLogGradleExtension()

    if (extension.enabled && extension.annotations.isEmpty()) {
      error("DebugLog is enabled, but no annotations were set")
    }

    val annotationOptions = extension.annotations.map { SubpluginOption(key = "debugLogAnnotation", value = it) }
    val enabledOption = SubpluginOption(key = "enabled", value = extension.enabled.toString())
    return annotationOptions + enabledOption
  }

  /**
   * Just needs to be consistent with the key for DebugLogCommandLineProcessor#pluginId
   */
  override fun getCompilerPluginId(): String = "debuglog"

  override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
      groupId = "debuglog",
      artifactId = "kotlin-plugin",
      version = "0.0.1" // remember to bump this version before any release!
  )
}
