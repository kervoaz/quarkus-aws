package com.zou.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HO.CKERVOAZOU
 */
@ApplicationScoped
public class DocumentSyncService extends AbstractService {

    @Inject
    DynamoDbClient dynamoDB;

    public List<Document> findAll() {
        return dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Document::from)
                .collect(Collectors.toList());
    }

    public List<Document> add(Document document) {
        dynamoDB.putItem(putRequest(document));
        return findAll();
    }

    public Document get(String id) {
        //TODO filter by revision
        return Document.from(dynamoDB.getItem(getRequest(id)).item());
    }
}