package com.fixedasset.dto;

import com.fixedasset.entity.InvRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
public class FindInvRecordDto extends InvRecord {
    private LocalDate createdTo;
    private LocalDate createdFrom;

    public LocalDate getCreatedFrom() {
        return createdFrom;
    }

    public LocalDate getCreatedTo() {
        return createdTo;
    }
}
