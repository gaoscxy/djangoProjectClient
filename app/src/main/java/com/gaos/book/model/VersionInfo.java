package com.gaos.book.model;

public class VersionInfo {
    private int version_id;
    private String version_code;
    private String updatemsg;

    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getUpdatemsg() {
        return updatemsg;
    }

    public void setUpdatemsg(String updatemsg) {
        this.updatemsg = updatemsg;
    }
}
