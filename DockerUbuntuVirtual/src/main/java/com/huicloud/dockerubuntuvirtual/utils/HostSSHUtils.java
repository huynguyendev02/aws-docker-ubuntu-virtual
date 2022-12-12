package com.huicloud.dockerubuntuvirtual.utils;

import com.huicloud.dockerubuntuvirtual.config.Config;
import com.huicloud.dockerubuntuvirtual.models.Server;
import com.huicloud.dockerubuntuvirtual.services.ServerServices;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HostSSHUtils {
    static java.util.Properties config = new java.util.Properties();
    static JSch jsch = new JSch();

    static Session session;

    public static String executeCommand(String command, int serverId){

        Server server =  ServerServices.findServerById(serverId);

        String outputConsole="";
        try{
            config.put("StrictHostKeyChecking", "no");
            jsch.addIdentity(Config.privatekeyPath);
            session = jsch.getSession("ubuntu", server.getIpServer(), 22);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
             outputConsole = new String(in.readAllBytes());

            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");

        }catch(Exception e){
            e.printStackTrace();
        }
        return outputConsole;
    }
}
