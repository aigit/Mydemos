package org.study.concurrent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class CircleListSortTest {

    public static void main(String[] args) throws JsonProcessingException {
        String r_receipt = "{\"receipt\":{\"receipt_type\":\"ProductionSandbox\", \"adam_id\":0, \"app_item_id\":0,\n" +
                " \"bundle_id\":\"com.crc-musiconline\", \"application_version\":\"7\", \"download_id\":0, \n" +
                "\"version_external_identifier\":0, \"receipt_creation_date\":\"2022-05-30 07:17:52 Etc/GMT\", \n" +
                "\"receipt_creation_date_ms\":\"1653895072000\", \"receipt_creation_date_pst\":\"2022-05-30 00:17:52 America/Los_Angeles\", \n" +
                "\"request_date\":\"2022-05-30 07:17:56 Etc/GMT\", \"request_date_ms\":\"1653895076260\", \"request_date_pst\":\"2022-05-30 00:17:56 America/Los_Angeles\", \n" +
                "\"original_purchase_date\":\"2013-08-01 07:00:00 Etc/GMT\", \"original_purchase_date_ms\":\"1375340400000\", \n" +
                "\"original_purchase_date_pst\":\"2013-08-01 00:00:00 America/Los_Angeles\", \"original_application_version\":\"1.0\", \"in_app\":[{\"quantity\":\"1\", \"product_id\":\"1001\", \"transaction_id\":\"2000000066628417\", \"original_transaction_id\":\"2000000066628417\", \"purchase_date\":\"2022-05-30 07:17:52 Etc/GMT\", \"purchase_date_ms\":\"1653895072000\", \"purchase_date_pst\":\"2022-05-30 00:17:52 America/Los_Angeles\", \"original_purchase_date\":\"2022-05-30 07:17:52 Etc/GMT\", \"original_purchase_date_ms\":\"1653895072000\", \"original_purchase_date_pst\":\"2022-05-30 00:17:52 America/Los_Angeles\", \"is_trial_period\":\"false\", \"in_app_ownership_type\":\"PURCHASED\"}, {\"quantity\":\"1\", \"product_id\":\"0000000\", \"transaction_id\":\"2000000064241868\", \"original_transaction_id\":\"2000000064241868\", \"purchase_date\":\"2022-05-26 08:13:47 Etc/GMT\", \"purchase_date_ms\":\"1653552827000\", \"purchase_date_pst\":\"2022-05-26 01:13:47 America/Los_Angeles\", \"original_purchase_date\":\"2022-05-26 08:13:47 Etc/GMT\", \"original_purchase_date_ms\":\"1653552827000\", \"original_purchase_date_pst\":\"2022-05-26 01:13:47 America/Los_Angeles\", \"is_trial_period\":\"false\", \"in_app_ownership_type\":\"PURCHASED\"}]}, \n" +
                "\"environment\":\"Sandbox\", \"status\":0}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode receiptNode = objectMapper.readTree(r_receipt);
        final List<JsonNode> inAppList = receiptNode.findValues("in_app");

        String replaceUrl = "/data/cloud/audio/CA011778/CA011778A-01.flac";
        String musicName = replaceUrl.substring(replaceUrl.lastIndexOf(File.separatorChar)+1,replaceUrl.length());

        String nMusicName = musicName.split("\\.")[0];
        String nFullMusicName = musicName.split("\\.")[0]+"-m-";
        String nFullMusicNameWithPath = replaceUrl.replace(nMusicName,nFullMusicName);
        log.info("封装后的歌曲播放地址:{}"+nFullMusicNameWithPath);
    }

}
