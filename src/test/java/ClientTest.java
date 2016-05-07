import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
// import java.util.Arrays;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client myClient = new Client("Mow the lawn", 1);
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void getClientName_clientInstantiatesWithClientName_String() {
    Client myClient = new Client("Mow the lawn", 1);
    assertEquals("Mow the lawn", myClient.getClientName());
  }

  @Test
  public void equals_returnsTrueIfClientNamesAretheSame() {
    Client firstClient = new Client("Mow the lawn", 1);
    Client secondClient = new Client("Mow the lawn", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfClientNamesAretheSame() {
    Client myClient = new Client("Mow the lawn", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Mow the lawn", 1);
    myClient.save();
    Client savedClient = Client.all().get(0);
    //SO if any of our clients have the same client_name, they will be assigned the same id in the database?
    //Why doesn't savedClient (as a new object) have a diff unique identifer? besause of override?
    //Will saved client have an index of (1)?
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client myClient = new Client("Mow the lawn", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist myStylist = new Stylist("Household chores");
    myStylist.save();
    Client myClient = new Client("Mow the lawn", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }

  @Test
  public void update_updatesClientClientName_true() {
    Client myClient = new Client("Mow the lawn", 1);
    myClient.save();
    myClient.update("Take a nap");
    assertEquals("Take a nap", Client.find(myClient.getId()).getClientName());
  }

  @Test
  public void delete_deletesClient_true() {
    Client myClient = new Client("Mow the lawn", 1);
    myClient.save();
    int myClientId = myClient.getId();
    myClient.delete();
    assertEquals(null, Client.find(myClientId));
  }
}
