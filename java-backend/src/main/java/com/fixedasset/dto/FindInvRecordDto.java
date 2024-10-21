package com.fixedasset.dto;

import com.fixedasset.entity.InvRecord;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
public class FindInvRecordDto extends InvRecord {
    @Schema(description = "Create date time form")
    private OffsetDateTime createdTo;

    @Schema(description = "Create date time to")
    private OffsetDateTime createdFrom;

    public OffsetDateTime getCreatedFrom() {
        return createdFrom.plusHours(8);
    }

    public OffsetDateTime getCreatedTo() {
        return createdTo.plusHours(8);
    }
}
