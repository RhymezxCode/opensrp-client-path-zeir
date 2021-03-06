package org.smartregister.pathzeir.reporting.monthly.domain

import org.codehaus.jackson.annotate.JsonProperty
import java.io.Serializable
import java.util.*

data class DailyTally(
        override val indicator: String,

        @JsonProperty
        var day : Date = Date(),

        @JsonProperty
        override var id: Long = 0,

        @JsonProperty
        override var value: String = "0",

        @JsonProperty
        override var providerId: String? = null,

        @JsonProperty
        override val grouping: String,

        @JsonProperty
        override var enteredManually: Boolean = false,

        @JsonProperty
        override var dependentCalculations: List<String> = emptyList()
) : Tally(), Serializable