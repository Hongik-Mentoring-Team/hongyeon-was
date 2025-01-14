package com.hongik.mentor.hongik_mentor.domain.tier;

public class TierAssigner {

    public Tier evaluate(Long rank) {   //현재 rank에 상응하는 Tier반환
        Tier tier = null;
        for (Tier t : Tier.values())
            if (t.getMin() >= rank) {
                tier=t;
            }

        return tier;
    }
}
