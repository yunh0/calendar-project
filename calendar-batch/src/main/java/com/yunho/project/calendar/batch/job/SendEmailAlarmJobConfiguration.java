package com.yunho.project.calendar.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SendEmailAlarmJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private static final int CHUNK_SIZE = 3;

    @Bean
    public Job sendEmailAlarmJob(
            Step sendEngagementAlarmStep,
            Step sendScheduleAlarmStep
    ) {
        return jobBuilderFactory.get("sendEmailAlarmJob")
                .start(sendEngagementAlarmStep)
                .next(sendScheduleAlarmStep)
                .build();
    }

    @Bean
    public Step sendEngagementAlarmStep(
            ItemReader<SendMailBatchReq> sendEngagementAlarmReader,
            ItemWriter<SendMailBatchReq> sendEmailAlarmWriter
    ) {
        return stepBuilderFactory.get("sendEngagementAlarmStep")
                .<SendMailBatchReq, SendMailBatchReq>chunk(CHUNK_SIZE)
                .reader(sendEngagementAlarmReader)
                .writer(sendEmailAlarmWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step sendScheduleAlarmStep(
            ItemReader<SendMailBatchReq> sendScheduleAlarmReader,
            ItemWriter<SendMailBatchReq> sendEmailAlarmWriter
    ) {
        return stepBuilderFactory.get("sendScheduleAlarmStep")
                .<SendMailBatchReq, SendMailBatchReq>chunk(CHUNK_SIZE)
                .reader(sendScheduleAlarmReader)
                .writer(sendEmailAlarmWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean sendEngagementAlarmQueryProvider() {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("select s.id, s.start_at, s.title, u.email as user_email");
        queryProvider.setFromClause("from schedules s left join engagements e on s.id = e.schedule_id left join users u on u.id = e.attendee_id");
        queryProvider.setWhereClause("where s.start_at >= now() + interval 10 minute and s.start_at < now() + interval 11 minute and e.status = 'ACCEPTED'");
        queryProvider.setSortKey("s.id");
        return queryProvider;
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean sendScheduleAlarmQueryProvider() {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("select s.id, s.start_at, s.title, u.email as user_email");
        queryProvider.setFromClause("from schedules s left join users u on u.id = s.writer_id");
        queryProvider.setWhereClause("where s.start_at >= now() + interval 10 minute and s.start_at < now() + interval 11 minute");
        queryProvider.setSortKey("s.id");
        return queryProvider;
    }

    @Bean
    public JdbcPagingItemReader<SendMailBatchReq> sendEngagementAlarmReader(
            PagingQueryProvider sendEngagementAlarmQueryProvider) {
        return new JdbcPagingItemReaderBuilder<SendMailBatchReq>()
                .name("sendEngagementAlarmReader")
                .dataSource(dataSource)
                .pageSize(CHUNK_SIZE)
                .rowMapper(new BeanPropertyRowMapper<>(SendMailBatchReq.class))
                .queryProvider(sendEngagementAlarmQueryProvider)
                .build();
    }

    @Bean
    public JdbcPagingItemReader<SendMailBatchReq> sendScheduleAlarmReader(
            PagingQueryProvider sendScheduleAlarmQueryProvider) {
        return new JdbcPagingItemReaderBuilder<SendMailBatchReq>()
                .name("sendScheduleAlarmReader")
                .dataSource(dataSource)
                .pageSize(CHUNK_SIZE)
                .rowMapper(new BeanPropertyRowMapper<>(SendMailBatchReq.class))
                .queryProvider(sendScheduleAlarmQueryProvider)
                .build();
    }

//    @Bean
//    public JdbcCursorItemReader<SendMailBatchReq> sendEngagementAlarmReader() {
//        return new JdbcCursorItemReaderBuilder<SendMailBatchReq>()
//                .fetchSize(CHUNK_SIZE)
//                .dataSource(dataSource)
//                .rowMapper(new BeanPropertyRowMapper<>(SendMailBatchReq.class))
//                .sql("select s.id, s.start_at, s.title, u.email as user_email\n" +
//                        "from schedules s\n" +
//                        "         left join engagements e on s.id = e.schedule_id\n" +
//                        "         left join users u on u.id = e.attendee_id\n" +
//                        "where s.start_at >= date_format(date_add(now(), interval 10 minute)" +
//                        ", '%Y-%m-%d %H:%i')\n" +
//                        "  and s.start_at < date_format(date_add(now(), interval 11 minute)," +
//                        " '%Y-%m-%d %H:%i')\n" +
//                        "  and e.status = 'ACCEPTED'")
//                .name("jdbcCursorItemReader")
//                .build();
//    }
//
//
//    @Bean
//    public JdbcCursorItemReader<SendMailBatchReq> sendScheduleAlarmReader() {
//        return new JdbcCursorItemReaderBuilder<SendMailBatchReq>()
//                .fetchSize(CHUNK_SIZE)
//                .dataSource(dataSource)
//                .rowMapper(new BeanPropertyRowMapper<>(SendMailBatchReq.class))
//                .sql("select s.id, s.start_at, s.title, u.email as user_email\n" +
//                        "from schedules s\n" +
//                        "    left join users u on u.id = s.writer_id\n" +
//                        "where s.start_at >= date_format(date_add(now(), interval 10 minute)" +
//                        ", '%Y-%m-%d %H:%i')\n" +
//                        "  and s.start_at < date_format(date_add(now(), interval 11 minute)," +
//                        " '%Y-%m-%d %H:%i')")
//                .name("jdbcCursorItemReader")
//                .build();
//    }

    @Bean
    public ItemWriter<SendMailBatchReq> sendEmailAlarmWriter() {
        return list -> new RestTemplate()
                .postForObject("http://localhost:8080/api/batch/send/mail", list, Object.class);
    }
}
