package pl.stqa.pft.test;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Set;


public class TestBase {

  public boolean isIssueOpen(int issueId){
    String status = getIssueStatusById(issueId);
    if(status.equals("Open")){
      return true;
    } else return false;
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public String getIssueStatusById(int issueId) {
    String json = RestAssured.get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)).asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    String status = issue.iterator().next().getStatus();
    return status;
  }
}