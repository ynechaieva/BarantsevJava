package pl.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.mantis.model.Issue;
import pl.pft.mantis.model.Project;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;


public class SoapTests extends  TestBase{

  @Test(enabled = false)
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for( Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test(enabled = true)
  public void testCerateIssue() throws MalformedURLException, ServiceException, RemoteException{
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue").withtDescription("test issue description")
            .withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    System.out.println(created.getId());
    Assert.assertEquals(issue.getSummary(), created.getSummary());
    skipIfNotFixed(created.getId());
  }

}
