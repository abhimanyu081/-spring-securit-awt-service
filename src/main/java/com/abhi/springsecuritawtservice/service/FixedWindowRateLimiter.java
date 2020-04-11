package com.abhi.springsecuritawtservice.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.springsecuritawtservice.dao.ApiAccessTimeRepo;
import com.abhi.springsecuritawtservice.domain.TableApiAccessTime;
import com.abhi.springsecuritawtservice.util.RateLimitUtil;

@Service
public class FixedWindowRateLimiter implements RateLimitingService {

	private static Logger LOGGER = LoggerFactory.getLogger(FixedWindowRateLimiter.class);

	@Autowired
	private ApiAccessTimeRepo apiAccessTimeRepo;

	@Override
	public boolean limit(String username, int apiAllowedCount) {

		// if user is new simply make and entry in table and set count to 1
		TableApiAccessTime apiAccessTime = apiAccessTimeRepo.findByUser(username);

		if (apiAccessTime == null) {
			LOGGER.info("New User {}. New Entry Made.", username);
			apiAccessTime = new TableApiAccessTime(username, apiAllowedCount,
					RateLimitUtil.getCurrentTimeNormalizedtoMinute());
			apiAccessTime = apiAccessTimeRepo.save(apiAccessTime);
		} else {

			// if not NULL
			// case 1 time window is ended
			if (windowExpired(apiAccessTime.getLastAccessTime(), LocalDateTime.now())) {
				// reset the counter and timestamp
				apiAccessTime.setLastAccessTime(RateLimitUtil.getCurrentTimeNormalizedtoMinute());
				apiAccessTime.setCount(1);
				apiAccessTimeRepo.save(apiAccessTime);
			} else {

				// if window not expired and limit is not exhausted
				if (apiAccessTime.getCount() < apiAllowedCount) {
					// update count
					apiAccessTime.setCount(apiAccessTime.getCount() + 1);
					apiAccessTimeRepo.save(apiAccessTime);
				} else {// if window is not expired but limit has ended
					return false;
					//throw new RuntimeException(
							//"user " + username + " has exhausetd all its requests for current window");
				}

			}

		}
		return true;

	}

	private boolean windowExpired(LocalDateTime from, LocalDateTime to) {
		return RateLimitUtil.diffInMinutes(from, to) > 1;
	}

}
