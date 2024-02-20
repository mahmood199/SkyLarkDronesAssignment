package com.example.data.model.response

import com.google.gson.annotations.SerializedName

data class Issue(
    @SerializedName("active_lock_reason") val activeLockReason: Any,
    @SerializedName("assignee") val assignee: Assignee,
    @SerializedName("assignees") val assignees: List<Assignee>,
    @SerializedName("author_association") val authorAssociation: String,
    @SerializedName("body") val body: String,
    @SerializedName("closed_at") val closedAt: Any,
    @SerializedName("comments") val comments: Int,
    @SerializedName("comments_url") val commentsUrl: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("draft") val draft: Boolean,
    @SerializedName("events_url") val eventsUrl: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("id") val id: Int,
    @SerializedName("labels") val labels: List<Label>,
    @SerializedName("labels_url") val labelsUrl: String,
    @SerializedName("locked") val locked: Boolean,
    @SerializedName("milestone") val milestone: Any,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("number") val number: Int,
    @SerializedName("performed_via_github_app") val performedViaGithubApp: Any,
    @SerializedName("pull_request") val pullRequest: PullRequest,
    @SerializedName("reactions") val reactions: Reactions,
    @SerializedName("repository_url") val repositoryUrl: String,
    @SerializedName("state") val state: String,
    @SerializedName("state_reason") val stateReason: Any,
    @SerializedName("timeline_url") val timelineUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("url") val url: String,
    @SerializedName("user") val user: User
)
