package io.sunland.chainpass.common

enum class ChainLinkStatus { ACTUAL, DRAFT, EDIT }

class ChainLink {
    class Name(value: String? = null) {
        var value = value ?: ""
            private set

        val isValid = value?.let { !(value.isEmpty() || value.length > 16) } ?: true
    }

    class Password(value: String? = null) {
        var value = value ?: ""
            private set

        val isValid = value?.let { !(value.isEmpty() || value.length > 32) } ?: true
    }

    var id = 0
    var name = Name()
    var password = Password()
    var status = ChainLinkStatus.DRAFT
}
