package br.com.lighttasks.commom.util

import android.content.Context
import android.content.SharedPreferences
import br.com.lighttasks.domain.model.BasicUser
import io.paperdb.Paper
import timber.log.Timber

class PreferencesWrapper(context: Context) {

    private var user: BasicUser? = null

    fun putString(key: String, value: String?) {
        save(key, value)
    }

    fun putInt(key: String, value: Int) {
        save(key, value)
    }

    fun putBoolean(key: String, value: Boolean) {
        save(key, value)
    }

    fun putLong(key: String, value: Long) {
        save(key, value)
    }

    fun getString(key: String?): String? {
        return sharedPreferences?.getString(key, "")
    }

    fun getString(key: String?, valueDefault: String? = ""): String? {
        return sharedPreferences?.getString(key, valueDefault)
    }

    fun getBoolean(key: String?, valueDefault: Boolean = true): Boolean? {
        return sharedPreferences?.getBoolean(key, valueDefault)
    }

    fun getInt(key: String?, valueDefault: Int = 0): Int? {
        return sharedPreferences?.getInt(key, valueDefault)
    }

    fun getLong(key: String?): Long? {
        return sharedPreferences?.getLong(key, 0)
    }

    private fun save(key: String, value: String?) {
        val editor = sharedPreferences?.edit()
        editor?.putString(key, value)?.apply()
    }

    private fun save(key: String, value: Boolean) {
        val editor = sharedPreferences?.edit()
        editor?.putBoolean(key, value)?.apply()
    }

    private fun save(key: String, value: Int) {
        val editor = sharedPreferences?.edit()
        editor?.putInt(key, value)?.apply()
    }

    private fun save(key: String, value: Long) {
        val editor = sharedPreferences?.edit()
        editor?.putLong(key, value)?.apply()
    }

    @Synchronized
    fun getUser(): BasicUser? {
        if (user == null) {
            try {
                user = Paper.book(PAPER_USER_BOOK).read(PreferencesKey.BASIC_USER_KEY)
            } catch (e: Exception) {
                Timber.e(e, "Error getting user from paperDB")
            }
        }
        return user
    }

    @Synchronized
    fun setUser(user: BasicUser) {
        this.user = user
        try {
            val book = Paper.book(PAPER_USER_BOOK)
            book.delete(PreferencesKey.BASIC_USER_KEY)
            book.write(PreferencesKey.BASIC_USER_KEY, user)
        } catch (e: Exception) {
            Timber.e(e, "Error setting user")
        }
    }

    fun clearPreferences() {
        Paper.book(PAPER_USER_BOOK).destroy()
        user = null
    }

    companion object {
        private const val PAPER_USER_BOOK = "USER_BOOK"

        private var preferencesWrapper: PreferencesWrapper? = null
        private var sharedPreferences: SharedPreferences? = null
        fun initPreferences(context: Context) {
            if (preferencesWrapper == null) {
                preferencesWrapper = PreferencesWrapper(context.applicationContext)
            }
        }

        fun getInstance(): PreferencesWrapper? {
            checkNotNull(preferencesWrapper) {
                "Preferences Wrapper wasn't initialized. " +
                        "Call initPreferences(Context context) to initialize this."
            }
            return preferencesWrapper
        }
    }

    init {
        sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }
}