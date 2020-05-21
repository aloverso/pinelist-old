package com.pinelist.listpolicy.railway

import arrow.core.Either
import com.pinelist.listpolicy.models.ErrorMessage

typealias TwoTrack<T> = Either<ErrorMessage, T>
typealias Success<T> = Either.Right<T>
typealias Failure = Either.Left<ErrorMessage>

typealias input<T> = Success<T>

fun <T, V> TwoTrack<T>.pipe(
        other: (TwoTrack<T>) -> TwoTrack<V>
): TwoTrack<V> {
    return other(this)
}

// take a y-shaped switch function and turn it into a two-track in/out
fun <A,B> convertToTwoTrack(
        func: (A) -> TwoTrack<B>
): (TwoTrack<A>) -> TwoTrack<B> {
    return { input: TwoTrack<A> ->
        when(input) {
            is Success -> { func(input.b) }
            is Failure -> { input }
        }
    }
}