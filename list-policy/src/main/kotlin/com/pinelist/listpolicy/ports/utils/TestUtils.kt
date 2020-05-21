package com.pinelist.listpolicy.ports.utils

import com.pinelist.listpolicy.models.ErrorMessage
import com.pinelist.listpolicy.railway.Failure
import com.pinelist.listpolicy.railway.Success
import com.pinelist.listpolicy.railway.TwoTrack
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat

fun assertFailure(result: TwoTrack<*>, expected: ErrorMessage) {
    when (result) {
        is Failure -> assertThat(result.a).isEqualTo(expected)
        is Success<*> -> Assertions.fail("Expected Failure track, got Success track with value ${result.b}")
    }
}

fun <T> assertSuccess(result: TwoTrack<T>, expected: T) {
    when (result) {
        is Success<T> -> assertThat(result.b).isEqualTo(expected)
        is Failure -> Assertions.fail("Expected Success track, got Failure track with value ${result.a}")
    }
}

fun <T> getSuccess(result: TwoTrack<T>): T {
    return when (result) {
        is Success<T> -> result.b
        is Failure -> Assertions.fail("Expected Success track, got Failure track with value ${result.a}")
    }
}