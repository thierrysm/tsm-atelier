package com.tm.tsmatelier.core.exception

import com.tm.tsmatelier.common.ExceptionMessage.PRODUCT_NOT_FOUND_EXCEPTION

class ProductNotFoundException(
    param: String,
) : RuntimeException(PRODUCT_NOT_FOUND_EXCEPTION.format(param))
