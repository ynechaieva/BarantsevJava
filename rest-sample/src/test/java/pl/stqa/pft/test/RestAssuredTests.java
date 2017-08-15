package pl.stqa.pft.test;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestAssuredTests extends  TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  @Test(enabled = false)
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newissue = new Issue().withSubject("new test issue").withDescription("new test issue description");
    int issueId = createIssue(newissue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newissue.withId(issueId));
    Assert.assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testSkipIssue() throws IOException {
    Set<Issue> issuesList = getIssues();
    Issue testIssue = issuesList.iterator().next();
    skipIfNotFixed(testIssue.getId());
  }


  private Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private int createIssue(Issue newissue) throws IOException {
    String json = RestAssured.given().parameter("subject", newissue.getSubject()).parameter("description", newissue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }


}
