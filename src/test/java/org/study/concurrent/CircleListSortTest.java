package org.study.concurrent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;
import org.study.oop.clazz.LoginRequestMessage;
import org.study.oop.clazz.Message;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class CircleListSortTest {

    public static void main(String[] args) throws JsonProcessingException {
        String r_receipt = "{\"receipt\":{\"receipt_type\":\"ProductionSandbox\", \"adam_id\":0, \"app_item_id\":0, \"bundle_id\":\"com.crc-musiconline\", \"application_version\":\"1\", \"download_id\":0, \"version_external_identifier\":0, \"receipt_creation_date\":\"2022-06-23 08:09:16 Etc/GMT\", \"receipt_creation_date_ms\":\"1655971756000\", \"receipt_creation_date_pst\":\"2022-06-23 01:09:16 America/Los_Angeles\", \"request_date\":\"2022-06-23 08:10:47 Etc/GMT\", \"request_date_ms\":\"1655971847591\", \"request_date_pst\":\"2022-06-23 01:10:47 America/Los_Angeles\", \"original_purchase_date\":\"2013-08-01 07:00:00 Etc/GMT\", \"original_purchase_date_ms\":\"1375340400000\", \"original_purchase_date_pst\":\"2013-08-01 00:00:00 America/Los_Angeles\", \"original_application_version\":\"1.0\", \"in_app\":[{\"quantity\":\"1\", \"product_id\":\"0000000\", \"transaction_id\":\"2000000064241868\", \"original_transaction_id\":\"2000000064241868\", \"purchase_date\":\"2022-05-26 08:13:47 Etc/GMT\", \"purchase_date_ms\":\"1653552827000\", \"purchase_date_pst\":\"2022-05-26 01:13:47 America/Los_Angeles\", \"original_purchase_date\":\"2022-05-26 08:13:47 Etc/GMT\", \"original_purchase_date_ms\":\"1653552827000\", \"original_purchase_date_pst\":\"2022-05-26 01:13:47 America/Los_Angeles\", \"is_trial_period\":\"false\", \"in_app_ownership_type\":\"PURCHASED\"}, {\"quantity\":\"1\", \"product_id\":\"0000005\", \"transaction_id\":\"2000000087534707\", \"original_transaction_id\":\"2000000087534707\", \"purchase_date\":\"2022-06-23 08:09:16 Etc/GMT\", \"purchase_date_ms\":\"1655971756000\", \"purchase_date_pst\":\"2022-06-23 01:09:16 America/Los_Angeles\", \"original_purchase_date\":\"2022-06-23 08:09:16 Etc/GMT\", \"original_purchase_date_ms\":\"1655971756000\", \"original_purchase_date_pst\":\"2022-06-23 01:09:16 America/Los_Angeles\", \"is_trial_period\":\"false\", \"in_app_ownership_type\":\"PURCHASED\"}]}, \"environment\":\"Sandbox\", \"status\":0}\\";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode receiptNode = objectMapper.readTree(r_receipt);
        final JsonNode inAppList = receiptNode.findValue("in_app");



        if(inAppList!=null && inAppList.size()>0){

            final long original_purchase_date_ms = inAppList.get(0).get("original_purchase_date_ms").asLong();
            log.info("支付时间:{}",original_purchase_date_ms);
        }

        String replaceUrl = "/data/cloud/audio/CA011778/CA011778A-01.flac";
        String musicName = replaceUrl.substring(replaceUrl.lastIndexOf(File.separatorChar)+1,replaceUrl.length());

        String nMusicName = musicName.split("\\.")[0];
        String nFullMusicName = musicName.split("\\.")[0]+"-m-";
        String nFullMusicNameWithPath = replaceUrl.replace(nMusicName,nFullMusicName);
        log.info("封装后的歌曲播放地址:{}"+nFullMusicNameWithPath);

        LoginRequestMessage loginRequestMessage = new LoginRequestMessage("aaa");
        Class<Message> messageClass = Message.class;
        log.info("是同一类吗:"+messageClass.isInstance(loginRequestMessage));

        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            System.out.println(address.getHostName());//主机名
            System.out.println(address.getCanonicalHostName());//主机别名
            System.out.println(address.getHostAddress());//获取IP地址
            System.out.println("===============");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }

}
