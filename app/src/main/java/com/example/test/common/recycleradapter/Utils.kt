package com.example.test.common.recycleradapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

internal fun RecyclerItem.bind(binding: ViewDataBinding) {
    val isVariableFound = binding.setVariable(variableId, data)
    if (isVariableFound.not()) {
        throw IllegalStateException(
            buildErrorMessage(variableId, binding)
        )
    }
}

private fun buildErrorMessage(
    variableId: Int, binding: ViewDataBinding
): String {
    val variableName = DataBindingUtil.convertBrIdToString(variableId)
    val className = binding::class.simpleName
    return "Failed to find variable='$variableName' in the following databinding layout: $className"
}
