package com.jsonlu.plug

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

/**
 * Author:JsonLu
 * DateTime:2019/12/24 14:16
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class UploadTask extends DefaultTask {


    @TaskAction
    void doAction() {
        if (check(project)) {
            project.android.applicationVariants.all {
                variant ->
                    variant.outputs.all {
                        if (buildType.name == 'debug') {
                            def extension = project.extensions.findByName('pgyer')
                            if (extension != null) {
                                UData uploadData = new UData();
                                String key = extension._api_key;
                                String buildInstallType = extension.buildInstallType;
                                String buildPassword = extension.buildPassword;
                                if (key == null || buildInstallType == null || buildPassword == null) {
                                    System.err.println('Please check pgyer{}')
                                } else {
                                    uploadData.post(outputFile, key, buildInstallType, buildPassword)
                                }
                            } else {
                                System.err.println('Please add pgyer{}')
                            }
                        }
                    }
            }
        } else {
            System.err.println('This Plugin only use for android application(not java)')
        }
    }
    /**
     * 检查配置是否正确
     */
    private boolean check(Project project) {
        /*检查是否正确的配置了*/
        /*检查是否是android应用*/
        try {
            def android = project.extensions['android']
        } catch (Exception e) {
            return false
        }

        return true;
    }
}
