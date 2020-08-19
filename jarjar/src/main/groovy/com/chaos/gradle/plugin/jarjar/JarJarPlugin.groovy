package com.chaos.gradle.plugin.jarjar

import com.chaos.gradle.plugin.jarjar.tasks.JarJarTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class JarJarPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.configurations.create('jarjar')

        project.extensions.create('jarjar', JarJarExtension)
        project.jarjar.outputDir = project.file(project.buildDir.path + File.separator + 'jarjar-libs')

        project.afterEvaluate {
            JarJarExtension extension = project.jarjar

            def jarjar = project.task('jarjar', type: JarJarTask)
            if (extension.useInCompileTime) {
                project.tasks.compileJava.dependsOn jarjar
                project.configurations.implementation.dependencies.addAll(project.configurations.jarjar.dependencies)
            } else {
                jarjar.run()

                project.configurations.implementation.with {
                    project.dependencies.add(it.name, project.fileTree(dir: extension.outputDir, include: "*$Configs.JARJAR_SUFFIX"))
                }
            }
        }
    }
}
