package com.zou.dynamodb;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.Objects;

/**
 * @author HO.CKERVOAZOU
 */
@RegisterForReflection
public class Document {

    private String id;
    private Integer revision;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public Document() {
    }

    public static Document from(Map<String, AttributeValue> item) {
        Document document = new Document();
        if (item != null && !item.isEmpty()) {
            document.setId(item.get(AbstractService.DOCUMENT_ID_COL).s());
            document.setRevision(Integer.parseInt(item.get(AbstractService.DOCUMENT_REVISION_COL).n()));
        }
        return document;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Document)) {
            return false;
        }

        Document other = (Document) obj;

        return Objects.equals(other.id, this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}