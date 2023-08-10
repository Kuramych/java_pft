package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.jcabi.github.RepoCommit.*;

public class GithubTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("Kuramych", "java_pft")).commits();
        for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new Smart(commit).message());
        }
    }
}
