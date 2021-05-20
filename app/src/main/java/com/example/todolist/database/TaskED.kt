package com.example.todolist.database

class TaskED(
    var id: String = "",
    var name: String? = null,
    var description: String? = null,
    var isDone: Boolean = false
) {
    class Builder private constructor() {
        private var id: String = ""
        private var name: String? = null
        private var description: String? = null
        private var isDone: Boolean = false


        companion object {
            fun createBuilder(): Builder {
                return Builder()
            }
        }

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun description(description: String): Builder {
            this.description = description
            return this
        }

        fun isDone(isDone: Boolean): Builder {
            this.isDone = isDone
            return this
        }

        fun build(): TaskED {
            return TaskED(
                id,
                name,
                description
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TaskED

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (isDone != other.isDone) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (isDone.hashCode())
        return result
    }
}
