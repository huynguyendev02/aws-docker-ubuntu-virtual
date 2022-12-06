package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.SSHKey;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import org.sql2o.Connection;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class SSHKeyService {
    public static String addKey(int userId, String keyName) throws NoSuchAlgorithmException, IOException {
        //generate RSA Key
        HostSSHUtils.executeCommand("mkdir /home/ubuntu/KEYSSH/" + UserService.getUsername(userId));
        HostSSHUtils.executeCommand("ssh-keygen -b 2048 -t rsa -m PEM -f  /home/ubuntu/KEYSSH/" + UserService.getUsername(userId) + "/" + keyName + " -q -N ''");
        //give PrivateKey to User
        String content = HostSSHUtils.executeCommand("cat /home/ubuntu/KEYSSH/" +UserService.getUsername(userId) +"/"+keyName);
        //To SQL
        String query = "insert into sshkey ( nameKey, userId ) values ( :nameKey, :userId )";
        try (Connection con = ConnectionUtils.openConnection()) {
            con.createQuery(query, true)
                    .addParameter("nameKey", keyName)
                    .addParameter("userId", userId)
                    .executeUpdate()
                    .getKey();
        }
        return content;
    }

    public static String getNameById(int keyId) {
        String query = "select * from sshkey where id= :keyId";
        try (Connection con = ConnectionUtils.openConnection()) {
            SSHKey sshKey = con.createQuery(query).addParameter("keyId", keyId).executeAndFetchFirst(SSHKey.class);
            return sshKey.getNameKey();
        }
    }


    //    ========================================
    public static List<SSHKey> findAllById(int userId) {
        String query = "select * from sshkey where userId= :userId";
        try (Connection con = ConnectionUtils.openConnection()) {
            return con.createQuery(query).addParameter("userId",userId).executeAndFetch(SSHKey.class);
        }
    }

  
}