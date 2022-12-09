package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.models.Snapshot;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import org.sql2o.Connection;

import java.util.List;

public class SnapshotService {
    public static List<Snapshot> findAll(){
        String query = "select * from snapshot";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query).executeAndFetch(Snapshot.class);
        }
    }
}
