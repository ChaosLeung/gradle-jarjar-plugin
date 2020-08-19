package com.chaos.gradle.plugin.jarjar.tasks

import com.chaos.gradle.plugin.jarjar.Configs
import com.chaos.gradle.plugin.jarjar.JarJarExtension
import com.tonicsystems.jarjar.Main
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.file.CopySpec
import org.gradle.api.file.DeleteSpec

class JarJarTaskHelper {

    private Project project
    private JarJarExtension extension
    private File rulesFile

    JarJarTaskHelper(Project project) {
        this.project = project
        this.extension = project.jarjar
        this.rulesFile = project.file(extension.outputDir + File.separator + Configs.RULES_FILE)
    }

    final startTasks() {
        clean()
        copyJars()
        createRulesFile()
        jarjar()

        if (extension.useInCompileTime) {
        } else {
        }
    }

    private clean() {
        project.delete(new Action<DeleteSpec>() {
            @Override
            void execute(DeleteSpec deleteSpec) {
                deleteSpec.delete(project.jarjar.outputDir)
            }
        })
        project.file(extension.outputDir).mkdirs()
    }

    private copyJars() {
        project.copy(new Action<CopySpec>() {
            @Override
            void execute(CopySpec copySpec) {
                copySpec.from(project.configurations.jarjar)
                copySpec.into(project.jarjar.outputDir)
            }
        })
    }

    private createRulesFile() {
        if (rulesFile.exists()) {
            rulesFile.delete()
        }
        rulesFile.createNewFile()
        def writer = new FileWriter(rulesFile)
        extension.rules.each { rule ->
            writer.write("rule $rule\n")
        }
        extension.zaps.each { zap ->
            writer.write("zap $zap\n")
        }
        extension.keeps.each { keep ->
            writer.write("keep $keep\n")
        }
        writer.close()
    }

    private jarjar() {
        def outputDir = project.file(extension.outputDir)
        def jars = outputDir.list({ _, name ->
            name.endsWith(".jar")
        })

        Main jarjar = new Main()
        jars.each { inJarName ->
            def inJar = project.file(extension.outputDir + File.separator + inJarName)
            def outJar = project.file(extension.outputDir + File.separator + inJarName.replace(".jar", Configs.JARJAR_SUFFIX))
            jarjar.process(rulesFile, inJar, outJar)
            inJar.delete()
        }
    }
}
