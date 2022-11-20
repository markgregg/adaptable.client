package org.adaptable.client.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus

class TextResponse(
    status: Int,
    private val body: String,
    headers: Map<String, String>
) : WebResponse(status, HashMap(headers)) {

    constructor(status: Int, body: String) : this(status, body, emptyMap() )

    constructor(status: Int, value: Any) : this(status, jacksonObjectMapper().writeValueAsString(value))

    constructor(body: String) : this(HttpStatus.OK.value(), body)

    constructor(value: Any) : this(HttpStatus.OK.value(), jacksonObjectMapper().writeValueAsString(value))

    override fun toDom(): org.adaptable.common.api.Response {

        return org.adaptable.common.web.TextResponse(status, body, headers, false)
    }
}