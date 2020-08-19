package com.chaos.gradle.plugin.jarjar.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class JarJarTask extends DefaultTask {

    @TaskAction
    def run() {
        new JarJarTaskHelper(project).startTasks()
    }

}
