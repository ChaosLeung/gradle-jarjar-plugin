package com.chaos.gradle.plugin.jarjar;

class JarJarExtension {

    /**
     * true - 代表编译期使用
     * false - 代表源码中使用（即源码中索引的是 jarjar 后的文件）
     */
    boolean useInCompileTime = true

    String outputDir

    List<String> rules = new ArrayList<>()
    List<String> keeps = new ArrayList<>()
    List<String> zaps = new ArrayList<>()

    void rule(String... rules) {
        if (rules != null) {
            this.rules.addAll(rules)
        }
    }

    void keep(String... keeps) {
        if (keeps != null) {
            this.keeps.addAll(keeps)
        }
    }

    void zap(String... zaps) {
        if (zaps != null) {
            this.zaps.addAll(zaps)
        }
    }
}