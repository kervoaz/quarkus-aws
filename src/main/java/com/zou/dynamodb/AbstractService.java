package com.zou.dynamodb;
import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
/**
 * @author HO.CKERVOAZOU
 */


public abstract class AbstractService {

    public final static String DOCUMENT_ID_COL = "id";
    public final static String DOCUMENT_REVISION_COL = "revision";

    public String getTableName() {
        return "documents-store-dev";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(DOCUMENT_ID_COL, DOCUMENT_REVISION_COL).build();
    }

    protected PutItemRequest putRequest(Document document) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(DOCUMENT_ID_COL, AttributeValue.builder().s(document.getId()).build());
        item.put(DOCUMENT_REVISION_COL, AttributeValue.builder().n(document.getRevision().toString()).build());

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected GetItemRequest getRequest(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(DOCUMENT_ID_COL, AttributeValue.builder().s(id).build());
        key.put(DOCUMENT_REVISION_COL, AttributeValue.builder().n("1").build());//TODO how rangekey null??
        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(DOCUMENT_ID_COL,DOCUMENT_REVISION_COL,"contentStorage")
                .build();
    }
}