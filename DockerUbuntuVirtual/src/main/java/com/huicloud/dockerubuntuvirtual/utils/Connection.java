package com.huicloud.dockerubuntuvirtual.utils;

import org.sql2o.Sql2o;

public class Connection {
    static Sql2o sql2o = new Sql2o("jdbc:mysql://localhost:3306/docker-ubuntu-virtual", "root", "");
}
