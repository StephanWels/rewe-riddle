package com.ecomhack.riddle.sphere;

public class ChallengeNotFoundException extends RuntimeException {

    public ChallengeNotFoundException(String challengeId) {
        super(challengeId);
    }
}
