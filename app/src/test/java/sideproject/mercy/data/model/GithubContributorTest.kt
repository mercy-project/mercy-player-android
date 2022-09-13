package sideproject.mercy.data.model

import org.junit.Assert.*
import org.junit.Test

class GithubContributorTest {

    @Test
    fun `data has correctly`() {
        val name = "YS"
        val photoUrl = "test_photo_url"
        val githubContributor = GithubContributor(name, photoUrl)

        assertEquals(name, githubContributor.name)
        assertEquals(photoUrl, githubContributor.photoUrl)
    }
}