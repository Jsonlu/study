package com.jsonlu.plug

import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Author:JsonLu
 * DateTime:2019/12/17 15:33
 * Email:jsonlu@qq.com
 * Desc:
 **/
class PgyerPlugin implements Plugin<Project> {

    private boolean hasAppPlugin

    @Override
    void apply(Project project) {
        this.hasAppPlugin = project.plugins.hasPlugin(AppPlugin)
        project.extensions.create('pgyer', PluginExtension)
        project.getTasks().create("upload", UploadTask.class)
        project.afterEvaluate {
            try {
                def debug = project.tasks.getByName("assembleDebug")
                project.upload.dependsOn debug
            } catch (Exception e) {
                println e.message
            }
        }
    }
}
