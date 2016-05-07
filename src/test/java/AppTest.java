import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*; // for DB support
import org.junit.*; // for @Before and @After


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Hair Salon");
    assertThat(pageSource()).contains("View Stylist List");
    assertThat(pageSource()).contains("Add a New Stylist");
  }

  @Test
  public void stylistIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a New Stylist"));
    fill("#name").with("Household chores");
    submit(".btn");
    assertThat(pageSource()).contains("Your stylist has been saved.");
  }

  @Test
  public void stylistIsDisplayedTest() {
    Stylist myStylist = new Stylist("Household chores");
    myStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Household chores");
  }

  @Test
  public void stylistShowPageDisplaysName() {
    goTo("http://localhost:4567/stylists/new");
    fill("#name").with("Household chores");
    submit(".btn");
    click("a", withText("View stylists"));
    click("a", withText("Household chores"));
    assertThat(pageSource()).contains("Household chores");
  }

  @Test
  public void stylistClientsFormIsDisplayed() {
    goTo("http://localhost:4567/stylists/new");
    fill("#name").with("Shopping");
    submit(".btn");
    click("a", withText("View stylists"));
    click("a", withText("Shopping"));
    click("a", withText("Add a new client"));
    assertThat(pageSource()).contains("Add a client to Shopping");
  }

  @Test
  public void clientsIsAddedAndDisplayed() {
    goTo("http://localhost:4567/stylists/new");
    fill("#name").with("Banking");
    submit(".btn");
    click("a", withText("View stylists"));
    click("a", withText("Banking"));
    click("a", withText("Add a new client"));
    fill("#client_name").with("Deposit paycheck");
    submit(".btn");
    click("a", withText("View stylists"));
    click("a", withText("Banking"));
    assertThat(pageSource()).contains("Deposit paycheck");
  }

  @Test
  public void allClientsDisplayClientNameOnStylistPage() {
    Stylist myStylist = new Stylist("Household chores");
    myStylist.save();
    Client firstClient = new Client("Mow the lawn", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Do the dishes", myStylist.getId());
    secondClient.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Mow the lawn");
    assertThat(pageSource()).contains("Do the dishes");
  }

  @Test
  public void clientShowPage() {
    Stylist myStylist = new Stylist("Home");
    myStylist.save();
    Client myClient = new Client("Clean", myStylist.getId());
    myClient.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", myStylist.getId());
    goTo(stylistPath);
    click("a", withText("Clean"));
    assertThat(pageSource()).contains("Clean");
    assertThat(pageSource()).contains("Return to Home");
  }

  @Test
  public void clientUpdate() {
    Stylist myStylist = new Stylist("Home");
    myStylist.save();
    Client myClient = new Client("Clean", myStylist.getId());
    myClient.save();
    String clientPath = String.format("http://localhost:4567/stylists/%d/clients/%d", myStylist.getId(), myClient.getId());
    goTo(clientPath);
    fill("#client_name").with("Dance");
    submit("#update-client");
    assertThat(pageSource()).contains("Dance");
  }

  @Test
  public void clientDelete() {
    Stylist myStylist = new Stylist("Home");
    myStylist.save();
    Client myClient = new Client("Clean", myStylist.getId());
    myClient.save();
    String clientPath = String.format("http://localhost:4567/stylists/%d/clients/%d", myStylist.getId(), myClient.getId());
    goTo(clientPath);
    submit("#delete-client");
    assertEquals(0, Client.all().size());
  }
}
