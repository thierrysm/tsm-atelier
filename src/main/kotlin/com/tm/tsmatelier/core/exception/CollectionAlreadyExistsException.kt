package com.tm.tsmatelier.core.exception

import com.tm.tsmatelier.common.ExceptionMessage.COLLECTION_ALREADY_EXISTS_EXCEPTION

class CollectionAlreadyExistsException(
    param: String,
) : RuntimeException(COLLECTION_ALREADY_EXISTS_EXCEPTION.format(param))
