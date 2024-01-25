package com.glodblock.github.ae2netanalyser.common.me.netdata;

public enum NodeFlag {

    NORMAL, DENSE, MISSING;

    public static NodeFlag byIndex(int index) {
        return NodeFlag.values()[index];
    }

}

