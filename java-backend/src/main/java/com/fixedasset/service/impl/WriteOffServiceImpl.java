package com.fixedasset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.entity.WriteOff;
import com.fixedasset.mapper.WriteOffMapper;
import com.fixedasset.service.WriteOffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.OffsetDateTime;

@Service
public class WriteOffServiceImpl extends ServiceImpl<WriteOffMapper, WriteOff> implements WriteOffService {

    @Resource WriteOffMapper writeOffMapper;

    public void saveWriteOff(WriteOff writeOff) {
        writeOff.setLastDay(OffsetDateTime.now());
        writeOffMapper.insert(writeOff);
    }

}
