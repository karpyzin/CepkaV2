package ru.karpyzin.cepka.ext

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectWhenCreated(scope: LifecycleCoroutineScope, block: (T) -> Unit) {
    scope.launchWhenCreated { this@collectWhenCreated.collect { block.invoke(it) } }
}

fun <T> Flow<T>.collectWhenStarted(scope: LifecycleCoroutineScope, block: (T) -> Unit) {
    scope.launchWhenStarted { this@collectWhenStarted.collect { block.invoke(it) } }
}

fun <T> Flow<T>.collectWhenResumed(scope: LifecycleCoroutineScope, block: (T) -> Unit) {
    scope.launchWhenResumed { this@collectWhenResumed.collect { block.invoke(it) } }
}

fun <T> Flow<T>.collectAndRepeatWithViewLifecycle(
    viewLifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    block: (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            collect { block(it) }
        }
    }
}