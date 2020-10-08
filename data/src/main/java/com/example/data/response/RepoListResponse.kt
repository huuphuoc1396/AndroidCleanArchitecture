package com.example.data.response


import com.google.gson.annotations.SerializedName

data class RepoListResponse(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<Item>?
) {
    data class Item(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("node_id")
        val nodeId: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("full_name")
        val fullName: String?,
        @SerializedName("owner")
        val owner: Owner?,
        @SerializedName("private")
        val `private`: Boolean?,
        @SerializedName("html_url")
        val htmlUrl: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("fork")
        val fork: Boolean?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("updated_at")
        val updatedAt: String?,
        @SerializedName("pushed_at")
        val pushedAt: String?,
        @SerializedName("homepage")
        val homepage: String?,
        @SerializedName("size")
        val size: Int?,
        @SerializedName("stargazers_count")
        val stargazersCount: Int?,
        @SerializedName("watchers_count")
        val watchersCount: Int?,
        @SerializedName("language")
        val language: String?,
        @SerializedName("forks_count")
        val forksCount: Int?,
        @SerializedName("open_issues_count")
        val openIssuesCount: Int?,
        @SerializedName("master_branch")
        val masterBranch: String?,
        @SerializedName("default_branch")
        val defaultBranch: String?,
        @SerializedName("score")
        val score: Double?
    )

    data class Owner(
        @SerializedName("login")
        val login: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("node_id")
        val nodeId: String?,
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("gravatar_id")
        val gravatarId: String?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("received_events_url")
        val receivedEventsUrl: String?,
        @SerializedName("type")
        val type: String?
    )
}