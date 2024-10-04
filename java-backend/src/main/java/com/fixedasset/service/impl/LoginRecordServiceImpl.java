package com.fixedasset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fixedasset.entity.LoginRecord;
import com.fixedasset.mapper.LoginRecordMapper;
import com.fixedasset.service.LoginRecordService;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import javax.annotation.Resource;

@Service
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordMapper, LoginRecord> implements LoginRecordService {

    @Resource private LoginRecordMapper loginRecordMapper;

    @Override
    public void saveData(LoginRecord loginRecord) {
        loginRecord.setLoginTime(OffsetDateTime.now());
        loginRecordMapper.insert(loginRecord);
    }

}
