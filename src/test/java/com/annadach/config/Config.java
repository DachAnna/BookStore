package com.annadach.config;

@Config.Sources({"classpath:config/properties"})
public interface Config extends org.aeonbits.owner.Config {

    String userName();
    String password();
}
