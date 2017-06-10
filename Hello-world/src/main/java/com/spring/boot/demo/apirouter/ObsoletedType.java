package com.spring.boot.demo.apirouter;

/**
 * 服务方法是否已经过期，过期的服务方法不能再访问
 * @author wdhcxx
 *
 */
public enum ObsoletedType {
	YES, NO, DEFAULT;

    public static boolean isObsoleted(ObsoletedType type) {
        if (YES == type ) {
            return true;
        } else {
            return false;
        }
    }
}
