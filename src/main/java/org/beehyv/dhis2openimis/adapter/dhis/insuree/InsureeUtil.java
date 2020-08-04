package org.beehyv.dhis2openimis.adapter.dhis.insuree;

import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramCache;
import org.beehyv.dhis2openimis.adapter.dhis.cache.program.ProgramStageCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsureeUtil {
	private static final Logger logger = LoggerFactory.getLogger(InsureeUtil.class);
	private ProgramCache programCache;
	private ProgramStageCache stageCache;
	
	@Autowired
	public InsureeUtil(ProgramCache programCache, ProgramStageCache stageCache) {
		this.programCache = programCache;
		this.stageCache = stageCache;
	}
	
	/**
	 * Returns program id for "Family/Insuree" Program
	 * @return can return null if not found.
	 */
	public String getProgramId() {
		return programCache.getIdByDisplayName("Family/Insuree");
	}
	
	
	/**
	 * Returns program stage id for "Policy details"
	 * @return can return null if not found.
	 */
	public String getProgramStageId() {
		return stageCache.getByDisplayName("Policy details");
	}
}
