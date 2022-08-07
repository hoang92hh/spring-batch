package com.example.spring_batch.config;



import com.example.spring_batch.model.UserBatch;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {




//    @Bean
//    protected Step step2(Tasklet tasklet, StepBuilderFactory stepBuilders) {
//        return stepBuilders.get("step2")
//                .tasklet(tasklet)
//                .build();
//    }


    @Bean
    public Job job(JobBuilderFactory jobBuilders,
                   StepBuilderFactory stepBuilders,
                   ItemReader<UserBatch> itemReader,
                   ItemProcessor<UserBatch, UserBatch> itemProcessor,
                   ItemWriter<UserBatch> itemWriter
    ) {

        Step step = stepBuilders.get("ETL-file-load")
                .<UserBatch, UserBatch>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilders.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
//                .next(step2);
                .build();
    }

    @Bean
    public FlatFileItemReader<UserBatch> itemReader() {

        FlatFileItemReader<UserBatch> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/users.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<UserBatch> lineMapper() {

        DefaultLineMapper<UserBatch> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "name", "dept", "salary");

        BeanWrapperFieldSetMapper<UserBatch> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(UserBatch.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}