package com.tm.tsmatelier.core.exception

import com.tm.tsmatelier.common.ExceptionMessage.PRODUCT_ALREADY_EXISTS_EXCEPTION

class ProductAlreadyExistsException(
    param: String,
) : RuntimeException(PRODUCT_ALREADY_EXISTS_EXCEPTION.format(param))
