package com.jsonlu.plug

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

/**
 * Author:JsonLu
 * DateTime:2019/12/17 15:33
 * Email:jsonlu@qq.com
 * Desc:
 **/
class SensorsDataPlugin implements Plugin<Project> {

    private boolean hasAppPlugin
    private boolean hasLibPlugin

    @Override
    void apply(Project project) {

        final def log = project.logger

        project.dependencies {
            api 'org.aspectj:aspectjrt:1.9.0'
        }

        this.hasAppPlugin = project.plugins.hasPlugin(AppPlugin)
        this.hasLibPlugin = project.plugins.hasPlugin(LibraryPlugin)

        def Variants = this.hasAppPlugin ? project.android.applicationVariants : project.android.libraryVariants
        Variants.all { variant ->
            JavaCompile javaCompile = variant.javaCompile
            javaCompile.doLast {
                String[] args = ["-showWeaveInfo",
                                 "-1.9",
                                 "-inpath", javaCompile.destinationDir.toString(),
                                 "-aspectpath", javaCompile.classpath.asPath,
                                 "-d", javaCompile.destinationDir.toString(),
                                 "-classpath", javaCompile.classpath.asPath,
                                 "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
                log.debug "ajc args: " + Arrays.toString(args)
                MessageHandler handler = new MessageHandler(true)
                new Main().run(args, handler)

                println("#############################################")
                println("###############Welecom#######################")
                println()
                for (IMessage message : handler.getMessages(null, true)) {
                    switch (message.getKind()) {
                        case IMessage.ABORT:
                        case IMessage.ERROR:
                        case IMessage.FAIL:
                            log.error message.message, message.thrown
                            break;
                        case IMessage.INFO:
                            log.info message.message, message.thrown
                            break
                        case IMessage.DEBUG:
                            log.debug message.message, message.thrown
                            break
                    }
                }
            }
        }


    }
}
