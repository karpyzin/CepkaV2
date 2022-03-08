package ru.karpyzin.domain.hint

import kotlinx.coroutines.flow.Flow
import ru.karpyzin.domain.hint.HintModel.Companion.TYPE_REMOTE_STORIES
import javax.inject.Inject

interface HintUseCase {
    val hints: Flow<List<HintModel>>
    suspend fun read(id: Int)
    suspend fun createBaseHints()
}

class HintUseCaseImpl @Inject constructor(private val repository: HintRepository) : HintUseCase {
    override val hints: Flow<List<HintModel>>
        get() = repository.getAll()

    override suspend fun read(id: Int) {
        repository.read(id)
    }

    override suspend fun createBaseHints() {
        val hints = mutableListOf<HintModel>()

        hints.add(HintModel(0, 0, "Hello!", "Let's try new functions 🎉", null, false))
        hints.add(HintModel(0, TYPE_REMOTE_STORIES, "What about sport?", "If you need some activities...", HintModel.HintContentModel(null, "https://www.visitmorocco.com/sites/default/files/thumbnails/image/maigrir-vite-sans-r%C3%A9gime-sans-sport-niaque-gain-vitesse-reussir-success.jpg", null, null), false))
        hints.add(HintModel(0, 0, "Finances", "Can you spend your money?", HintModel.HintContentModel(null, "https://miro.medium.com/max/1400/1*lJoevcmt5HHTWbaz0u-s6w.jpeg", null, null), false))
        hints.add(HintModel(0, 0, "Time...", "Time management life hacks!", HintModel.HintContentModel(null, "https://www.timedoctor.com/blog/images/2017/12/Preview-min-11-770x470.jpg", null, null), false))
        repository.addHints(hints)
    }
}