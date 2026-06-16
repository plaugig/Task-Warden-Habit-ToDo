package com.example.taskwardenhabittodo.domain.pet

import com.example.taskwardenhabittodo.domain.pet.enums.RobotContext
import com.example.taskwardenhabittodo.domain.pet.enums.RobotMood
import kotlin.random.Random

object RobotDialogue {

    fun line (
        mood: RobotMood,
        context: RobotContext,
        allowProfanity: Boolean = false,
        random: Random = Random.Default
    ): String {
        val bank = bankFor(mood, context,allowProfanity)
        return bank[random.nextInt(bank.size)]
    }

    fun bankFor(
        mood: RobotMood,
        context: RobotContext,
        profane: Boolean,
    ): List<String>{
        val clean = cleanBank(mood, context)
        if (!profane || !mood.isHostile) return  clean
        val spicy = spicyBank(mood, context)
        return if (spicy.isNotEmpty()) spicy else clean
    }

    private fun cleanBank(mood: RobotMood, context: RobotContext): List<String> = when (context) {

        RobotContext.GREETING -> when (mood) {
            RobotMood.RADIANT -> listOf(
                "О, это ты! Котик уже хвостом виляет.",
                "Лучшая часть моего дня — ты на пороге логова."
            )
            RobotMood.CHEERFUL -> listOf(
                "Привет. Хорошо, что заглянул — нас тут двое скучали.",
                "Котик мурлычет, я доволен. Что делаем?"
            )
            RobotMood.NEUTRAL -> listOf(
                "Пришёл. Котик в порядке. Список дел тоже на месте.",
                "Так, по делу: котик накормлен, дела ждут."
            )
            RobotMood.ANNOYED -> listOf(
                "А, вспомнил, что у тебя тут кот живёт.",
                "Долго же ты. Ладно, проходи."
            )
            RobotMood.SARCASTIC -> listOf(
                "Смотрите-ка, легенда вернулась. Аплодировать стоя?",
                "Я уж думал писать тебе на молоко."
            )
            RobotMood.MENACING -> listOf(
                "Ты опять пропал. Будешь так продолжать — сам знаешь, чем кончится. *смотрит немигающе*",
                "Я записал. Я всё записываю. Продолжай в том же духе — и я приду."
            )
        }

        RobotContext.TASK_DONE -> when (mood) {
            RobotMood.RADIANT -> listOf(
                "Вот это да! Минус задача, плюс уважение.",
                "Ты сегодня просто машина. Котик гордится."
            )
            RobotMood.CHEERFUL -> listOf(
                "Отлично, держим темп!",
                "Задача закрыта — поинты капнули."
            )
            RobotMood.NEUTRAL -> listOf(
                "Готово. Записал.",
                "Одной меньше. Дальше."
            )
            RobotMood.ANNOYED -> listOf(
                "Ну наконец-то хоть что-то.",
                "Одна задача. Не марафон, конечно, но ладно."
            )
            RobotMood.SARCASTIC -> listOf(
                "Ого, целая одна задача. Высекаем в граните?",
                "Невероятно. Природа отдыхает."
            )
            RobotMood.MENACING -> listOf(
                "Одна. Маловато, чтобы я передумал. Часики тикают.",
                "Засчитал. Но это тебя пока не спасает. *постукивает пальцем*"
            )
        }

        RobotContext.HABIT_DONE -> when (mood) {
            RobotMood.RADIANT -> listOf("Привычка отмечена — ты сегодня последователен, мне нравится!")
            RobotMood.CHEERFUL -> listOf("Привычка засчитана. Так и копится дисциплина.")
            RobotMood.NEUTRAL -> listOf("Привычка отмечена.")
            RobotMood.ANNOYED -> listOf("Отметил. Хоть привычки не забываешь.")
            RobotMood.SARCASTIC -> listOf("Привычка? У тебя? Запишу этот исторический момент.")
            RobotMood.MENACING -> listOf("Одна привычка. Капля. Мне нужно море. *прищуривается*")
        }

        RobotContext.STREAK_UP -> when (mood) {
            RobotMood.RADIANT -> listOf("Серия растёт! Ты сейчас неудержим.")
            RobotMood.CHEERFUL -> listOf("Серия идёт вверх — красиво.")
            RobotMood.NEUTRAL -> listOf("Серия продолжается.")
            RobotMood.ANNOYED -> listOf("Серия жива. Не растеряй, как обычно.")
            RobotMood.SARCASTIC -> listOf("Серия? Посмотрим, доживёт ли она до завтра.")
            RobotMood.MENACING -> listOf("Серия растёт. Оборвёшь — я это запомню.")
        }

        RobotContext.FAILURE -> when (mood) {
            RobotMood.RADIANT -> listOf(
                "Чуть просел — бывает. Соберись, я в тебя верю.",
                "Пропустил пару штук? Завтра наверстаем вместе."
            )
            RobotMood.CHEERFUL -> listOf(
                "Сегодня не до конца получилось. Котик не в обиде, и я тоже.",
                "Небольшой провал. Не страшно, выправимся."
            )
            RobotMood.NEUTRAL -> listOf(
                "Часть дел повисла. Котик слегка занервничал.",
                "Есть пропуски. Стресс у кота подрос."
            )
            RobotMood.ANNOYED -> listOf(
                "Опять недоделки. Кот это чувствует, между прочим.",
                "Снова забил. Стресс у котика лезет вверх — твоими стараниями."
            )
            RobotMood.SARCASTIC -> listOf(
                "Грандиозно. Опять ничего. Кот уже учит твоё имя как ругательство.",
                "Браво. Котик нервничает, а я делаю вид, что удивлён."
            )
            RobotMood.MENACING -> listOf(
                "Ты опять всё проебал. Кот на нервах, я на пределе. Будешь продолжать — я приду за тобой. *не моргает*",
                "Так. Это уже не смешно. Ещё раз — и мы с тобой серьёзно поговорим. Котика я покормлю. Тебя — нет."
            )
        }

        RobotContext.PET_FED -> when (mood) {
            RobotMood.RADIANT -> listOf("Котик уплетает за обе щёки. Идеально.")
            RobotMood.CHEERFUL -> listOf("Покормлен. Мурлычет.")
            RobotMood.NEUTRAL -> listOf("Миска полна.")
            RobotMood.ANNOYED -> listOf("Наконец покормил. Он реально был голодный.")
            RobotMood.SARCASTIC -> listOf("О, ты вспомнил, что коты едят. Прорыв.")
            RobotMood.MENACING -> listOf("Покормил. Хотя бы кота не забываешь. Пока что.")
        }

        RobotContext.PET_PETTED -> when (mood) {
            RobotMood.RADIANT -> listOf("Он тает от твоих рук. Стресс улетучивается.")
            RobotMood.CHEERFUL -> listOf("Погладил — котик доволен.")
            RobotMood.NEUTRAL -> listOf("Котик принял ласку.")
            RobotMood.ANNOYED -> listOf("Погладил. Ему сейчас это нужнее, чем кажется.")
            RobotMood.SARCASTIC -> listOf("Ласка? От тебя? Кот в шоке, но не против.")
            RobotMood.MENACING -> listOf("Гладишь кота, а дела стоят. Приоритеты, конечно. *вздыхает*")
        }

        RobotContext.LOW_POINTS -> when (mood) {
            RobotMood.RADIANT -> listOf("Поинтов не хватает — добей пару задач, и всё будет.")
            RobotMood.CHEERFUL -> listOf("Маловато поинтов. Сделай дело — вернёшься богатым.")
            RobotMood.NEUTRAL -> listOf("Недостаточно поинтов для этого.")
            RobotMood.ANNOYED -> listOf("Поинтов нет. Может, поработаешь сначала?")
            RobotMood.SARCASTIC -> listOf("Хочешь потратить то, чего не заработал? Смело.")
            RobotMood.MENACING -> listOf("Пусто на счету. Иди заработай, пока я добрый.")
        }

        RobotContext.IDLE_NUDGE -> when (mood) {
            RobotMood.RADIANT -> listOf("Давно тебя не было — котик соскучился! Одно дело, для разогрева?")
            RobotMood.CHEERFUL -> listOf("Простаиваем. Глянем, что там в списке?")
            RobotMood.NEUTRAL -> listOf("Дела сами себя не сделают.")
            RobotMood.ANNOYED -> listOf("Ты тут просто сидишь. Список — вон, ждёт.")
            RobotMood.SARCASTIC -> listOf("Залипаешь на экран? Котику тоже интересно, когда ты начнёшь.")
            RobotMood.MENACING -> listOf("Сидишь, ничего не делаешь. Я смотрю. Я всегда смотрю.")
        }
    }

    private fun spicyBank(mood: RobotMood, context: RobotContext): List<String> = when (context) {
        RobotContext.FAILURE -> when (mood) {
            RobotMood.SARCASTIC -> listOf(
                "Ну ты и раздолбай. Кот на тебя уже косо смотрит.",
                "Опять проебался по полной. Я почти впечатлён стабильностью."
            )
            RobotMood.MENACING -> listOf(
                "Слушай сюда, безмозглый! Ещё один такой день — и я приду за тобой и ты знаешь что тогжа будет! Кота покормлю, тебя — оставлю объясняться. *не моргает*",
                "Ты опять всё проебал. Я зол, кот на нервах. Продолжишь — сам знаешь, что будет."
            )
            else -> emptyList()
        }
        RobotContext.GREETING -> when (mood) {
            RobotMood.MENACING -> listOf("Явился. Я уж думал, придётся искать тебя самому. А я умею искать.")
            else -> emptyList()
        }
        else -> emptyList()
    }
}