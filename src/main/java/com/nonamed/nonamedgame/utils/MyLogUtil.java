package com.nonamed.nonamedgame.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class MyLogUtil {

    private MyLogUtil() {
    }

    public static void logDebug(String serviceName, String message, Object... values) {
        log.debug("[" + serviceName + "] debug: " + message, values);
    }

    public static void logDebug(Class serviceClassType, String message, Object... values) {
        logDebug(serviceClassType.getSimpleName(), message, values);
    }

    public static void logDebug(Object serviceInstance, String message, Object... values) {
        logDebug(serviceInstance.getClass(), message, values);
    }

    public static void logWarn(String serviceName, String message, Object... values) {
        log.warn("[" + serviceName + "] warn: " + message, values);
    }

    public static void logWarn(Class serviceClassType, String message, Object... values) {
        logWarn(serviceClassType.getSimpleName(), message, values);
    }

    public static void logWarn(Object serviceInstance, String message, Object... values) {
        logWarn(serviceInstance.getClass(), message, values);
    }

    public static void logErr(String serviceName, Exception exception) {
        log.error("[" + serviceName + "] error: " + exception.getMessage(), exception);
    }

    public static void logErr(Object serviceInstance, Exception exception) {
        logErr(serviceInstance.getClass().getSimpleName(), exception);
    }

    public static void logErr(String serviceName, String message, Object... values) {
        log.error("[" + serviceName + "] error: " + message, values);
    }

    public static void logErr(Class serviceClassType, String message, Object... values) {
        logErr(serviceClassType.getSimpleName(), message, values);
    }

    public static void logErr(Object serviceInstance, String message, Object... values) {
        logErr(serviceInstance.getClass(), message, values);
    }

    public static void logInfo(String serviceName, String message, Object... values) {
        log.info("[" + serviceName + "] info: " + message, values);
    }

    public static void logInfo(Class serviceClassType, String message, Object... values) {
        logInfo(serviceClassType.getSimpleName(), message, values);
    }

    public static void logInfo(Object serviceInstance, String message, Object... values) {
        logInfo(serviceInstance.getClass(), message, values);
    }

    public static void logTrace(String serviceName, String message, Object... values) {
        log.trace("[" + serviceName + "] trace: " + message, values);
    }

    public static void logTrace(Class serviceClassType, String message, Object... values) {
        logTrace(serviceClassType.getSimpleName(), message, values);
    }

    public static void logTrace(Object serviceInstance, String message, Object... values) {
        logTrace(serviceInstance.getClass(), message, values);
    }

}