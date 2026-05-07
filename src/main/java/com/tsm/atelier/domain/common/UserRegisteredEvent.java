package com.tsm.atelier.domain.common;

public record UserRegisteredEvent(String email, String token) {}
