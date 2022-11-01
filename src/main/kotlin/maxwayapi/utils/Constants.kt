package maxwayapi.utils

interface Constants {
    companion object {
        const val SECRET_KEY = "my-secure-key-is-banana"
        const val EXPIRE_DATE = 1000L * 60 * 60 * 24 * 7
    }
}