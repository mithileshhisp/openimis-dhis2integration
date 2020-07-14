package org.hispindia.dhis2openimis.adapter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hispindia.dhis2openimis.adapter.util.ParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Scheduler {
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
	//@Autowired private Tester adapterCycle;
        @Autowired private OrganisationUnitSync orgUnitSync;
	
       @Scheduled(cron = "${app.schedule.orgSyncCronExpression}")
	public void runOrgUnitSync() {
            logger.info("orgUnit sync started at: " + LocalDateTime.now().toString());
            orgUnitSync.run();
            logger.info("orgUnit sync end at: " + LocalDateTime.now().toString());
		
	}
        
	@Scheduled(cron = "${app.schedule.cronExpression}")
	public void runAdapter() {
		ParamsUtil.REF_DATE_PARAM = "refDate=" + LocalDate.now().minusDays(1).toString();
		logger.info("Program cycle started at: " + LocalDateTime.now().toString());
		//adapterCycle.run();
		logger.info("Program cycle finished at: " + LocalDateTime.now().toString());
	}
}
