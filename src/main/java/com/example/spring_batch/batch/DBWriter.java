package com.example.spring_batch.batch;


import com.example.spring_batch.model.UserBatch;
import com.example.spring_batch.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class DBWriter implements ItemWriter<UserBatch> {

    private UserRepository userRepository;

    @Autowired
    public DBWriter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends UserBatch> users) throws Exception {
        System.out.println("Data Saved for Users: " + users);
        userRepository.saveAll(users);
    }
}