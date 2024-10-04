package com.fixedasset.dto;

import com.fixedasset.entity.InvRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
public class FindInvRecordDto extends InvRecord {
    private OffsetDateTime createdTo;
    private OffsetDateTime createdFrom;

    public OffsetDateTime getCreatedFrom() {
        return createdFrom;
    }

    public OffsetDateTime getCreatedTo() {
        return createdTo;
    }
}
