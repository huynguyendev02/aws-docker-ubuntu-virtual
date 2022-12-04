package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.SSHKey;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.models.User;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import com.huicloud.dockerubuntuvirtual.utils.ServerUtils;
import org.sql2o.Connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class SSHKeyService {
    public static String addKey(int userId, String keyName) throws NoSuchAlgorithmException, IOException {
        //generate RSA Key
        HostSSHUtils.executeCommand("ssh-keygen -b 2048 -t rsa -f  /home/ubuntu/KEYSSH/" + UserService.getUsername(userId)+"/"+ keyName +" -q -N \"\"");
        //give PrivateKey to User
        String content = new String(Files.readAllBytes(Paths.get("/home/ubuntu/KEYSSH/" + UserService.getUsername(userId)+"/"+ keyName)));
        //To SQL
        String query = "insert into sshkey ( nameKey, userId ) values ( :nameKey, :userId )";
        try (Connection con = ConnectionUtils.openConnection()){
            con.createQuery(query,true)
                    .addParameter("nameKey",keyName)
                    .addParameter("userId", userId)
                    .executeUpdate()
                    .getKey();
        }
        return content;
    }
    public static String getNameById(int keyId) {
        String query = "select * from sshkey where id= :keyId";
        try (Connection con = ConnectionUtils.openConnection()){
            SSHKey sshKey =  con.createQuery(query).addParameter("keyId",keyId).executeAndFetchFirst(SSHKey.class);
            return sshKey.getNameKey();
        }
    }
}
