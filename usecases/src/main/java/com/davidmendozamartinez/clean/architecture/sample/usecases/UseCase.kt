package com.davidmendozamartinez.clean.architecture.sample.usecases

abstract class UseCase<out Type> where Type : Any {

    abstract operator fun invoke(): Type
}