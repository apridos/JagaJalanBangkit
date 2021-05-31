package com.example.data.utils

import com.example.data.source.network.response.UserRemote
import com.example.data.source.network.response.UsersRemote
import com.example.domain.model.User
import com.google.gson.Gson
import org.json.JSONObject

object DataMapper {
    fun mapUsersRemoteToUserRemotes(usersRemote: Map.Entry<String, Any>) : List<UserRemote>{
        val userRemotes = arrayListOf<UserRemote>()
        val jsonObject = JSONObject(Gson().toJson(usersRemote, Map::class.java)).optJSONObject("users")
        for (user in 0 until jsonObject.length()){
            print(user.toString())
        }
        return userRemotes
    }
/**
    fun mapUserResponseToUser(it: UsersRemote): User {
        return User(
            email =
        )
    }*/
}