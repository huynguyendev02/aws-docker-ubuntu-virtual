package com.huicloud.dockerubuntuvirtual.services;
import java.time.LocalDate;
import com.huicloud.dockerubuntuvirtual.models.Instance;
import com.huicloud.dockerubuntuvirtual.models.Network;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.models.Snapshot;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import org.sql2o.Connection;

import java.util.List;

public class SnapshotService {
    public static List<Snapshot> findAll(){
        String query = "select * from snapshot";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query).executeAndFetch(Snapshot.class);
        }
    }
    public static void createSnap(String nameSnap, int idInstance){

        Instance ins = InstanceService.findInsById(idInstance);
        int serverId = NetworkService.findNetworkById(ins.getNetworkId()).getServerId();

        HostSSHUtils.executeCommand("docker checkpoint create "+ins.getNameInstance()+" "+nameSnap);
        InstanceService.startIns(idInstance);

        String query = "insert into snapshot ( nameSnapshot, userId, instanceId, serverId, createdAt) " +
                "values ( :nameSnapshot, :userId, :instanceId, :serverId, :createdAt)";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query)
                    .addParameter("nameSnapshot",nameSnap)
                    .addParameter("userId",ins.getUserId())
                    .addParameter("instanceId",ins.getId())
                    .addParameter("serverId",serverId)
                    .addParameter("createdAt", LocalDate.now().toString())
                    .executeUpdate();
        }
    }
    public static Snapshot findSnapById(int snapId) {
        String query1 = "select * from snapshot where id=:snapId";
        try (Connection con = ConnectionUtils.openConnection()){
            return con.createQuery(query1)
                    .addParameter("snapId",snapId)
                    .executeAndFetchFirst(Snapshot.class);
        }
    }
    public static void removeSnap(int snapId){
        Snapshot snap = SnapshotService.findSnapById(snapId);
        Instance ins = InstanceService.findInsById(snap.getinstanceId());

        HostSSHUtils.executeCommand("docker checkpoint rm "+ins.getNameInstance()+" "+snap.getNameSnapshot());

        String query1 = "delete from snapshot where id= :snapId";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query1)
                    .addParameter("snapId",snapId)
                    .executeUpdate();
        }
    }
    public static void restoreSnap(int snapId) {
        Snapshot snap = SnapshotService.findSnapById(snapId);
        Instance ins = InstanceService.findInsById(snap.getinstanceId());

        HostSSHUtils.executeCommand("docker start --checkpoint "+snap.getNameSnapshot()+" "+ins.getNameInstance());
    }
}
