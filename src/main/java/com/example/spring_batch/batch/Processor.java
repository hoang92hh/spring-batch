package com.example.spring_batch.batch;


import com.example.spring_batch.model.UserBatch;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<UserBatch, UserBatch> {

    private static final Map<String, String> DEPT_NAMES =
            new HashMap<>();

    public Processor() {
        DEPT_NAMES.put("001", "Technology");
        DEPT_NAMES.put("002", "Operations");
        DEPT_NAMES.put("003", "Accounts");
    }

    @Override
    public UserBatch process(UserBatch userBatch) throws Exception {
        String deptCode = userBatch.getDept();
        String dept = DEPT_NAMES.get(deptCode);
        userBatch.setDept(dept);
        userBatch.setTime(new Date());
        System.out.println(String.format("Converted from [%s] to [%s]", deptCode, dept));
        return userBatch;
    }
}