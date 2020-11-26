@file:Suppress("UNCHECKED_CAST")

package com.example.clean_architecture.core_unit_test

import com.example.clean_architecture.core_lib.extension.nextChar
import com.example.clean_architecture.core_lib.extension.nextString
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.typeOf

inline fun <reified T : Any> makeRandomInstance(): T {
    return makeRandomInstance(T::class) as T
}

@ExperimentalStdlibApi
inline fun <reified T : Any> makeRandomListInstance(minSize: Int = 0, maxSize: Int = 10): List<T> {
    return makeRandomListInstance(typeOf<T>(), minSize = minSize, maxSize = maxSize)
}

fun <T : Any> makeRandomListInstance(kType: KType, minSize: Int = 0, maxSize: Int = 10): List<T> {
    val listT = mutableListOf<T?>()
    val count = Random.nextInt(minSize, maxSize + 1)
    val classifier = kType.classifier as KClass<T>
    repeat(count) {
        if (classifier == List::class) {
            val classifierChildKType = kType.arguments[0].type
            val classifierChild = classifierChildKType?.classifier
            if (classifierChild != null) {
                val item = makeRandomListInstance<T>(kType = classifierChildKType) as T
                listT.add(item)
            }
        } else {
            val item = makeRandomInstance(classifier)
            listT.add(item)
        }
    }
    return listT.filterNotNull()
}


fun <T : Any> makeRandomInstance(clazz: KClass<T>): T? {
    val primitive = makePrimitiveOrNull(clazz)
    if (primitive != null) {
        return primitive as? T?
    }

    if (clazz.isSubclassOf(Enum::class)) {
        return clazz.java.enumConstants.random()
    }

    val constructors = clazz.constructors
        .sortedBy { it.parameters.size }

    for (constructor in constructors) {
        try {
            val arguments = constructor.parameters
                .map { it.type }
                .map {
                    val classOb = it.classifier as KClass<*>
                    return@map if (classOb == List::class) {
                        val kType = it.arguments[0].type
                        val classListArg = kType?.classifier as KClass<*>?
                        if (classListArg != null && kType != null) {
                            makeRandomListInstance<Any>(kType = kType)
                        } else {
                            null
                        }
                    } else {
                        makeRandomInstance(classOb)
                    }
                }
                .toTypedArray()
            return constructor.call(*arguments)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
    throw NoUsableConstructor()
}

private fun <T : Any> makePrimitiveOrNull(clazz: KClass<T>): Any? = when (clazz) {
    Boolean::class -> Random.nextBoolean()
    Int::class -> Random.nextInt()
    Long::class -> Random.nextLong()
    Float::class -> Random.nextFloat()
    Double::class -> Random.nextDouble()
    Char::class -> Random.nextChar()
    String::class -> Random.nextString()
    else -> null
}

class NoUsableConstructor : Error()