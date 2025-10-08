package io.bitbot.bemusedbaboon.domain.usecase

import io.bitbot.bemusedbaboon.data.entity.index.Index

class GetPokemonIndex(/* Add services, repositories etc */) :
    UseCase<List<Index>>() {
    suspend operator fun invoke() {}
}