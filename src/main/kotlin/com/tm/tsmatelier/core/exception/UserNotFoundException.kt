package com.tm.tsmatelier.core.exception

import com.tm.tsmatelier.common.ExceptionMessage.USER_NOT_FOUND

class UserNotFoundException(param: String) : RuntimeException(USER_NOT_FOUND.format(param))
