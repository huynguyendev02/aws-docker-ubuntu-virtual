package com.huicloud.dockerubuntuvirtual.services;

import com.huicloud.dockerubuntuvirtual.models.SSHKey;
import com.huicloud.dockerubuntuvirtual.utils.ConnectionUtils;
import com.huicloud.dockerubuntuvirtual.utils.HostSSHUtils;
import org.bouncycastle.openssl.PEMWriter;
import org.bouncycastle.openssl.PKCS8Generator;
import org.bouncycastle.util.io.pem.PemObject;
import org.sql2o.Connection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.List;

public class SSHKeyService {
    public static String addKey(int userId, String keyName, String pathKey) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        //generate RSA Key
        HostSSHUtils.executeCommand("mkdir /home/ubuntu/KEYSSH/" + UserService.getUsername(userId));
        HostSSHUtils.executeCommand("sudo openssl genrsa -out /home/ubuntu/KEYSSH/"+ UserService.getUsername(userId)+"/"+keyName+".pem 4096");
        System.out.println(HostSSHUtils.executeCommand("sudo openssl rsa -pubout -in /home/ubuntu/KEYSSH/"+ UserService.getUsername(userId)+"/"+keyName+".pem -out /home/ubuntu/KEYSSH/"+ UserService.getUsername(userId)+"/"+keyName+"pub.pem"));
        System.out.println(HostSSHUtils.executeCommand("sudo openssl pkcs8 -topk8 -in /home/ubuntu/KEYSSH/"+ UserService.getUsername(userId)+"/"+keyName+".pem -inform pem -out /home/ubuntu/KEYSSH/"+UserService.getUsername(userId)+"/"+keyName+"java.pem -outform pem -nocrypt"));



        //give PrivateKey to User

        String privateKeyContent = HostSSHUtils.executeCommand("sudo cat /home/ubuntu/KEYSSH/" +UserService.getUsername(userId) +"/"+keyName+"java.pem");

        privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);
        PEMWriter writer = new PEMWriter(new FileWriter(new File(pathKey+"key.pem")));
        PKCS8Generator encryptorBuilder = new PKCS8Generator(privKey);
        PemObject obj = encryptorBuilder.generate();
        writer.writeObject(obj);
        writer.flush();
        writer.close();

        System.out.println(privKey);

        //To SQL

        String query = "insert into sshkey ( nameKey, userId ) values ( :nameKey, :userId )";
        try (Connection con = ConnectionUtils.openConnection()) {
            con.createQuery(query, true)
                    .addParameter("nameKey", keyName)
                    .addParameter("userId", userId)
                    .executeUpdate()
                    .getKey();
        }
        return privateKeyContent;
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

    public static SSHKey findSSHKeyById(int id) {
        String query = "select * from sshkey where id = :id";
        try (Connection con = ConnectionUtils.openConnection()) {
            return con.createQuery(query).addParameter("id",id).executeAndFetchFirst(SSHKey.class);
        }
    }
}