package pl.stqa.pft.test;


import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("6c8a7fc8b0b08b21fa8d9ad70b51af1da775d0cb");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("ynechaieva", "JavaProgrammingForQA")).commits();
    for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }

  }
}
