package com.hongik.mentor.hongik_mentor.domain.tier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public enum Tier {
    BRONZE(0L,199L), SILVER(200L,299L), GOLD(300L,399L), PLATINUM(400L,499L), DIAMOND(500L,599L);
    private final Long min;
    private final Long max;
}
