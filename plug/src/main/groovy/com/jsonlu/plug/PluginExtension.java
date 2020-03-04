package com.jsonlu.plug;

/**
 * Author:JsonLu
 * DateTime:2019/12/26 13:46
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class PluginExtension {

    private String _api_key;
    private String buildPassword;
    private String buildInstallType;

    public String get_api_key() {
        return _api_key;
    }

    public void set_api_key(String _api_key) {
        this._api_key = _api_key;
    }

    public String getBuildPassword() {
        return buildPassword;
    }

    public void setBuildPassword(String buildPassword) {
        this.buildPassword = buildPassword;
    }

    public String getBuildInstallType() {
        return buildInstallType;
    }

    public void setBuildInstallType(String buildInstallType) {
        this.buildInstallType = buildInstallType;
    }
}
